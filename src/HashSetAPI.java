import java.util.HashSet;
import java.util.Iterator;

public class HashSetAPI {
    public static void main(String[] args) {
        // Insert or Add or Write a particular object or all the objects
        // Delete or Remove a particular object or all the objects
        // Get or Fetch or Read a particular object or all the objects
        // Update a particular object or all the objects
        // Iterating over the entire collection
        // size or length

        HashSet<String> set = new HashSet<>();

        set.add("Bob");
        set.add("Anthony");
        set.add("Cat");
        set.add("Dave");
        set.add("Anthony");

        set.addAll(set);

        System.out.println(set);

        boolean contains = set.contains("XYZ");

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String currentString = iterator.next();
            System.out.println(currentString);
        }

        set.remove("Bob");
        set.removeAll(set);
        set.clear();

        int size = set.size();
    }
}
