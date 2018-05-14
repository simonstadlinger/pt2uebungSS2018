package TempConv;
public class Fahrenheit extends Conversion {
	static double convert(double val){
		double result = (val - 32.0)*(5.0/9.0);
		return result;
	}
}
