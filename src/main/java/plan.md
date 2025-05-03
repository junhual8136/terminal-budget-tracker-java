* Saved Instance Arraylist
  * Category Manager 
    * Categories
      * Transaction
        * item 
        * cost
        * date

  

  



























Challenge:
* find out how to save files
  * 






















Takeaways:

1. format doubles to strings with `System.out.printf()` or `String.format()`
```java
class Demo {
    public static void main(String[] args) {
        double x = 1;
        System.out.println(x);
        System.out.printf("%.2f",x);
    }
}
```
Output:
1.0
1.00


2. Enhanced case switches

```java
import java.util.Scanner;

class Demo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            boolean running = checkThing(input);
            if (!running) break;
        }
    }
    public static boolean checkThing(input) {
        switch(input) {
            case "print":
                System.out.println(input);
                return false;
            break;
            case "stop":
                System.out.println("Stopping");
                return true;
            break;
            default:
                System.out.println(input + "not recognized");
        }
    }
    public static boolean checkThing2(input) {
        switch(input) {
            case "print", "other thing" -> {
                System.out.println(input);
                return false;
            }
            case "stop" -> {
                System.out.println("Stopping");
                return true;
            }
            default -> System.out.println(input + "not recognized");
        }
    }
}

```




