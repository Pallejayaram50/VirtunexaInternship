import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Expense {
    int id;
    String category;
    double amount;
    String date;

    public Expense(int id, String category, double amount, String date) {
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Category: " + category + " | Amount: $" + amount + " | Date: " + date;
    }
}

public class ExpenseManager {
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static int expenseId = 1;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Expense Management System =====");
            System.out.println("1. Add Expense");
            System.out.println("2. Edit Expense");
            System.out.println("3. Delete Expense");
            System.out.println("4. View Expenses");
            System.out.println("5. Generate Report");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addExpense();
                        break;
                    case 2:
                        editExpense();
                        break;
                    case 3:
                        deleteExpense();
                        break;
                    case 4:
                        viewExpenses();
                        break;
                    case 5:
                        generateReport();
                        break;
                    case 6:
                        System.out.println("Exiting the application. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice! Please select a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }

    private static void addExpense() {
        try {
            System.out.print("Enter category: ");
            String category = scanner.nextLine();
            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            expenses.add(new Expense(expenseId++, category, amount, date));
            System.out.println("Expense added successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount! Please enter a valid number.");
            scanner.nextLine(); // Consume invalid input
        }
    }

    private static void editExpense() {
        try {
            System.out.print("Enter expense ID to edit: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            for (Expense exp : expenses) {
                if (exp.id == id) {
                    System.out.print("Enter new category: ");
                    exp.category = scanner.nextLine();
                    System.out.print("Enter new amount: ");
                    exp.amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new date (YYYY-MM-DD): ");
                    exp.date = scanner.nextLine();

                    System.out.println("Expense updated successfully!");
                    return;
                }
            }
            System.out.println("Expense ID not found!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a number.");
            scanner.nextLine(); // Consume invalid input
        }
    }

    private static void deleteExpense() {
        try {
            System.out.print("Enter expense ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            for (int i = 0; i < expenses.size(); i++) {
                if (expenses.get(i).id == id) {
                    expenses.remove(i);
                    System.out.println("Expense deleted successfully!");
                    return;
                }
            }
            System.out.println("Expense ID not found!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a number.");
            scanner.nextLine(); // Consume invalid input
        }
    }

    private static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
        } else {
            System.out.println("\n===== Expense List =====");
            for (Expense exp : expenses) {
                System.out.println(exp);
            }
        }
    }

    private static void generateReport() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to generate a report.");
            return;
        }

        double totalAmount = 0;
        System.out.println("\n===== Expense Report =====");
        for (Expense exp : expenses) {
            System.out.println(exp);
            totalAmount += exp.amount;
        }
        System.out.println("--------------------------");
        System.out.println("Total Expenses: $" + totalAmount);
    }
}
