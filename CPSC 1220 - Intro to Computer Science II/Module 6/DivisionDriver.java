import javax.swing.JOptionPane;

/**
* Intro to Computer Science II - Activity 6
* @author Jonathan Elder
* @date   9/21/2022
* Driver class to perform division operations by accepting user inputs with pane dialog boxes.
*/
public class DivisionDriver {

    /**
    * Driver program for implementing the Division class.
    * Handles exceptions for invalid input and division by zero.
    * @param args Prompts user input for numerator and denominator using dialog boxes.
    */
    public static void main(String[] args) {
        
        // Prompt user for numerator and denominator input via dialog boxes.
        String numInput = JOptionPane.showInputDialog("Enter the numerator:");
        String denomInput = JOptionPane.showInputDialog("Enter the denominator:");

        try {
            int num = Integer.parseInt(numInput);
            int denom = Integer.parseInt(denomInput);

            // Perform division and prepare the result message.
            String result = "Integer division: \n"
                + Division.intDivide(num, denom)
                + "\n\nFloating point division: \n"
                + Division.decimalDivide(num, denom);

            // Display the calculated result in a dialog box.
            JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.PLAIN_MESSAGE);
        }
        
        // Handle invalid numeric input from the user.
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "Invalid input: please enter numerical values only.", "Error",
                JOptionPane.ERROR_MESSAGE);
        }
        
        // Handle division by zero exception.
        catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,
                e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}