/**
 * Applies the linear scan strategy to counting the number of negative
 * values in an array.
 */
public class CountNegatives {

/**
* Returns the number of negative values in the given array
*/

public static int countNegatives(int[] a) {

// Initializing counter to 0

int negativeCount = 0;

// Iterating over array and finding number of negative elements

for (int i=0; i<a.length; i++) {

if(a[i] < 0)
negativeCount += 1;
}

return negativeCount;
}

// main method to test countNegatives method

public static void main(String[] args) {

// Declaring an array

int[] a = {-1, 1, -24, 3, -7, -3};

System.out.println("Negative values in array: " + countNegatives(a));
}
}