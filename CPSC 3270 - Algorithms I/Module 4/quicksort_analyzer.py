import random
import time
import csv
import math

# Jonathan Elder
# CPSC 3270 Programming Assignment 2
# quicksort_analyzer.py
class QuickSortAnalyzer:
    def __init__(self):
        self.data_file = 'data.csv'  # Filename for storing collected data
        self.max_val = 1000  # Maximum value for random elements in arrays
        self.L = 250000  # Maximum length of arrays

    def partition(self, arr, p, r):
        """
        Perform partitioning step of the QuickSort algorithm.

        :param arr: List to be partitioned.
        :param p: Starting index of the partition.
        :param r: Ending index of the partition.
        :return: Index of current p element after partitioning.
        """
        x = arr[r]  # Pivot element
        i = p - 1  # Index of smaller element
        for j in range(p, r):
            if arr[j] <= x:
                i += 1
                arr[i], arr[j] = arr[j], arr[i]  # Swap elements
        arr[i + 1], arr[r] = arr[r], arr[i + 1]  # Swap pivot to correct position
        return i + 1

    def quicksort(self, arr, p, r):
        """
        Perform the QuickSort algorithm recursively.

        :param arr: List to be sorted.
        :param p: Starting index of the array/subarray.
        :param r: Ending index of the array/subarray.
        """
        if p < r:
            q = self.partition(arr, p, r)
            self.quicksort(arr, p, q - 1)  # Sort left subarray.
            self.quicksort(arr, q + 1, r)  # Sort right subarray.

    def collect_data(self):
        """
        Collect execution data for sorting arrays of different sizes using QuickSort.
        """
        print("Collecting data...")
        with open(self.data_file, mode='w') as file:
            writer = csv.writer(file)
            writer.writerow(['n', 'T(n)', 'T(n)/g_1(n)', 'T(n)/g_2(n)', 'T(n)/g_3(n)'])
            G = [random.randint(0, self.max_val) for _ in range(self.L)]
            for n in range(7500, self.L + 1, 2500):
                A = G[:n]
                start_time = time.time()  # Use time() instead of time_ns()
                self.quicksort(A, 0, len(A) - 1)
                end_time = time.time()  # Use time() instead of time_ns()
                execution_time = end_time - start_time

                g1 = 10 * math.sqrt(n)
                g2 = 2 * n ** 2
                g3 = 5 * n * math.log(n)

                writer.writerow([n, execution_time, execution_time / g1, execution_time / g2, execution_time / g3])
        print("Data collection completed.")

    def demo_sorting(self):
        """
        Demonstrate sorting of a random array with 10 elements using QuickSort.
        """
        print("Demonstrating sorting...")
        # Generate a random array with 10 elements
        original_array = [random.randint(0, 100) for _ in range(10)]

        # Print the original array
        print("Original array:", original_array)

        # Sort the array using QuickSort
        self.quicksort(original_array, 0, len(original_array) - 1)

        # Print the sorted array
        print("Sorted array:", original_array)
        print("Sorting demonstration completed.")


# This will create and save the usage data needed for the assignment/graphs.
analyzer = QuickSortAnalyzer()
analyzer.collect_data()
analyzer.demo_sorting()