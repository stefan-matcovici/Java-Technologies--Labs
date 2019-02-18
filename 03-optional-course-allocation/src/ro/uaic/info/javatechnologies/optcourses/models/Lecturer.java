package ro.uaic.info.javatechnologies.optcourses.models;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.net.URL;
import java.util.Objects;

public class Lecturer extends AbstractEntity<Integer> implements Serializable {

    @NotEmpty(message = "{lecturer.firstname.notnull}")
    private String firstName;

    @NotEmpty(message = "{lecturer.lastname.notnull}")
    private String lastName;

    private String email;

    private URL url;

    public Lecturer() {
        super();
    }

    public Lecturer(Integer id, String firstName, String lastName, String email, URL url) {
        this.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.url = url;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecturer lecturer = (Lecturer) o;
        return Objects.equals(firstName, lecturer.firstName) &&
                Objects.equals(lastName, lecturer.lastName) &&
                Objects.equals(email, lecturer.email) &&
                Objects.equals(url, lecturer.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, url);
    }

    @Override
    public String toString() {
        return getId() + "," + firstName+","+lastName + "," +email + "," + url.toString();
    }
}
