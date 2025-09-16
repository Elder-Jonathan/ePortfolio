/**
* Activity 5 CPSC 1210 Intro to Computer Science.
* @author Jonathan Elder
* @date 6/23/2022
*/


/**
 * The NumberOperations class performs operations on an integer value.
 */
public class NumberOperations {
    private int number;

    /**
     * Constructor that initializes the number.
     * @param numberIn - the number to initialize
     */
    public NumberOperations(int numberIn) {
        number = numberIn;
    }

    /**
     * Returns the value of the number.
     * @return the value of number
     */
    public int getValue() {
        return number;
    }

    /**
     * Returns a String containing the positive odd integers less than the number.
     * @return a String of positive odd integers less than number
     */
    public String oddsUnder() {
        String output = "";
        int i = 1;
        while (i < number) {
            if (i % 2 != 0) { // Check if i is odd
                output += i + " ";
            }
            i++;
        }
        return output.trim();
    }

    /**
     * Returns a String containing the positive powers of 2 less than the number.
     * @return a String of powers of 2 less than number
     */
    public String powersTwoUnder() {
        String output = "";
        int power = 1;
        while (power < number) {
            output += power + " ";
            power *= 2; // Calculate the next power of two
        }
        return output.trim();
    }

    /**
     * Compares the value of the number with another number.
     * @param compareNumber - the number to compare with
     * @return 1 if number is greater, -1 if less, 0 if equal
     */
    public int isGreater(int compareNumber) {
        if (number > compareNumber) {
            return 1;
        } else if (number < compareNumber) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Returns the number as a String.
     * @return a String representation of the number
     */
    @Override
    public String toString() {
        return number + "";
    }
}
