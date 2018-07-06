import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SortStdin
{
    public static class ComparableRow implements Comparable {
        private Comparable key;
        private String data;
        private int column;

        public ComparableRow(int col, String d) {
            data = d;
            column = col;
            key = data.split("\\s+")[column - 1];
        }

        public int compareTo(Object o) {
            Comparable comp = o.toString().split("\\s+")[column - 1];
            return key.compareTo(comp);
        }

        public String toString() {
            return data;
        }
    } 

    public static void main(String[] args)
    {
        StableQuickSortableList list = new StableQuickSortableList();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        String input;

        if (args.length == 0) {
            try {
                while((input = br.readLine()) != null && (input.length() > 0)){
                    if("q".equals(input)){
                        System.exit(0);
                    }
                    list.addLast(input);
                }
            } 
            catch(IOException e){
               e.printStackTrace(); 
            }
        } else if (args.length == 2) {
            try {
                while((input = br.readLine()) != null && (input.length() > 0)){
                    if("q".equals(input)){
                        System.exit(0);
                    }
                    list.addLast(new ComparableRow(Integer.parseInt(args[1]), input));
                }
            } 
            catch(IOException e){
               e.printStackTrace(); 
            }
        } else {
            System.out.println("Usage: java SortStdin (-c n)");
        }
        
        list.sort(); 
        list.print();
    }
}
