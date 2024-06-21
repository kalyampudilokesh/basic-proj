import java.util.Objects;
import java.util.Scanner;

public class PasswordGenerator {
    CharacterSet charSet;
    public static Scanner inputScanner;

    public PasswordGenerator(Scanner scanner) {
        inputScanner = scanner;
    }

    public PasswordGenerator(boolean includeUppercase, boolean includeLowercase, boolean includeDigits, boolean includeSpecialChars) {
        charSet = new CharacterSet(includeUppercase, includeLowercase, includeDigits, includeSpecialChars);
    }

    public void mainLoop() {
        System.out.println("Welcome to Ziz Password Services :)");
        displayMenu();

        String userChoice = "-1";

        while (!userChoice.equals("4")) {
            userChoice = inputScanner.next();

            switch (userChoice) {
                case "1" -> {
                    generatePasswordRequest();
                    displayMenu();
                }
                case "2" -> {
                    verifyPassword();
                    displayMenu();
                }
                case "3" -> {
                    displayUsefulInfo();
                    displayMenu();
                }
                case "4" -> displayExitMessage();
                default -> {
                    System.out.println();
                    System.out.println("Kindly select one of the available commands");
                    displayMenu();
                }
            }
        }
    }

    private Password createPassword(int length) {
        final StringBuilder password = new StringBuilder("");

        final int charSetLength = charSet.getCharacterSet().length();

        int max = charSetLength - 1;
        int min = 0;
        int range = max - min + 1;

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * range) + min;
            password.append(charSet.getCharacterSet().charAt(index));
        }

        return new Password(password.toString());
    }

    private void displayUsefulInfo() {
        System.out.println();
        System.out.println("Use a minimum password length of 8 or more characters if permitted");
        System.out.println("Include lowercase and uppercase alphabetic characters, numbers and symbols if permitted");
        System.out.println("Generate passwords randomly where feasible");
        System.out.println("Avoid using the same password twice (e.g., across multiple user accounts and/or software systems)");
        System.out.println("Avoid character repetition, keyboard patterns, dictionary words, letter or number sequences," +
                "\nusernames, relative or pet names, romantic links (current or past) " +
                "and biographical information (e.g., ID numbers, ancestors' names or dates).");
        System.out.println("Avoid using information that the user's colleagues and/or " +
                "acquaintances might know to be associated with the user");
        System.out.println("Do not use passwords which consist wholly of any simple combination of the aforementioned weak components");
    }

    private void generatePasswordRequest() {
        boolean includeUppercase = false;
        boolean includeLowercase = false;
        boolean includeDigits = false;
        boolean includeSpecialChars = false;

        boolean validSelection;

        System.out.println();
        System.out.println("Hello, welcome to the Password Generator :) answer"
                + " the following questions by Yes or No \n");

        do {
            String input;
            validSelection = false;

            do {
                System.out.println("Do you want Lowercase letters \"abcd...\" to be used? ");
                input = inputScanner.next();
                validateInput(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (shouldInclude(input)) includeLowercase = true;

            do {
                System.out.println("Do you want Uppercase letters \"ABCD...\" to be used? ");
                input = inputScanner.next();
                validateInput(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (shouldInclude(input)) includeUppercase = true;

            do {
                System.out.println("Do you want Numbers \"1234...\" to be used? ");
                input = inputScanner.next();
                validateInput(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (shouldInclude(input)) includeDigits = true;

            do {
                System.out.println("Do you want Symbols \"!@#$...\" to be used? ");
                input = inputScanner.next();
                validateInput(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (shouldInclude(input)) includeSpecialChars = true;

            if (!includeUppercase && !includeLowercase && !includeDigits && !includeSpecialChars) {
                System.out.println("You have selected no characters to generate your " +
                        "password, at least one of your answers should be Yes\n");
                validSelection = true;
            }

        } while (validSelection);

        System.out.println("Great! Now enter the length of the password");
        int length = inputScanner.nextInt();

        final PasswordGenerator generator = new PasswordGenerator(includeUppercase, includeLowercase, includeDigits, includeSpecialChars);
        final Password password = generator.createPassword(length);

        System.err.println("Your generated password -> " + password);
    }

    private boolean shouldInclude(String input) {
        return input.equalsIgnoreCase("yes");
    }

    private void validateInput(String input) {
        if (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no")) {
            System.out.println("You have entered something incorrect let's go over it again \n");
        }
    }

    private void verifyPassword() {
        String input;

        System.out.print("\nEnter your password:");
        input = inputScanner.next();

        final Password password = new Password(input);

        System.out.println(password.calculateScore());
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("Enter 1 - Password Generator");
        System.out.println("Enter 2 - Password Strength Check");
        System.out.println("Enter 3 - Useful Information");
        System.out.println("Enter 4 - Quit");
        System.out.print("Choice:");
    }

    private void displayExitMessage() {
        System.out.println("Closing the program bye bye!");
    }
}
