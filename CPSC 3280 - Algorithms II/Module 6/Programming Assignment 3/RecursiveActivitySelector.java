// Jonathan Elder
// CPSC 3280 Algorithms 2
// Programming Assignment 3
// RecursiveActivitySelector.java

import java.util.Arrays;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


// This should follow the pseudocode given for the assignment.
public class RecursiveActivitySelector {
    public static void selectActivities(List<Integer> s, List<Integer> f, int[] A, int k, int n) {
        int m = k + 1;
        while (m <= n && s.get(m) < f.get(k)) {
            m++;
        }
        if (m <= n) {
            A[m] = 1;
            selectActivities(s, f, A, m, n);
        }
    }

	// Sets up the given array to then be ran and analyzed after.
    private static void initializeArrays(List<Integer> s, List<Integer> f, int n) {
        s.add(0);
        f.add(0);
        for (int i = 1; i < n; i++) {
            if (i % 2 == 0) {
                s.add(f.get(i - 2));
                f.add(s.get(i) + 2);
            } else {
                s.add(f.get(i - 1) - 1);
                f.add(f.get(i - 1) + 1);
            }
        }
    }

	// This will gather data on the above algorithm to then graph in excel.
    public static void studyOverhead(int numberPoints, int numberRuns) throws IOException {
        List<Integer> s = new ArrayList<>(numberPoints);
        List<Integer> f = new ArrayList<>(numberPoints);
        int[] A = new int[numberPoints];

        try (FileWriter output = new FileWriter("recursive_data.csv")) {
            output.write("i,TimeRecursive\n");

            initializeArrays(s, f, numberPoints);

            for (int i = 1; i <= numberPoints; i++) {
                double timeRecursive = 0;

                for (int j = 0; j < numberRuns; j++) {
                    Arrays.fill(A, 0);

                    long start = System.nanoTime();
                    selectActivities(s, f, A, 0, i - 1);
                    long end = System.nanoTime();
                    timeRecursive += (end - start);
                }

                double avgTimeRecursive = timeRecursive / numberRuns;
                output.write(i + "," + avgTimeRecursive + "\n");
            }
        }
    }
	// This should match the input from the starting and start times from the example.
    public static void main(String[] args) {
        List<Integer> exampleS = Arrays.asList(0, 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12);
        List<Integer> exampleF = Arrays.asList(0, 4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16);
        int[] exampleA = new int[exampleS.size()];

        selectActivities(exampleS, exampleF, exampleA, 0, exampleS.size() - 1);

        // Output the selected activities for the original example
        System.out.print("Selected activities (Recursive): ");
        for (int i = 1; i < exampleA.length; i++) {
            if (exampleA[i] == 1) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        // Study overhead
        int numberPoints = 200;
        int numberRuns = 200;

        try {
            studyOverhead(numberPoints, numberRuns);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}