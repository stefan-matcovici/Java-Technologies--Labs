package ro.uaic.info.javatechnologies.optcourses.models;

import java.net.URL;
import java.util.Objects;

public class OptionalCourse extends Course {

    private OptionalPackage optionalPackage;
    private int remainingPlaces;

    public OptionalCourse() {
        super();
        optionalPackage = new OptionalPackage();
    }

    public OptionalCourse(String id, String name, int year, Semester semester, URL url, int studyGroups) {
        super(id, name, year, semester, url, null, studyGroups);
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

    public int getRemainingPlaces() {
        return remainingPlaces;
    }

    public void setRemainingPlaces(int remainingPlaces) {
        this.remainingPlaces = remainingPlaces;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionalPackage);
    }

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + getYear() + "," + (getSemester()!=null ? getSemester().getName() + "," :"") + (getUrl() != null ?getUrl().toString() +",":"") + getStudyGroups();
    }
}
