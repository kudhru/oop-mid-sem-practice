import java.util.*;

class Practice {
    private int testVariable;
    private Set<String> courses;
    private Map<Integer, String> mapVariable;
    Practice(int testVariable) {
        this.testVariable = testVariable;
        this.courses = new HashSet<>();
        this.courses.add("Java");
        this.courses.add("Python");
        this.courses.add("C#");
        this.mapVariable = new HashMap<>();
        this.mapVariable.put(1, "Java");
        this.mapVariable.put(2, "Python");
        this.mapVariable.put(3, "C#");
        this.mapVariable.put(4, "PHP");
    }

    public Set<String> getCourses() {
        Set<String> courses = new HashSet<>(this.courses);
        return courses;
    }

    public List<String> getStrings() {
//        List<String> list = new ArrayList<>(mapVariable.values());
//        System.out.println(list);
        Iterator<String> iterator = this.mapVariable.values().iterator();
        List<String> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    public List<Integer> getIntegers() {
//        this.mapVariable.keySet();
        List<Integer> integers = new ArrayList<>(this.mapVariable.keySet());
        return integers;
    }
    String overrriddenMethod() {
        return "SuperClass: ChildPractice";
    }

    public boolean equals(Object obj) {
        System.out.println("Practice: Equals class");
        return testVariable == ((Practice) obj).testVariable;
    }
}

public class ChildPractice extends Practice {
    int dummyChildVariable;
    ChildPractice(int dummyChildVariable) {
        super(dummyChildVariable);
        this.dummyChildVariable = dummyChildVariable;
    }

    public int nonoverriddenMethod() {
        return dummyChildVariable;
    }

    public String overrriddenMethod() {
        return "SubClass: ChildPractice";
    }

    public boolean equals(Object obj) {
        System.out.println("ChildPractice: Equals class");
        return dummyChildVariable == ((ChildPractice) obj).dummyChildVariable;
    }

    public static void main(String[] args) {
        ChildPractice cp1 = new ChildPractice(1);
        ChildPractice cp2 = new ChildPractice(1);
        cp1.getStrings();
//        System.out.println(cp1.overrriddenMethod());
//        System.out.println(cp2.overrriddenMethod());
//        System.out.println(cp1.equals(cp2));
//        Set<String> courses = cp1.getCourses();
//        courses.add("C++");
//        System.out.println(courses);
//        Set<String> courses1 = cp1.getCourses();
//        System.out.println(courses1);
//        System.out.println(cp1.nonoverriddenMethod());
//        System.out.println(cp2.nonoverriddenMethod());
//        System.out.println(cp1.dummyChildVariable);
//        System.out.println(cp2.dummyChildVariable);


    }
}
