
public class LinkedDeque implements Deque {
	private int cap;
	private int length;

	private Item first;
	private Item last;

	private class Item {
		public Item next;
		public Item prev;
		public Object val;

		public Item(Object e, Item n, Item p) {
			val = e;
			next = n;
			prev = p;
		}
	}

	public LinkedDeque(int c) {
		cap = c;
		length = 0;
		first = null;
		last = null;
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
		first = null;
		last = null;
	}

	public void addFirst(Object e) throws DequeFull {
		if (length < cap) {
			first = new Item(e, first, null);
			first.next.prev = first;
			length++;
		} else {
			throw new DequeFull("Deque is full!");
		}
	}

	public void addLast(Object e) throws DequeFull {
		if (length < cap) {
			last = new Item(e, null, last);
			last.prev.next = last;
			length++;
		} else {
			throw new DequeFull("Deque is full!");
		}
	}
 
	public Object removeFirst() throws DequeEmpty {
		if (length > 0) {
			Object result = first.val;
			first = first.next;
			if (first != null) {
				first.prev = null;
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
			Object result = last.val;
			last = last.prev;
			if (last != null) {
				last.next = null;
			}
			length--;
			return result;
		} else {
			throw new DequeEmpty("Deque is empty!");
			return null;
		}
	}

}
