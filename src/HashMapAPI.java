import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class CustomStudent {
    String name;
    String branch;
    double cgpa;

    CustomStudent(String name, String branch, double cgpa) {
        this.name = name;
        this.branch = branch;
        this.cgpa = cgpa;
    }

    CustomStudent(CustomStudent student) {
        this.name = student.name;
        this.branch = student.branch;
        this.cgpa = student.cgpa;
    }

    public boolean equals(Object obj) {
        CustomStudent student = (CustomStudent) obj;
        return this.name.equals(student.name)
                && this.branch.equals(student.branch)
                && this.cgpa == student.cgpa;
    }
}

public class HashMapAPI {
    public static void main(String[] args) {
        // Insert or Add or Write a particular object or all the objects
        // Delete or Remove a particular object or all the objects
        // Get or Fetch or Read a particular object or all the objects
        // Update a particular object or all the objects
        // Iterating over the entire collection
        // size or length

        HashMap<String, CustomStudent> map1 = new HashMap<>();
        map1.put("2023A7PS0014P", new CustomStudent("Himanshu", "CS", 8.5));
        map1.put("2022B3A7PS0014P", new CustomStudent("Devanshu", "Eco and CS", 9.1));
        map1.put("2023A7PS1001P", new CustomStudent("Aashish", "CS", 8.75));

        HashMap<String, CustomStudent> map2 = new HashMap<>();
        map2.put("2023A7PS0014P", new CustomStudent("Himanshu", "CS", 8.5));
        map2.put("2022B3A7PS0014P", new CustomStudent("Devanshu", "Eco and CS", 9.1));
        map2.put("2023A7PS1001P", new CustomStudent("Aashish", "CS", 8.75));

        // insert all the key-value pairs of map2 in map1
        Set<String> keys = map2.keySet();
        for (String key : keys) {
            CustomStudent student = map2.get(key);
            map1.put(key, new CustomStudent(student));
        }

        Set<Map.Entry<String, CustomStudent>> entries = map2.entrySet();
        for (Map.Entry<String, CustomStudent> entry : entries) {
            String key = entry.getKey();
            CustomStudent student = entry.getValue();
            map1.put(key, new CustomStudent(student));
        }

        boolean contains1 = map1.containsKey("2023A7PS0014P");
        System.out.println(contains1);
        boolean contains2 = map1.containsValue(new CustomStudent("Himanshu", "CS", 8.5));
        System.out.println(contains2);
        boolean contains3 = map1.containsValue(map1.get("2022B3A7PS0014P"));
        System.out.println(contains3);


        map1.putAll(map2);


        String id = "2023A7PS1001P";
        CustomStudent student = map1.get(id);

        map1.clear();

        System.out.println(keys);
    }
}
