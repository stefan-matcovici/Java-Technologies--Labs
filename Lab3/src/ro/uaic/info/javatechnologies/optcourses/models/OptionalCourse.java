package ro.uaic.info.javatechnologies.optcourses.models;

import java.net.URL;
import java.util.Objects;

public class OptionalCourse extends Course {

    private OptionalPackage optionalPackage;

    public OptionalCourse() {
        optionalPackage = new OptionalPackage();
    }

    public OptionalCourse(String id, String name, int year, Semester semester, URL url, Lecturer lecturer, int studyGroups, OptionalPackage optionalPackage) {
        super(id, name, year, semester, url, lecturer, studyGroups);
        this.optionalPackage = optionalPackage;
    }

    public OptionalCourse(String name, int year, Semester semester, URL url, Lecturer lecturer, int studyGroups, OptionalPackage optionalPackage) {
        super(name, year, semester, url, lecturer, studyGroups);
        this.optionalPackage = optionalPackage;
    }

    public OptionalPackage getOptionalPackage() {
        return optionalPackage;
    }

    public void setOptionalPackage(OptionalPackage optionalPackage) {
        this.optionalPackage = optionalPackage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionalCourse that = (OptionalCourse) o;
        return Objects.equals(optionalPackage, that.optionalPackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionalPackage);
    }
}
