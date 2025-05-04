package budgettracker;

import java.util.Scanner;

public class Read {
    private static final Scanner scan = new Scanner(System.in);

    /* Attempts to get a valid Integer value from Scanner by:
            1. Trying to parse it to an int to check if it is a number
            2. Checking if the value is between the arguments passed, lowest(inclusive) and highest(exclusive)
       Loops until the user types a valid number or type "exit" which will return null

       @param lowest - inclusive lowest value the user input could be
       @param highest - exclusive highest value the user input could be
       @returns - integer that is (lowest <= int < highest), or null if user types exit
    * */
    public static Integer getValidInt(int lowest, int highestExclusive) {
        int number;
        while (true) {
            String response = scan.nextLine();
            if (response.equals("exit")) {
                return null;
            }
            try {
                // tries to parse the String from Scanner.nextLine()
                // Should throw a NumberFormatException if ihe String isn't just numbers
                number = Integer.parseInt(response);

                // Checks if (lowest <= number < highest) and returns if true
                // Else, it will just throw an Exception
                if (number >= lowest && number < highestExclusive) {
                    return number;
                }

                throw new Exception("Invalid Index");

            }
            // Handles all the Exceptions that could potentially be thrown so program won't just stop
            catch (Exception e) {
                System.err.println("Not a valid number, try again or type 'exit' to quit");
            }
        }
    }

    // int values are not constrained so it just calls the method passing in the lowest and highest values of an int
    public static Integer getValidInt() {
        return getValidInt(Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    // same concept as getValidInt but for doubles
    public static Double getValidDouble() {
        double number;
        while (true) {
            String response = scan.nextLine();
            if (response.equals("exit")) {
                return null;
            }
            try {
                number = Double.parseDouble(response);
                return number;
            } catch (NumberFormatException e) {
                System.err.println("Not a valid number, try again or type 'exit' to quit");
            }
        }
    }

    // same concept as getValidInt but for Strings
    // Prevents empty strings instead
    public static String getNoEmptyString() {
        String response;
        while (true) {
            response = scan.nextLine();
            if (response.equals("exit")) {
                return null;
            }

            if (!response.isEmpty()) {
                return response;
            }

            System.err.println("Lets not have any empty strings");
        }
    }
}
