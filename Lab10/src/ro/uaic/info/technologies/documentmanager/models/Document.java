package ro.uaic.info.technologies.documentmanager.models;

public class Document {
    private Integer id;
    private Integer registrationNumber;
    private String name;

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

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", registrationNumber=" + registrationNumber +
                ", name='" + name + '\'' +
                '}';
    }
}
