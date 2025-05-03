import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Data {

    /*
    Because all the previous saved data is retrieved and stored in the InstanceManager,
    this will retrieve all that back into the data variable to later save.
     */
    static ArrayList<CategoryManager> data = InstanceManager.getInstances();

    // saves the data and creates or overwrites "data.ser" using OOS
    public static void saveData() {
        if (data.isEmpty()) {
            System.out.println("There is nothing to save");
        } else {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.ser"));
                oos.writeObject(data);

            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Successfully saved all instances");
        }
    }

    // loads and return all that data from "data.ser" using OIS
    // returns null if something fails or is empty
    public static ArrayList<CategoryManager> loadData() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"));
            return (ArrayList<CategoryManager>) ois.readObject();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }





    
}
