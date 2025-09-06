import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagementSystem {
    private List<Student> students;
    private List<Course> courses;
    private List<Enrollment> enrollments;
    private Scanner scanner;

    public ManagementSystem() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.enrollments = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addStudent() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student email: ");
        String email = scanner.nextLine();

        Student student = new Student(studentId, name, email);
        students.add(student);
        System.out.println("Student added successfully!");
    }

    public void addCourse() {
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Course course = new Course(courseId, courseName, credits);
        courses.add(course);
        System.out.println("Course added successfully!");
    }

    public void enrollStudentInCourse() {
        System.out.print("Enter student ID to enroll: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter course ID to enroll in: ");
        String courseId = scanner.nextLine();

        Student student = findStudentById(studentId);
        Course course = findCourseById(courseId);

        if (student != null && course != null) {
            Enrollment enrollment = new Enrollment(student, course);
            enrollments.add(enrollment);
            student.getEnrolledCourses().add(course);
            System.out.println("Enrollment successful!");
        } else {
            System.out.println("Student or course not found.");
        }
    }

    public void displayStudentDetails() {
        System.out.print("Enter student ID to display details: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);

        if (student != null) {
            System.out.println("\n--- Student Details ---");
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Name: " + student.getName());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Enrolled Courses:");
            if (student.getEnrolledCourses().isEmpty()) {
                System.out.println("  No courses enrolled.");
            } else {
                for (Course course : student.getEnrolledCourses()) {
                    System.out.println("  - " + course.getCourseName() + " (" + course.getCourseId() + ")");
                }
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private Student findStudentById(String studentId) {
        for (Student s : students) {
            if (s.getStudentId().equals(studentId)) {
                return s;
            }
        }
        return null;
    }

    private Course findCourseById(String courseId) {
        for (Course c : courses) {
            if (c.getCourseId().equals(courseId)) {
                return c;
            }
        }
        return null;
    }

    public void displayMenu() {
        System.out.println("\n--- Student Course Management System ---");
        System.out.println("1. Add a new student");
        System.out.println("2. Add a new course");
        System.out.println("3. Enroll a student in a course");
        System.out.println("4. Display a student's details");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    public void run() {
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addCourse();
                    break;
                case 3:
                    enrollStudentInCourse();
                    break;
                case 4:
                    displayStudentDetails();
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
        scanner.close();
    }

    public static void main(String[] args) {
        ManagementSystem system = new ManagementSystem();
        system.run();
    }
}
