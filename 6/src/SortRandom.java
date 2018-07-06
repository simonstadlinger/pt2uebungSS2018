import java.util.SplittableRandom;


public class SortRandom{
    public static void main(String[] args){
        StableQuickSortableList list = new StableQuickSortableList();
        int j = new SplittableRandom().nextInt(0, 10);
        int[] random = new SplittableRandom().ints(j, 0, 20).parallel().toArray();

        for(int i: random){
            System.out.println(i);
            Integer val = new Integer(i);
            list.addLast(val);
        }

        list.print();
        list.sort();
        list.print();
    }
}
