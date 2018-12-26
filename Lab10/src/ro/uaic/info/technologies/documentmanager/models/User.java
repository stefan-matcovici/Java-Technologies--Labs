package ro.uaic.info.technologies.documentmanager.models;

public class User {
    private Integer id;
    private String username;
    private String password;
    private UserType type;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public enum UserType {
        guest("guest"),
        admin("admin");

        UserType(String value) {
            this.value = value;
        }
        private String value;

        public String getValue() {
            return value;
        }
    }
}
