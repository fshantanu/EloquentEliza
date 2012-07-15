package facebook.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import facebook.domain.convertor.Comments;
import facebook.domain.convertor.Feed;
import facebook.domain.convertor.FriendList;
import facebook.domain.core.Comment;
import facebook.domain.core.Post;
import facebook.domain.core.User;
import facebook.rest.util.CommentConverter;
import facebook.rest.util.CommentsConvertor;
import facebook.rest.util.FeedConverter;
import facebook.rest.util.FriendListConverter;
import facebook.rest.util.PostConverter;
import facebook.rest.util.UserConverter;

/**
 * The base class for making rest requests to the Facebook
 * graph API using the preaquired access token.
 * 
 * @author shantanu
 */

public class Facebook {
	
	/**
	 * Access token with appropriate permissions
	 */
	private String accessToken;
	
	/**
	 * Unique user identifier. Can be the numeric id or the username
	 */
	private String userId;
	
	/**
	 *  Facebook API end point
	 */
	private static final String apiEndpoint ="https://graph.facebook.com/";
	
	/**
	 *  For making rest requests
	 */
	private RestTemplate restOperations;
	
	/**
	 * Private Default construtor. Initialises the
	 * restOperations object and registers the 
	 * Convertors
	 */
	private Facebook(){
		restOperations = new RestTemplate();
		List<HttpMessageConverter<?>> convertors = new ArrayList<HttpMessageConverter<?>>();
		convertors.add(new CommentsConvertor());
		convertors.add(new CommentConverter());
		convertors.add(new FeedConverter());
		convertors.add(new PostConverter());
		convertors.add(new UserConverter());
		convertors.add(new FriendListConverter());
		restOperations.setMessageConverters(convertors);
	}
	
	/**
	 * Creates a faceboook object for a user.
	 * @param userId
	 * 					unique identifier for the user
	 * @param accessToken
	 * 				access token with the desired permissions
	 */
	public Facebook(String userId, String accessToken){
		this();
		this.accessToken = accessToken;
		this.userId = userId;
	}
	
	/**
	 * @return {@link Facebook#userId}
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * Retrieves a set of feed from the user. Represents the user's wall.
	 * @return {@link Feed} from the user's wall
	 */
	public Collection<Post> getFeed(){
		String url = apiEndpoint+"{userId}/feed?fields=id,message,from&access_token={accessToken}";
		Collection<Post> posts= 
			(Collection<Post>)restOperations.getForObject(url, Feed.class,userId, accessToken);
		return posts;
	}
	
	/**
	 * Retrieves the comments for a given post
	 * @param post for which the comment is to retrieved
	 * @return
	 */
	public Collection<Comment> getComments(Post post){
		
		String url = apiEndpoint+"{postId}/comments&access_token={accessToken}";
		Collection<Comment> comments = restOperations.getForObject(url, Comments.class,
				post.getId(),accessToken );
		
		return comments;
	}
	
	/**
	 * Posts the message to users wall
	 * @param post
	 * 				post with a message to be posted on the users wall
	 * @param user
	 * 				user the message is intended for
	 * @return {@link Post} 
	 * 					post with the its id set
	 */
	public Post postOnWall(Post post, User user){
		String url = apiEndpoint + "{userId}/feed?access_token={accessToken}";
		Post newPost = restOperations.postForObject(url, post, Post.class,
									 user.getUsername(), accessToken);
		post.setId(newPost.getId());
		return post;
	}
	
	/**
	 * Posts a status message
	 * @param post
	 * @return
	 */
	public Post postStatus(Post post){
		// write a post on the users own wall
		User user = new User();
		user.setId(userId);
		return postOnWall(post, user);
	}
	
	/**
	 * Posts a comment on the entity represented by the post
	 * @param comment
	 * 				the comment to be posted
	 * @return comment object with its id set
	 */
	public Comment commentOnPost(Comment comment){
		String url = apiEndpoint + "{postId}/comments?access_token={accessToken}";
		Comment newComment = restOperations.postForObject(url, comment, Comment.class,
										comment.getPost().getId() ,accessToken);
		comment.setId(newComment.getId());
		return comment;
	}
	
	/**
	 * Fetches an object
	 * @param <T> Type of object to be fetched
	 *
	 * @param objectId
	 * 				Id of the object being retrieved
	 * @param clazz
	 * 				Class of the object being retrieved
	 * @return
	 * 			Object of class
	 */
	public <T> T getObject(String objectId,	Class<T> clazz){
		String url = apiEndpoint+"{objectId}&access_token={accessToken}";
		T obj = restOperations.getForObject(url, clazz, objectId, accessToken);
		return obj;
	}
	
	/** 
	 * Gets a list of friends for the logged in user.
	 * 
	 * @return Collection<User> :
	 * 						the user object returned only has the id
	 * 						and the name field populated
	 */
	public Collection<User> getFriendList(){
		String url = apiEndpoint + "me/friends&access_token={accessToken}";
		Collection<User> friends = restOperations.getForObject(url, FriendList.class, 
				accessToken);
		return friends;
	}
	
	// Test driver. to be removed later
	public static void main(String str[]){
		String accessToken = "AAACuzRr6cdUBAHC8qtVYRxkMyqrTc7i833U8HWPnkrhjjm1WZCEkKByyDlH1IuQXyiiD9tfxS6PUIjGkZAnTppCLIiWqfE9WFUKbZC4QgZDZD";
		Facebook facebook = new Facebook("eloquent.eliza", accessToken);
//		Collection<Post> feed = facebook.getFeed();
//		for(Post post: feed){
//			System.out.println(facebook.getComments(post));
//		}
		Collection<User> friends =	facebook.getFriendList();
		for(User friend: friends){
		//	System.out.println(String.format("%s - %s", friend.getId(),friend.getName()));
			User user = facebook.getObject(friend.getId(), User.class);
			System.out.println(user.getName() + " - " +user.getBirthday());
		}
//		User shantanu = facebook.getObject("713670047", User.class);
//		User eliza = facebook.getObject("eloquent.eliza", User.class);
//		Post post = new Post();
//		post.setFrom(eliza);
//		post.setMessage("Test message");
//		facebook.postOnWall(post, shantanu);
	}
}
