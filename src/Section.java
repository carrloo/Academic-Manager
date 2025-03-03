import java.util.*;

public class Section {
    private String sectionName;
    private Course course;
    private List<Student> students;
    private List<Teacher> teachers;

    public Section(String sectionName, Course course) {
        this.sectionName = sectionName;
        this.course = course;
        this.students = new ArrayList<>();
    }

    public String getSectionName() { 
        return sectionName; 
    }

    public Course getCourse() { 
        return course; 
    }

    public List<Student> getStudents() { 
        return students; 
    }

    public List<Teacher> getTeachers() { 
        return teachers;
     }


    public void addStudent(Student student) {
        students.add(student);
        System.out.println(student.getFullName() + " added to section: " + sectionName);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        System.out.println(teacher.getFullName() + " assigned to section: " + sectionName);
    }

    public void displayStudents() {
        System.out.println("Students in " + sectionName + ":");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i).getFullName());
        }
    }

    public void displayTeachers() {
        System.out.println("Teachers in " + sectionName + ":");
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println((i + 1) + ". " + teachers.get(i).getFullName());
        }
    }

    public String getCourseName() { 
        return course.getCourseName(); 
    }
}
