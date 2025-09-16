import java.util.Scanner;
import java.util.ArrayList;

/**
 * Demonstrates the NumberOperations class.
 */
public class NumberOpsDriver {

   /**
    * Reads a set of positive numbers from the user until the user enters 0.
    * Prints odds under and powers of 2 under for each number.
    *
    * @param args - Standard commandline arguments
    */
   public static void main(String[] args) {
      
      // Create a Scanner object to read user input
      Scanner in = new Scanner(System.in);
      
      // Declare and instantiate an ArrayList with the generic type <NumberOperations>
      ArrayList<NumberOperations> numOpsList = new ArrayList<NumberOperations>();
      
      // Prompt the user for a set of positive numbers
      System.out.println("Enter a list of positive integers separated with a space followed by 0:");
      
      int n = -1; // Initialize a variable to hold the user's number input

      // Continue reading input until the user enters 0
      while (n != 0) {
         // Get the next user input using in.nextInt()
         n = in.nextInt();
         if (n != 0) { // Check if the input is not equal to 0
            // Add a new NumberOperations object to numOpsList based on user input
            numOpsList.add(new NumberOperations(n));
         }
      }
      
      // Initialize an index variable for iterating through numOpsList
      int index = 0;
      
      // Iterate through numOpsList to process each NumberOperations object
      while (index < numOpsList.size()) {
         NumberOperations num = numOpsList.get(index);
         
         // Print the current number
         System.out.println("For: " + num);
         
         // Print odds under the current number
         System.out.println("\tOdds under: " + num.oddsUnder());
         
         // Print powers of 2 under the current number
         System.out.println("\tPowers of 2 under: " + num.powersTwoUnder());
         
         // Increment the index to move to the next number in the list
         index++;
      }
   }
}