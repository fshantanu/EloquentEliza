package facebook.domain.core;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

/**
 * A Facebook user.
 * 
 * @author shantanu
 *
 */
public class User {

	// Facebook Id of the user
	private String id;

	// Full name of the user
	private String name;

	// First name of the user
	@SerializedName("first_name")
	private String firstName;

	// Last name of the user
	@SerializedName("last_name")
	private String lastName;

	// Link to the user's profile
	private String link;

	// Username of the user
	private String username;

	// Gender of the User
	private String gender;

	// Locale of the user
	private String locale;

	// User's birthday
	private String birthday;

	/**
	 * @return the id of the user
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name of the user
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return first name of the user
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return last name of the user
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return link to the users profile
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @return facebook username of the user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return gender of the user
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @return user's locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}


	/**
	 * @param gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @param locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * @return user's birthday in MM/dd format 
	 * Only Month and Day field of the date object are valid. 
	 * Facebook may or may not return the user's year depending 
	 * on the users setting. For consistency, we ignore the year 
	 * field completely
	 */
	public Date getBirthday() {
		if(birthday == null)
			return null;

		try{
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
			return formatter.parse(birthday);
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @param birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @param username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", link=" + link + ", username="
				+ username + ", gender=" + gender + ", locale=" + locale
				+ ", birthday=" + birthday + "]";
	}

}
