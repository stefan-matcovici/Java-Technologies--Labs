package ro.uaic.info.javatechnologies.optcourses.entities;

import javax.persistence.*;

@Entity
@Table(name = "grades", schema = "public", catalog = "optcourses_jpa")
public class GradesEntity {
    private Integer grade;
    private int id;
    private StudentsEntity studentsByStudentId;
    private CoursesEntity coursesByCourseId;

    @Basic
    @Column(name = "grade", nullable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GradesEntity that = (GradesEntity) o;

        if (id != that.id) return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = grade != null ? grade.hashCode() : 0;
        result = 31 * result + id;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    public StudentsEntity getStudentsByStudentId() {
        return studentsByStudentId;
    }

    public void setStudentsByStudentId(StudentsEntity studentsByStudentId) {
        this.studentsByStudentId = studentsByStudentId;
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
