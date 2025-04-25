// Jonathan Elder
// CPSC 4970 - C++ Programming
// Module 2 Solution 1

#include <iostream>
using namespace std;

int main()
{
    double weight, distance, rate, totalCharges;

    cout << "The Fast Freight Shipping Company\n" << endl;

    // Displays shipping rates for simple representation.
    cout << "\nWeight of Package (in Kilograms)     Rate per 500 Miles Shipped\n";
    cout << "2 Kg or less                         $1.10\n";
    cout << "Over 2 Kg but not more than 6 Kg     $2.20\n";
    cout << "Over 6 Kg but not more than 10 Kg    $3.70\n";
    cout << "Over 10 Kg but not more than 20 Kg   $4.80\n";
    cout << endl;

    // Validates the weight input before proceding.
    while (true) {
        cout << "What is the weight of the package (in kilograms) you would like to send: ";
        cin >> weight;

        if (weight > 0 && weight <= 20) {
            break;
        }
        else {
            cout << "\nPlease enter a valid weight. Total must not be more than 20 kilograms and also more than zero.\n";
        }
    }

    // Validates the distance input before proceding.
    while (true) {
        cout << "Enter the total shipping distance for the package (in miles): ";
        cin >> distance;

        if (distance >= 10 && distance <= 3000) {
            break;
        }
        else {
            cout << "\nPlease enter a traveling distance that is more than 10 miles and less than 3000 total miles.\n";
        }
    }

    // Determines the rate based on the weight of the package being sent.
    if (weight <= 2) {
        rate = 1.10;
    }
    else if (weight <= 6) {
        rate = 2.20;
    }
    else if (weight <= 10) {
        rate = 3.70;
    }
    else {
        rate = 4.80;
    }

    // Calculates the total charges and prints a toString version of what the total charges would be.
    totalCharges = (distance / 500) * rate;
    cout << "The total shipping charges will be: $" << totalCharges << endl;

    return 0;
}