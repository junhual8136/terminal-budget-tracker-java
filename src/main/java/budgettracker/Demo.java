package budgettracker;

import java.util.Scanner;

class Demo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("type something");
        String a = scanner.nextLine();
        while (true) {
            System.out.println("type a number");
            int input = scanner.nextInt();
            if (input == 0) {
                break;
            }
            System.out.println(input);

        }

    }
}