package ro.uaic.info.javatechnologies.optcourses.models;

import java.net.URL;

public class OptionalCourse extends Course {

    private Package optionalPackage;

    public OptionalCourse(String name, int year, int semester, URL url, Lecturer lecturer, int studyGroups, Package optionalPackage) {
        super(name, year, semester, url, lecturer, studyGroups);
        this.optionalPackage = optionalPackage;
    }

    public Package getOptionalPackage() {
        return optionalPackage;
    }

    public void setOptionalPackage(Package optionalPackage) {
        this.optionalPackage = optionalPackage;
    }
}
