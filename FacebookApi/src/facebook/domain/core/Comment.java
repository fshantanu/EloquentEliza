package facebook.domain.core;

/**
 * Represents a comment on a {@link Post}
 * @author shantanu
 *
 */
public class Comment {
	
	/**
	 * The parent object
	 */
	private Post post;
	
	/**
	 * Id of the object
	 */
	private String id;
	
	/**
	 * message in the comment
	 */
	private String message;
	
	/**
	 * The user who made this comment
	 */
	private User from;

	/**
	 * @return user
	 */
	public User getFrom() {
		return from;
	}

	/**
	 * @param from to set
	 */
	public void setFrom(User from) {
		this.from = from;
	}

	/**
	 * @return post
	 */
	public Post getPost() {
		return post;
	}

	/**
	 * @return Id for this comment
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return message in this comment
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param post to set
	 */
	public void setPost(Post post) {
		this.post = post;
	}

	/**
	 * @param id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Message to set
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
