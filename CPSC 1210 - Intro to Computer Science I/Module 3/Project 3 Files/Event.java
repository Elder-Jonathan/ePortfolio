import java.util.Scanner;
import java.text.DecimalFormat;

/**
 *
 * Project 3 Part 2 CPSC 1210 Intro to Computer Science.
 * @author Jonathan Elder
 * @version 6/11/2022
 */
public class Event {
   /**
    *
    * Accept coded information about an event that breaks down
    * exactly what the ticket is for.
    * @param args Command line arguments (not used)
    */
   public static void main(String[] args) {

      // Initialize all variables.
      String userCode, eventDetail, dateOfEvent, time, section, row, seat;
      double ticketPrice, discount, costAfterDiscount;
      int prize_Number;

      // Default scanner object for taking in a line of user input.
      Scanner userInput = new Scanner(System.in);
      DecimalFormat df = new DecimalFormat("$#,##0.00"); // Default decimal format.

      // User is prompted to enter the complete code that was "provided" to them.
      // Will loop until a valid response is entered.
      while (true) {
         System.out.print("Enter your event code: ");
         userCode = userInput.nextLine();
         userCode = userCode.trim();
         System.out.println();

         // "Catch all" error message for when the userCode entered does not meet the minimum length required.
         if (userCode.length() < 26) {
            System.out.println("Invalid Event Code.");
            System.out.println("Event code must have at least 26 characters.");
         } else {
            // As long as the userCode is 26 characters, the remainder always becomes the eventDetail.
            // Each other substring variable always keeps the same positions.
            eventDetail = userCode.substring(25, userCode.length());
            dateOfEvent = userCode.substring(0, 8);
            time = userCode.substring(8, 12);
            ticketPrice = Double.parseDouble(userCode.substring(12, 15)
                  + "." + userCode.substring(15, 17));
            discount = Double.parseDouble(userCode.substring(17, 19));
            section = userCode.substring(19, 21);
            row = userCode.substring(21, 23);
            seat = userCode.substring(23, 25);

            // Calculates cost after discount. Does not include tax.
            costAfterDiscount = ticketPrice - (ticketPrice * (discount / 100));
            // Gives a random number between 1 and 9999.
            prize_Number = (int) (Math.random() * 10000 + 1);
            
            // Acting as the toString and giving a formatted version of the formatted userCode.
            System.out.print("Event: " + eventDetail + "   ");
            System.out.print("Date: " + dateOfEvent.substring(0, 2)
                  + "/" + dateOfEvent.substring(2, 4) + "/"
                  + dateOfEvent.substring(4, 8) + "   ");
            System.out.println("Time: " + time.substring(0, 2)
                  + ":" + time.substring(2, 4));
            System.out.print("Section: " + section + "   ");
            System.out.print("Row: " + row + "   ");
            System.out.println("Seat: " + seat);
            System.out.print("Price: " + df.format(ticketPrice) + "   "); // Default decimal format
            
            // Changes the pattern of the decimalFormat object for each other variable as needed.
            df.applyPattern("#.#'%'");
            System.out.print("Discount: " + df.format(discount) + "   ");
            df.applyPattern("$#,##0.00");
            System.out.println("Cost: " + df.format(costAfterDiscount));
            df.applyPattern("0000");
            System.out.println("Prize Number: " + prize_Number);

            break; // Makes sure to exit the while loop when valid. Effectively ending the program.
         }
      }
   }
}