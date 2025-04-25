// Jonathan Elder
// CPSC 4970 C++ Programming
// Module 2 Solution 7

#include <iostream>
#include <sstream>

using namespace std;

double max(double number1, double number2) {
    return (number1 > number2) ? number1 : number2;
}

// Overloaded version.
double max(double number1, double number2, double number3) {
    return max(max(number1, number2), number3);
}

int main() {
    string input;
    double number1, number2, number3;

    while (true) {
        cout << "Enter either 2 or 3 values separated by spaces: ";
        getline(cin, input);
        stringstream ss(input);

        if (ss >> number1 && ss >> number2) {
            if (ss >> number3) {
                cout << "You entered 3 different values" << endl;
                cout << "The max value of the given numbers is: " << max(number1, number2, number3) << endl;
                break; // Exit the loop if input is valid
            }
            else if (ss.eof()) {
                cout << "You entered only 2 values" << endl;
                cout << "The max value of the given numbers is: " << max(number1, number2) << endl;
                break; // Exit the loop if input is valid
            }
        }

        // Display error message if input is invalid and clear the stringstream object.
        ss.clear();
        cout << "Invalid entry!! There should be either 2 or 3 values ONLY separated by a space. Try again!!" << endl;
    }

    return 0;
}