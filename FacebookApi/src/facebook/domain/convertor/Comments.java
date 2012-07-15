package facebook.domain.convertor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import facebook.domain.core.Comment;

/**
 * <p>
 * A collection of {@link Comment}. 
 * We choose to use a custom implementation 
 * of Comments instead of using an ArrayList<T> so that HTTP Message Convertor 
 * can tell the difference between two collections of different objects.
 * Apparently ArrayList<T1>.isAssignableFrom(ArrayList<T2>)==true, which
 * makes it difficult to determine which converter should be used when multiple
 * Convertors support a collection but of different objects.
 * </p>
 *    
 * 
 * @author shantanu
 *
 */
public class Comments implements Collection<Comment> {
	
	private List<Comment> list;
	
	public Comments(){
		list = new ArrayList<Comment>();
	}

	@Override
	public boolean add(Comment e) {
		return list.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends Comment> c) {
		return list.addAll(c);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<Comment> iterator() {
		return list.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

}
