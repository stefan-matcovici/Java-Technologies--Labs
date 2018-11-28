package ro.uaic.info.javatechnologies.optcourses.entities;

import javax.persistence.*;

@Entity
@Table(name = "lecturers_courses", schema = "public", catalog = "optcourses_jpa")
public class LecturersCoursesEntity {
    private int id;
    private LecturersEntity lecturersByLecturerId;
    private CoursesEntity coursesByCourseId;

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

        LecturersCoursesEntity that = (LecturersCoursesEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id")
    public LecturersEntity getLecturersByLecturerId() {
        return lecturersByLecturerId;
    }

    public void setLecturersByLecturerId(LecturersEntity lecturersByLecturerId) {
        this.lecturersByLecturerId = lecturersByLecturerId;
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
