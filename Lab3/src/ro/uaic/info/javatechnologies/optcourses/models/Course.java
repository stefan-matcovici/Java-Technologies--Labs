package ro.uaic.info.javatechnologies.optcourses.models;

import java.net.URL;

public class Course extends AbstractEntity<String> {

    private String name;
    private int year;
    private int semester;
    private URL url;
    private Lecturer lecturer;
    private int studyGroups;

    public Course(String name, int year, int semester, URL url, Lecturer lecturer, int studyGroups) {
        this.name = name;
        this.year = year;
        this.semester = semester;
        this.url = url;
        this.lecturer = lecturer;
        this.studyGroups = studyGroups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public int getStudyGroups() {
        return studyGroups;
    }

    public void setStudyGroups(int studyGroups) {
        this.studyGroups = studyGroups;
    }
}
