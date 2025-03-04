import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Course> availableCourses = new ArrayList<>();
        availableCourses.add(new Course("GIN314"));
        availableCourses.add(new Course("GEL314"));
        availableCourses.add(new Course("GERE200"));
        availableCourses.add(new Course("MAT220"));
        
        List<Section> availableSections = new ArrayList<>();
    availableSections.add(new Section("Section A", availableCourses.get(0)));
    availableSections.add(new Section("Section B", availableCourses.get(0)));
    availableSections.add(new Section("Section A", availableCourses.get(1)));
    availableSections.add(new Section("Section B", availableCourses.get(1)));
    availableSections.add(new Section("Section A", availableCourses.get(2)));
    availableSections.add(new Section("Section A", availableCourses.get(3)));

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
                System.out.println("Login successful! Welcome, " + loggedInUser.getFullName() +
                                   " (" + loggedInUser.getRole() + ")");
                boolean keepGoing = true;
                while (keepGoing) {
                    System.out.println("\n── Main Menu ──");
                    System.out.println("1. Perform Role-Specific Action");
                    System.out.println("2. View Schedule");
                    System.out.println("3. Logout");
                    System.out.println("4. Exit Application");
                    System.out.print("Choose an option: ");
                    int choice = readInt(scanner);

                    switch (choice) {
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
                                    int studentChoice = readInt(scanner);
                                    switch (studentChoice) {
                                        case 1:
                                            manageStudentSections(student, scanner, availableSections, availableCourses);
                                            break;
                                        case 2:
                                            if (student.getGrades().isEmpty())
                                                System.out.println("No Grades Available.");
                                            else
                                                student.viewGrades();
                                            break;
                                        case 3:
                                            System.out.println("Returning to Main Menu...");
                                            manageStudentOptions = false;
                                            break;
                                        default:
                                            System.out.println("Invalid option! Please choose again.");
                                    }
                                }
                            } else if (loggedInUser instanceof Teacher) {
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
                                    int teacherChoice = readInt(scanner);
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
                                                int gradeChoice = readInt(scanner);
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
                                                        for (int i = 0; i < availableCourses.size(); i++) {
                                                            Course course = availableCourses.get(i);
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
                                                        for (int i = 0; i < availableSections.size(); i++) {
                                                            Section section = availableSections.get(i);
                                                            if (section.getSectionName().equalsIgnoreCase(sectionName) &&
                                                                section.getCourse().equals(selectedCourse)) {
                                                                selectedSection = section;
                                                                break;
                                                            }
                                                        }
                                                        if (selectedSection == null) {
                                                            System.out.println("Section not found.");
                                                            break;
                                                        }
                                                        System.out.print("Enter Grade Value: ");
                                                        double gradeValue;
                                                        if (scanner.hasNextDouble()) {
                                                            gradeValue = scanner.nextDouble();
                                                            scanner.nextLine();
                                                        } else {
                                                            System.out.println("Invalid grade value.");
                                                            scanner.nextLine();
                                                            break;
                                                        }
                                                        teacher.addStudentGrade(targetStudent, selectedSection, selectedCourse, gradeValue);
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
                                                        for (int i = 0; i < availableCourses.size(); i++) {
                                                            Course course = availableCourses.get(i);
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
                                                        double newGradeValue;
                                                        if (scanner.hasNextDouble()) {
                                                            newGradeValue = scanner.nextDouble();
                                                            scanner.nextLine();
                                                        } else {
                                                            System.out.println("Invalid grade value.");
                                                            scanner.nextLine();
                                                            break;
                                                        }
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
                                                        for (int i = 0; i < availableCourses.size(); i++) {
                                                            Course course = availableCourses.get(i);
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
                                            // Assignment Management
                                            boolean manageAssignments = true;
                                            while (manageAssignments) {
                                                System.out.println("\n── Assignment Management ──");
                                                System.out.println("1. Add Assignment");
                                                System.out.println("2. Edit Assignment");
                                                System.out.println("3. Delete Assignment");
                                                System.out.println("4. View Assignments");
                                                System.out.println("5. Back to Teacher Options");
                                                System.out.print("Choose an option: ");
                                                int assignmentChoice = readInt(scanner);
                                                switch (assignmentChoice) {
                                                    case 1:
                                                        System.out.print("Enter Course Name: ");
                                                        String courseNameA = scanner.nextLine();
                                                        Course selectedCourseA = null;
                                                        for (int i = 0; i < availableCourses.size(); i++) {
                                                            Course course = availableCourses.get(i);
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
                                                        for (int i = 0; i < availableSections.size(); i++) {
                                                            Section section = availableSections.get(i);
                                                            if (section.getSectionName().equalsIgnoreCase(sectionNameA) &&
                                                                section.getCourse().equals(selectedCourseA)) {
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
                                                        
                                                        Date dueDate = null;

                                                        while (dueDate == null) {
                                                            try {
                                                                System.out.print("Enter Due Day: ");
                                                                int dueDay = scanner.nextInt();
                                                                System.out.print("Enter Due Month: ");
                                                                int dueMonth = scanner.nextInt();
                                                                System.out.print("Enter Due Year: ");
                                                                int dueYear = scanner.nextInt();
                                                                scanner.nextLine();
                                                                dueDate = new Date(dueDay, dueMonth, dueYear);
                                                                dueDate.setDay(dueDay);
                                                                dueDate.setMonth(dueMonth);
                                                                dueDate.setYear(dueYear);
                                                                if (dueDate.getDay() != dueDay || dueDate.getMonth() != dueMonth || dueDate.getYear() != dueYear) {
                                                                    dueDate = null;
                                                                }
                                                        
                                                            } catch (InputMismatchException e) {
                                                                System.out.println("Invalid input! Please enter numbers only.");
                                                                scanner.nextLine();
                                                            }
                                                        }
                                                        
                                                        System.out.println("Due Date set to: " + dueDate);
                                                        
                                                        
                                                        int dueHour = 0, dueMinute = 0;
                                                        boolean validTime = false;
                                                        while (!validTime) {
                                                            System.out.print("Enter Due Time (HH:MM): ");
                                                            String timeInput = scanner.nextLine();
                                                            String[] timeParts = timeInput.split(":");
                                                            if (timeParts.length == 2) {
                                                                try {
                                                                    dueHour = Integer.parseInt(timeParts[0].trim());
                                                                    dueMinute = Integer.parseInt(timeParts[1].trim());
                                                                    if (dueHour < 0 || dueHour >= 24 || dueMinute < 0 || dueMinute >= 60) {
                                                                        System.out.println("Invalid time values. Please enter time in HH:MM format (24-hour).");
                                                                    } else {
                                                                        validTime = true;
                                                                    }
                                                                } catch (NumberFormatException ex) {
                                                                    System.out.println("Invalid numbers in time. Please use HH:MM format.");
                                                                }
                                                            } else {
                                                                System.out.println("Invalid time format. Please use HH:MM format.");
                                                            }
                                                        }
                                                        teacher.addAssignment(selectedSectionA, selectedCourseA, assignmentDetails, dueDate, dueHour, dueMinute);
                                                        break;
                                                    case 2:
                                                        System.out.print("Enter Assignment Index to Edit: ");
                                                        int editIndex = readInt(scanner);
                                                        System.out.print("Enter New Assignment Details: ");
                                                        String newDetails = scanner.nextLine();
                                                        System.out.print("Enter New Due Date (DD/MM/YYYY): ");
                                                        Date newDueDate = null;
                                                        while (newDueDate == null) {
                                                            String newDueDateStr = scanner.nextLine();
                                                            try {
                                                                newDueDate = Date.parseDate(newDueDateStr);
                                                            } catch (IllegalArgumentException e) {
                                                                System.out.println(e.getMessage());
                                                            }
                                                        }
                                                        System.out.print("Enter New Due Time (HH:MM): ");
                                                        int newDueHour = 0, newDueMinute = 0;
                                                        boolean validNewTime = false;
                                                        while (!validNewTime) {
                                                            String newTimeInput = scanner.nextLine();
                                                            String[] newTimeParts = newTimeInput.split(":");
                                                            if (newTimeParts.length == 2) {
                                                                try {
                                                                    newDueHour = Integer.parseInt(newTimeParts[0].trim());
                                                                    newDueMinute = Integer.parseInt(newTimeParts[1].trim());
                                                                    if (newDueHour < 0 || newDueHour >= 24 || newDueMinute < 0 || newDueMinute >= 60) {
                                                                        System.out.println("Invalid time values. Please enter time in HH:MM format (24-hour).");
                                                                    } else {
                                                                        validNewTime = true;
                                                                    }
                                                                } catch (NumberFormatException ex) {
                                                                    System.out.println("Invalid numbers in time. Please use HH:MM format.");
                                                                }
                                                            } else {
                                                                System.out.println("Invalid time format. Please use HH:MM format.");
                                                            }
                                                        }
                                                        teacher.editAssignment(editIndex - 1, newDetails, newDueDate,newDueHour, newDueMinute);
                                                        break;
                                                    case 3:
                                                        System.out.print("Enter Assignment Index to Delete: ");
                                                        int delIndex = readInt(scanner);
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
                            Schedule.viewSchedules();
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

    private static int readInt(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                scanner.nextLine();
                return num;
            } else {
                System.out.print("Invalid input. Please enter a valid number: ");
                scanner.nextLine();
            }
        }
    }

    private static void manageStudentSections(Student student, Scanner scanner, List<Section> availableSections, List<Course> availableCourses) {
        boolean keepManaging = true;
        while (keepManaging) {
            System.out.println("\n── Section Management ──");
            System.out.println("1. Add Section");
            System.out.println("2. Drop Section");
            System.out.println("3. View My Sections");
            System.out.println("4. Back to Student Options");
            System.out.print("Choose an option: ");
            int sectionChoice = readInt(scanner);
            switch (sectionChoice) {
                case 1:
                    System.out.print("Enter Course Name: ");
                    String courseName = scanner.nextLine();
                    Course selectedCourse = null;
                    for (int i = 0; i < availableCourses.size(); i++) {
                        Course course = availableCourses.get(i);
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
                    for (int i = 0; i < availableSections.size(); i++) {
                        Section section = availableSections.get(i);
                        if (section.getSectionName().equalsIgnoreCase(sectionName) &&
                            section.getCourse().equals(selectedCourse)) {
                            selectedSection = section;
                            break;
                        }
                    }
                    if (selectedSection == null)
                        System.out.println("Section not available for this course.");
                    else
                        student.enrollInSection(selectedSection);
                    break;
                case 2:
                    System.out.print("Enter Section Name to Drop: ");
                    String dropSectionName = scanner.nextLine();
                    System.out.print("Enter Course Name to Drop: ");
                    String dropCourseName = scanner.nextLine();
                    Section sectionToDrop = null;
                    for (int i = 0; i < student.getSections().size(); i++) {
                        Section section = student.getSections().get(i);
                        if (section.getSectionName().equalsIgnoreCase(dropSectionName) &&
                            section.getCourse().getCourseName().equalsIgnoreCase(dropCourseName)) {
                            sectionToDrop = section;
                            break;
                        }
                    }
                    if (sectionToDrop != null) {
                        student.dropSection(sectionToDrop);
                        student.dropCourse(sectionToDrop.getCourse());
                        System.out.println("Successfully dropped Section: " + dropSectionName +
                                           " for Course: " + dropCourseName);
                    } else {
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

    private static Student getStudentById(String studentId) {
        String filePath = "C:\\Users\\User\\OneDrive\\Documents\\USEK\\4th Semester\\GIN314\\AcademicApp";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath+"/users.csv"))) {
            String line = br.readLine();
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
