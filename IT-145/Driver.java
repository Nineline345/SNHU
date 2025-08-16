import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    /**
     * @Author Hunter Goodwin
     * @Verson 1.1
     * @Since 02/16/2025
     */
    // Lists to store Dog and Monkey
    private static ArrayList<Dog> dogList = new ArrayList<Dog>();
    private static ArrayList<Monkey> monkeyList = new ArrayList<Monkey>();
    // private static ArrayList<String> monkeySpecies = new ArrayList<String>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        // Initialize dogs and monkeys
        initializeDogList();
        initializeMonkeyList();
        initializeMonkeySpecies();

        // Menu loop for options and inputs
        do {
            displayMenu(); // Show menu options
            userInput = scanner.nextLine().trim();

            // Handle input and call methods
            switch (userInput.toUpperCase()) {
                case "NEW DOG":
                    intakeNewDog(scanner);
                    break;
                case "NEW MONKEY":
                    intakeNewMonkey(scanner);
                    break;
                case "RESERVE":
                    reserveAnimal(scanner);
                    break;
                case "PRINT DOGS":
                    printAnimals("dog");
                    break;
                case "PRINT MONKEYS":
                    printAnimals("monkey");
                    break;
                case "PRINT AVAILABLE":
                    printAnimals("available");
                    break;
                case "EXIT":
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid input. Please enter a valid option.");
            }
        } while (!userInput.equalsIgnoreCase("EXIT")); // Loop until user exits
        scanner.close(); // Close the scanner
    }

    // Displays menu options
    public static void displayMenu() {
        System.out.println("\n\n");
        System.out.println("\t\t\t\tGrazioso Salvare System Menu");
        System.out.println("[NEW DOG] Intake a new dog");
        System.out.println("[NEW MONKEY] Intake a new monkey");
        System.out.println("[RESERVE] Reserve an animal");
        System.out.println("[PRINT DOGS] Print list of all dogs");
        System.out.println("[PRINT MONKEYS] Print list of all monkeys");
        System.out.println("[PRINT AVAILABLE] Print list of all animals that are not reserved");
        System.out.println("[EXIT] Quit");
        System.out.println();
        System.out.print("Enter a menu selection: ");
    }

    // Test data for dogs
    public static void initializeDogList() {
        Dog dog1 = new Dog("Fido", "Greyhound", "male", "8", "44.2", "08-02-2022", "United States", "intake", false,
                "United States");
        Dog dog2 = new Dog("Roxy", "Pit Bull", "female", "3", "53.3", "02-15-2024", "United States", "Phase I", true,
                "United States");
        Dog dog3 = new Dog("Molly", "Chihuahua", "female", "11", "14.5", "12-12-2023", "United States", "Phase I", true,
                "United States");
        dogList.add(dog1);
        dogList.add(dog2);
        dogList.add(dog3);
    }

    // Test data for monkeys
    public static void initializeMonkeyList() {
        Monkey monkey1 = new Monkey("George", "Capuchin", "male", "2", "10.5", "01-10-2021", "Brazil", "intake", false,
                "Brazil", 15.0, 40.0, 20.0);
        Monkey monkey2 = new Monkey("Ozaru", "Marmoset", "female", "3", "12.0", "03-05-2020", "Colombia", "Phase I",
                false, "Colombia", 12.0, 35.0, 18.0);
        Monkey monkey3 = new Monkey("Bubbles", "Squirrel Monkey", "male", "4", "11.2", "07-07-2019", "Peru",
                "in service", true, "Peru", 14.0, 38.0, 19.0);
        monkeyList.add(monkey1);
        monkeyList.add(monkey2);
        monkeyList.add(monkey3);
    }

    // Test data for monkey species
    public static void initializeMonkeySpecies() {
        monkeySpecies.add("Capuchin");
        monkeySpecies.add("Marmoset");
        monkeySpecies.add("Squirrel Monkey");
    }

    // Intake a new dog and add to list
    public static void intakeNewDog(Scanner scanner) {
        System.out.println("Enter the dog's name:");
        String name = scanner.nextLine();
        for (Dog dog : dogList) {
            if (dog.getName().equalsIgnoreCase(name)) {
                System.out.println("This dog is already in our system.");
                return;
            }
        }
        // Dog attributes from user input
        System.out.println("What is the dog's breed:");
        String breed = scanner.nextLine();
        System.out.println("What is the dog's gender:");
        String gender = scanner.nextLine();
        System.out.println("What is the dog's age:");
        String age = scanner.nextLine();
        System.out.println("What is the dog's weight:");
        String weight = scanner.nextLine();
        System.out.println("What is the acquisition date:");
        String acquisitionDate = scanner.nextLine();
        System.out.println("What is the acquisition country:");
        String acquisitionCountry = scanner.nextLine();
        System.out.println("What is the training status:");
        String trainingStatus = scanner.nextLine();
        System.out.println("Is the dog reserved? (true/false):");
        boolean reserved = Boolean.parseBoolean(scanner.nextLine());
        System.out.println("What is the in-service country:");
        String inServiceCountry = scanner.nextLine();

        Dog newDog = new Dog(name, breed, gender, age, weight, acquisitionDate, acquisitionCountry, trainingStatus,
                reserved, inServiceCountry);
        dogList.add(newDog);
        System.out.println("New dog added successfully.");
    }

    // Intake a new monkey and add to list
    public static void intakeNewMonkey(Scanner scanner) {
        System.out.println("Enter the monkey's name:");
        String name = scanner.nextLine();
        for (Monkey monkey : monkeyList) {
            if (monkey.getName().equalsIgnoreCase(name)) {
                System.out.println("This monkey is already in our system.");
                return;
            }
        }
        // Monkey attributes from user input
        System.out.println("Enter the monkey's species:");
        String species = scanner.nextLine();
        if (!monkeySpecies.contains(species)) {
            System.out.println("This species is not in our list of supported species.");
            return;
        }
        System.out.println("Enter the monkey's gender:");
        String gender = scanner.nextLine();
        System.out.println("Enter the monkey's age:");
        String age = scanner.nextLine();
        System.out.println("Enter the monkey's weight:");
        String weight = scanner.nextLine();
        System.out.println("Enter the tail length:");
        double tailLength = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter the monkey's height:");
        double height = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter the monkey's body length:");
        double bodyLength = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter the acquisition date:");
        String acquisitionDate = scanner.nextLine();
        System.out.println("Enter the acquisition country:");
        String acquisitionCountry = scanner.nextLine();
        System.out.println("Enter the training status:");
        String trainingStatus = scanner.nextLine();
        System.out.println("Is the monkey reserved? (true/false):");
        boolean reserved = Boolean.parseBoolean(scanner.nextLine());
        System.out.println("Enter the in-service country:");
        String inServiceCountry = scanner.nextLine();

        Monkey newMonkey = new Monkey(name, species, gender, age, weight, acquisitionDate, acquisitionCountry,
                trainingStatus, reserved, inServiceCountry, tailLength, height, bodyLength);
        monkeyList.add(newMonkey);
        System.out.println("New monkey added successfully.");
    }

    // Reserve an animal
    public static void reserveAnimal(Scanner scanner) {
        System.out.println("Enter animal type: (Dog/Monkey)");
        String type = scanner.nextLine();
        if (!type.equals("dog") && !type.equals("monkey")) {
            System.out.println("Invalid input");
            return;
        }
        System.out.println("Enter animal service country: ");
        String serviceCountry = scanner.next().toLowerCase();
        if (type.equals("dog")) {
            for (int i = 0; i < dogList.size(); i++) {
                if (dogList.get(i).getInServiceCountry().equals(serviceCountry) && !dogList.get(i).getReserved()) {
                    System.out.println(dogList.get(i).toString() + " is now reserved.");
                    return;
                }
            }
        } else {
            for (int i = 0; i < monkeyList.size(); i++) {
                if (monkeyList.get(i).getInServiceCountry().equals(serviceCountry) && !monkeyList.get(i).getReserved()) {
                    System.out.println(monkeyList.get(i).getName() + " is now reserved.");
                    return;
                }
            }
        }
    }

    // Print animals based on the list parameters
    public static void printAnimals(String listType) {
        switch (listType.toLowerCase()) {
            case "dog":
                for (Dog dog : dogList) {
                    System.out.println(dog);
                }
                break;
            case "monkey":
                for (Monkey monkey : monkeyList) {
                    System.out.println(monkey);
                }
                break;
            case "available":
                for (Dog dog : dogList) {
                    if (!dog.getReserved() && dog.getTrainingStatus().equalsIgnoreCase("in service")) {
                        System.out.println(dog);
                    }
                }
                for (Monkey monkey : monkeyList) {
                    if (!monkey.getReserved() && monkey.getTrainingStatus().equalsIgnoreCase("in service")) {
                        System.out.println(monkey);
                    }
                }
                break;
            case "all":
                for (Dog dog : dogList) {
                    System.out.println(dog);
                }
                for (Monkey monkey : monkeyList) {
                    System.out.println(monkey);
                }
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
}