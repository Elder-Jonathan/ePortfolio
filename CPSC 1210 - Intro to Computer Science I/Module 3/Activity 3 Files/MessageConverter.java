import java.util.Scanner;
/**
 *
 * Activity 3 CPSC 1210 Intro to Computer Science. 
 * @author Jonathan Elder
 * @version 6/8/2022
 */
public class MessageConverter 
{ 
/**
*
* Program that takes in a message and is changed 
* based a range of choices within the string class.
* @param args Command line arguments (not used)
*/
   public static void main(String[] args) {
      // Creates a new scanner object called userInput using system.in.
      Scanner userInput = new Scanner(System.in);
      
      // Initialize variables.
      String message = "";
      String result = "";
      int outputType;
      
      // User is prompted to enter a message.
      System.out.print("Type in a message and press enter:\n\t> ");
      // Using the scanner object "userInput" to save into the "message" variable.
      message = userInput.nextLine();
      // User is prompted to select which way to manipulate the variable "message".
      System.out.print("\nOutput types:"
         + "\n\t0: As is "
         + "\n\t1: Trimmed"
         + "\n\t2: lower case"
         + "\n\t3: UPPER CASE"
         + "\n\t4: v_w_ls r_pl_c_d"
         + "\n\t5: Without first and last character"
         + "\nEnter your choice: ");
      outputType = Integer.parseInt(userInput.nextLine());
      if (outputType == 0) { // simply prints what was entered
         result = message;
      }
      else if (outputType == 1) { // Trims the extra white spaces.
         result = message.trim();
      }
      else if (outputType == 2) { // Makes all the characters lower case.
         result = message.toLowerCase();
      }
      else if (outputType == 3) { // Makes all the characters upper case.
         result = message.toUpperCase();
      }
      else if (outputType == 4) { // Using the method "replace" to change a char of a given value.
         result = message.replace('a', '_');
         result = result.replace('e', '_');
         result = result.replace('i', '_');
         result = result.replace('o', '_');
         result = result.replace('u', '_');
      }
      else if (outputType == 5) { 
         // Creates a substring starting with the second and second to last char of the variable "message"
         result = message.substring(1, message.length() - 1);
      }
      else { // invalid input error message
         result = "Error: Invalid choice input.";
      }
      // Prints the variable "result" with the user selected type and the program ends
      System.out.println("\n" + result);
   }
}