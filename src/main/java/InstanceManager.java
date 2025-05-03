import java.util.ArrayList;

public class InstanceManager {
    // stores all the instances of CategoryManager so all the data inside the Arraylist can be saved.
    private static final ArrayList<CategoryManager> instances = new ArrayList<>();
    // variable for a save the user creates or load
    private static CategoryManager currentInstance;

    // getters
    public static ArrayList<CategoryManager> getInstances() {
        return instances;
    }
    public static CategoryManager getCurrentInstance() {
        return currentInstance;
    }



    public static boolean createNewInstance() {
        System.out.println("Enter name of this instance: ");
        // custom Scanner.nextLine() method that prevents empty strings
        String categoryName = Read.getNoEmptyString();
        // null happens when the user inputs "exit" instead of any other non-empty string
        // Which just returns false and the loop in Main will just start over, asking to either create or load an instance
        if (categoryName == null) return false;

        // creates a new instance and returns true so the loop in Main breaks
        // it also sets the newly created CategoryManager object to the current instance variable for other classes to retrieve
        currentInstance = new CategoryManager(categoryName);
        // adds this save to the ArrayList of instances
        instances.add(currentInstance);
        System.out.println(String.join("\n",
            "Created a new Budget Tracker",
        "You should probably set your income first by running: income set",
        "You should also create a categories first by running: new "));
        return true;
    }


    public static boolean loadInstances() {
        // return if loop is empty
        if (instances.isEmpty()) {
            System.out.println("There are no saved data");
            return false;
        }
        // prints out all the instances
        System.out.println("Choose an instance to load");
        for (int i = 0; i < instances.size(); i++) {
            System.out.println("\t" + i + " - " + instances.get(i).getName());
        }

        // Loop asks for an input within 0 to the ArrayList's length - 1 that saves all the instances
        Integer loadChoice = Read.getValidInt(0, instances.size());
        // returns if user types "exit"
        if (loadChoice == null) return false;

        // gets the index of the chosen instance and set that to the current instance variable
        currentInstance = instances.get(loadChoice);
        return true;


    }

}
