import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskManagerApp {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    taskManager.addTask(getTaskName(scanner));
                    break;
                case 2:
                    if (!taskManager.isEmpty()) {
                        taskManager.listTasks();
                        int taskNumber = getUserInput(scanner, "Enter the task number to remove: ");
                        if (taskManager.isValidTaskNumber(taskNumber)) {
                            taskManager.removeTask(taskNumber);
                        } else {
                            System.out.println("Invalid task number.");
                        }
                    } else {
                        System.out.println("No tasks to remove.");
                    }
                    break;
                case 3:
                    if (!taskManager.isEmpty()) {
                        taskManager.listTasks();
                    } else {
                        System.out.println("No tasks to list.");
                    }
                    break;
                case 4:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Task Manager Application");
        System.out.println("1. Add Task");
        System.out.println("2. Remove Task");
        System.out.println("3. List Tasks");
        System.out.println("4. Quit");
        System.out.print("Select an option: ");
    }

    private static int getUserChoice(Scanner scanner) {
        int choice = -1;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Clear invalid input
        }
        return choice;
    }

    private static String getTaskName(Scanner scanner) {
        System.out.print("Enter task name: ");
        scanner.nextLine(); // Consume the newline character
        return scanner.nextLine();
    }

    private static int getUserInput(Scanner scanner, String prompt) {
        int input = -1;
        System.out.print(prompt);
        try {
            input = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Clear invalid input
        }
        return input;
    }
}

class TaskManager {
    private ArrayList<String> tasks = new ArrayList<>();

    public void addTask(String name) {
        tasks.add(name);
        System.out.println("Task added.");
    }

    public void removeTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
        System.out.println("Task removed.");
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 1 && taskNumber <= tasks.size();
    }
}
