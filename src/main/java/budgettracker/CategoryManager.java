package budgettracker;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

// This class and the subclasses in its ArrayList all need the Serializable interface to save the data to a file
public class CategoryManager implements Serializable {
    // Arraylist that stores Categories class which contains an ArrayList containing Transactions
    private final ArrayList<Category> allCategories;
    private final String name;
    // creates an instance of the Income class containing a double value with a setter and getter
    private final Income income = new Income();

    // default sort, oldest created - newest
    private String sortStatus = Sort.sortOptions[0];

    public CategoryManager(String name) {
        this.allCategories = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // clear the arrayList and sets income to default
    public void reset() {
        this.allCategories.clear();
        System.out.println("Cleared all previous categories and their transactions.");
        income.setIncome(0);
    }

    // prints the tracker into the terminal
    public void printAll() {
        System.out.println("------------------------------------------------------------------");
        if (this.allCategories.isEmpty()) {
            System.out.println("There is currently no categories or transactions");
        }

        else {
            // prints out all the category names and its total
            for (Category c : this.allCategories) {
                System.out.printf("%s Category's Total: $%,.2f\n", c.getName(), c.getCategoryTotal());
                // prints out a message saying there are none under this category
                if (c.getCategoryList().isEmpty()) {
                    System.out.println("\tThis category has no stored transactions");
                }
                else {
                    // or prints out all the transactions along with its data under this category
                    for (Transaction item : c.getCategoryList()) {
                        System.out.println("\t- " + item.getDateOfPurchase().toString() + " | " + item);
                    }
                }
                // extra line break under each category
                System.out.println();
            }
        }


        System.out.println("Sort " + sortStatus );
        System.out.println("------------------------------------------------------------------");
        double totalExpense = this.getTotal();
        double totalIncome = this.income.getIncome();
        double left = totalIncome - totalExpense;
        // prints out the total expense, money left, and income with 2 decimal places
        System.out.printf("Total Expense: $%,.2f     Left: $%,.2f     Total Income: $%,.2f \n",totalExpense, left, totalIncome );
        System.out.println("------------------------------------------------------------------");

    }

    public void printAllCategories() {
        for (int i=0; i<this.allCategories.size();i++) {
            System.out.println("\t" + i + " - " + this.allCategories.get(i).getName());
        }
    }

    // creates a new category
    public void createNewCategory() {
        // ask user for a non-empty name
        System.out.println("Enter name of the new Category");
        String name = Read.getNoEmptyString();

        // creates the new category and adds it to the allCategories ArrayList
        if (name != null) {
            this.allCategories.add(new Category(name));
            System.out.println("Successfully created new category: " + name);
        }

    }

    // deletes a category or sends it to the next method below to delete a transaction
    public void delete() {
        // returns early if there are none to delete
        if (this.allCategories.isEmpty()) {
            System.out.println("There is nothing to delete");
            return;
        }

        // asks the user to choose to delete either a category or a transaction
        System.out.println(String.join("\n\t",
                "Which one do you wan to delete?",
                "0 - categories",
                "1 - transactions"
        ));
        // Loop ask until a valid input(0 or 1) or user types exit
        Integer type = Read.getValidInt(0,2);
        // if "exit" was the input, it will return early
        if (type == null) return;

        // if input was 0, it will print line 112
        // if not, it will print the line 113
        System.out.println((type == 0) ?
                    "Which category would you like to delete?":
                    "Which category contains the transaction you want to delete?"
                );

        // calls the other method to print all Categories to display what to delete
        this.printAllCategories();

        // gets a valid index to delete by loop asking for an input that is between 0 and allCategorizes length - 1
        Integer index = Read.getValidInt(0,this.allCategories.size());
        // returns early if user types exit
        if (index == null) return;

        // deletes the category by removing the index inputted of allCategories
        if (type == 0) {
            System.out.println("Deleted the category: " + this.allCategories.get(index).getName());
            this.allCategories.remove(index.intValue());
        }
        // if user chose to delete transaction, it passes the index of the Category containing choices of Transactions to delete
        else {
            this.deleteTransaction(index);
        }

    }

    public void deleteTransaction(int categoryIndex) {
        // gets all the Transactions of the Category the user chose in the delete() method
        ArrayList<Transaction> thisCategory = this.allCategories.get(categoryIndex).getCategoryList();
        // returns early if this category is empty
        if (thisCategory.isEmpty()) {
            System.out.println("This category has no transactions");
            return;
        }
        // prints out all the transaction and its index
        for (int i=0; i < thisCategory.size(); i++) {
            System.out.println(i + " - " + thisCategory.get(i).getItem() + ": $" + thisCategory.get(i).getFormattedCost());
        }

        // loop asks the user for a value between 0 and the length of the ArrayList - 1
        System.out.println("Which transaction would you like to delete?");
        Integer index = Read.getValidInt(0,thisCategory.size());
        // returns early if user inputs "exit"
        if (index == null) return;

        // access the category and index of that category to delete the transaction
        this.allCategories.get(categoryIndex).deleteTransaction(index);
    }

    public void addTransaction() {
        // tells the user to create a category first and returns early if there are no categories
        if (this.allCategories.isEmpty()) {
            System.out.println("There are currently no categories to save transactions in");
            System.out.println("\tnew - to create a new category");
            return;
        }
        // print a list of categories to add a transaction under
        this.printAllCategories();
        System.out.println("Enter which category do you want to add to?");

        // Loop asks the user for a value between 0 and the length of the ArrayList - 1
        Integer index = Read.getValidInt(0, this.allCategories.size());
        if (index == null) return;

        // Loop asks for a transaction item's name until not empty String, return early if user types "exit"
        System.out.println("Enter item name: ");
        String itemName = Read.getNoEmptyString();
        if (itemName == null) return;

        // Loop asks for a transaction cost until input can be parsed into a double, return early if user types "exit"
        System.out.println("Enter cost: ");
        Double cost = Read.getValidDouble();
        if (cost == null) return;

        // Loop ask the user until they either type 0,1 or "exit"
        // 0 - sets the date as the time when transaction was created
        // 1 - ask the user for a year, month, date
        // exit - null return value so method exits early
        System.out.println("Would you like to enter a custom date?");
        System.out.println(String.join("\n\t",
                "Options:",
                "0 - No : Sets the date as today",
                "1 - Yes : Enter YYYY:MM:DD"
                ));
        Integer option = Read.getValidInt(0,2);
        if (option == null) return;

        // creates the transaction object with the current date (no LocalDate parameter)
        // returns early so the rest of the method that asks for a custom date values won't run
        if (option == 0) {
             this.allCategories.get(index).addToList(new Transaction(itemName,cost));
             return;
         }

        // declares an LocalDate variable
        // and asks the user for an input that could be parsed into an int
        LocalDate time;
        System.out.println("Enter day");
        Integer day = Read.getValidInt();

        System.out.println("Enter month");
        Integer month = Read.getValidInt();

        System.out.println("Enter Year");
        Integer year = Read.getValidInt();

        // tries to check if the inputs create a valid date
        // Java will throw an error if the date is not valid such as 2024,02,31 as there February does not have 31 days.
        try {
            time = LocalDate.of(year,month,day);
        } catch (Exception e) {
            System.err.println("Seems like the date(YYYY:MM:DD): " + year + "-" + month + "-" + day + " doesn't exist");
            return;
        }
        this.allCategories.get(index).addToList(new Transaction(itemName,cost,time));

    }

    // asks user for a valid value that can be parsed into a double and set that as the income
    public void setIncome() {
        System.out.println("Enter Amount");
        Double amount = Read.getValidDouble();
        if (amount != null) {
            this.income.setIncome(amount);
            System.out.printf("Income set to $%.2f \n",amount);
        }
    }

    // asks user for a valid value that can be parsed into a double and adds that as the income
    public void addIncome() {
        System.out.println("Enter amount");
        Double value = Read.getValidDouble();
        if (value != null) {
            this.income.addIncome(value);
            String word = (value < 0) ? "decreased" : "increased";
            System.out.printf("Income was %s by $%.2f \n",word,value);
        }
    }

    // Loops through all the categories and returns the total sum of all the expenses
    public double getTotal() {
        double total = 0;
        for (Category c : this.allCategories) {
            total += c.getCategoryTotal();
        }
        return total;
    }

    public void sort() {
        // prints a list of sort options from a static array in Sort.java
        System.out.println("How do you want the transactions to be sorted?");
        String[] m = Sort.sortOptions;
        System.out.println(String.join("\n\t", "Options",m[0],m[1],m[2],m[3],m[4],m[5]));

        // Loop ask until input is between 0 - 5, or null which returns early
        Integer option = Read.getValidInt(0,6);
        if (option == null) return;
        sortStatus = Sort.sortOptions[option];

        // finds the corresponding option value
        switch(option) {
            // sorts by creation date, 0 ascending, 1 descending
            case 0,1 -> {
                for (Category c : this.allCategories) {
                    Sort.sortArrayListByCreatedOrder(c.getCategoryList());
                    if (option == 1) Sort.reverse(c.getCategoryList());
                }
            }
            // sorts by cost, 2 ascending, 3 descending
            case 2,3-> {
                for (Category c : this.allCategories) {
                   Sort.sortArrayListByCost(c.getCategoryList());
                    if (option == 3) Sort.reverse(c.getCategoryList());
                }
            }
            // sorts by purchase date, 4 ascending, 5 descending
            case 4,5 -> {
                for (Category c : this.allCategories) {
                    Sort.sortArrayListByDate(c.getCategoryList());
                    if (option == 5) Sort.reverse(c.getCategoryList());
                }
            }
        }
        System.out.println("Sorted by" + sortStatus);
    }
}
