package facebook.domain.core;

/**
 * Represents a Facebook post.
 * 
 * @author shantanu
 *
 */
public class Post {

	/**
	 *  Id of the feed
	 */
	private String id;
	
	/**
	 *  Message in the post
	 */
	private String message;
	
	/**
	 *  The user who posted this message
	 */
	private User from;
	
	/**
	 * @return id of the feed
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return message in the post
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the user who posted this message
	 */
	public User getFrom() {
		return from;
	}

	/**
	 * @param id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param from to set
	 */
	public void setFrom(User from) {
		this.from = from;
	}
}
