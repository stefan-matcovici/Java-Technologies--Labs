package ro.uaic.info.javatechnologies.optcourses.models;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class OptionalPackage extends AbstractEntity<String> {

    @Min(value = 1, message = "{opt-package.year.one}")
    @Max(value = 3, message = "{opt-package.year.three}")
    private Integer year;

    private Semester semester;

    public OptionalPackage() {

    }

    public OptionalPackage(String id, Integer year, Semester semester) {
        this.setId(id);
        this.setSemester(semester);
        this.setYear(year);
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

    @Override
    public String toString() {
        return "OptionalPackage{" +
                "year=" + year +
                ", semester=" + semester +
                '}';
    }
}
