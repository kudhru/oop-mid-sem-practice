import java.util.*;

abstract class Person {
    protected String name;
    protected final int id;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public abstract void displayDetails();

    public String getName() {
        /* Write Code here */
    }

    public int getId() {
        /* Write Code here */
    }
}

class Student extends Person {
    private List<String> courses;
    private static int totalStudents = 0;

    public Student(String name, int id) {
        super(name, id);
        courses = new ArrayList<>();
        totalStudents++;
    }

    public void addCourse(String course) {
        if (!courses.contains(course))
            courses.add(course);
//        if (!isTakingCourse(course))
//            courses.add(course);
    }

    public boolean isTakingCourse(String course) {
        return courses.contains(course);
    }

    @Override
    public void displayDetails() {
        System.out.println(toString());
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", id=" + id + ", courses=" + courses + "]";
    }

    public List<String> getCourses() {
        List<String> courses = new ArrayList<>(this.courses);
        return courses;
    }

    public static int getTotalStudents() {
        return totalStudents;
    }

    @Override
    public boolean equals(Object obj) {
        /* Write Code here */
    }

    @Override
    public int hashCode() {
        /* Write Code here */
    }
}

class Teacher extends Person {
    private Set<String> courses;
    private static final String SCHOOL_NAME = "Green Valley High School";

    public Teacher(/* Write Code here */) {
        /* Write Code here */
    }

    public void addCourse(String course) {
        /* Write Code here */
    }

    public boolean teachesCourse(String course) {
        /* Write Code here */
    }

    @Override
    public void displayDetails() {
        /* Write Code here */
    }

    public Set<String> getCourses() {
        /* Write Code here */
    }

    @Override
    public boolean equals(Object obj) {
        /* Write Code here */
    }

    @Override
    public int hashCode() {
        /* Write Code here */
    }
}

interface ClassManagement {
    void assignStudentToClass(Student student);

    List<Student> getClassStudents();
}

class Classroom implements ClassManagement {
    private Map<Integer, Student> students;
    private Teacher teacher;

    public Classroom(Teacher teacher) {
        /* Write Code here */
    }

    @Override
    public void assignStudentToClass(Student student) {
        /* Write Code here */
    }

    @Override
    public List<Student> getClassStudents() {
        /* Write Code here */
    }

    public void displayClassInfo() {
        /* Write Code here */
    }
}

class School {
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Classroom> classrooms;
    private Set<String> courses;
    Double xyz;
    public School(/* Write Code here */) {
        /* Write Code here */
    }

    public void addStudent(Student student) {
        /* Write Code here */
    }

    public void addTeacher(Teacher teacher) {
        /* Write Code here */
    }

    public void addClassroom(Classroom classroom) {
        /* Write Code here */
    }

    public void addCourse(String course) {
        /* Write Code here */
    }

    public Map<String, Map<String, Object>> generateCourseMapping() {
        Map<String, Map<String, Object>> courseMapping = new HashMap<>();

        /* Write Code here */

        return courseMapping;
    }

    public void displaySchoolDetails() {
        /* Write Code here */
    }

    public List<Teacher> findTeachersByCourse(String course) {
        List<Teacher> courseTeachers = new ArrayList<>();
        /* Write Code here */
        return courseTeachers;
    }

    public List<Student> findStudentsByTeacher(String teacherName) {
        List<Student> studentsOfTeacher = new ArrayList<>();
        /* Write Code here */
        return studentsOfTeacher;
    }

    public void updateStudentCourses(int studentId, List<String> newCourses) {
        /* Write Code here */
    }

    public void reassignTeacherCourse(String course, Teacher newTeacher) {
        /* Write Code here */
    }

    public boolean compareEntities(Person p1, Person p2) {
        /* Write Code here */
    }
}

public class SchoolManagementSystem {
    public static void main(String[] args) {
        // Creating subjects for students
//        List<String> subjects1 = new ArrayList<>(Arrays.asList("Math", "Science"));
//        List<String> subjects2 = new ArrayList<>(Arrays.asList("Math", "Art"));

        // Creating Students
//        Student student1 = new Student("Alice", 1, subjects1);
//        Student student2 = new Student("Bob", 2, subjects2);
        Student student1 = new Student("Alice", 1);
        Student student2 = new Student("Bob", 2);

        // add courses for each student
        student1.addCourse("Math");
        student1.addCourse("Science");
        student2.addCourse("Math");
        student2.addCourse("Art");
        // Creating a set of courses for the teacher
        Set<String> teacherCourses = new HashSet<>(Arrays.asList("Math", "Science"));

        // Creating a Teacher
        Teacher teacher1 = new Teacher("Mr. Smith", 101, teacherCourses);

        // Creating a Classroom and assigning teacher
        Classroom classroom1 = new Classroom(teacher1);

        // Assigning students to the classroom
        classroom1.assignStudentToClass(student1);
        classroom1.assignStudentToClass(student2);

        // Creating a School
        School school = new School();

        // Adding entities to the school
        school.addStudent(student1);
        school.addStudent(student2);
        school.addTeacher(teacher1);
        school.addClassroom(classroom1);
        school.addCourse("Math");
        school.addCourse("Science");
        school.addCourse("Art");

        // Updating student courses
        List<String> newCourses = new ArrayList<>(Arrays.asList("Math", "History"));
        school.updateStudentCourses(1, newCourses); // Updating courses for student with ID 1

        // Reassigning teacher course
        Teacher newTeacher = new Teacher("Mrs. Johnson", 102, new HashSet<>());
        school.reassignTeacherCourse("Math", newTeacher);

        // Displaying classroom info
        classroom1.displayClassInfo();

        // Displaying total number of students
        System.out.println("Total number of students: " + Student.getTotalStudents());

        // Finding teachers by course
        List<Teacher> mathTeachers = school.findTeachersByCourse("Math");
        System.out.println("Teachers teaching Math: " + mathTeachers);

        // Finding students by teacher
        List<Student> studentsOfTeacher = school.findStudentsByTeacher("Mr. Smith");
        System.out.println("Students taught by Mr. Smith: " + studentsOfTeacher);

        // Displaying school details
        school.displaySchoolDetails();
    }
}
