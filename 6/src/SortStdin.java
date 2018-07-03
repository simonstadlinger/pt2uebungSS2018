

public class SortStdin
{
    public static void main(String[] args)
    {
        StableQuickSortableList list = new StableQuickSortableList();
        
        for(String i: args)
        {
            System.out.print(i+" "); 
            int value = Integer.parseInt(i);
            list.addLast(value); 
        }
        
        list.print();
    }
}
