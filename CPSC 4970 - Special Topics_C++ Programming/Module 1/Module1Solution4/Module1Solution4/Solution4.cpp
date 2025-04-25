// Jonathan Elder
// CPSC 4970 C++ Programming
// Module 1 Solution 4

#include <iostream>
#include <string>
#include <iomanip>

using namespace std;

int main()
{
    const double STATE_TAX = 0.04;
    const double COUNTY_TAX = 0.02;

    double totalTax = STATE_TAX + COUNTY_TAX;
    string month;
    int year;
    double totalCollected, salesForMonth, countySalesTax, stateSalesTax, totalSalesTax;

    // Input month, year, and total amount collected in total for a given month. The total entered will be broken down into total sales plus totalTax.
    cout << "For what month are you entering in the Sales Tax Report: ";
    getline(cin, month);
    cout << "For what year are you entering in the Sales Tax Report: ";
    cin >> year;
    cout << "Enter the total amount collected for " << month << " " << year << " (including all applicable taxes) : $";
    cin >> totalCollected;

    // Calculates product sales for the month based on how it was described we should do it in the assignment description.
    salesForMonth = totalCollected / (1 + totalTax);

    // Calculates county and state sales tax separately for representing in the report.
    countySalesTax = salesForMonth * COUNTY_TAX;
    stateSalesTax = salesForMonth * STATE_TAX;

    // Calculates total sales tax for the month by multiplying sales for the month by the total tax rate.
    totalSalesTax = salesForMonth * totalTax;

    // Output of the report that should match close enough for what was represented in the hw assignment.
    cout << fixed << setprecision(2);
    cout << endl;
    cout << "\"Monthly Sales Tax Report\"\n";
    cout << "--------------------" << endl;
    cout << "Month: " << month << endl;
    cout << "Year: " << year << endl;
    cout << "--------------------" << endl;
    cout << "Total Collected:  $ " << totalCollected << endl;
    cout << "Total Sales:      $ " << salesForMonth << endl;
    cout << "County Sales Tax: $ " << countySalesTax << endl;
    cout << "State Sales Tax:  $ " << stateSalesTax << endl;
    cout << "Total Sales Tax:  $ " << totalSalesTax << endl;
    cout << endl;

    return 0;
}