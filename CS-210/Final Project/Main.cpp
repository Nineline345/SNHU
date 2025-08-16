/*
* CS-210 7-3 Project Three
*
* Author: Hunter Goodwin
*
* Date: April 20, 2025
*/


#include <iostream>
#include <limits>
#include "ItemTracker.h"

void displayMenu() {
    std::cout << "==============================\n";
    std::cout << "Welcome to Corner Grocer!\n";
    std::cout << "==============================\n";
    std::cout << "1. Search for an Item Frequency\n";
    std::cout << "2. Display Frequencies of All Items\n";
    std::cout << "3. Display Histogram of Frequencies\n";
    std::cout << "4. Exit\n";
    std::cout << "==============================\n";
    std::cout << "Enter your choice: ";
}

int main() {
    ItemTracker tracker;
    tracker.loadDataFromFile("CS210_Project_Three_Input_File.txt");

    int choice = 0;
    do {
        displayMenu();
        std::cin >> choice;

        // Input validation for non-integer and invalid inputs
        if (std::cin.fail()) {
            std::cin.clear(); // Clear the fail state
            std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n'); // Discard invalid input
            std::cout << "Invalid input. Please enter an integer between 1 and 4.\n";
            continue;
        }

        switch (choice) {
        case 1: {
            std::string item;
            std::cout << "Enter the item name: ";
            std::cin >> item;
            int frequency = tracker.getItemFrequency(item);
            if (frequency > 0) {
                std::cout << item << " was purchased " << frequency << " times.\n";
            }
            else {
                std::cout << item << " was not found in the records.\n";
            }
            break;
        }
        case 2:
            tracker.displayAllFrequencies();
            break;
        case 3:
            tracker.displayHistogram();
            break;
        case 4:
            std::cout << "Exiting the program. Goodbye!\n";
            break;
        default:
            std::cout << "Invalid selection. Please enter a number between 1 and 4.\n";
            break;
        }
        std::cout << "\n";
    } while (choice != 4);

    tracker.saveDataToFile("frequency.dat");
    return 0;
}