import TempConv.*;

public class F2C{
	public static int main(String args[]){
		Fahrenheit Fahrenheit = new Fahrenheit();
		for(String arg: args){
			System.out.println(Fahrenheit.convert(Double.parseDouble(arg)));	
		}
	}
}
