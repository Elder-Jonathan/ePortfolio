// Jonathan Elder
// CPSC 4970 C++ Programming
// Module 2 Solution 4

#include <iostream>

using namespace std;

int main() {

    int n;
    double pi = 0.0;

    cout << "Enter the number of terms for the approximation of pi: ";
    cin >> n;

    // Loops over n times for the term variable.
    for (int i = 0; i < n; i++) {
        double term = (i % 2 == 0) ? 1.0 : -1.0;
        pi += term / (2.0 * i + 1);
    }

    pi *= 4.0;

    cout << "The approximate value of pi after " << n << " terms is: " << pi << "." << endl;

    return 0;
}