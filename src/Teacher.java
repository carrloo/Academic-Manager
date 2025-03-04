import java.util.*;

public class Teacher extends User {
    private List<Grade> studentGrades;
    private List<Assignment> assignments;
    private List<Section> sections;
    private List<Course> courses;

    public Teacher(String id, String fullName, String password) {
        super(id, fullName, password, "Teacher");
        this.studentGrades = new ArrayList<>();
        this.assignments = new ArrayList<>();
        this.sections = new ArrayList<>();
        this.courses = new ArrayList<>();
    }
    public void addStudentGrade(Student student, Section section, Course course, double gradeValue) {
        if (gradeValue < 0 || gradeValue > 100) {
            System.out.println("Grade must be between 0 and 100.");
            return;
        }
        Grade grade = new Grade(student, section, course, gradeValue);
        studentGrades.add(grade);        
        student.getGrades().add(grade);   
        System.out.println("Grade " + gradeValue + " added for " 
            + student.getFullName() + " in " + course.getCourseName());
    }

    public void editStudentGrade(Student student, Course course, double newGradeValue) {
        for (int i = 0; i < studentGrades.size(); i++) {
            Grade grade = studentGrades.get(i);
            if (grade.getStudent().equals(student) && grade.getCourse().equals(course)) {
                grade.setGradeValue(newGradeValue);
                System.out.println("Updated grade for " + student.getFullName() + " in " + course.getCourseName() + ", new grade: " + newGradeValue);
                return;
            }
        }
        System.out.println("No grade found for " + student.getFullName() + " in " + course.getCourseName());
    }

    public void deleteStudentGrade(Student student, Course course) {
        for (int i = 0; i < studentGrades.size(); i++) {
            Grade grade = studentGrades.get(i);
            if (grade.getStudent().equals(student) && grade.getCourse().equals(course)) {
                studentGrades.remove(i);
                System.out.println("Deleted grade for " + student.getFullName() + " in " + course.getCourseName());
                return;
            }
        }
        System.out.println("No grade found for " + student.getFullName() + " in " + course.getCourseName());
    }

    public void addAssignment(Section section, Course course, String assignmentDetails, Date dueDate, int newHour, int newMinute) {
        if (!Schedule.isTestConflict(dueDate, newHour, newMinute)) {
            Assignment assignment = new Assignment(section, course, assignmentDetails, dueDate, newHour, newMinute);
            assignments.add(assignment);
            System.out.println("Assignment added for " + course.getCourseName() + ": " + assignmentDetails +
                    " (Due: " + dueDate + " at " + String.format("%02d:%02d", newHour, newMinute) + ")");
        } else {
            System.out.println("Time conflict! Another test is already scheduled at this time.");
        }
    }
    public void editAssignment(int index, String newDetails, Date newDueDate,int newDueHour, int newDueMinute) {
        if (index >= 0 && index < assignments.size()) {
            Assignment assignment = assignments.get(index);
            assignment.setDetails(newDetails);
            assignment.setDueDate(newDueDate);
            assignment.setHour(newDueHour);
            assignment.setMinute(newDueMinute);
            System.out.println("Assignment updated: " + newDetails + " (Due Date: " + newDueDate +  " at " + String.format("%02d:%02d", newDueHour, newDueMinute) + ")");
        } else {
            System.out.println("Invalid assignment index.");
        }
    }

    public void deleteAssignment(int index) {
        if (index >= 0 && index < assignments.size()) {
            System.out.println("Deleted assignment: " + assignments.get(index).getAssignmentDetails());
            assignments.remove(index);
        } else {
            System.out.println("Invalid assignment index.");
        }
    }

    public void addSection(Section section) {
        sections.add(section);
        System.out.println("Assigned to section: " + section.getSectionName());
    }

    public void addCourse(Course course) {
        courses.add(course);
        System.out.println("Assigned to course: " + course.getCourseName());
    }

    public void displayStudentGrades() {
        System.out.println("Student Grades:");
        for (int i = 0; i < studentGrades.size(); i++) {
            Grade grade = studentGrades.get(i);
            System.out.println((i + 1) + ". " + grade.getStudent().getFullName() + " | " + grade.getCourse().getCourseName() + " | Grade: " + grade.getGradeValue());
        }
    }

    public void displayAssignments() {
        System.out.println("Assignments:");
        for (int i = 0; i < assignments.size(); i++) {
            Assignment assignment = assignments.get(i);
            System.out.println((i + 1) + "assignments." + assignment.getCourse().getCourseName() + " | " + assignment.getAssignmentDetails() + " (Due: " + assignment.getDueDate() + ")");
        }
    }

    public void displaySections() {
        System.out.println("Assigned Sections:");
        for (int i = 0; i < sections.size(); i++) {
            System.out.println((i + 1) + " sections;" + sections.get(i).getSectionName());
        }
    }

    public void displayCourses() {
        System.out.println("Assigned Courses:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + " courses;" + courses.get(i).getCourseName());
        }
    }

    @Override
    public void roleSpecificAction() {
        System.out.println(getFullName() + " is accessing teacher materials.");
    }
}
