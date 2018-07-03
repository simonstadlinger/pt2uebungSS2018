import java.lang.*;


public class StableQuickSortableList{// implements Comparable<Object>{

    private item first;
    private item last;
    private int length;

    private class item{
        public item next;
        public item prev;
        public Object val;

        public item(Object e, item n, item p) 
        {
            next = n;
            prev = p;
            val = e;
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
    
    public int getLenth()
    {
        return length;
    }

    public void addLast(Object e)
    {
        if(length==0)
        {
            first = new item(e, null, null);
            last = first;
            length++;
        }
        else
        {
            last = new item(e, null, last);
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
    
    private void partListSort(Object pivot, item left, item right)
    {
        if(left.compareTo(right)==0)
        {

        }
    }

    public void QuickSort()
    {
        Object pivot = last.val; 
        item leftIndex = first;
        item rightIndex = last.prev;
    }
}
