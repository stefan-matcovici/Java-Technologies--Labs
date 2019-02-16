package ro.uaic.info.javatechnologies.optcourses.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses", schema = "public", catalog = "optcourses_jpa")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        discriminatorType = DiscriminatorType.INTEGER,
        name = "type",
        columnDefinition = "smallint"
)
public class CoursesEntity {
    private int id;
    private String name;
    private int year;
    private String semester;
    private String url;
    private int studyGroups;
    private LecturersEntity lecturer;
    private List<StudentsEntity> students = new ArrayList<>();

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
    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Basic
    @Column(name = "url", nullable = false, length = 20)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "study_groups", nullable = false)
    public int getStudyGroups() {
        return studyGroups;
    }

    public void setStudyGroups(int studyGroups) {
        this.studyGroups = studyGroups;
    }

    @ManyToOne
    @JoinTable(name="lecturers_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "lecturer_id")
    )
    public LecturersEntity getLecturer() {
        return lecturer;
    }

    public void setLecturer(LecturersEntity lecturer) {
        this.lecturer = lecturer;
    }

    @ManyToMany(mappedBy = "optionalCourses")
    public List<StudentsEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StudentsEntity> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoursesEntity that = (CoursesEntity) o;

        if (id != that.id) return false;
        if (year != that.year) return false;
        if (studyGroups != that.studyGroups) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (semester != null ? !semester.equals(that.semester) : that.semester != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (semester != null ? semester.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + studyGroups;
        return result;
    }
}
