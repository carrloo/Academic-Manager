public abstract class User {
    private String id; 
    private String fullName;
    private String password;
    private String role;

    public User(String id, String fullName, String password, String role) {
        this.id = id;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
    
    public abstract void roleSpecificAction();
}