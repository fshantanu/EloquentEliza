package facebook.rest.util;

import java.util.Collection;

import facebook.domain.convertor.FriendList;
import facebook.domain.core.User;

/**
 * Converts friend list to a collection of users
 *  
 * @author Shantanu
 *
 */

public class FriendListConverter extends AbstractCollectionConvertor<User> {

	/*
	 * (non-Javadoc)
	 * @see facebook.rest.util.AbstractCollectionConvertor#getObjectClass()
	 */
	@Override
	protected Class<?> getObjectClass() {
		return User.class;
	}

	/*
	 * (non-Javadoc)
	 * @see facebook.rest.util.AbstractConverter#supports(java.lang.Class)
	 */
	@Override
	protected boolean supports(Class<?> clazz) {
		return FriendList.class.isAssignableFrom(clazz);
	}

	@Override
	protected String writeInternal(Collection<User> obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
