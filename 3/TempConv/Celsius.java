package TempConv;
public class Celsius extends Conversion{
	static double convert(double val){
		double result = (9/5) * val + 32;
		return result;
	}
}
