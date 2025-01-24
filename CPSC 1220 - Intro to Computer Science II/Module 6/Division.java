/**
* Intro to Computer Science II - Activity 6
* @author Jonathan Elder
* @date   9/21/2022
* Methods to perform integer and floating-point division using exception handling.
*/
public class Division {

    /**
    * Performs integer division of two integers.
    * Handles division by zero by returning zero instead of propagating an exception.
    * @param numIn Numerator of the division (int).
    * @param denomIn Denominator of the division (int).
    * @return The result of integer division if valid; otherwise, returns 0 if denominator is zero.
    */
    public static int intDivide(int numIn, int denomIn) {
        try {
            return numIn / denomIn;
        } catch (ArithmeticException e) {
            return 0;  // Returns 0 when denominator is zero.
        }
    }

    /**
    * Performs floating-point division with exception handling for division by zero.
    * @param numIn Numerator of the division (int).
    * @param denomIn Denominator of the division (int).
    * @return The result of floating-point division.
    * @throws IllegalArgumentException When the denominator entered is zero.
    */
    public static double decimalDivide(int numIn, int denomIn) {
        if (denomIn == 0) {
            throw new IllegalArgumentException("The denominator cannot be zero.");
        }
        return (double) numIn / denomIn;
    }
}