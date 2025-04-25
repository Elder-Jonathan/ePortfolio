// Jonathan Elder
// CPSC 4979 C++ Programming
// Module 2 Solution 3

#include <iostream>
using namespace std;

int main() {
    while (true) {
        int toothpicks = 23;
        int playerMove, aiMove;
        char playAgain;

        cout << "Welcome to the game of 23!\n" << endl;
        cout << "---------------------------" << endl;

        while (true) {
            cout << "Would you like to start a new game? (y/n): ";
            cin >> playAgain;
            if (playAgain == 'y' || playAgain == 'Y' || playAgain == 'n' || playAgain == 'N') {
                break;
            }
            else {
                cout << "Not a valid input. Please enter a single char value 'y' or 'n'." << endl;
            }
        }

        if (playAgain == 'n' || playAgain == 'N') {
            cout << "Thank you for playing! Goodbye!" << endl;
            break;
        }

        while (toothpicks > 0) {
            cout << "Toothpicks remaining: " << toothpicks << endl;
            cout << "Your turn. How many toothpicks do you want to withdraw? (1, 2, or 3): ";
            cin >> playerMove;

            // Validate input of user.
            while (playerMove < 1 || playerMove > 3 || playerMove > toothpicks) {
                cout << "Not a valid move! Please enter a number between 1-3,"
                     << "that does not exceed the number of remaining toothpicks: ";
                cin >> playerMove;
            }

            toothpicks -= playerMove;

            if (toothpicks == 0) {
                cout << "You were forced to take the last toothpick. You lose!" << endl;
                break;
            }

            // Computer's decision structure during turn.
            if (toothpicks > 4) {
                aiMove = 4 - playerMove;
            }
            else if (toothpicks >= 2 && toothpicks <= 4) {
                aiMove = toothpicks - 1;
            }
            else {
                aiMove = 1;
            }

            cout << "The computer takes " << aiMove << " toothpicks from the pile.\n" << endl;
            toothpicks -= aiMove;

            if (toothpicks == 0) {
                cout << "The computer has taken the last toothpick. You win!" << endl;
                break;
            }
        }

        while (true) {
            cout << "Would you like to play another game? (y/n): \n";
            cin >> playAgain;
            if (playAgain == 'y' || playAgain == 'Y' || playAgain == 'n' || playAgain == 'N') {
                break;
            }
            else {
                cout << "Not a valid input. Please a single char value 'y' or 'n'." << endl;
            }
        }

        if (playAgain == 'n' || playAgain == 'N') {
            cout << "Goodbye!" << endl;
            break;
        }
    }

    return 0;
}