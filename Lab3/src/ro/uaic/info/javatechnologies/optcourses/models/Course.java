package ro.uaic.info.javatechnologies.optcourses.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.net.URL;
import java.util.Objects;

public class Course extends AbstractEntity<String> {

    @NotEmpty(message = "{course.name.notnull}")
    private String name;

    @Min(value = 1, message = "{opt-package.year.one}")
    @Max(value = 3, message = "{opt-package.year.three}")
    private int year;

    private Semester semester;
    private URL url;
    private Lecturer lecturer;
    private int studyGroups;

    public Course() {
        lecturer = new Lecturer();
    }

    public Course(String id) {
        setId(id);
    }

    public Course(String id, String name, int year, Semester semester, URL url, Lecturer lecturer, int studyGroups) {
        super(id);
        this.name = name;
        this.year = year;
        this.semester = semester;
        this.url = url;
        this.lecturer = lecturer;
        this.studyGroups = studyGroups;
    }

    public Course(String name, int year, Semester semester, URL url, Lecturer lecturer, int studyGroups) {
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

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return year == course.year &&
                studyGroups == course.studyGroups &&
                Objects.equals(name, course.name) &&
                semester == course.semester &&
                Objects.equals(url, course.url) &&
                Objects.equals(lecturer, course.lecturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, semester, url, lecturer, studyGroups);
    }

    @Override
    public String toString() {
        return getId() + "," + name + "," + year + "," + (semester!=null ? semester.getName() + "," :"") + (url != null ?url.toString() +",":"") + studyGroups +"," + lecturer;
    }
}
