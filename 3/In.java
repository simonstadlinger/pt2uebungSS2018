// Sedgewick, Adapterklasse

import java.io.*;

public class In {
	private static int c;
	private static boolean blank() {
		return c == 0 || Character.isWhitespace((char) c); 
	}
	private static void readC() {
		try {
			c = System.in.read(); 
		} catch (IOException e) {
			c = -1;
		}
	}
	public static boolean empty() {
		return c == -1;
	}
	public static String getString() {
		if (empty()) return null;
		String s = "";
		while (!empty() && blank())
			readC();
		do {
			s += (char) c; readC(); 
		} while (!(empty() | blank()));
		return s;
	}
	public static int getInt() {
		return Integer.parseInt(getString());
	}
	public static double getDouble() {
		return Double.parseDouble(getString());
	}
}
