// Jonathan Elder
// CPSC 3280 Algorithms 2
// Module 5 Homework Assignment
// UpdatedMemoizedCutRod.java

import java.util.ArrayList;
import java.util.List;

public class UpdatedMemoizedCutRod {

    // Function to calculate the maximum revenue using memoization and return the solution.
    public static DataResults memoizedCutRod(int[] priceArray, int rodLength) {
        // Create arrays to store the maximum revenue for each length and the cuts.
        int[] revenueArray = new int[rodLength + 1];
        int[] solutionArray = new int[rodLength + 1];
        
        // Initialize the revenue array with minimum value.
        for (int i = 0; i <= rodLength; i++) {
            revenueArray[i] = Integer.MIN_VALUE;
        }
        
        // Calculate the maximum revenue and fill the solution array.
        int maxRevenue = memoizedCutRodAux(priceArray, rodLength, revenueArray, solutionArray);
        
        // Construct the new solution of cuts.
        List<Integer> cuts = new ArrayList<>();
        while (rodLength > 0) {
            cuts.add(solutionArray[rodLength]);
            rodLength -= solutionArray[rodLength];
        }
        
        return new DataResults(maxRevenue, cuts);
    }

    // Aux function that calculates the maximum revenue.
    private static int memoizedCutRodAux(int[] priceArray, int rodLength, int[] revenueArray, int[] solutionArray) {
        // If the revenue for length rodLength is already calculated, and then return it.
        if (revenueArray[rodLength] >= 0) {
            return revenueArray[rodLength];
        }
        
        int maxRevenue;
        
        if (rodLength == 0) {
            maxRevenue = 0;
        } else {
            maxRevenue = Integer.MIN_VALUE;
            // Loop over each possible cut of length "i".
            for (int i = 1; i <= rodLength; i++) {
                int currentRevenue = priceArray[i] + memoizedCutRodAux(priceArray, rodLength - i, revenueArray, solutionArray);
                if (currentRevenue > maxRevenue) {
                    maxRevenue = currentRevenue;
                    solutionArray[rodLength] = i;
                }
            }
        }
        
        // Store the calculated maxRevenue.
        revenueArray[rodLength] = maxRevenue;
        return maxRevenue;
    }

    // Main function that uses test data like before.
    public static void main(String[] args) {
        // Example prices for rod lengths 0 to 10. This should match the book's price weights.
        int[] priceArray = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        
        int rodLength = 4;

        // Calculate the maximum revenue and the cuts
        DataResults result = memoizedCutRod(priceArray, rodLength);

        // Print the results in an updated format that includes the cuts for maxRevenue as well.
        System.out.println("\nThe test rod length given: " + rodLength);
        System.out.println("The given prices that match the textbook: ");
        for (int i = 1; i < priceArray.length; i++) {
            System.out.print(priceArray[i] + " ");
        }
        System.out.println("\nThe maximum revenue of length " + rodLength + " is: " + result.maxRevenue);
        System.out.println("The cuts are: " + result.cuts + "\n");
    }
}

// Class to store the result with max revenue and cuts.
class DataResults {
    int maxRevenue;
    List<Integer> cuts;

    DataResults(int maxRevenue, List<Integer> cuts) {
        this.maxRevenue = maxRevenue;
        this.cuts = cuts;
    }
}