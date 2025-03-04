import java.util.*;

public class Student extends User {
    private List<Section> sections;
    private List<Grade> grades;
    private List<Course> courses;
    private List<Assignment> assignments;

    public Student(String id, String fullName, String password) {
        super(id, fullName, password, "Student");
        this.sections = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.grades = new ArrayList<>();
        this.assignments = new ArrayList<>();
    }

    public List<Section> getSections() { 
        return sections; 
    }
    public List<Course> getCourses() { 
        return courses; 
    }
    public List<Grade> getGrades(){
        return grades;
    }

    public void enrollInSection(Section section) {
        if (!sections.contains(section)) {
            sections.add(section);
            section.addStudent(this);
        } else {
            System.out.println("Already enrolled in section: " + section.getSectionName());
        }
    }

    public void dropSection(Section section) {
        for (int i = 0; i < sections.size(); i++) {
            if (sections.get(i).equals(section)) {
                sections.remove(i);
                section.getStudents().remove(this);
                System.out.println(getFullName() + " left section: " + section.getSectionName());
                return;
            }
        }
        System.out.println("Not enrolled in section: " + section.getSectionName());
    }

    public void displaySections() {
        System.out.println("\n Sections for " + getFullName() + ":");
        if (sections.isEmpty()) {
            System.out.println("No sections enrolled.");
        } else {
            for (int i = 0; i < sections.size(); i++) {
                System.out.println("-" + sections.get(i).getSectionName() + " (" + sections.get(i).getCourseName() + ")");
            }
        }
    }

    public void displayCourses() {
        System.out.println("\n Courses for " + getFullName() + ":");
        if (courses.isEmpty()) {
            System.out.println("No courses enrolled.");
        } else {
            for (int i = 0; i < courses.size(); i++) {
                System.out.println("-" + courses.get(i).getCourseName());
            }
        }
    }
    

    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            System.out.println(getFullName() + " added course: " + course.getCourseName());
        } else {
            System.out.println("Already enrolled in course: " + course.getCourseName());
        }
    }

    public void dropCourse(Course course) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).equals(course)) {
                courses.remove(i);
                System.out.println(getFullName() + " dropped course: " + course.getCourseName());
                return;
            }
        }
        System.out.println("Not enrolled in course: " + course.getCourseName());
    }

    public void viewGrades() {
        System.out.println("\n Grades for " + getFullName() + ":");
    
        if (grades.isEmpty()) {
            System.out.println("No Grades Available.");
        } else {
            for (int i = 0; i < grades.size(); i++) {
                Grade grade = grades.get(i);
                System.out.println((i + 1) + ". " + grade.getCourse().getCourseName() + " | Grade: " + grade.getGradeValue());
            }
        }
    }
    

    public void viewAssignments() {
        System.out.println("Assignments for " + getFullName() + ":");
        if (assignments.isEmpty()) {
            System.out.println("No assignments available.");
        }
        for (int i = 0; i < assignments.size(); i++) {
            Assignment assignment = assignments.get(i);
            System.out.println((i + 1) + "number of assignments." + assignment.getCourse().getCourseName() + " | " + assignment.getAssignmentDetails() + " (Due: " + assignment.getDueDate() + ")");
        }
    }

    @Override
    public void roleSpecificAction() {
        System.out.println(getFullName() + " is accessing student materials.");
    }
}
