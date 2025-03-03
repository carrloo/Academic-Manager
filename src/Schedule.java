import java.io.*;

public class Schedule {
    private Course course;
    private String type;
    private Date scheduledDate;
    private int hour;
    private int minute;
    private static final String SCHEDULE_FILE = "C:\\Users\\User\\OneDrive\\Documents\\USEK\\4th Semester\\GIN314\\AcademicApp";

    public Schedule(Course course, String type, Date scheduledDate, int hour, int minute) {
        this.course = course;
        this.type = type;
        this.scheduledDate = scheduledDate;
        this.hour = hour;
        this.minute = minute;
    }

    public Course getCourse() {
        return course;
     }

    public String getType() { 
        return type; 
    }
    public Date getScheduledDate() { 
        return scheduledDate; 
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }
    public int getHour() { 
        return hour; 
    }
    public int getMinute() { 
        return minute; 
    }
    public void setHour(int hour) { 
        this.hour = hour; 
    }
    public void setMinute(int minute) { 
        this.minute = minute; 
    }

    public void saveToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCHEDULE_FILE+"/schedule.csv", true))) {
            writer.write(course.getCourseName() + "," + getType() + "," + getScheduledDate() + "," + getHour() + ":" + String.format("%02d", getMinute()) + "\n");
        } catch (IOException e) {
            System.out.println("Error saving schedule: " + e.getMessage());
        }
    }
    public static boolean isTestConflict(Date scheduledDate, int newHour, int newMinute) {
        try (BufferedReader reader = new BufferedReader(new FileReader("schedule.csv"))) { 
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 6 && values[2].trim().equalsIgnoreCase("Test")) {
                    String[] timeParts = values[4].trim().split(":");
                    int existingHour = Integer.parseInt(timeParts[0]);
                    int existingMinute = Integer.parseInt(timeParts[1]);
                    String[] dateParts = values[3].split("/");
                    Date existingDate = new Date(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
    
                    if (existingDate.equals(scheduledDate) && existingHour == newHour && existingMinute == newMinute) {
                        return true;
                    }
                }
            }
        }

    catch (IOException e) {
            System.out.println("No existing test schedules found.");
        }
        return false;

}

    @Override
    public String toString() {
        return course.getCourseName() + " | " + getType() + " (Scheduled: " + getScheduledDate() + " at " + getHour() + ":" + String.format("%02d", getMinute()) + ")";
    }
}
