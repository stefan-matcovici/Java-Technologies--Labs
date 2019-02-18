package ro.uaic.info.javatechnologies.optcourses.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "packages", schema = "public", catalog = "optcourses_jpa")
public class PackagesEntity {
    private int id;
    private int year;
    private String semester;
    private String code;
    private List<OptionalCourseEntity> optionalCourseEntities;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "year", nullable = false)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "semester", nullable = false, length = 7)
    public String getSemester() {
        return semester;
    }

    @Basic
    @Column(name = "code", nullable = true, length = 20)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @OneToMany
    @JoinTable(name="packages_courses",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    public List<OptionalCourseEntity> getOptionalCourseEntities() {
        return optionalCourseEntities;
    }

    public void setOptionalCourseEntities(List<OptionalCourseEntity> optionalCourseEntities) {
        this.optionalCourseEntities = optionalCourseEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PackagesEntity that = (PackagesEntity) o;

        if (id != that.id) return false;
        if (year != that.year) return false;
        if (semester != null ? !semester.equals(that.semester) : that.semester != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + year;
        result = 31 * result + (semester != null ? semester.hashCode() : 0);
        return result;
    }
}
