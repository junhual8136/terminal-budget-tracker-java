package budgettracker;

import java.io.Serializable;

// income class for CategoryManager.java
// just holds the income value with a getter and setter

public class Income implements Serializable {
    private Double income;

    public Income(double income) {
        this.income = income;
    }

    public Income() {
        this(0);
    }

    public Double getIncome() {
        return income;
    }
    public void setIncome(double newValue) {
        this.income = newValue;
    }
    public void addIncome(double value) {
        this.income += value;
    }


}
