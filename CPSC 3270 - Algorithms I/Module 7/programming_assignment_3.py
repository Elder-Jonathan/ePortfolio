# Jonathan Elder
# CPSC 3270 Algorithms 1
# Programming Assignment 3

from __future__ import print_function, division

import time
import random
import csv

def insertion_sort(arr):
    for j in range(1, len(arr)):
        key = arr[j]
        i = j - 1
        while i >= 0 and arr[i] > key:
            arr[i + 1] = arr[i]
            i -= 1
        arr[i + 1] = key

def quicksort(arr, low, high):
    if low < high:
        pi = partition(arr, low, high)
        quicksort(arr, low, pi - 1)
        quicksort(arr, pi + 1, high)

def partition(arr, low, high):
    pivot = arr[high]
    i = low - 1
    for j in range(low, high):
        if arr[j] < pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]
    arr[i + 1], arr[high] = arr[high], arr[i + 1]
    return i + 1

def generate_large_random_array(L, max_val=0x7ffffffe):
    return [random.randint(1, max_val) for _ in range(L)]

def measure_sort_time(func, arr):
    start = time.time()
    func(arr)  # Directly modify the array passed
    end = time.time()
    return (end - start) * 1000  # Convert seconds to milliseconds

def collect_data(L=200000):
    G = generate_large_random_array(L)
    results = []

    print("\nStarting data collection for larger array sizes...")
    for n in range(10000, L + 1, 5000):
        print("\nSorting array of size {0}. Please wait...".format(n))
        A = G[:n]
        t_insert = measure_sort_time(insertion_sort, A[:])
        t_quick = measure_sort_time(lambda arr: quicksort(arr, 0, len(arr) - 1), G[:n])
        results.append([n, t_insert, t_quick])
        print("Size {0}: Insertion Sort took {1:.2f} ms, QuickSort took {2:.2f} ms".format(n, t_insert, t_quick))

    with open('performance_data.csv', 'wb') as f:  # 'wb' for binary write mode in Python 2
        writer = csv.writer(f)
        writer.writerow(['Array Size', 'Insertion Sort Time (ms)', 'QuickSort Time (ms)'])
        writer.writerows(results)
    print("\nData collection complete. Check 'performance_data.csv' for results.")

if __name__ == "__main__":
    raw_input("Press Enter to randomly generate a 10 element array...")
    test_array = [random.randint(1, 100) for _ in range(10)]
    print("Random 10 element array: " + str(test_array))

    raw_input("Press Enter to sort the array using Insertion Sort...")
    insertion_sorted_array = test_array[:]  # Make a copy using slicing
    insertion_sort(insertion_sorted_array)
    print("Sorted by Insertion Sort: " + str(insertion_sorted_array))

    raw_input("Press Enter to sort the array using QuickSort...")
    quicksort_sorted_array = test_array[:]  # Make a copy using slicing
    quicksort(quicksort_sorted_array, 0, len(quicksort_sorted_array) - 1)
    print("Sorted by QuickSort: " + str(quicksort_sorted_array))

    raw_input("Press Enter to start collecting data for larger array values...")
    collect_data()