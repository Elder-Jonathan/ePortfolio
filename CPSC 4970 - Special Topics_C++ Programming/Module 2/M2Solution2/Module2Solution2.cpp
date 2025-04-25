// Jonathan Elder
// CPSC 4970 C++ Programming
// Module 2 Solution 2

#include <iostream>
using namespace std;

const double PI = 3.141593;

int main() {

    int choice;
    double radius, length, width, base, height, area;

    do {
        cout << "\nGeometry Calculator" << endl;
        cout << "1. Calculate the Area of a Circle" << endl;
        cout << "2. Calculate the Area of a Rectangle" << endl;
        cout << "3. Calculate the Area of a Triangle" << endl;
        cout << "4. Quit" << endl;
        cout << "Enter your choice (1-4): ";
        cin >> choice;

        switch (choice) {
        case 1:
            cout << "Enter the radius of the circle you would like to calculate the area for: ";
            cin >> radius;
            area = PI * radius * radius;
            cout << "The area of the circle is: " << area << " given a radius of " << radius << "." << endl;
            break;

        case 2:
            cout << "Enter the length and width of the rectangle.\n";
            cout << "Length: ";
            cin >> length;
            cout << "Width: ";
            cin >> width;
            area = length * width;
            cout << "The area of the rectangle is: " << area << endl;
            break;

        case 3:
            cout << "Enter the base and height of the triangle: \n" << endl;
            cout << "Base: ";
            cin >> base;
            cout << "Height: ";
            cin >> height;
            area = 0.5 * base * height;
            cout << "\nWith a base of " << base << " and a height of " << height << ", the area of the triangle is: " << area << endl;
            break;

        case 4:
            cout << "Exiting the program now..." << endl;
            break;

        default:
            cout << "Not a valid choice. Please enter a number between 1-4 and press the enter key." << endl;
            break;
        }
    } while (choice != 4);

    return 0;
}