import java.util.*;

abstract class Person {
    private String name;
    private final int id;

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
        return "Student [name=" + this.getName() + ", id=" + this.getId() + ", courses=" + courses + "]";
    }

    public List<String> getCourses() {
        List<String> courses = new ArrayList<>(this.courses);
        return courses;
    }

    public void removeCourse(String course) {
        this.courses.remove(course);
    }

    public static int getTotalStudents() {
        return totalStudents;
    }

    @Override
    public boolean equals(Object obj) {
        Student student = (Student) obj;
        return this.getName().equals(student.getName()) &&
                this.getId() == student.getId() && this.getCourses().size() == student.getCourses().size();
    }

    @Override
    public int hashCode() {
//        return Objects.hash(this.getName(), this.getId(), this.getCourses());
        return Objects.hash(this.getName(), this.getId(), this.getCourses().size());
    }
}

class Teacher extends Person {
    private Set<String> courses;
    private static final String SCHOOL_NAME = "Green Valley High School";

    public Teacher(String name, int id) {
        super(name, id);
        courses = new HashSet<>();
    }

    public void addCourse(String course) {
//        if (!courses.contains(course))
        courses.add(course);
    }

    public boolean teachesCourse(String course) {
        return courses.contains(course);
    }

    public String toString() {
        return "Teacher [name=" + this.getName() + ", id=" + this.getId() + ", courses=" + courses + "]";
    }
    @Override
    public void displayDetails() {
        System.out.println(toString());
    }

    public Set<String> getCourses() {
        Set<String> courses = new HashSet<>(this.courses);
        return courses;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Teacher teacher = (Teacher) obj;
        return getId() == teacher.getId() &&
                getName().equals(teacher.getName()) && courses.equals(teacher.courses);

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
        this.teacher = teacher;
        this.students = new HashMap<>();
    }

    @Override
    public void assignStudentToClass(Student student) {
        students.put(student.getId(), student);
    }

    @Override
    public List<Student> getClassStudents() {
        List<Student> studentList = new ArrayList<>(students.values());
        return studentList;
    }

    public void displayClassInfo() {
        /* Write Code here */
    }
}

class CourseDetails {
    private String courseName;
    private Teacher teacher;
    private List<Student> students;

    CourseDetails(String courseName, Teacher teacher) {
        this.courseName = courseName;
        this.teacher = teacher;
        this.students = new ArrayList<>();
    }

    CourseDetails(String courseName, Teacher teacher, List<Student> students) {
        this.courseName = courseName;
        this.teacher = teacher;
        this.students = students;
    }

    public void addStudents(Student student) {
        if (!students.contains(student))
            students.add(student);
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}

class School {
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Classroom> classrooms;
    private Set<String> courses;
    public School() {
        students = new ArrayList<>();
        teachers = new ArrayList<>();
        classrooms = new ArrayList<>();
        courses = new HashSet<>();
    }

    public void addStudent(Student student) {
        if(!students.contains(student)) {
            students.add(student);
        }
    }

    public void addTeacher(Teacher teacher) {
        if(!teachers.contains(teacher)) {
            teachers.add(teacher);
        }
    }

    public void addClassroom(Classroom classroom) {
        if(!classrooms.contains(classroom)) {
            classrooms.add(classroom);
        }
    }

    public void addCourse(String course) {
        courses.add(course);
    }

    public Set<CourseDetails> generateCourseMapping() {
        // let us say there are 15 students in a class
        // there are 4 teachers who are teaching them.
        // each teacher is teaching 2 subjects.
        // Assume that a subject is only taught by
        // for a course,
        // 1. who is the teacher teaching it?
        // 2. who are the students who are taking that course?
        Set<CourseDetails> courseDetailsSet = new HashSet<>();
        // Student class gives us students registered in a course
        // Teacher class gives us courses taught by a teacher

        // Let us use the teachers list to initialize the courseMappings
        Iterator<Teacher> teacherIterator = teachers.iterator();
        while (teacherIterator.hasNext()) {
            Teacher teacher = teacherIterator.next();
            Set<String> courses = teacher.getCourses();
            Iterator<String> courseIterator = courses.iterator();
            while (courseIterator.hasNext()) {
                String course = courseIterator.next();
                CourseDetails courseDetails = new CourseDetails(course, teacher);
                courseDetailsSet.add(courseDetails);
            }
        }

        Iterator<Student> studentIterator = students.iterator();
        while (studentIterator.hasNext()) {
            Student student = studentIterator.next();
            List<String> courses = student.getCourses();
            Iterator<String> courseIterator = courses.iterator();
            while (courseIterator.hasNext()) {
                String course = courseIterator.next();
                // search the courseDetails object from the courseDetailsSet
                Iterator<CourseDetails> courseDetailsIterator = courseDetailsSet.iterator();
                while (courseDetailsIterator.hasNext()) {
                    CourseDetails courseDetails = courseDetailsIterator.next();
                    if(courseDetails.getCourseName().equals(course)) {
                        courseDetails.addStudents(student);
                    }
                }
            }
        }
        return courseDetailsSet;
    }

    public void displaySchoolDetails() {
        /* Write Code here */
    }

    public List<Teacher> findTeachersByCourse(String course) {
        List<Teacher> courseTeachers = new ArrayList<>();
        Iterator<Teacher> teacherIterator = teachers.iterator();
        while (teacherIterator.hasNext()) {
            Teacher teacher = teacherIterator.next();
            if(teacher.getCourses().contains(course)) {
                courseTeachers.add(teacher);
            }
        }
        return courseTeachers;
    }

    public List<Student> findStudentsByTeacher(String teacherName) {
        List<Student> studentsOfTeacher = new ArrayList<>();
        // first get the list of courses taught by this teacher.
        Teacher teacher = null;
        for (int i = 0; i < teachers.size(); i++) {
            teacher = teachers.get(i);
            if(teacher.getName().equals(teacherName)) {
                break;
            }
        }
        Set<String> courses = teacher.getCourses();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            List<String> studentCourses = student.getCourses();
            for (int j = 0; j < studentCourses.size(); j++) {
                String studentCourse = studentCourses.get(j);
                if(courses.contains(studentCourse)) {
                    studentsOfTeacher.add(student);
                    break;
                }
            }
        }

        return studentsOfTeacher;
    }

    public void updateStudentCourses(int studentId, List<String> newCourses) {
        Student student = null;
        for (int i = 0; i < this.students.size(); i++) {
            student = this.students.get(i);
            if(student.getId() == studentId) {
                break;
            }
        }
        List<String> oldCourses = student.getCourses();
        for (int i = 0; i < oldCourses.size(); i++) {
            student.removeCourse(oldCourses.get(i));
        }

        for (int i = 0; i < newCourses.size(); i++) {
            student.addCourse(newCourses.get(i));
        }
    }

    public void reassignTeacherCourse(String course, Teacher newTeacher) {

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
