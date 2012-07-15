package facebook.rest.util;

import java.util.Collection;

import facebook.domain.convertor.Feed;
import facebook.domain.core.Post;

/**
 * Converts the Feed from a User's wall to a Collection of 
 * {@link Post}s.
 * 
 * @author shantanu
 *
 */
public class FeedConverter extends AbstractCollectionConvertor<Post>{

	/*
	 * (non-Javadoc)
	 * @see facebook.rest.util.AbstractCollectionConvertor#getObjectClass()
	 */
	@Override
	protected Class<?> getObjectClass() {
		return Post.class;
	}

	/*
	 * (non-Javadoc)
	 * @see facebook.rest.util.AbstractConverter#supports(java.lang.Class)
	 */
	protected boolean supports(Class<?> clazz){
		return Feed.class.isAssignableFrom(clazz);
	}
	
	@Override
	public String writeInternal(Collection<Post> obj){
		// TODO add support for writting feeds
		return null;
	}
}
