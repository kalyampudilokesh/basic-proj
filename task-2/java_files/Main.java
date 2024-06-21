import java.util.Scanner;

public class Main {

    public static final Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args) {
        PasswordGenerator passwordGenerator = new PasswordGenerator(inputScanner);
        passwordGenerator.mainLoop();
        inputScanner.close();
    }
}
