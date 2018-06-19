/*
	int capacity(); 
	int size(); 
	void clear(); 
	void addFirst(Object e) throws DequeFull; 
	void addLast(Object e) throws DequeFull; 
	Object removeFirst() throws DequeEmpty; 
	Object removeLast() throws DequeEmpty;
*/


public class ArrayDeque implements Deque {
	private int cap;
	private int length;
	private Object[] deque;

	private int first;
	private int last;

	public ArrayDeque(int c) {
		cap = c;
		length = 0;
		deque = new Object[c];

	}

	public int capacity() {
		return cap;
	}

	public int size() {
		return length;
	}

	public void clear() {
		length = 0;
		deque = [];
	}

	public void addFirst(Object e) throws DequeFull {
		if (length < cap) {
			if (length != 0) {
				first = (first - 1) % cap;
			}
			deque[first] = e;
			length++;
		} else {
			throw new DequeFull("Deque is full!");
		}
	}

	public void addLast(Object e) throws DequeFull {
		if (length < cap) {
			if (length != 0) {
				last = (last + 1) % cap;
			}
			deque[last] = e;
			length++;
		} else {
			throw new DequeFull("Deque is full!");
		}
	}
 
	public Object removeFirst() throws DequeEmpty {
		if (length > 0) {
			Object result = deque[first];
			if (first != last) {
				first = (first + 1) % cap;
			}
			length--;
			return result;
		} else {
			throw new DequeEmpty("Deque is empty!");
			return null;
		}
	}
 
	public Object removeLast() throws DequeEmpty {
		if (length > 0) {
			Object result = deque[last];
			if (last =! first) {
				last = (last - 1) % cap;
			}
			length--;
			return result;
		} else {
			throw new DequeEmpty("Deque is empty!");
			return null;
		}
	}

}