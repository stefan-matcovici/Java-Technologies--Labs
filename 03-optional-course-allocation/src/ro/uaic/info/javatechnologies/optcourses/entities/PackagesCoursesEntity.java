package ro.uaic.info.javatechnologies.optcourses.entities;

import javax.persistence.*;

@Entity
@Table(name = "packages_courses", schema = "public", catalog = "optcourses_jpa")
public class PackagesCoursesEntity {
    private int id;
    private PackagesEntity packagesByPackageId;
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

        PackagesCoursesEntity that = (PackagesCoursesEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "id")
    public PackagesEntity getPackagesByPackageId() {
        return packagesByPackageId;
    }

    public void setPackagesByPackageId(PackagesEntity packagesByPackageId) {
        this.packagesByPackageId = packagesByPackageId;
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
