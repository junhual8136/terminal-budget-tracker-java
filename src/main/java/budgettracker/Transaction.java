package budgettracker;

import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Serializable {
    private final double cost;
    private final String item;
    private final LocalDate dateOfPurchase;

    // this is use to sort the ArrayList<Transaction> back to default
    private static int number = 1;
    private final int id;

    public Transaction(String item, double cost,LocalDate dateOfPurchase) {
        this.item = item;
        this.cost = cost;
        this.dateOfPurchase = dateOfPurchase;
        this.id = number;
        number++;
    }
    public Transaction(String item, double cost) {
        this(item,cost,LocalDate.now());
    }

    // a bunch of getters
    public int getId() {
        return this.id;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public double getCost() {
        return cost;
    }

    public String getItem() {
        return item;
    }

    // uses String.format to force 2 decimals to print when printing whole numbers
    public String getFormattedCost() {
        return String.format("%,.2f",this.cost);
    }

    public String toString() {
        return String.format("%s: $%,.2f",this.item,this.cost);
    }

}
