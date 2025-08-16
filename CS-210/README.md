# CS-210-7-3

**Summarize the project and what problem it was solving.**
The Corner Grocer Item-Tracking Program reads a text file of grocery purchases, tallies how many times each item appears, and then presents that information via a simple menu. Then, users can look up a single item’s frequency, list all frequencies, or view a text-based histogram. This was done to solve the store’s need to analyze purchasing patterns so they can rearrange produce or departments based on popularity.

**What did you do particularly well?**
I believe I did a good job at input validation by checking for case-sensitivity and non-integer/out-of-range menu choices.

**Where could you enhance your code? How would these improvements make your code more efficient, secure, and so on?**
In hindsight, I should've also added a means to validate singular/plural inputs so that "Onions" or "Onion" would both return correctly.

**Which pieces of the code did you find most challenging to write, and how did you overcome this? What tools or resources are you adding to your support network?**
Preventing an infinite loop when the user types “q” or any non-integer took some debugging with cin.clear() and ignore(). I relied heavily on online references (stack overflow and W3Schools) and my IDE’s debugger to step through fail states.

**What skills from this project will be particularly transferable to other projects or course work?**
Reading, writing, and validating streams will certainly be useful and required for future projects. Input-validation loops and error-checks for edge-cases will also be highly valuable.

**How did you make this program maintainable, readable, and adaptable?**
This was achieved with modular design like keeping all tracking logic in ItemTracker, Consistent Naming and comments, Header guards, and standard containers.
