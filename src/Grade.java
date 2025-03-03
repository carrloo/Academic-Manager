public class Grade {
    private Student student;
    private Course course;
    private Section section;
    private double gradeValue;

    public Grade(Student student,Section section, Course course, double gradeValue) {
        this.student = student;
        this.section = section;
        this.course = course;
        this.gradeValue = gradeValue;
    }

    public Student getStudent() {
         return student; 
        }
    
    public void setStudent(Student student){
            this.student = student;
        }

    public Course getCourse() { 
        return course;
     }

     public void setCourse(Course course){
        this.course = course;
     }

    public double getGradeValue() {
         return gradeValue;
         }
    
    public void setGradeValue(double gradeValue) {
        this.gradeValue = gradeValue;
    }
    
    public void setSection(Section section){
        this.section = section;
    }

    public Section getSection(){
        return section;
    }

    @Override
    public String toString() {
        return student.getFullName() + " | " + course.getCourseName() + " | Grade: " + gradeValue;
    }
}
