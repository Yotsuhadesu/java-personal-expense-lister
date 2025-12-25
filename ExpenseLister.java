/*
    Title: PERSONAL EXPENSE LISTER
    Start: 19 December 2025
    Finish: 25 December 2025
    Description: A Java program that creates a list of your expenses.
    Expense - money spent on something
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseLister {

    public static ArrayList<String> expenses = new ArrayList<>();  // store expense as one String

    public static void save() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("log.txt"));

            // write to the log text file
            for (String expense : expenses) {
                writer.println(expense);
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Saving failed.");
        }
    }

    public static void load() {
        try {
            Scanner scanner = new Scanner(new File("log.txt"));   // reading the log text file

            // add items to the array
            while (scanner.hasNextLine()) {
                expenses.add(scanner.nextLine());
            }

            scanner.close();
        } catch (IOException e) {
            System.out.println("Loading failed.");
        }
    }

    public static void main(String[] args) {
        expenses.clear();   // clear the expenses list to avoid duplication before loading
        load(); // add the previously written expenses on the text file to the expenses list

        Scanner scanner = new Scanner(System.in);   // Scanner object

        // menu
        int choice = 0; // initialization for the menu variable
        String date;
        String time;
        String category;
        String amount;

        while (choice != 4) {
            System.out.println();
            System.out.print("Options:\n1. Add Expense\n2. View all expenses\n3. View total spending\n4. Exit\nSelect: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // add expense
                    System.out.println();
                    System.out.println("Add Expense:\n");
                    System.out.print("Date (mm/dd/yy): ");  // date
                    date = scanner.nextLine();
                    System.out.print("Time (military time): "); // time
                    time = scanner.nextLine();
                    System.out.print("Category: "); // category
                    category = scanner.nextLine();
                    System.out.print("Amount: ");   // amount
                    amount = scanner.nextLine();

                    // add to the expenses list as one String
                    expenses.add(date + " | " + time + " | " + category + " | " + amount);

                    save(); // save to the log text file
                    break;
                case 2: // view all expenses
                    System.out.println();
                    System.out.println("Expenses List:\n");
                    if (expenses.isEmpty()) {   // when the expenses list is empty
                        System.out.println("No expenses yet.");
                    } else {
                        for (String expense : expenses) {
                            System.out.println(expense);    // show previously added expenses
                        }
                    }
                    break;
                case 3: // view total spending
                    double total = 0;

                    // How can I extract the total on the String list? split each expense using a delimiter
                    for (String expense : expenses) {
                        String[] parts = expense.split("\\| ");    // split previously added expenses
                        double amountLog = Double.parseDouble(parts[3]);    // turn the 3rd index (amount) into a double
                        total += amountLog;
                    }

                    System.out.println();
                    System.out.printf("Total Spent: ₱%.2f\n", total);   // show total
                    break;
                case 4: // exit
                    break;
            }
        }

        scanner.close();
    }
}
