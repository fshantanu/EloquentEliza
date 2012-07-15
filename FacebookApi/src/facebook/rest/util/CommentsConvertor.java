package facebook.rest.util;

import java.util.Collection;

import facebook.domain.core.Comment;
import facebook.domain.convertor.Comments;

/**
 * Converts the an array of comments to a collection of
 * {@link Comment}s.
 * 
 * @author shantanu
 *
 */
public class CommentsConvertor extends AbstractCollectionConvertor<Comment>  {

	/*
	 * (non-Javadoc)
	 * @see facebook.rest.util.AbstractCollectionConvertor#getObjectClass()
	 */
	@Override
	protected Class<?> getObjectClass() {
		return Comment.class;
	}
	
	/*
	 * (non-Javadoc)
	 * @see facebook.rest.util.AbstractConverter#supports(java.lang.Class)
	 */
	protected boolean supports(Class<?> clazz){
		return Comments.class.isAssignableFrom(clazz);
	}
	
	@Override
	protected String writeInternal(Collection<Comment>obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
