package facebook.rest.util;

import com.google.gson.Gson;

import facebook.domain.core.Post;

/**
 * Converts a Json object to a {@link Post} object.
 * 
 * @author shantanu
 *
 */
public class PostConverter extends AbstractConverter<Post>{

	/*
	 * (non-Javadoc)
	 * @see facebook.rest.util.AbstractConverter#readInternal(java.lang.String)
	 */
	@Override
	protected Post readInternal(String json) {
		Gson gson = new Gson();
		Post post = gson.fromJson(json, Post.class);
		return post;
	}

	/*
	 * (non-Javadoc)
	 * @see facebook.rest.util.AbstractConverter#supports(java.lang.Class)
	 */
	@Override
	protected boolean supports(Class<?> clazz) {
		return Post.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 * @see facebook.rest.util.AbstractConverter#writeInternal(java.lang.Object)
	 */
	@Override
	protected String writeInternal(Post obj) {
		String postStr = String.format("message=%s", obj.getMessage());
		return postStr;
	}

}
