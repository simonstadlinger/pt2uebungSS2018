import TempConv.*;

public class F2C{
	public static void main(String args[]){
		Conversion Fahrenheit = new Fahrenheit();
		for(String arg: args){
			System.out.println(Double.parseDouble(arg));
			System.out.println(Fahrenheit.convert(Double.parseDouble(arg)));	
		}
		System.exit(42);
	}
}
