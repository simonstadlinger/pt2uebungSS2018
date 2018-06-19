package de.uni_potsdam.hpi;

public interface Deque {

	int capacity(); 
	int size(); 
	void clear(); 
	void addFirst(Object e) throws DequeFull; 
	void addLast(Object e) throws DequeFull; 
	Object removeFirst() throws DequeEmpty; 
	Object removeLast() throws DequeEmpty;

}