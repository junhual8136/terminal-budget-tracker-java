package budgettracker;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        // loads previous instances from saved data if there is any
        ArrayList<CategoryManager> temp = Data.loadData();
        if (temp != null) {
            InstanceManager.getInstances().addAll(temp);
        }


        System.out.println("Welcome to this basic budget tracker!");
        System.out.println(String.join("\n",
                "Options: ",
                "0 - new instance",
                "1 - load previous saved instances"
        ));

        System.out.println("Enter an option: ");
        // This uses a while loop to prompt the user for a valid input
        while(true) {
            // gets the input 0, 1, or null from the user and does something depending on the input
            Integer option = Read.getValidInt(0,2);
            // "exit" was the input so it just asks the question again
            if (option == null) {
                System.out.println("Enter an option: ");
            }
            // creates a new budget instance. If successful, the while loop breaks.
            else if (option == 0) {
                boolean created = InstanceManager.createNewInstance();
                if (created) break;
            }
            // loads any potential saves. If successful, the while loop breaks.
            else if (option == 1) {
                boolean loaded = InstanceManager.loadInstances();
                if (loaded) break;
            }
            // this handles everything else in case some other input somehow slipped in
            else {
                System.out.println("Not a valid option, try again");
            }
        }

        // Reference to the chosen CategoryManager class from InstanceManager.java
        // Less effort and easier than having to use the static getter everytime i need to do something with it
        CategoryManager currentInstance = InstanceManager.getCurrentInstance();

        System.out.println("Type help for a list of commands");

        // Asks the user for an input and loops through a list of options
        // if it matches something in the switch statement, it will do something
        // if not, it will print the input + " is not a valid command"
        // exit to break
        boolean stop = false;
        while(!stop) {
            String input = scan.nextLine();
            switch(input.toLowerCase()) {
                case "exit" -> stop = true;
                case "help" -> printListOfCommands();
                case "reset"-> currentInstance.reset();
                case "print" -> currentInstance.printAll();
                case "add" -> currentInstance.addTransaction();
                case "new" -> currentInstance.createNewCategory();
                case "delete"-> currentInstance.delete();
                case "income set" -> currentInstance.setIncome();
                case "income add" -> currentInstance.addIncome();
                case "sort" -> currentInstance.sort();
                case "save" -> Data.saveData();
                default -> System.out.println(input + " is not a valid command");
            }
        }
    }


    // Prints a list of commands and uses String.join() for easier formatting
    public static void printListOfCommands() {
        System.out.println(String.join("\n\t",
        "Available commands:",
                "exit - closes the program",
                "help - print list of commands",
                "reset - clear history and resets everything",
                "print - prints all categories and their transactions",
                "new - creates a new category",
                "add - adds a new transaction",
                "delete - delete a category OR a certain transaction",
                "save - saves current instance which could be loaded later",
                "income set - set your income",
                "income add - add/subtract a value from your income",
                "sort - sort the categories in a certain way"
        ));
    }
}