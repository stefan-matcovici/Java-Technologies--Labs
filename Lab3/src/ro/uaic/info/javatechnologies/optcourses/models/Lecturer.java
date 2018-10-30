package ro.uaic.info.javatechnologies.optcourses.models;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class Lecturer extends AbstractEntity<Integer> implements Serializable {

    @NotEmpty(message = "{lecturer.firstname.notnull}")
    private String firstName;

    @NotEmpty(message = "{lecturer.lastname.notnull}")
    private String lastName;

    public Lecturer() {
        super();
    }

    public Lecturer(String firstName) {
        this.firstName = firstName;
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
}
