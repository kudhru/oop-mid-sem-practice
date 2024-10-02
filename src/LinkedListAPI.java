import java.util.LinkedList;

public class LinkedListAPI {
    public static void main(String[] args) {
        LinkedList<String> list1 = new LinkedList<>();

        list1.addFirst("Hello");
        list1.addFirst("World");
        list1.addFirst("Java");
        System.out.println(list1);

        LinkedList<String> list2 = new LinkedList<>();
        list2.add("Hello");
        list2.add("World");
        list2.add("Java");
        System.out.println(list2);

        list1.push("Hello");
        list1.peek();
        list1.pop();
        list1.element();
    }
}
