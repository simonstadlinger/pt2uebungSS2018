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
    
    private void partListSort(Object pivot, Item left, Item right)
    {


    }

    public void QuickSort()
    {

    }
}
