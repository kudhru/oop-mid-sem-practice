### School Management System

You are tasked with developing a **School Management System** to manage students, teachers, classrooms, and courses within a school. You need to design a program with multiple classes, including a `School` class that will manage all entities. The system should store information on which courses each student is taking and which courses each teacher is teaching. Additionally, the `School` class should be able to generate a course-to-teacher-and-students mapping using this data.

Below are the updated requirements for each class:

#### 1. **Abstract Class: Person**
- Represents a general person in the school.
- Attributes:
    - `protected String name`
    - `protected final int id` - (Unique ID for each person)
- Methods:
    - Constructor (`Person(String name, int id)`)
    - `abstract void displayDetails();` - Abstract method for displaying details of the person.
    - `String getName()` - Method that returns the name of the person.

#### 2. **Class: Student (Extends Person)**
- Represents a student in the school.
- Attributes:
    - `private List<String> courses` - List of courses the student is enrolled in.
    - `private static int totalStudents` - Keeps track of the total number of students.
- Methods:
    - Constructor (`Student(String name, int id, List<String> courses)`)
    - `void addCourse(String course)` - Adds a course to the student's list.
    - `boolean isTakingCourse(String course)` - Checks if the student is enrolled in a particular course.
    - `void displayDetails()` - Overrides the abstract method to display student details.
    - `List<String> getCourses()` - Returns the list of courses the student is enrolled in.
    - `static int getTotalStudents()` - Returns the total number of students.

#### 3. **Class: Teacher (Extends Person)**
- Represents a teacher in the school.
- Attributes:
    - `private Set<String> courses` - Set of courses that the teacher is teaching.
    - `private static final String SCHOOL_NAME = "Green Valley High School"` - A constant representing the school name.
- Methods:
    - Constructor (`Teacher(String name, int id, Set<String> courses)`)
    - `void addCourse(String course)` - Adds a course to the teacher's set of courses.
    - `boolean teachesCourse(String course)` - Checks if the teacher teaches a specific course.
    - `void displayDetails()` - Overrides the abstract method to display teacher details.
    - `Set<String> getCourses()` - Returns the set of courses the teacher is teaching.

#### 4. **Interface: ClassManagement**
- Represents class management for teachers.
- Methods:
    - `void assignStudentToClass(Student student)` - Assigns a student to a class.
    - `List<Student> getClassStudents()` - Returns the list of students in a class.

#### 5. **Class: Classroom (Implements ClassManagement)**
- Represents a classroom in the school.
- Attributes:
    - `private Map<Integer, Student> students` - Map of students assigned to this classroom (using student ID as the key).
    - `private Teacher teacher` - Teacher assigned to this classroom.
- Methods:
    - Constructor (`Classroom(Teacher teacher)`)
    - Implement methods from `ClassManagement`:
        - `void assignStudentToClass(Student student)` - Adds a student to the class.
        - `List<Student> getClassStudents()` - Returns all students in the classroom.
    - `void displayClassInfo()` - Displays the information about the class, including teacher and student details.

#### 6. **Class: School**
- Represents the school and manages all the entities.
- Attributes:
    - `private List<Student> students` - List of all students in the school.
    - `private List<Teacher> teachers` - List of all teachers in the school.
    - `private List<Classroom> classrooms` - List of all classrooms in the school.
    - `private Set<String> courses` - Set of all courses offered in the school.
- Methods:
    - Constructor (`School()`)
    - `void addStudent(Student student)` - Adds a student to the school.
    - `void addTeacher(Teacher teacher)` - Adds a teacher to the school.
    - `void addClassroom(Classroom classroom)` - Adds a classroom to the school.
    - `void addCourse(String course)` - Adds a new course to the list of courses offered.
    - `Map<String, Map<String, Object>> generateCourseMapping()` (unimplemented) - Generates and returns a mapping of courses to teachers and students based on the data stored in the `Teacher` and `Student` classes.
    - `void displaySchoolDetails()` - Displays the details of the entire school, including students, teachers, classrooms, and course information.
    - `List<Teacher> findTeachersByCourse(String course)` - Returns a list of teachers who are teaching a specific course.
    - `List<Student> findStudentsByTeacher(String teacherName)` - Returns a list of students being taught by a specific teacher.
    - `void updateStudentCourses(int studentId, List<String> newCourses)` - Updates the courses for a student with a specific ID.
    - `void reassignTeacherCourse(String course, Teacher newTeacher)` - Reassigns a specific course to a new teacher.

#### 7. **Main Class: SchoolManagementSystem**
- Entry point of the program.
- **Implementation provided below**:

```java
import java.util.*;

public class SchoolManagementSystem {
    public static void main(String[] args) {
        // Creating subjects for students
        List<String> subjects1 = new ArrayList<>(Arrays.asList("Math", "Science"));
        List<String> subjects2 = new ArrayList<>(Arrays.asList("Math", "Art"));

        // Creating Students
        Student student1 = new Student("Alice", 1, subjects1);
        Student student2 = new Student("Bob", 2, subjects2);

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
```

### **Student Tasks:**
1. **Implement the Constructors**:
    - Implement the constructors for `Student`, `Teacher`, `Classroom`, and `School` classes.

2. **Implement the Methods**:
    - Implement the following methods in the `School` class:
        - `generateCourseMapping()`: Generates a mapping of courses to the teacher and students.
        - `findTeachersByCourse(String course)`: Finds and returns a list of teachers who are teaching a particular course.
        - `findStudentsByTeacher(String teacherName)`: Finds and returns a list of students being taught by a specific teacher.
        - `updateStudentCourses(int studentId, List<String> newCourses)`: Updates the list of courses for a student identified by their ID.
        - `reassignTeacherCourse(String course, Teacher newTeacher)`: Reassigns a course to a new teacher.

3. **Implement Polymorphism**:


- Override the `displayDetails()` method in both `Student` and `Teacher` classes to demonstrate polymorphism.

---