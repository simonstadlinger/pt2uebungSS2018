import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;




public class SortStdin
{
    public static void main(String[] args)
    {
        StableQuickSortableList list = new StableQuickSortableList();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        String input;
        try{
            while((input = br.readLine()) != null && (input.length()> 0)){
                if("q".equals(input)){
                    System.exit(0);
                }
            list.addLast(input);
            }
        } 
        catch(IOException e){
           e.printStackTrace(); 
        }
        //list.sort(); 
        list.print();
    }
}
