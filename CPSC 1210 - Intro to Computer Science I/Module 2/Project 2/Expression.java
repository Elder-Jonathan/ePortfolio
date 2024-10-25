import java.util.Scanner;
/**
 *
 * Project 2 Part 1 CPSC 1210 Intro to Computer Science. 
 * @author Jonathan Elder
 * @version 6/3/2022
 */
public class Expression {
/**
*
* computes an expression after taking values for x and y.
* @param args Command line arguments (not used)
*/
   public static void main(String[] args) {
   /**
   *
   *Does calculation based on when either
   *x or y is not equal to 0.
   */
      double x;
      double y;
      double result;
      System.out.println("result = (10x + 32.6) (5y - 1.567) / xy ");
      Scanner userInput = new Scanner(System.in);
      System.out.print("\tx = ");
      x = userInput.nextDouble();
      System.out.print("\ty = ");
      y = userInput.nextDouble();
      //when x is equal to 0
      if (x == 0) 
      {
         System.out.println("result is \"undefined\"");
      }
      //when y is equal to 0
      else if (y == 0) 
      {
         System.out.println("result is \"undefined\"");
      }
      //Use calculation when either x or y not equal to 0.
      else 
      { 
         result = ((10 * x + 32.6) * (5 * y - 1.567)) / (x * y);
         System.out.println("result = " + result);
      }
   }
}