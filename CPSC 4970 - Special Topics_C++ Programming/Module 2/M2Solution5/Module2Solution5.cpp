// Jonathan Elder
// CPSC 4970 C++ Programming
// Module 2 Solution 5

#include <iostream>

using namespace std;

int main() {
    const int rows = 10;

    // Loop through the number of rows to print the left and right side of the pyramid.
    for (int i = 1; i <= rows; ++i) {
        
        for (int j = 1; j <= rows - i; ++j) {
            cout << " ";
        }

        // Adds '*' to the left side of the pyramid.
        for (int k = 1; k <= i; ++k) {
            cout << "*";
        }

        // Adds '*' to the right side of the pyramid.
        for (int k = i - 1; k >= 1; --k) {
            cout << "*";
        }

        // Makes sure to end each row with a new line char.
        cout << endl;
    }

    return 0;
}