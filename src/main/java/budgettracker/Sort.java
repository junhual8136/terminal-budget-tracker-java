package budgettracker;

import java.util.ArrayList;


public class Sort {
    public final static String[] sortOptions = {
            "0 : oldest - newest created (default)",
            "1 : newest - oldest created",
            "2 : lowest - highest cost",
            "3 : highest - lowest descending cost,",
            "4 : latest - earliest date of purchase",
            "5 : earliest - latest date of purchase"
    };

/*
All these sort uses insertion sort which starts at index 1 and compares it to the previous indexes until it whichever previous value is bigger
which then will insert into the index before that bigger value

They are all sorted from smallest to largest
and I will use a reverse method to reverse the elements so it becomes largest - smallest
*/

    public static void sortArrayListByCost(ArrayList<Transaction> list) {
        for (int i=1;i<list.size();i++) {
            Transaction temp = list.get(i);
            int j = i - 1;
            while(j >= 0 && list.get(j).getCost() > list.get(i).getCost()) {
                list.set(j+1,list.get(j));
                j--;
            }
            list.set(j + 1, temp);
        }
    }

    public static void sortArrayListByDate(ArrayList<Transaction> list) {
        for (int i=1;i<list.size();i++) {
            Transaction temp = list.get(i);
            int j = i - 1;
            while(j >= 0 && list.get(j).getDateOfPurchase().isBefore(list.get(i).getDateOfPurchase())) {
                list.set(j+1,list.get(j));
                j--;
            }
            list.set(j + 1, temp);
        }
    }

    public static void sortArrayListByCreatedOrder(ArrayList<Transaction> list) {
        for (int i=1;i<list.size();i++) {
            Transaction temp = list.get(i);
            int j = i - 1;
            while(j >= 0 && list.get(j).getId() > list.get(i).getId()) {
                list.set(j+1,list.get(j));
                j--;
            }
            list.set(j + 1, temp);
        }
    }

    // reverses the arrayList
    public static void reverse(ArrayList<Transaction> list) {
        int listLength = list.size();
        for (int i = 0; i< listLength / 2; i++) {
            Transaction temp = list.get(i);
            list.set(i,list.get(listLength - i - 1));
            list.set(listLength - i - 1,temp);
        }
    }


}
