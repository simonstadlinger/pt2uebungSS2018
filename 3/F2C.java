import TempConv.*;

public class F2C{
	public static void main(String args[]){
		Conversion conversion = new Fahrenheit();
		for(String arg: args){
			System.out.println(conversion.convert(Double.parseDouble(arg)));	
		}
	}
}
