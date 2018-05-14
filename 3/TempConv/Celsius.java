package TempConv;
public class Celsius extends Conversion{
	public double convert(double val){
		double result = (9.0/5.0) * val + 32.0;
		return result;
	}
}
