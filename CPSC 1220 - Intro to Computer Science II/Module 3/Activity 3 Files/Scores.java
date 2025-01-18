/**
* Activity 3 Intro to Computer Science II
* Jonathan Elder
* 9/6/2022
*/
public class Scores {

    private int[] numbers;

    /**
    * Constructor that initializes the Scores array with a user defined quantity.
    * @param numbersIn The total integers variables to enter for the array.
    */
    public Scores(int[] numbersIn) {
        numbers = numbersIn;
    }

    /**
    * Method to find all even integers in the "numbers" array.
    * @return An array containing the even numbers from the "numbers" array.
    */
    public int[] findEvens() {
        int numberEvens = 0;

        // Each integer divided by two with no remainder must be even, increment numberEvens.
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                numberEvens++;
            }
        }

        // Creates an array to store the even numbers.
        int[] evens = new int[numberEvens];
        int count = 0;

        // Populates the evens array.
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                evens[count] = numbers[i];
                count++;
            }
        }

        return evens;
    }

    /**
    * Method to find all odd integers in the "numbers" array.
    * @return An array containing the odd numbers from the "numbers" array.
    */
    public int[] findOdds() {
        int numberOdds = 0;

        // Each integer divided by two with a remainder must be odd, increment numberOdds.
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 != 0) {
                numberOdds++;
            }
        }

        // Creates an array to store the odd numbers.
        int[] odds = new int[numberOdds];
        int count = 0;

        // Populates the odds array.
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 != 0) {
                odds[count] = numbers[i];
                count++;
            }
        }

        return odds;
    }

    /**
    * Calculates the average of the numbers in the array.
    * @return The average of the "numbers" array.
    */
    public double calculateAverage() {
        // Instance variable.
        double sum = 0.0;

        // Calculates the sum of all integers in the "numbers" array.
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }

        // Return the average.
        return sum / numbers.length;
    }

    /**
    * Provides a string representation of the "numbers" array.
    * Displays the numbers in the order they appear.
    * @return A string of tab-separated numbers.
    */
    @Override
    public String toString() {
        String result = "";

        // Appends each number to the resulting string
        for (int i = 0; i < numbers.length; i++) {
            result += numbers[i] + "\t";
        }

        return result;
    }

    /**
    * Provides a string representation of the "numbers" array in reverse order.
    * Displays the numbers starting from the last element.
    * @return A string of tab-separated numbers in reverse order.
    */
    public String toStringInReverse() {
        String result = "";

        // Append each number to the result string in reverse order
        for (int i = numbers.length - 1; i >= 0; i--) {
            result += numbers[i] + "\t";
        }

        return result;
    }
}