package ro.uaic.info.javatechnologies.optcourses.entities;

import javax.persistence.*;

@Entity
@Table(name = "student_prefs", schema = "public", catalog = "optcourses_jpa")
public class StudentPrefsEntity {
    private int pos;
    private int id;
    private StudentsEntity studentsByStudentId;
    private CoursesEntity coursesByOptCourseId;

    @Basic
    @Column(name = "pos", nullable = false)
    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

        StudentPrefsEntity that = (StudentPrefsEntity) o;

        if (pos != that.pos) return false;
        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pos;
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
    @JoinColumn(name = "opt_course_id", referencedColumnName = "id")
    public CoursesEntity getCoursesByOptCourseId() {
        return coursesByOptCourseId;
    }

    public void setCoursesByOptCourseId(CoursesEntity coursesByOptCourseId) {
        this.coursesByOptCourseId = coursesByOptCourseId;
    }
}
