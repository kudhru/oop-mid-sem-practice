import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListAPI {
    public static void main(String[] args) {
        // Insert or Add or Write a particular object or all the objects
        // Delete or Remove a particular object or all the objects
        // Get or Fetch or Read a particular object or all the objects
        // Update a particular object or all the objects
        // Iterating over the entire collection
        // size or length

        // List Interface
//        List<String> listOfStrings = new ArrayList<>();
        ArrayList<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("Java");
        listOfStrings.add("Python");
        listOfStrings.add("C++");
        listOfStrings.add("Rust");

        listOfStrings.addAll(listOfStrings);

        String listElement = listOfStrings.get(2);
        System.out.println(listElement);
        String first = listOfStrings.getFirst();
        String last = listOfStrings.getLast();
        int index = listOfStrings.indexOf("Java");


        listOfStrings.addFirst(listElement);
        listOfStrings.addLast(listElement);

        boolean contains = listOfStrings.contains(listElement);
        System.out.println(contains);

        boolean selfComparison = listOfStrings.equals(listOfStrings);
        boolean newObject = listOfStrings.equals(new ArrayList<String>());

        int size = listOfStrings.size();

        Iterator<String> iterator = listOfStrings.iterator();
        while (iterator.hasNext()) {
            String currentElement = iterator.next();
            System.out.println(currentElement);
        }

        listOfStrings.remove("Python");
        listOfStrings.removeAll(listOfStrings);
        listOfStrings.clear();
    }
}
