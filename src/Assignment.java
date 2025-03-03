public class Assignment {
    private Section section;
    private Course course;
    private String assignmentDetails;
    private Date dueDate;
    private int hour;
    private int minute;

    public Assignment(Section section, Course course, String assignmentDetails, Date dueDate, int hour, int minute) {
        this.section = section;
        this.course = course;
        this.assignmentDetails = assignmentDetails;
        this.dueDate = dueDate;
        this.hour = hour;
        this.minute = minute;
    }

    public Section getSection() { return section; }
    public Course getCourse() { return course; }
    public String getAssignmentDetails() { return assignmentDetails; }
    public void setDetails(String assignmentDetails) { this.assignmentDetails= assignmentDetails; }
    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    public int getHour() { return hour; }
    public int getMinute() { return minute; }
}

