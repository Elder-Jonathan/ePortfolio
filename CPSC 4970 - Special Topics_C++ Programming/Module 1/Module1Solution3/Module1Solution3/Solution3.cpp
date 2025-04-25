// Jonathan Elder
// CPSC 4970 C++ Programming
// Module 1 Solution 3

#include <iostream>
#include <iomanip>

using namespace std;

int main()
{
    const double YEN_PER_DOLLAR = 154.98;
    const double EURO_PER_DOLLAR = 0.92;

    double dollarsEntered, dollarsInYen, dollarsInEuros;
    
    cout << "Simple Currency Calculator" << endl;
    cout << "--------------------------\n" << endl;
    cout << "Enter the amount of American Dollars you would like to preview in Yen and Euros: ";
    cin >> dollarsEntered;

    dollarsInYen = dollarsEntered * YEN_PER_DOLLAR;
    dollarsInEuros = dollarsEntered * EURO_PER_DOLLAR;

    cout << endl;
    cout << fixed << setprecision(2);
    cout << "$" << dollarsEntered << " dollars in the currency \"Yen\" would be " << dollarsInYen << " yen." << endl;
    cout << "The current exchange rate as of 5/16/2024 for dollars to yen is: " << YEN_PER_DOLLAR << "." << endl;
    cout << endl;
    cout << "$" << dollarsEntered << " dollars in the currency \"Euros\" would be " << dollarsInEuros << " euros." << endl;
    cout << "The current exchange rate as of 5/16/2024 for dollars to euros is: " << EURO_PER_DOLLAR << "." << endl;
    cout << endl;

    return 0;
}