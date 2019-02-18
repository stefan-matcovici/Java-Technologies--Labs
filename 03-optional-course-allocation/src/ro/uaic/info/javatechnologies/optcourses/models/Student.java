package ro.uaic.info.javatechnologies.optcourses.models;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

public class Student extends AbstractEntity<Integer> implements Serializable {

    @NotEmpty(message = "{lecturer.firstname.notnull}")
    private String firstName;

    @NotEmpty(message = "{lecturer.lastname.notnull}")
    private String lastName;

    private String email;

    private int year;

    public Student() {
        super();
    }

    public Student(Integer id, String firstName, String lastName, String email, int year) {
        this.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.year = year;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return year == student.year &&
                student.firstName.equals(firstName) &&
                student.lastName.equals(lastName) &&
                student.email.equals(email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, year);
    }

    @Override
    public String toString() {
        return getId() + "," + firstName+","+lastName + "," +email + "," + year;
    }
}
