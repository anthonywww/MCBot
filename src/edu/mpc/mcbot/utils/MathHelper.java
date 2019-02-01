package edu.mpc.mcbot.utils;

public final class MathHelper {
	
	// Prevent instantiation
	private MathHelper() {}
	
	
	public static final double degreesToRadians(double degrees) {
		return ((degrees*Math.PI)/180);
	}
	
	public static final float degreesToRadians(float degrees) {
		return (float) ((degrees*Math.PI)/180);
	}
	
	public static final double radiansToDegrees(double radians) {
		return ((radians*180)/Math.PI);
	}
	
	public static final float radiansToDegrees(float radians) {
		return (float) ((radians*180)/Math.PI);
	}
	
	/**
	 * Raise a number base to a power
	 * @param base
	 * @param power
	 * @return
	 */
	public static final double power(double base, double power) {
		if (power == 0) {
			return 1;
		} else if (power == 1) {
			return base;
		} else if (power == 2) {
			return (base * base);
		}
		
		return Math.pow(base, power);
	}
	
	/**
	 * Get factorial of a number
	 * @param num
	 * @return
	 */
	public static final int factorial(int num) {
		int fact = 1;
		for (int i = 1; i <= num; i++) {
			fact *= i;
		}
		return fact;
	}
	
	/**
	 * Extract a integer from a string
	 * or return 0 if the string is invalid.
	 * @param num
	 * @return
	 */
	public static final int stringToInt(String num) {
		if (num == null) {
			return 0;
		}
		try {
			return Integer.parseInt(num);
		} catch (NumberFormatException | NullPointerException e) {
			return 0;
		}
	}
	
	/**
	 * Extract a double from a string
	 * or return 0 if the string is invalid.
	 * @param num
	 * @return double
	 */
	public static final double stringToDouble(String num) {
		if (num == null) {
			return 0;
		}
		try {
			return Double.parseDouble(num);
		} catch (NumberFormatException | NullPointerException e) {
			return 0;
		}
	}
	
	/**
	 * Extract a float from a string
	 * or return 0 if the string is invalid.
	 * @param num
	 * @return float
	 */
	public static final float stringToFloat(String num) {
		if (num == null) {
			return 0;
		}
		try {
			return Float.parseFloat(num);
		} catch (NumberFormatException | NullPointerException e) {
			return 0;
		}
	}
	
	/**
	 * Convert a double to a float
	 * @param num
	 * @return float
	 */
	public static final float doubleToFloat(double num) {
		return ((float) num);
	}
	
	/**
	 * Convert a float to a double
	 * @param num
	 * @return double
	 */
	public static final double floatToDouble(float num) {
		return ((double) num);
	}
	
	/**
	 * Check if a float is about equal to another float within a range of tolerance.
	 * @param a
	 * @param b
	 * @param tolerance
	 * @return
	 */
	public static final boolean isAboutEqual(float a, float b, float tolerance) {
		return Math.abs(a-b) <= Math.max(0, tolerance);
	}
	
	/**
	 * Check if a double is about equal to another double within a range of tolerance.
	 * @param a
	 * @param b
	 * @param tolerance
	 * @return
	 */
	public static final boolean isAboutEqual(double a, double b, float tolerance) {
		return Math.abs(a-b) <= Math.max(0, tolerance);
	}
	
	/**
	 * Linear interpolates an associated value for given xb.
	 * <br>Example:<br>
	 * x1=10; y1=100; x2=100; y2=1000; xb=500 Would result in 
	 * an interpolation of <br> 5000.
	 * @param x1 -Any number.
	 * @param y1 -A number associated with 'x1'.
	 * @param xb -The value to find the linear interpolated, associated value from.
	 * @param x2 -Another number
	 * @param y2 -A number associated with 'x2'.
	 * @return The linear interpolated associated number for the given 'xb'.
	 */
	public static final float interpolate(float x1, float y1, float xb, float x2, float y2) {
		return y1+((xb-x1)*(y2-y1))/(x2-x1);
	}
	
}
