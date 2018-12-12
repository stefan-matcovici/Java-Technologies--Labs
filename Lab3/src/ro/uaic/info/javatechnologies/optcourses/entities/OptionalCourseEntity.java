package ro.uaic.info.javatechnologies.optcourses.entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
public class OptionalCourseEntity extends CoursesEntity {
    private PackagesEntity packageEntity;
    private int remainingPlaces;

    @ManyToOne
    @JoinTable(name="packages_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "package_id")
    )
    public PackagesEntity getPackageEntity() {
        return packageEntity;
    }

    @Basic
    @Column(name = "remaining_places", nullable = false)
    public int getRemainingPlaces() {
        return remainingPlaces;
    }

    public void setRemainingPlaces(int remainingPlaces) {
        this.remainingPlaces = remainingPlaces;
    }

    public void setPackageEntity(PackagesEntity packageEntity) {
        this.packageEntity = packageEntity;
    }
}
