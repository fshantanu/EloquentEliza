package facebook.rest.util;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * Abstract class for HTTP Message Converters which convert
 * an array of Json objects to a collection of domain objects
 * 
 * @author shantanu
 * 
 * @see CommentsConvertor
 * @see FeedConverter
 *
 */
public abstract class AbstractCollectionConvertor<T> extends AbstractConverter<Collection<T>> {
	
	/*
	 * (non-Javadoc)
	 * @see facebook.rest.util.AbstractConverter#readInternal(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected Collection<T> readInternal(String json) {
		
		// extract the json array from the response.
		int startIndex = json.indexOf('[');
		int endIndex = json.indexOf(']');
		String feedArray = json.substring(startIndex, endIndex+1);
		
		if(feedArray==null || feedArray.trim().length()==0){
			return new ArrayList<T>();
		}
		
		// convert the response in a json array
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonArray rawFeeds = parser.parse(feedArray).getAsJsonArray();
		
		// map each comment in the array to an individual comment
		Collection<T> objects = new ArrayList<T>();
		for(int i=0; i<rawFeeds.size();i++){
			
			T obj =	(T) gson.fromJson(rawFeeds.get(i), getObjectClass());
			objects.add(obj);
		}
		return objects;
	}
	
	/**
	 * Returns the class
	 * @return
	 */
	abstract protected Class<?> getObjectClass();
}
