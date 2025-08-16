/*
* CS-210 7-3 Project Three
*
* Author: Hunter Goodwin
*
* Date: April 20, 2025
*/

#ifndef ITEMTRACKER_H
#define ITEMTRACKER_H

#include <string>
#include <map>

// This class keeps track of how often each item is purchased.
// It can load data from a file, save it, and show the frequencies in different ways.
class ItemTracker {
private:
    std::map<std::string, int> itemsFrequency;
public:
    void loadDataFromFile(const std::string& filename);
    void saveDataToFile(const std::string& filename);
    int getItemFrequency(const std::string& item) const;
    void displayAllFrequencies() const;
    void displayHistogram() const;
};

#endif