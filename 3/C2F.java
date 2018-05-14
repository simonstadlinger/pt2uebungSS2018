import TempConv.*;

public class C2F{
	public static void main(String args[]){
		Conversion conversion = new Celsius();
		for(String arg: args){
			System.out.println(conversion.convert(Double.parseDouble(arg)));	
		}
	}
}
