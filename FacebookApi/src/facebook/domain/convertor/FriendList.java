package facebook.domain.convertor;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import facebook.domain.core.User;

public class FriendList implements Collection<User> {
	
	private List<User> friends;

	@Override
	public boolean add(User e) {
		return friends.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends User> c) {
		return friends.addAll(c);
	}

	@Override
	public void clear() {
		friends.clear();
	}

	@Override
	public boolean contains(Object o) {
		return friends.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return friends.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return friends.isEmpty();
	}

	@Override
	public Iterator<User> iterator() {
		return friends.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return friends.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return friends.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return friends.retainAll(c);
	}

	@Override
	public int size() {
		return friends.size();
	}

	@Override
	public Object[] toArray() {
		return friends.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return friends.toArray(a);
	}
	
}
