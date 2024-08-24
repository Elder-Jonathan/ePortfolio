// Jonathan Elder
// CPSC 3280 Algorithms 2
// Module 5 Homework Assignment
//MemoizedCutRod.java

public class MemoizedCutRod {

    // Function to calculate the maximum revenue using memoization as described through my provided pseudocode.
    public static int memoizedCutRod(int[] priceArray, int rodLength) {
        // Create an array that stores maxRevenue for each given length.
        int[] revenueArray = new int[rodLength + 1];
        
        // Initialize the revenueArray.
        for (int i = 0; i <= rodLength; i++) {
            revenueArray[i] = Integer.MIN_VALUE;
        }
        
        // Calls the Aux function defined below.
        return memoizedCutRodAux(priceArray, rodLength, revenueArray);
    }

    // Aux function that's used to calculate maximum revenue.
    private static int memoizedCutRodAux(int[] priceArray, int rodLength, int[] revenueArray) {
        // If the revenue for length rodLength is already calculated, return it
        if (revenueArray[rodLength] >= 0) {
            return revenueArray[rodLength];
        }
        
        int maxRevenue;
        
        if (rodLength == 0) {
            maxRevenue = 0;
        } else {
            maxRevenue = Integer.MIN_VALUE;
            // Loops over each possible cut length i and updates maxRevenue when needed.
            for (int i = 1; i <= rodLength; i++) {
                maxRevenue = Math.max(maxRevenue, priceArray[i] + memoizedCutRodAux(priceArray, rodLength - i, revenueArray));
            }
        }
        
        // Store the calculated maximum revenue
        revenueArray[rodLength] = maxRevenue;
        return maxRevenue;
    }

    // Main function that is simply used with test data.
    public static void main(String[] args) {
        // Example prices for rod lengths 0 to 10. This should match the books price weights.
        int[] priceArray = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        
        int rodLength = 4;

        // Calculate the maximum revenue
        int maxRevenue = memoizedCutRod(priceArray, rodLength);

        // Print the results that should show a match from the book 15.1 within the first figure.
        System.out.println("\nThe test rod length given: " + rodLength);
        System.out.println("The given prices that match the textbook: ");
        for (int i = 1; i < priceArray.length; i++) {
            System.out.print(priceArray[i] + " ");
        }
        System.out.println("\nThe maximum revenue of length " + rodLength + " is: " + maxRevenue + "\n");
    }
}