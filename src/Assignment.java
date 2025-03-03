public class Assignment {
    private Course course;
    private Section section;
    private String details;
    private Date dueDate;

    public Assignment(Section section, Course course, String details, Date dueDate) {
        this.section = section;
        this.course = course;
        this.details = details;
        this.dueDate = dueDate;
    }

    public Section getSection() {
        return section;
    }
    
    public Course getCourse() { 
        return course; 
    }
    public void setCourse(Course course){
        this.course = course;
     }
    
    public void setSection(Section section){
        this.section = section;
    }

    public String getDetails() { 
        return details;
     }
    public void setDetails(String details) {
        this.details = details;
     }

    public Date getDueDate() { 
        return dueDate; 
    }

    public void setDueDate(Date dueDate) { 
        this.dueDate = dueDate;
     }
}
