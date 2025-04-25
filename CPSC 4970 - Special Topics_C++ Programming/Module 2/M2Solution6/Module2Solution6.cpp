// Jonathan Elder
// CPSC 4970 C++ Programming
// Module 2 Solution 6

#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

int main() {

    // This will make sure the random number is different at the start of the program each time.
    srand(time(0));
    int randomNumber = rand() % 100 + 1;
    int guess;
    int totalGuesses = 0;

    // It will continue to loop this section while the randomNumber and the user's current guess variables are different.
    cout << "Guess a number between 1 and 100: ";
    do {
        cin >> guess;
        totalGuesses++;
        if (guess > randomNumber) {
            cout << "Too high, try again!" << endl;
        }
        else if (guess < randomNumber) {
            cout << "Too low, try again!" << endl;
        }
        else {
            cout << "CONGRATULATIONS!!! You guessed the number correctly!" << endl;
        }
    } while (guess != randomNumber);

    // Effectively acting as the toString for the program.
    cout << "It took you " << totalGuesses << " total guesses before guessing the right number." << endl;

    return 0;
}