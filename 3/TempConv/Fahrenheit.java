package TempConv;
public class Fahrenheit extends Conversion {
	public double convert(double val){
		double result = (val - 32)*(5/9);
		return result;
	}
}
