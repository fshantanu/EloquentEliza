package facebook.rest.util;

import com.google.gson.Gson;

import facebook.domain.core.User;

/**
 * Converts a Json Object to a {@link User} object
 * @author shantanu
 *
 */
public class UserConverter extends AbstractConverter<User>{

  /*
   * (non-Javadoc)
   * @see facebook.rest.util.AbstractConverter#readInternal(java.lang.String)
   */
	@Override
	public User readInternal(String userJson){
		// Map the jason string to the user object 
		Gson gson = new Gson();
		User user = gson.fromJson(userJson, User.class);
		return user;
	}

	/*
	 * (non-Javadoc)
	 * @see facebook.rest.util.AbstractConverter#supports(java.lang.Class)
	 */
	protected boolean supports(Class<?> clazz){
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	protected String writeInternal(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
