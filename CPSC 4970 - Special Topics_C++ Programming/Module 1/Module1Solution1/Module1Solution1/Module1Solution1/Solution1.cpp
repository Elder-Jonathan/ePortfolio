// Jonathan Elder
// CPSC 4970 C++ Programming
// Module 1 Solution 1

#include <iostream>
#include <string>
#include <iomanip>

using namespace std;

int main()
{
	string month1, month2, month3 = "";
	double month1Rainfall, month2Rainfall, month3Rainfall, total, average;

	cout << "\"A simple program to calculate the average rainfall over a given three month span.\"\n";
	cout << endl;
	cout << "Enter the name of the first month: ";
	cin >> month1;
	cout << "How much rainfall was there in " << month1 << "? (in inches) : ";
	cin >> month1Rainfall;
	cout << endl;
	cout << "Enter the name of the second month: ";
	cin >> month2;
	cout << "How much rainfall was there in " << month2 << "? (in inches) : ";
	cin >> month2Rainfall;
	cout << endl;
	cout << "Enter the name of the third month: ";
	cin >> month3;
	cout << "How much rainfall was there in " << month3 << "? (in inches) : ";
	cin >> month3Rainfall;

	total = month1Rainfall + month2Rainfall + month3Rainfall;
	average = total / 3;
	cout << fixed << setprecision(2);
	cout << endl;
	cout << "The average rainfall for " << month1 << ", " << month2 << ", and " << month3 << " is " << average << " inches." << endl;


	return 0;
}
