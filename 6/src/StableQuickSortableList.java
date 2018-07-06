import java.lang.*;


public class StableQuickSortableList{

    public Item first;
    public Item last;
    private int length;

    private class Item {
        public Item next;
        public Item prev;
        public Comparable val;

        public Item(Comparable e, Item n, Item p) {
            next = n;
            prev = p;
            val = e;
        }

        public int compareTo(Item e) {
            return val.compareTo(e.val);
        }

        public void print() {
            System.out.println(val.toString());
            if(next!=null) {
                next.print();
            }
        }
        
    }

    public StableQuickSortableList() {
        first = null;
        last = null;
        length= 0;
        
    }
    
    public int getLength() {
        return length;
    }

    public void addLast(Comparable e) {
        if(length==0)
        {
            first = new Item(e, null, null);
            last = first;
            length++;
        }
        else
        {
            last = new Item(e, null, last);
            last.prev.next = last;
            length++;
        }
    }

    public void print() {
        if(first != null)
        {
            first.print();
        }
    }

    public void exchange(Item a, Item b) {
        if (a.prev == b) {
            if (b.prev != null) {
                b.prev.next = a;
            }
            if (a.next != null) {
                a.next.prev = b;
            }

            b.next = a.next;
            a.next = b;
            a.prev = b.prev;
            b.prev = a;
        } else if (a.next == b) {
            if (a.prev != null) {
                a.prev.next = b;
            }
            if (b.next != null) {
                b.next.prev = a;
            }
            
            b.prev = a.prev;
            a.prev = b;
            a.next = b.next;
            b.next = a;
        } else {
            if (a.prev != null) {
                a.prev.next = b;
            }
            if (a.next != null) {
                a.next.prev = b;
            }
            if (b.prev != null) {
                b.prev.next = a;
            }
            if (b.next != null) {
                b.next.prev = a;
            }


            Item temp_next = a.next;
            Item temp_prev = a.prev;
            a.prev = b.prev;
            a.next = b.next;
            b.prev = temp_prev;
            b.next = temp_next;
        }
        if (a == first) {
            System.out.println("fa");
            first = b;
        } else if (b == first) {
            System.out.println("fb");
            first = a;
        }
        if (a == last) {
            last = b;
        } else if (b == last) {
            last = a;
        }
    }

    public void sort() {
        quickSort(first, last);
    }

    private void quickSort(Item left, Item right) {
        if (left == right) {
            return;
        }
        Item pivot = right;
        Item shift = left;
        StableQuickSortableList less = new StableQuickSortableList();
        StableQuickSortableList greater = new StableQuickSortableList();
        while(shift != pivot) {
            if (shift.compareTo(pivot) <= 0) {
                less.addLast(shift.val);
            } else {
                greater.addLast(shift.val);
            }
            shift = shift.next;
        }

        less.sort();
        greater.sort();

        if (less.first != null) {
            first = less.first;
        } else {
            first = pivot;
        }
        if (greater.last != null) {
            last = greater.last;
        } else {
            last = pivot;
        }
        if (less.last != null) {
            less.last.next = pivot;
        }
        pivot.prev = less.last;
        pivot.next = greater.first;
        if (greater.first != null) {
            greater.first.prev = pivot;
        }
        return;
    }
}
