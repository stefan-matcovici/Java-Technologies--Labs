package ro.uaic.info.technologies.documentmanager.models;

import java.io.Serializable;

public class Document implements Serializable {
    private Integer id;
    private Integer registrationNumber;
    private String name;
    private User user;

    public Document() {
    }

    public Document(Integer registrationNumber, String name) {
        this.registrationNumber = registrationNumber;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", registrationNumber=" + registrationNumber +
                ", name='" + name + '\'' +
                '}';
    }
}
