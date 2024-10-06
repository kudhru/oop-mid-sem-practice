package full_solution;

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
        return name;
    }

    public int getId() {
        return id;
    }
}

class Student extends Person {
    private List<String> courses;
    private static int totalStudents = 0;

    public Student(String name, int id, List<String> courses) {
        super(name, id);
        this.courses = new ArrayList<>(courses);
        totalStudents++;
    }

    public void addCourse(String course) {
        if (!courses.contains(course)) {
            courses.add(course);
        }
    }

    public boolean isTakingCourse(String course) {
        return courses.contains(course);
    }

    @Override
    public void displayDetails() {
        System.out.println("Student ID: " + id + ", Name: " + name + ", Courses: " + courses);
    }

    public List<String> getCourses() {
        return courses;
    }

    public static int getTotalStudents() {
        return totalStudents;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return id == student.id && Objects.equals(name, student.name) && Objects.equals(courses, student.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, courses);
    }
}

class Teacher extends Person {
    private Set<String> courses;
    private static final String SCHOOL_NAME = "Green Valley High School";

    public Teacher(String name, int id, Set<String> courses) {
        super(name, id);
        this.courses = new HashSet<>(courses);
    }

    public void addCourse(String course) {
        courses.add(course);
    }

    public boolean teachesCourse(String course) {
        return courses.contains(course);
    }

    @Override
    public void displayDetails() {
        System.out.println("Teacher ID: " + id + ", Name: " + name + ", Courses: " + courses + ", School: " + SCHOOL_NAME);
    }

    public Set<String> getCourses() {
        return courses;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Teacher teacher = (Teacher) obj;
//        return id == teacher.id && Objects.equals(name, teacher.name) && Objects.equals(courses, teacher.courses);
        return id == teacher.id &&
                name.equals(teacher.name) && courses.equals(teacher.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, courses);
    }
}

interface ClassManagement {
    void assignStudentToClass(Student student);

    List<Student> getClassStudents();
}

class Classroom implements ClassManagement {
    private Map<Integer, Student> students = new HashMap<>();
    private Teacher teacher;

    public Classroom(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public void assignStudentToClass(Student student) {
        students.put(student.getId(), student);
    }

    @Override
    public List<Student> getClassStudents() {
        return new ArrayList<>(students.values());
    }

    public void displayClassInfo() {
        System.out.println("Class Teacher: " + teacher.getName());
        System.out.println("Students in Class:");
        for (Student student : students.values()) {
            student.displayDetails();
        }
    }
}

class School {
    private List<Student> students = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();
    private List<Classroom> classrooms = new ArrayList<>();
    private Set<String> courses = new HashSet<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void addClassroom(Classroom classroom) {
        classrooms.add(classroom);
    }

    public void addCourse(String course) {
        courses.add(course);
    }

    public Map<String, Map<String, Object>> generateCourseMapping() {
        Map<String, Map<String, Object>> courseMapping = new HashMap<>();

        for (String course : courses) {
            Map<String, Object> details = new HashMap<>();
            List<Student> enrolledStudents = new ArrayList<>();
            Teacher assignedTeacher = null;

            // Find the teacher teaching the course
            for (Teacher teacher : teachers) {
                if (teacher.teachesCourse(course)) {
                    assignedTeacher = teacher;
                    break;
                }
            }

            // Find the students enrolled in the course
            for (Student student : students) {
                if (student.isTakingCourse(course)) {
                    enrolledStudents.add(student);
                }
            }

            details.put("teacher", assignedTeacher);
            details.put("students", enrolledStudents);
            courseMapping.put(course, details);
        }

        return courseMapping;
    }

    public void displaySchoolDetails() {
        System.out.println("School Details:");
        System.out.println("Courses Offered: " + courses);
        System.out.println("Students:");
        for (Student student : students) {
            student.displayDetails();
        }
        System.out.println("Teachers:");
        for (Teacher teacher : teachers) {
            teacher.displayDetails();
        }
        System.out.println("Classrooms:");
        for (Classroom classroom : classrooms) {
            classroom.displayClassInfo();
        }
    }

    public List<Teacher> findTeachersByCourse(String course) {
        List<Teacher> courseTeachers = new ArrayList<>();
        for (Teacher teacher : teachers) {
            if (teacher.teachesCourse(course)) {
                courseTeachers.add(teacher);
            }
        }
        return courseTeachers;
    }

    public List<Student> findStudentsByTeacher(String teacherName) {
        List<Student> studentsOfTeacher = new ArrayList<>();
        for (Teacher teacher : teachers) {
            if (teacher.getName().equals(teacherName)) {
                for (String course : teacher.getCourses()) {
                    for (Student student : students) {
                        if (student.isTakingCourse(course)) {
                            if (!studentsOfTeacher.contains(student)) {
                                studentsOfTeacher.add(student);
                            }
                        }
                    }
                }
            }
        }
        return studentsOfTeacher;
    }

    public void updateStudentCourses(int studentId, List<String> newCourses) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                student.getCourses().clear();
                student.getCourses().addAll(newCourses);
                break;
            }
        }
    }

    public void reassignTeacherCourse(String course, Teacher newTeacher) {
        // Remove the course from the previous teacher
        for (Teacher teacher : teachers) {
            if (teacher.teachesCourse(course)) {
                teacher.getCourses().remove(course);
            }
        }
        // Add the course to the new teacher
        newTeacher.addCourse(course);
        if (!teachers.contains(newTeacher)) {
            addTeacher(newTeacher);
        }
    }

    public boolean compareEntities(Person p1, Person p2) {
        return p1.equals(p2);
    }
}

public class SchoolManagementSystem {
    public static void main(String[] args) {
        // Creating subjects for students
        List<String> subjects1 = new ArrayList<>(Arrays.asList("Math", "Science"));
        List<String> subjects2 = new ArrayList<>(Arrays.asList("Math", "Art"));

        // Creating Students
        Student student1 = new Student("Alice", 1, subjects1);
        Student student2 = new Student("Bob", 2, subjects2);
        Student student3 = new Student("Alice", 1, subjects1); // Another instance but with the same data as student1

        // Creating a set of courses for the teacher
        Set<String> teacherCourses = new HashSet<>(Arrays.asList("Math", "Science"));

        // Creating Teachers
        Teacher teacher1 = new Teacher("Mr. Smith", 101, teacherCourses);
        Teacher teacher2 = new Teacher("Mr. Smith", 101, teacherCourses); // Another instance but with the same data as teacher1

        // Creating a Classroom and assigning teacher
        Classroom classroom1 = new Classroom(teacher1);

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

        // Comparing students
        System.out.println("Are student1 and student2 equal? " + school.compareEntities(student1, student2));
        System.out.println("Are student1 and student3 equal? " + school.compareEntities(student1, student3));

        // Comparing teachers
        System.out.println("Are teacher1 and teacher2 equal? " + school.compareEntities(teacher1, teacher2));
    }
}
