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


}