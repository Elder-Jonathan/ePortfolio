import java.util.Scanner;
import java.text.DecimalFormat;
/**
 *
 * Project 3 Part 1 CPSC 1210 Intro to Computer Science. 
 * @author Jonathan Elder
 * @version 6/11/2022
 */
public class Solver {
/**
*
* Computes an expression after taking a value for x.
* @param args Command line arguments (not used)
*/
   public static void main(String[] args) {
   
      Scanner sc = new Scanner(System.in);
      
      // User input value for 'x'
      System.out.print("Enter a value for x: ");
      
      double x = sc.nextDouble();
      
      double result = (Math.sqrt(11 * Math.pow(x, 4) + 9 * Math.pow(x, 3) 
         + 7 * Math.pow(x, 2) + 5 * x + 4)) / ((2 * x) + 4);
      
      // Changes the result to a string and counts the chars to 
      // the left and right and the decimal location in the string.   
      String toStringResult = String.valueOf(result);
      int decimal = toStringResult.indexOf(".");
      int lengthOfResult = toStringResult.length();
      String leftOf = toStringResult.substring(0, decimal);
      String rightOf = toStringResult.substring(decimal + 1, lengthOfResult);
      
      // toString that includes the result formatted with the # of values to the left/right of "."
      System.out.println("Result: " + toStringResult);
      System.out.println("# of characters to left of decimal point: " 
         + leftOf.length());
      System.out.println("# of characters to right of decimal point: " 
         + rightOf.length());
      DecimalFormat df = new DecimalFormat("#,##0.0####");
      System.out.println("Formatted Result: " + df.format(result));
   }
}