// Jonathan Elder
// CPSC 4970 C++ Programming
// Module 2 Solution 9

#include <iostream>

using namespace std;

// Function to check if a number contains a certain digit.
bool containsDigit(int number, int digit) {
    if (number == 0 && digit == 0) return true;
    while (number > 0) {
        if (number % 10 == digit)
            return true;
        number /= 10;
    }
    return false;
}

// Function to find the next smallest and next largest temperatures to display.
void findTemperatures(int desiredTemp) {
    int nextSmallest = desiredTemp - 1;
    int nextLargest = desiredTemp + 1;

    while (nextSmallest >= 0) {
        if (!containsDigit(nextSmallest, 1) && !containsDigit(nextSmallest, 4) && !containsDigit(nextSmallest, 7))
            break;
        nextSmallest--;
    }

    while (true) {
        if (!containsDigit(nextLargest, 1) && !containsDigit(nextLargest, 4) && !containsDigit(nextLargest, 7))
            break;
        nextLargest++;
    }

    cout << "The next smallest temperature is " << nextSmallest << endl;
    cout << "The next largest temperature is " << nextLargest << endl;
}

int main() {
    int tempDesired;

    cout << "A program for a broken Oven Keypad..." << endl;
    cout << "-------------------------------------\n" << endl;

    // Input validation loop
    while (true) {
        cout << "Enter desired temperature between zero and 999: ";
        cin >> tempDesired;

        if (tempDesired >= 0 && tempDesired <= 999) {
            break;
        }
        else {
            cout << "Not a valid temperature. Please enter a temperature between 0 and 999." << endl;
        }
    }

    // If the number does not contain any of the broken keypad row numbers.
    if (!containsDigit(tempDesired, 1) && !containsDigit(tempDesired, 4) && !containsDigit(tempDesired, 7)) {
        cout << "The desired temperature is " << tempDesired << endl;
    }
    else {

        findTemperatures(tempDesired);

    }

    return 0;
}