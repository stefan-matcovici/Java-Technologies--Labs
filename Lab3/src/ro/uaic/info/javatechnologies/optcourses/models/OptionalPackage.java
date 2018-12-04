package ro.uaic.info.javatechnologies.optcourses.models;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OptionalPackage extends AbstractEntity<String> {

    @Min(value = 1, message = "{opt-package.year.one}")
    @Max(value = 3, message = "{opt-package.year.three}")
    private Integer year;

    private Semester semester;

    private String code;

    private List<Course> courses;

    public OptionalPackage() {

    }

    public OptionalPackage(String id, String code, Integer year, Semester semester) {
        this.setId(id);
        this.setSemester(semester);
        this.setYear(year);
        this.setCode(code);
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionalPackage that = (OptionalPackage) o;
        return Objects.equals(year, that.year) &&
                semester == that.semester &&
                code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, semester, code);
    }

    @Override
    public String toString() {
        return getId() + "," + getCode() + "," + getYear() + "," + getSemester().getName() + (courses != null? "," + courses.stream().map(Course::getId).collect(Collectors.joining(",")):"");
    }
}
