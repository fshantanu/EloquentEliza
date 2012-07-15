package facebook.rest.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.FileCopyUtils;

import facebook.domain.core.User;

/**
 * The base class for converting Json objects in http response to the
 * desired domain object
 * 
 * @author shantanu
 *
 * @param <T>
 */

public abstract class AbstractConverter<T> implements HttpMessageConverter<T>{

	/**
	 * The default {@link Charset} to use when the response
	 * header does not supply the used charset.  
	 */
	private static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");

	/**
	 * returns <code>true</code> for all the supported {@link MediaType} and
	 * classes that can be read
	 */
	@Override
	public boolean canRead(Class<?> clazz, MediaType media) {
		return (supports(clazz) && canRead(media));
	}

	/**
	 * returns <code>true</code> for all the supported {@link MediaType} and
	 * classes that can be written
	 */
	@Override
	public boolean canWrite(Class<?> clazz, MediaType media) {
		return (supports(clazz) && canWrite(media));
	}
	
	/**
	 * Determines if the give media type can be read by this 
	 * class or not
	 * 
	 * @param media
	 * @return <code>true</code> if the given media is JASON or null,
	 * <code>false</code> otherwise.
	 */
	protected boolean canRead(MediaType media) {
		return (media==null || media.isCompatibleWith(MediaType.APPLICATION_JSON));
	}

	/**
	 * Determines if the give media type can be written by this class
	 * or not.
	 * 
	 * @param media
	 * @return <code>true</code> if the given media is JASON or null,
	 * <code>false</code> otherwise.
	 */
	protected boolean canWrite(MediaType media) {
		return (media==null || media.isCompatibleWith(MediaType.APPLICATION_JSON));
	}
	
	/**
	 * Determines if the given class is supported by 
	 * the converter or not.
	 * @param clazz
	 * @return <code>true</code> if the given class is a subclass or 
	 * the same as the {@link User} class, otherwise returns <code>false</code>.
	 */
	protected abstract boolean supports(Class<?> clazz);

	/**
	 * Takes the json represntation of the object and converts it
	 * into the object supported by the class.
	 * @param jason representation of the object to be converted
	 * @return Object
	 */
	protected abstract T readInternal(String json); 

	/**
	 * Takes the object and converts it to its jason representation
	 * @param obj to be converted to jason
	 * @return json representation of the object
	 */
	protected abstract String writeInternal(T obj);
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.http.converter.HttpMessageConverter#getSupportedMediaTypes()
	 */
	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return Arrays.asList(MediaType.APPLICATION_JSON);
	}

	/**
	 * Converts the response to a feed object
	 */
	@Override
	public T read(Class<? extends T> clazz, HttpInputMessage message)
	throws IOException, HttpMessageNotReadableException {

		// Get the character set used in the response. If no character set is
		// supplied use the default characterset
		MediaType contentType = message.getHeaders().getContentType();
		Charset charset = 
			contentType.getCharSet() != null ? contentType.getCharSet() : DEFAULT_CHARSET;

		// Read the response and convert to a string 
		String jason = 
			FileCopyUtils.copyToString(new InputStreamReader(message.getBody(), charset));
		// Do the class specific conversion and return
		return readInternal(jason);
	}

	/**
	 * Writes the string returned by the {@link AbstractConverter#writeInternal(Object)}
	 * to the HttpResponse
	 */
	@Override
	public void write(T obj, MediaType media, HttpOutputMessage opMessage)
	throws IOException, HttpMessageNotWritableException {
		
		// get the string to be written
		String str = writeInternal(obj);
		
		// Set response header
		HttpHeaders header = opMessage.getHeaders();
		setHeaderContentType(header, media);
		setHeaderContentLength(header, str);
		ArrayList<Charset> acceptCharset = 
			new ArrayList<Charset>(Charset.availableCharsets().values());
		header.setAcceptCharset(acceptCharset);
		
		MediaType mediaType = header.getContentType();
		Charset charset = mediaType.getCharSet()!=null ? mediaType.getCharSet():DEFAULT_CHARSET;
		FileCopyUtils.copy(str, new OutputStreamWriter(opMessage.getBody(), charset));
		opMessage.getBody().flush();
	}
	
	/**
	 * Sets the header content type if it is not already set
	 * @param header
	 * @param mediaType
	 */
	private void setHeaderContentType(HttpHeaders header, MediaType mediaType){
		MediaType contentType = header.getContentType();
		if((contentType ==null) && 
		   (mediaType == null || mediaType.isWildcardType() ||	mediaType.isWildcardSubtype()))
					mediaType = getSupportedMediaTypes().get(0);
		if(mediaType != null) 
			header.setContentType(mediaType);	
	}
	
	/**
	 * sets the content length in the header if it is not already set
	 * @param header to set
	 * @param str the string in response
	 */
	private void setHeaderContentLength(HttpHeaders header, String str){
		if(header.getContentLength()!=-1)
			return;
		MediaType mediaType = header.getContentType();
		Charset charset = mediaType.getCharSet()!=null? 
											mediaType.getCharSet():DEFAULT_CHARSET;
		header.setContentLength(str.getBytes(charset).length);
	}
		
}
