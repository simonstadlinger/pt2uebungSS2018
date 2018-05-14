import TempConv.*;

public class C2F{
	public static void main(String args[]){
		Conversion celsius = new Celsius();
		for(String arg: args){
			System.out.println(celsius.convert(Double.parseDouble(arg)));	
		}
		System.exit(42);
	}
}
