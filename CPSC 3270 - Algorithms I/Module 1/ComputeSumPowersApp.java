import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

// Jonathan Elder
// CPSC 3270 Programming Assignment 1

public class ComputeSumPowersApp {

    public static void main(String[] args) {
        double a = 5.0;
        double x = 0.80;
        int startN = 5000;
        int endN = 12000; // Updated as per your request
        int step = 250;

        try (PrintWriter out = new PrintWriter(new FileWriter("outputData.txt"))) {
            for (int n = startN; n <= endN; n += step) {
                long startTime = System.nanoTime();
                double result = computeSumPowers(a, x, n); // Store result to possibly use later
                long endTime = System.nanoTime();
                long duration = endTime - startTime;

                double function1 = (double) duration / n;
                double function2 = (double) duration / (0.5 * Math.pow(n, 2));
                double function3 = (double) duration / (5 * Math.sqrt(n));
                
                out.println(n + "," + function1 + "," + function2 + "," + function3);
            }
        } catch (IOException e) {
            System.out.println("File could not complete writing.");
            e.printStackTrace();
        }
    }

    public static double computeSumPowers(double a, double x, int n) {
        double sum = 0;
        double prod = x;
        for (int i = 1; i <= n; i++) {
            sum += prod;
            prod *= x;
        }
        return a * sum;
    }
}