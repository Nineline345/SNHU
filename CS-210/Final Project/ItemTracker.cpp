/*
* CS-210 7-3 Project Three
*
* Author: Hunter Goodwin
*
* Date: April 20, 2025
*/

#include "ItemTracker.h"
#include <cctype>
#include <fstream>
#include <iostream>
#include <iomanip>
#include <algorithm>

// Converts a string to lowercase
static std::string toLowerCase(const std::string& str) {
    std::string lowerStr = str;
    std::transform(
        lowerStr.begin(),
        lowerStr.end(),
        lowerStr.begin(),
        [](unsigned char c) { return static_cast<char>(std::tolower(c)); }
    );
    return lowerStr;
}

// Reads item data from a file and updates the frequency map.
void ItemTracker::loadDataFromFile(const std::string& filename) {
    std::ifstream inFile(filename);
    std::string item;
    if (inFile.is_open()) {
        while (inFile >> item) {
            itemsFrequency[toLowerCase(item)]++;
        }
        inFile.close();
    }
    else {
        std::cerr << "Error opening file: " << filename << "\n";
    }
}

// Writes the frequency map to a backup file.
void ItemTracker::saveDataToFile(const std::string& filename) {
    std::ofstream outFile(filename);
    if (outFile.is_open()) {
        for (const auto& pair : itemsFrequency) {
            outFile << pair.first << " " << pair.second << "\n";
        }
        outFile.close();
    }
    else {
        std::cerr << "Error opening file: " << filename << "\n";
    }
}

// Retrieves the frequency of a specific item.
int ItemTracker::getItemFrequency(const std::string& item) const {
    auto it = itemsFrequency.find(toLowerCase(item));
    if (it != itemsFrequency.end()) {
        return it->second;
    }
    return 0;
}

// Displays all items and their frequencies.
void ItemTracker::displayAllFrequencies() const {
    std::cout << "Item Frequencies:\n";
    for (const auto& pair : itemsFrequency) {
        std::cout << pair.first << " " << pair.second << "\n";
    }
}

// Displays a histogram of item frequencies.
void ItemTracker::displayHistogram() const {
    std::cout << "Histogram:\n";
    for (const auto& pair : itemsFrequency) {
        std::cout << pair.first << " " << std::string(pair.second, '*') << "\n";
    }
}