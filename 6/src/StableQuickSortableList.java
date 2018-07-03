import java.lang.*;


public class StableQuickSortableList{

    private Item first;
    private Item last;
    private int length;

    private class Item {
        public Item next;
        public Item prev;
        public Comparable val;

        public Item(Comparable e, Item n, Item p) 
        {
            next = n;
            prev = p;
            val = e;
        }

        public int compareTo(Item e)
        {
            return val.compareTo(e.val);
        }

        public void print()
        {
            System.out.print(val+" ");
            if(next!=null)
            {
                next.print();
            } 
        }
        
    }

    public StableQuickSortableList()
    {
        first = null;
        last = null;
        length= 0;
        
    }
    
    public int getLength()
    {
        return length;
    }

    public void addLast(Comparable e)
    {
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

    public void print( )
    {
        if(first != null)
        {
            first.print();
        }
    }

    private void exchange(Item a, Item b) {
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
        
        Item temp_prev = a.prev;
        Item temp_next = a.next;
        a.next = b.next;
        a.prev = b.prev;
        b.next = temp_next;
        b.prev = temp_prev;
    }

    private void compareExchange(Item a, Item b) {
        if (b.compareTo(a) < 0) {
            exchange(a,b);
        }
    }
    
    public void sort() {
        quickSort(first, last);
    }

    private void quickSort(Item left, Item right)
    {
        System.out.println("Hi");
        if (left == right || left.prev == right) {
            return;
        }
        Item pivot = partition(left, right);
        quickSort(left, pivot.prev);
        quickSort(pivot.next, right);
    }

    private Item partition(Item left, Item right) {
        System.out.println("part");
        Item a = left;
        Item b = right.prev;
        Item v = right;
        boolean r = true;
        for(;;) {
            System.out.println("for");
            System.out.println(a.val + " " + v.val);
            while(a.compareTo(v) <= 0) {
                System.out.println("w1");
                a = a.next;
            }
            System.out.println(v.val + " " + b.val);
            while(v.compareTo(b) <= 0) {
                System.out.println("w2");
                b = b.prev;
                if (b == left) {
                    break;
                }
            }
            if (a == b || a.prev == b) {
                break;
            }
            exchange(a,b);
        }
        exchange(a,right);
        return a;
    }
}
