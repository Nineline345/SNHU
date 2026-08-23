### How can I ensure that my code, program, or software is functional and secure?

I can make sure my code is functional by testing it against the actual requirements instead of just assuming it works because it runs. For this project, that meant using JUnit tests to check valid inputs, invalid inputs, required fields, length limits, duplicate IDs, and delete or update behavior. Security also starts with not allowing bad data into the program in the first place. For example, the contact service rejected null values, phone numbers that were not exactly 10 digits, and fields that were too long.

### How do I interpret user needs and incorporate them into a program?

I interpret user needs by breaking the requirements down into specific rules the program has to follow. In this project, the requirements explained what each object needed to store and what restrictions each field had. I used that to decide what fields, constructors, setters, and service methods were needed. I also used the requirements as a checklist for the unit tests, so I could show that the program matched what the customer asked for.

### How do I approach designing software?

I approach software design by breaking the problem into smaller pieces instead of trying to build everything at once. For this project, that meant separating the object classes from the service classes. The object classes handled the data and validation rules, while the service classes handled adding, deleting, and updating objects in memory. I also tried to avoid adding extra features that were not part of the requirements, because that would make the program harder to test and maintain.
