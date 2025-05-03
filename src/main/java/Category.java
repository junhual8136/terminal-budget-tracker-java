import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {
    private final String name;
    // Each category has an ArrayList to store Transactions
    private final ArrayList<Transaction> categoryList;
    public Category(String name) {
        this.name = name;
        this.categoryList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Transaction> getCategoryList() {
        return categoryList;
    }

    // adds a new Transaction
    public void addToList(Transaction transaction) {
        this.categoryList.add(transaction);
        System.out.println("Added " + transaction.getItem() + " to " + this.name);
    }

    // deletes an index in the category
    public void deleteTransaction(int index) {
        System.out.println("Deleted the transaction : " + this.categoryList.get(index).getItem() + " $" + this.categoryList.get(index).getCost());
        this.categoryList.remove(index);
    }

    // returns this category's sum of Transaction
    public double getCategoryTotal() {
        double total = 0;
        for (Transaction item : this.categoryList) {
            total += item.getCost();
        }
        return total;
    }

}
