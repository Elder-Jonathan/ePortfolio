import java.util.Scanner;
/**
 *
 * Project 2 Part 2 CPSC 1210 Intro to Computer Science. 
 * @author Jonathan Elder
 * @version 6/3/2022
 */
public class SquareUnits {
/**
*
* Expression that breaks down squareInches into other values.
* @param args Command line arguments (not used)
*/
   public static void main(String[] args) {
   /**
   *
   * If limit is not reached then variable is broken down
   * into other measurements.
   * 
   */
      int squareInches;
      Scanner userInput = new Scanner(System.in);
      System.out.print("Enter the area in square inches: ");
      squareInches = userInput.nextInt();
      //Whenever the limit is surpassed.
      if (squareInches > 1000000000) 
      {
         System.out.println("Limit of 1,000,000,000 square inches exceeded!");
      }
      //Breaks down into different incriments for all numbers under the limit.
      else 
      {
         System.out.println("Number of Units:");
         int acres = squareInches / 6272640;
         int squareYards = (squareInches % 6272640) / 1296;
         int squareFeet = (squareInches % 1296) / 144;
         int squareInchLeft = squareInches % 144;
         System.out.println("\tAcres: " + acres);
         System.out.println("\tSquare Yards: " + squareYards);
         System.out.println("\tSquare Feet: " + squareFeet);
         System.out.println("\tSquare Inches: " + squareInchLeft);
      }
   }
}