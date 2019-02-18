package ro.uaic.info.javatechnologies.optcourses.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class MandatoryCourseEntity extends CoursesEntity {

}
