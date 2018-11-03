package ro.uaic.info.javatechnologies.optcourses.models;

import java.net.URL;

public class OptionalCourse extends Course {

    private OptionalPackage optionalPackage;

    public OptionalCourse() {
        optionalPackage = new OptionalPackage();
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
}
