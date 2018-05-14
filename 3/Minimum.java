import java.io.*;
public class Minimum{
	public static void main(String args[]){
		In Getter = new In();
		int minimum = Getter.getInt();
		int temp = 0;
		while((temp = Getter.getInt())>=0)
		{
			if(temp < minimum) minimum = temp;
		}		
		System.out.println(minimum);
	}
}
