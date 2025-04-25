// Jonathan Elder
// CPSC 4970 C++ Programming
// Module 1 Solution 2

#include <iostream>

using namespace std;

int main()
{
	cout << "This is a simple demonstration representing how many bytes each data type takes up on our Cyborg supercomputer's memory.\n" << endl;

	cout << "The size of a \"char\" datatype in bytes is " << sizeof(char) << " total byte." << endl;
	cout << "The size of a \"int\" datatype in bytes is " << sizeof(int) << " total bytes." << endl;
	cout << "The size of a \"float\" datatype in bytes is " << sizeof(float) << " total bytes." << endl;
	cout << "The size of a \"double\" datatype in bytes is " << sizeof(double) << " total bytes." << endl;

	return 0;
}