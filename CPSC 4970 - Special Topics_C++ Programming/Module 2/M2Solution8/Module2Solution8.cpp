// Jonathan Elder
// CPSC 4970 C++ Programming
// Module 2 Solution 8

#include <iostream>

using namespace std;

// Function Prototypes to be used in the main function at the bottom.
void getJudgeData(double&);
void calcScore(double[], int);
double findHighest(double[], int);
double findLowest(double[], int);

void getJudgeData(double& score) {
    do {
        cout << "Enter a judge's score more than 0 and no more than 10: ";
        cin >> score;

        if (score < 0 || score > 10) {
            cout << "Not a valid score. Please enter a value above 0 no more than 10. Decimal point is allowed." << endl;
        }
    } while (score < 0 || score > 10);
}

void calcScore(double scores[], int size) {
    double lowest = findLowest(scores, size);
    double highest = findHighest(scores, size);
    double sum = 0.0;
    int count = 0;

    for (int i = 0; i < size; i++) {
        if (scores[i] != lowest && scores[i] != highest) {
            sum += scores[i];
            count++;
        }
    }

    double average = sum / count;

    // Output the highest and lowest scores that were dropped
    cout << endl;
    cout << "The lowest score that was dropped: " << lowest << endl;
    cout << "The highest score that was dropped: " << highest << endl;
    cout << endl;
    // Output the final average score
    cout << "The performer's final score is: " << average << endl;
}

double findHighest(double scores[], int size) {
    double highest = scores[0];
    for (int i = 1; i < size; i++) {
        if (scores[i] > highest) {
            highest = scores[i];
        }
    }
    return highest;
}

double findLowest(double scores[], int size) {
    double lowest = scores[0];
    for (int i = 1; i < size; i++) {
        if (scores[i] < lowest) {
            lowest = scores[i];
        }
    }
    return lowest;
}

int main() {
    const int NUM_JUDGES = 5;
    double scores[NUM_JUDGES];

    cout << "\nA simple program that calculates a performer's judges scores.\n"
        << "--------------------------------------------------\n" << endl;

    // This function is called once per judge.
    for (int i = 0; i < NUM_JUDGES; i++) {
        getJudgeData(scores[i]);
    }

    // As per the description for calcScore.
    calcScore(scores, NUM_JUDGES);

    return 0;
}

