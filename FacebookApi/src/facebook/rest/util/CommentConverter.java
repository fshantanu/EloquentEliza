package facebook.rest.util;

import com.google.gson.Gson;

import facebook.domain.core.Comment;

/**
 * Converts the json object to {@link Comment} object
 * @author shantanu
 *
 */
public class CommentConverter extends AbstractConverter<Comment>{

	/*
	 * (non-Javadoc)
	 * @see facebook.rest.util.AbstractConverter#readInternal(java.lang.String)
	 */
	@Override
	protected Comment readInternal(String json) {
		Gson gson = new Gson();
		Comment comment = gson.fromJson(json, Comment.class);
		return comment;
	}

	/*
	 * (non-Javadoc)
	 * @see facebook.rest.util.AbstractConverter#supports(java.lang.Class)
	 */
	@Override
	protected boolean supports(Class<?> clazz) {
		return Comment.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 * @see facebook.rest.util.AbstractConverter#writeInternal(java.lang.Object)
	 */
	@Override
	protected String writeInternal(Comment obj) {
		String comment = String.format("message=%s",obj.getMessage());
		return comment;
	}
}
