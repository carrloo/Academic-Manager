import java.io.*;
import java.util.*;

public class UserManager {
    private static final String FILE_PATH = "C:\\Users\\User\\OneDrive\\Documents\\USEK\\4th Semester\\GIN314\\AcademicApp";
    private List<User> users;

    public UserManager() {
        users = new ArrayList<>();
        loadUsers();
    }

    private void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH+"/users.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 4) {
                    String id = values[0].trim();
                    String name = values[1].trim();
                    String password = values[2].trim();
                    String role = values[3].trim();

                    if (role.equals("Student")) {
                        users.add(new Student(id, name, password));
                    } else if (role.equals("Teacher")) {
                        users.add(new Teacher(id, name, password));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users file: " + e.getMessage());
        }
    }

    public User authenticateUser(String id, String password) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                return user;
            }
        }
        System.out.println("Invalid ID or password.");
        return null;
    }
}