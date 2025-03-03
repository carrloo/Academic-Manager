import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Course> availableCourses = Arrays.asList(
            new Course("GIN314"),
            new Course("GEL314"),
            new Course("GERE200"),
            new Course("MAT220")
        );
        List<Section> availableSections = Arrays.asList(
            new Section("Section A", availableCourses.get(0)), 
            new Section("Section B", availableCourses.get(0)), 
            new Section("Section A", availableCourses.get(1)), 
            new Section("Section B", availableCourses.get(1)),
            new Section("Section A", availableCourses.get(2)),
            new Section("Section A", availableCourses.get(3))
        );

        UserManager userManager = new UserManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Academic App!");

        while (true) {
            System.out.println("\n── User Login ──");
            System.out.print("Enter User ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            User loggedInUser = userManager.authenticateUser(id, password);

            if (loggedInUser != null) {
                System.out.println("Login successful! Welcome, " + loggedInUser.getFullName() 
                        + " (" + loggedInUser.getRole() + ")");
                boolean keepGoing = true;

                while (keepGoing) {
                    System.out.println("\n── Main Menu ──");
                    System.out.println("1. Perform Role-Specific Action");
                    System.out.println("2. View Schedule");
                    System.out.println("3. Logout");
                    System.out.println("4. Exit Application");
                    System.out.print("Choose an option: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch(choice) {
                        case 1:
                            loggedInUser.roleSpecificAction();
                            if (loggedInUser instanceof Student) {
                                Student student = (Student) loggedInUser;
                                boolean manageStudentOptions = true;
                                while (manageStudentOptions) {
                                    System.out.println("\n── Student Options ──");
                                    System.out.println("1. Manage Sections");
                                    System.out.println("2. View Grades");
                                    System.out.println("3. Back to Main Menu");
                                    System.out.print("Choose an option: ");
                                    int studentChoice = scanner.nextInt();
                                    scanner.nextLine();
                                    switch(studentChoice) {
                                        case 1:
                                            manageStudentSections(student, scanner, availableSections, availableCourses);
                                            break;
                                        case 2:
                                            if (student.getGrades().isEmpty()) {
                                                System.out.println("No Grades Available.");
                                            } else {
                                                student.viewGrades();
                                            }
                                            break;
                                        case 3:
                                            System.out.println("Returning to Main Menu...");
                                            manageStudentOptions = false;
                                            break;
                                        default:
                                            System.out.println("Invalid option! Please choose again.");
                                    }
                                }
                            }
                            else if (loggedInUser instanceof Teacher) {
                                Teacher teacher = (Teacher) loggedInUser;
                                boolean manageTeacherOptions = true;
                                while (manageTeacherOptions) {
                                    System.out.println("\n── Teacher Options ──");
                                    System.out.println("1. Manage Grades");
                                    System.out.println("2. Manage Assignments");
                                    System.out.println("3. View Assigned Sections");
                                    System.out.println("4. View Assigned Courses");
                                    System.out.println("5. Back to Main Menu");
                                    System.out.print("Choose an option: ");
                                    int teacherChoice = scanner.nextInt();
                                    scanner.nextLine();

                                    switch (teacherChoice) {
                                        case 1:
                                            boolean manageGrades = true;
                                            while (manageGrades) {
System.out.println("\n── Grade Management ──");
System.out.println("1. Add Student Grade");
System.out.println("2. Edit Student Grade");
System.out.println("3. Delete Student Grade");
System.out.println("4. View Student Grades");
System.out.println("5. Back to Teacher Options");
System.out.print("Choose an option: ");
int gradeChoice = scanner.nextInt();
scanner.nextLine();
switch (gradeChoice) {
case 1:
                                                        System.out.print("Enter Student ID: ");
String studentId = scanner.nextLine();
                                                        Student targetStudent = getStudentById(studentId);
                                                        if (targetStudent == null) {
                                                            System.out.println("Student not found.");
                                                            break;
                                                        }
                                                        System.out.print("Enter Course Name: ");
                                                        String courseName = scanner.nextLine();
                                                        Course selectedCourse = null;
                                                        for (Course course : availableCourses) {
                                                            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                                                                selectedCourse = course;
                                                                break;
                                                            }
                                                        }
                                                        if (selectedCourse == null) {
                                                            System.out.println("Course not found.");
                                                            break;
                                                        }
                                                        System.out.print("Enter Section Name: ");
                                                        String sectionName = scanner.nextLine();
                                                        Section selectedSection = null;
                                                        for (Section section : availableSections) {
                                                            if (section.getSectionName().equalsIgnoreCase(sectionName) 
                                                                    && section.getCourse().equals(selectedCourse)) {
                                                                selectedSection = section;
                                                                break;
                                                            }
                                                        }
                                                        if (selectedSection == null) {
                                                            System.out.println("Section not found.");
                                                            break;
                                                        }
                                                        System.out.print("Enter Grade Value: ");
                                                        double gradeValue = scanner.nextDouble();
                                                        scanner.nextLine();
                                                        teacher.addStudentGrade(targetStudent, selectedSection, selectedCourse, gradeValue);
                                                        Grade grade = new Grade(targetStudent, selectedSection, selectedCourse, gradeValue);
                                                        targetStudent.getGrades().add(grade);
                                                        break;
                                                    case 2:
                                                        System.out.print("Enter Student ID for grade update: ");
                                                        String editStudentId = scanner.nextLine();
                                                        Student editStudent = getStudentById(editStudentId);
                                                        if (editStudent == null) {
                                                            System.out.println("Student not found.");
                                                            break;
                                                        }
                                                        System.out.print("Enter Course Name: ");
                                                        String editCourseName = scanner.nextLine();
                                                        Course editCourse = null;
                                                        for (Course course : availableCourses) {
                                                            if (course.getCourseName().equalsIgnoreCase(editCourseName)) {
                                                                editCourse = course;
                                                                break;
                                                            }
                                                        }
                                                        if (editCourse == null) {
                                                            System.out.println("Course not found.");
                                                            break;
                                                        }
                                                        System.out.print("Enter New Grade Value: ");
                                                        double newGradeValue = scanner.nextDouble();
                                                        scanner.nextLine();
                                                        teacher.editStudentGrade(editStudent, editCourse, newGradeValue);
                                                        break;
                                                    case 3:
                                                        System.out.print("Enter Student ID for grade deletion: ");
                                                        String delStudentId = scanner.nextLine();
                                                        Student delStudent = getStudentById(delStudentId);
                                                        if (delStudent == null) {
                                                            System.out.println("Student not found.");
                                                            break;
                                                        }
                                                        System.out.print("Enter Course Name: ");
                                                        String delCourseName = scanner.nextLine();
                                                        Course delCourse = null;
                                                        for (Course course : availableCourses) {
                                                            if (course.getCourseName().equalsIgnoreCase(delCourseName)) {
                                                                delCourse = course;
                                                                break;
                                                            }
                                                        }
                                                        if (delCourse == null) {
                                                            System.out.println("Course not found.");
                                                            break;
                                                        }
                                                        teacher.deleteStudentGrade(delStudent, delCourse);
                                                        break;
                                                    case 4:
                                                        teacher.displayStudentGrades();
                                                        break;
                                                    case 5:
                                                        manageGrades = false;
                                                        break;
                                                    default:
                                                        System.out.println("Invalid option. Please try again.");
                                                }
                                            }
                                            break;
                                        case 2:
                                            boolean manageAssignments = true;
                                            while (manageAssignments) {
                                                System.out.println("\n── Assignment Management ──");
                                                System.out.println("1. Add Assignment");
                                                System.out.println("2. Edit Assignment");
                                                System.out.println("3. Delete Assignment");
                                                System.out.println("4. View Assignments");
                                                System.out.println("5. Back to Teacher Options");
                                                System.out.print("Choose an option: ");
                                                int assignmentChoice = scanner.nextInt();
                                                scanner.nextLine();
                                                switch (assignmentChoice) {
                                                    case 1:
                                                        System.out.print("Enter Course Name: ");
                                                        String courseNameA = scanner.nextLine();
                                                        Course selectedCourseA = null;
                                                        for (Course course : availableCourses) {
                                                            if (course.getCourseName().equalsIgnoreCase(courseNameA)) {
                                                                selectedCourseA = course;
                                                                break;
                                                            }
                                                        }
                                                        if (selectedCourseA == null) {
                                                            System.out.println("Course not found.");
                                                            break;
                                                        }
                                                        System.out.print("Enter Section Name: ");
                                                        String sectionNameA = scanner.nextLine();
                                                        Section selectedSectionA = null;
                                                        for (Section section : availableSections) {
                                                            if (section.getSectionName().equalsIgnoreCase(sectionNameA) 
                                                                    && section.getCourse().equals(selectedCourseA)) {
                                                                selectedSectionA = section;
                                                                break;
                                                            }
                                                        }
                                                        if (selectedSectionA == null) {
                                                            System.out.println("Section not found.");
                                                            break;
                                                        }
                                                        System.out.print("Enter Assignment Details: ");
                                                        String assignmentDetails = scanner.nextLine();
                                                        System.out.print("Enter Due Date (DD/MM/YYYY): ");
                                                        String dueDateStr = scanner.nextLine();
                                                        Date dueDate = Date.parseDate(dueDateStr);
                                                        teacher.addAssignment(selectedSectionA, selectedCourseA, assignmentDetails, dueDate);
                                                        break;
                                                    case 2:
                                                        System.out.print("Enter Assignment Index to Edit: ");
                                                        int editIndex = scanner.nextInt();
                                                        scanner.nextLine();
                                                        System.out.print("Enter New Assignment Details: ");
                                                        String newDetails = scanner.nextLine();
                                                        System.out.print("Enter New Due Date (DD/MM/YYYY): ");
                                                        String newDueDateStr = scanner.nextLine();
                                                        Date newDueDate = Date.parseDate(newDueDateStr);
                                                        teacher.editAssignment(editIndex - 1, newDetails, newDueDate);
                                                        break;
                                                    case 3:
                                                        System.out.print("Enter Assignment Index to Delete: ");
                                                        int delIndex = scanner.nextInt();
                                                        scanner.nextLine();
                                                        teacher.deleteAssignment(delIndex - 1);
                                                        break;
                                                    case 4:
                                                        teacher.displayAssignments();
                                                        break;
                                                    case 5:
                                                        manageAssignments = false;
                                                        break;
                                                    default:
                                                        System.out.println("Invalid option. Please try again.");
                                                }
                                            }
                                            break;
                                        case 3:
                                            teacher.displaySections();
                                            break;
                                        case 4:
                                            teacher.displayCourses();
                                            break;
                                        case 5:
                                            System.out.println("Returning to Main Menu...");
                                            manageTeacherOptions = false;
                                            break;
                                        default:
                                            System.out.println("Invalid option. Please try again.");
                                    }
                                }
                            }
                            break;
                        case 2:
                            viewSchedules();
                            break;
                        case 3:
                            System.out.println("\nLogging out...");
                            keepGoing = false;
                            break;
                        case 4:
                            System.out.println("\nExiting the application. Goodbye!");
                            scanner.close();
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid option! Please choose again.");
                    }
                }
            } else {
                System.out.println("Invalid credentials! Please try again.");
            }
        }
    }

    // Helper method for managing student sections
    private static void manageStudentSections(Student student, Scanner scanner, List<Section> availableSections, List<Course> availableCourses) {
        boolean keepManaging = true;
        while (keepManaging) {
            System.out.println("\n── Section Management ──");
            System.out.println("1. Add Section");
            System.out.println("2. Drop Section");
            System.out.println("3. View My Sections");
            System.out.println("4. Back to Student Options");
            System.out.print("Choose an option: ");
            int sectionChoice = scanner.nextInt();
            scanner.nextLine();
            switch (sectionChoice) {
                case 1:
                    System.out.print("Enter Course Name: ");
                    String courseName = scanner.nextLine();
                    Course selectedCourse = null;
                    for (Course course : availableCourses) {
                        if (course.getCourseName().equalsIgnoreCase(courseName)) {
                            selectedCourse = course;
                            break;
                        }
                    }
                    if (selectedCourse == null) {
                        System.out.println("Course not found. Please choose an available course.");
                        continue;
                    }
                    System.out.print("Enter Section Name: ");
                    String sectionName = scanner.nextLine();
                    Section selectedSection = null;
                    for (Section section : availableSections) {
                        if (section.getSectionName().equalsIgnoreCase(sectionName) 
                                && section.getCourse().equals(selectedCourse)) {
                            selectedSection = section;
                            break;
                        }
                    }
                    if (selectedSection == null) {
                        System.out.println("Section not available for this course.");
                    } else {
                        student.enrollInSection(selectedSection);
                    }
                    break;
                case 2:
                    System.out.print("Enter Section Name to Drop: ");
                    String dropSectionName = scanner.nextLine();
                    System.out.print("Enter Course Name to Drop: ");
                    String dropCourseName = scanner.nextLine();
                    boolean found = false;
                    for (Section section : student.getSections()) {
                        if (section.getSectionName().equalsIgnoreCase(dropSectionName)
                                && section.getCourse().getCourseName().equalsIgnoreCase(dropCourseName)) {
                            student.dropSection(section);
                            student.dropCourse(section.getCourse());
                            found = true;
                            System.out.println("Successfully dropped Section: " + dropSectionName + " for Course: " + dropCourseName);
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Section not found for the specified course.");
                    }
                    break;
                case 3:
                    student.displaySections();
                    break;
                case 4:
                    System.out.println("Returning to Student Options...");
                    keepManaging = false;
                    break;
                default:
                    System.out.println("Invalid option! Please choose again.");
            }
        }
    }

    // Helper method to read and display schedules from the CSV file
    private static void viewSchedules() {
        String filePath = "C:\\Users\\First Tek\\Desktop\\Projects\\Test\\schedule.csv";
        System.out.println("\n── Schedule ──");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) { 
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String section = parts[0].trim();
                    String courseName = parts[1].trim();
                    String type = parts[2].trim();
                    String scheduledDate = parts[3].trim();
                    String scheduledTime = parts[4].trim();
                    String students = parts[5].trim();
                    System.out.println("Section: " + section + " | Course: " + courseName 
                        + " | Type: " + type + " | Date: " + scheduledDate 
                        + " | Time: " + scheduledTime + " | Students: " + students);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading schedule file: " + e.getMessage());
        }
    }
    
    // Helper method to retrieve a Student object from the users CSV file using String ID
    private static Student getStudentById(String studentId) {
        String filePath = "C:\\Users\\First Tek\\Desktop\\Projects\\Test\\users.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String idFromFile = parts[0].trim();
                    if (idFromFile.equals(studentId) && parts[3].trim().equalsIgnoreCase("Student")) {
                        String fullName = parts[1].trim();
                        String password = parts[2].trim();
                        return new Student(studentId, fullName, password);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users file: " + e.getMessage());
        }
        return null;
    }
}
