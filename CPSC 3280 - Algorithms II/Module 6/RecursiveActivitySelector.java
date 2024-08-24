// Jonathan Elder
// CPSC 3280 Algorithms 2
// Programming Assignment 3
// RecursiveActivitySelector.java

import java.util.Arrays;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RecursiveActivitySelector {
    public static void selectActivities(List<Integer> s, List<Integer> f, int[] A, int k, int n) {
        int m = k + 1;
        while (m <= n && s.get(m) < f.get(k)) {
            m++;
        }
        if (m <= n) {
            A[m] = 1; // include activity m in set A
            selectActivities(s, f, A, m, n);
        }
    }

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

    public static void studyOverhead(int numberPoints, int numberRuns) throws IOException {
        List<Integer> s = new ArrayList<>(numberPoints);
        List<Integer> f = new ArrayList<>(numberPoints);
        int[] A = new int[numberPoints];

        try (FileWriter output = new FileWriter("data_recursive.csv")) {
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

    public static void main(String[] args) {
        int numberPoints = 100; // Adjust based on your machine's capability
        int numberRuns = 100;   // Adjust based on your machine's capability

        try {
            studyOverhead(numberPoints, numberRuns);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}