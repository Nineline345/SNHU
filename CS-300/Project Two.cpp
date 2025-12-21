//============================================================================
// Name        : Project Two.cpp
// Author      : Hunter Goodwin
// Version     : 1.0
// Description : Computer Science department at ABCU Course Sorting Algorithm
//============================================================================

#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <vector>
#include <algorithm>
#include <cctype>
#include <limits>

using std::cin;
using std::cout;
using std::endl;
using std::getline;
using std::ifstream;
using std::string;
using std::stringstream;
using std::vector;

//============================================================================
// Data Structures
//============================================================================

struct Course {
    string courseNumber;
    string name;
    vector<string> prerequisites;
};

class HashTable {
public:
    explicit HashTable(size_t size = 37) : tableSize(size), buckets(size) {}

    // Insert or update a course by courseNumber
    void Insert(const Course& course) {
        size_t index = Hash(course.courseNumber);
        // If course already exists, update it
        for (Course& c : buckets[index]) {
            if (ToUpper(c.courseNumber) == ToUpper(course.courseNumber)) {
                c = course;
                return;
            }
        }
        // Else, add new entry
        buckets[index].push_back(course);
    }

    // Retrieve a course by courseNumber; return true if found
    bool GetCourse(const string& courseNumber, Course& outCourse) const {
        size_t index = Hash(courseNumber);
        string keyUpper = ToUpper(courseNumber);
        for (const Course& c : buckets[index]) {
            if (ToUpper(c.courseNumber) == keyUpper) {
                outCourse = c;
                return true;
            }
        }
        return false;
    }

    // Return all courses stored in the table
    vector<Course> GetAllCourses() const {
        vector<Course> all;
        for (const auto& bucket : buckets) {
            for (const Course& c : bucket) {
                all.push_back(c);
            }
        }
        return all;
    }

    // Remove all stored courses
    void Clear() {
        for (auto& bucket : buckets) {
            bucket.clear();
        }
    }

private:
    size_t tableSize;
    vector<vector<Course>> buckets;

    // Hash Function for courseNumber keys
    size_t Hash(const string& key) const {
        unsigned long hash = 0;
        for (char ch : key) {
            hash = hash * 31u + static_cast<unsigned long>(std::toupper(static_cast<unsigned char>(ch)));
        }
        return hash % tableSize;
    }

    // Case-insensitive helper
    static string ToUpper(const string& s) {
        string result = s;
        for (char& ch : result) {
            ch = static_cast<char>(std::toupper(static_cast<unsigned char>(ch)));
        }
        return result;
    }
};

//============================================================================
// Helper Functions
//============================================================================

// Trim whitespace from both ends of a string
string Trim(const string& s) {
    size_t start = s.find_first_not_of(" \t\r\n");
    if (start == string::npos) {
        return "";
    }
    size_t end = s.find_last_not_of(" \t\r\n");
    return s.substr(start, end - start + 1);
}

// Convert string to uppercase
string ToUpper(const string& s) {
    string result = s;
    for (char& ch : result) {
        ch = static_cast<char>(std::toupper(static_cast<unsigned char>(ch)));
    }
    return result;
}

// Load course data from CSV file into Hash Table
void LoadDataStructure(HashTable& table, bool& dataLoaded) {
    cout << "Enter the file name to load: ";

    string filename;
    std::getline(cin, filename);
    filename = Trim(filename);


    ifstream inFile(filename);
    if (!inFile.is_open()) {
        cout << "Error: Could not open file \"" << filename << "\"." << endl;
        dataLoaded = false;
        return;
    }

    table.Clear();

    string line;
    while (getline(inFile, line)) {
        line = Trim(line);
        if (line.empty()) {
            continue;
        }

        stringstream ss(line);
        string token;
        vector<string> tokens;

        // Split line on commas
        while (getline(ss, token, ',')) {
            tokens.push_back(Trim(token));
        }

        // Need at least course number and name
        if (tokens.size() < 2) {
            cout << "Format error: line has fewer than 2 fields: " << line << endl;
            continue;
        }

        Course course;
        course.courseNumber = tokens[0];
        course.name = tokens[1];

        // Remaining tokens are prerequisite course numbers
        for (size_t i = 2; i < tokens.size(); ++i) {
            if (!tokens[i].empty()) {
                course.prerequisites.push_back(tokens[i]);
            }
        }

        table.Insert(course);
    }

    inFile.close();
    dataLoaded = true;
    cout << "Data loaded successfully from \"" << filename << "\"." << endl;
}

// Print list of all courses in alphanumeric order by courseNumber
void PrintCourseList(const HashTable& table) {
    vector<Course> courses = table.GetAllCourses();

    if (courses.empty()) {
        cout << "No course data is loaded." << endl;
        return;
    }

    // Sort by courseNumber
    std::sort(courses.begin(), courses.end(),
              [](const Course& a, const Course& b) {
                  return a.courseNumber < b.courseNumber;
              });

    cout << "Here is a sample schedule:" << endl;
    for (const Course& c : courses) {
        cout << c.courseNumber << ", " << c.name << endl;
    }
}

// Print information for a single course
void PrintCourseInformation(const HashTable& table) {
    cout << "What course do you want to know about? ";
    string courseNumber;
    cin >> courseNumber;

    courseNumber = ToUpper(courseNumber);

    Course course;
    if (!table.GetCourse(courseNumber, course)) {
        cout << "Course " << courseNumber << " not found." << endl;
        return;
    }

    cout << course.courseNumber << ", " << course.name << endl;

    if (course.prerequisites.empty()) {
        cout << "Prerequisites: None" << endl;
        return;
    }

    cout << "Prerequisites:" << endl;

    // For each prerequisite number, look up its title in the hash table
    for (const string& prereqNum : course.prerequisites) {
        Course prereqCourse;
        if (table.GetCourse(prereqNum, prereqCourse)) {
            cout << "  " << prereqCourse.courseNumber << ", " << prereqCourse.name << endl;
        } else {
            // number will still show even if course cannot be found
            cout << "  " << prereqNum << endl;
        }
    }
}


//============================================================================
// Main Menu
//============================================================================

int main() {
    HashTable courseTable;
    bool dataLoaded = false;
    int choice = 0;

    cout << "Welcome to the course planner." << endl;

    while (true) {
        cout << "1. Load Data Structure." << endl;
        cout << "2. Print Course List." << endl;
        cout << "3. Print Course." << endl;
        cout << "9. Exit" << endl;
        cout << "What would you like to do? ";

        if (!(cin >> choice)) {
            // For non-numeric inputs
            cin.clear();
            cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
            cout << "Invalid input. Please enter a number." << endl;
            continue;
        }

        cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

        switch (choice) {
        case 1:
            LoadDataStructure(courseTable, dataLoaded);
            break;

        case 2:
            if (!dataLoaded) {
                cout << "Please load the data structure first." << endl;
            } else {
                PrintCourseList(courseTable);
            }
            break;

        case 3:
            if (!dataLoaded) {
                cout << "Please load the data structure first." << endl;
            } else {
                PrintCourseInformation(courseTable);
            }
            break;

        case 9:
            cout << "Thank you for using the ABCU Computer Science department's course planner!" << endl;
            return 0;

        default:
            cout << choice << " is not a valid option." << endl;
            break;
        }
    }
}
