package facebook.domain.convertor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import facebook.domain.core.Post;

/**
 * <p>
 * Feeds is collection of {@link Post}s.We choose to use a custom implementation 
 * of Comments instead of using an ArrayList<T> so that HTTP Message Convertor 
 * can tell the difference between two collections of different objects.
 * Apparently ArrayList<T1>.isAssignableFrom(ArrayList<T2>)==true, which
 * makes it difficult to determine which converter should be used when multiple
 * Convertors support a collection but of different objects.
 * </p>
 * 
 * @author shantanu
 *
 */
public class Feed implements Collection<Post> {
	
	/**
	 * Array list containing all the posts
	 */
	private List<Post> posts;
	
	public Feed(){
		posts = new ArrayList<Post>();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	@Override
	public boolean add(Post e) {
		return posts.add(e);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends Post> c) {
		return posts.addAll(c);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Collection#clear()
	 */
	@Override
	public void clear() {
		posts.clear();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		return posts.contains(o);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return posts.containsAll(c);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Collection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return posts.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Collection#iterator()
	 */
	@Override
	public Iterator<Post> iterator() {
		return posts.iterator();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		return posts.remove(o);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		return posts.removeAll(c);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		return posts.retainAll(c);
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Collection#size()
	 */
	@Override
	public int size() {
		return posts.size();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Collection#toArray()
	 */
	@Override
	public Object[] toArray() {
		return posts.toArray();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Collection#toArray(T[])
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		return posts.toArray(a);
	}
}
