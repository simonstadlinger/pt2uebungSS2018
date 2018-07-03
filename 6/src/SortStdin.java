

public class SortStdin
{
    public static void main(String[] args)
    {
        StableQuickSortableList list = new StableQuickSortableList();
        
        for(String i: args)
        {
            System.out.print(i+" "); 
            Integer value = Integer.parseInt(i);
            list.addLast(value); 
        }
        list.sort();
        list.print();
    }
}
