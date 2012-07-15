package processor;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;

import facebook.domain.core.Post;
import facebook.domain.core.User;
import facebook.rest.Facebook;

public class BirthdayProcessor implements Runnable {

	/**
	 * Facebook to process birthday events
	 */
	private Facebook facebook;
	
	/**
	 * Logger for logging
	 */
	private Logger logger = Logger.getLogger(getClass());

	public BirthdayProcessor(Facebook facebook){
		this.facebook = facebook;
	}

	@Override
	public void run() {
		// Get Today's Date
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
		String today = formatter.format(new Date());
		logger.info("Processing Birthday events for - " + today);

		// Get the user object for the logged in user
		User me = facebook.getObject(facebook.getUserId(), User.class);
		// get a list of friends for the user. 
		Collection<User> friends = facebook.getFriendList();

		// for each user check if his birthday today.
		// if so wish the user Happy Birthday!
		for(User friend: friends){
			User user = facebook.getObject(friend.getId(), User.class);
			if(user.getBirthday()!=null && 
					formatter.format(user.getBirthday()).equals(today)){

				try{
					logger.info(String.format("Wishing %s happy birthday", user.getName()));
					Post post = new Post();
					post.setFrom(me);
					post.setMessage(String.format("Happy Birthday %s !", user.getFirstName()));
					facebook.postOnWall(post, user);
				}
				catch(Throwable ex){
					logger.error(String.format("Error processing birthday event " +
							"for user [%s]", user.getName()), ex);
				}
			}
		}

		logger.info("Finished Processing birthday events");
	}

	public static void main(String[] args){
		String userName = "eloquent.eliza";
		String accessToken = "AAACuzRr6cdUBAHC8qtVYRxkMyqrTc7i833U8HWPnkrhjjm1WZCEkKByyDlH1IuQXyiiD9tfxS6PUIjGkZAnTppCLIiWqfE9WFUKbZC4QgZDZD";
		Facebook facebook = new Facebook(userName, accessToken);

		BirthdayProcessor birthdayProcessor = new BirthdayProcessor(facebook);
		birthdayProcessor.run();
	}
}
