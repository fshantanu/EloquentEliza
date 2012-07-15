package processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import eliza.ElizaMain;
import facebook.domain.core.Comment;
import facebook.domain.core.Post;
import facebook.domain.core.User;
import facebook.rest.Facebook;

public class Processor implements Runnable {

	/**
	 * logger for logging 
	 */
	private Logger logger = Logger.getLogger(getClass());

	public Processor(Facebook facebook){
		try{
			ClassPathResource resource = new ClassPathResource("ProcessedPosts.txt");
			file = resource.getFile(); 
			this.facebook = facebook;
		}
		catch(Throwable ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * facebook object
	 */
	private Facebook facebook;
	/**
	 * File to write the processed entities
	 */
	private File file;

	/**
	 * Eliza to generate replies for the post
	 */
	private ElizaMain eliza;

	public void setEliza(ElizaMain eliza) {
		this.eliza = eliza;
	}

	@Override
	public void run() {

		logger.info("Starting processing posts");
		
		// load the list of processed posts
		List<String> processedEntities = readFromFile();
		if(processedEntities == null)
			processedEntities = new ArrayList<String>();

		List<String> newEntities = new ArrayList<String>();

		// Read feed from the user's wall
		Collection<Post> feed = facebook.getFeed();
		for(Post post: feed){

			try{
				// process the message in the post
				String postReply = processEntity(post.getId(), 
						post.getMessage(), post.getFrom(), processedEntities);
				if(postReply!=null){
					facebook.commentOnPost(createComment(post, postReply));
					newEntities.add(post.getId());
				}

				// Get a list of comments for the post
				Collection<Comment> comments = facebook.getComments(post);
				for(Comment comment: comments){
					// process the message in the comment
					String commentReply = processEntity(comment.getId(), 
							comment.getMessage(), comment.getFrom(), processedEntities);
					if(commentReply!=null){
						String personalisedReply =  
								String.format("@%s: %s", comment.getFrom().getName(), commentReply); 
						facebook.commentOnPost(createComment(post, personalisedReply));
						newEntities.add(comment.getId());
					}
				}
			}
			catch(Exception ex){
				logger.error(String.format("Error processing post [%s]. The post will" +
						"be processed in the next iteration.", post.getMessage()), ex);
			}
		}
		// write the newly processed entries to the file
		writeToFile(newEntities);
		logger.info("finished processing all posts");
	}


	private Comment createComment(Post post, String message){
		Comment comment = new Comment();
		comment.setPost(post);
		comment.setMessage(message);
		return comment;
	}

	private String processEntity(String id, String message, 
			User from, Collection<String> processedItems){

		// This is the id for eloquent.eliza
		if(message== null || processedItems.contains(id) || from.getId().equals("100002508051477"))
			return null;

		return eliza.getReply(message);	
	}

	private void writeToFile(Collection<String> list){
		FileWriter writer;
		try {

			writer = new FileWriter(file, true);
			for(String id: list){
				writer.write( id + "\n" );
			}
			writer.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	private List<String> readFromFile(){

		List<String> list = new ArrayList<String>();
		try{

			BufferedReader reader = new BufferedReader(new FileReader(file));
			String str;
			while((str = reader.readLine())!=null){
				list.add(str);
			}
			reader.close();
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}
		return list;
	}

	public static void main(String str[]){
		String userName = "eloquent.eliza";
		String accessToken = "AAACuzRr6cdUBAHC8qtVYRxkMyqrTc7i833U8HWPnkrhjjm1WZCEkKByyDlH1IuQXyiiD9tfxS6PUIjGkZAnTppCLIiWqfE9WFUKbZC4QgZDZD";
		Facebook facebook = new Facebook(userName, accessToken);

		Processor processor = new Processor(facebook);
		processor.setEliza(new ElizaMain());
		processor.run();
	}
}
