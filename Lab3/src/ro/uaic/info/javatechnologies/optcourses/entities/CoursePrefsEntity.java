package ro.uaic.info.javatechnologies.optcourses.entities;

import javax.persistence.*;

@Entity
@Table(name = "course_prefs", schema = "public", catalog = "optcourses_jpa")
public class CoursePrefsEntity {
    private Double value;
    private int id;
    private CoursesEntity coursesByOptCourseId;
    private CoursesEntity coursesByCourseId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "value", nullable = true, precision = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoursePrefsEntity that = (CoursePrefsEntity) o;

        if (id != that.id) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + id;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "opt_course_id", referencedColumnName = "id")
    public CoursesEntity getCoursesByOptCourseId() {
        return coursesByOptCourseId;
    }

    public void setCoursesByOptCourseId(CoursesEntity coursesByOptCourseId) {
        this.coursesByOptCourseId = coursesByOptCourseId;
    }

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    public CoursesEntity getCoursesByCourseId() {
        return coursesByCourseId;
    }

    public void setCoursesByCourseId(CoursesEntity coursesByCourseId) {
        this.coursesByCourseId = coursesByCourseId;
    }
}
