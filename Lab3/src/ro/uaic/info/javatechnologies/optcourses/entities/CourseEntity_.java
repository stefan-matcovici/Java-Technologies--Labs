package ro.uaic.info.javatechnologies.optcourses.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CoursesEntity.class)
public abstract class CourseEntity_ {
    public static volatile SingularAttribute<CoursesEntity, String> name;
    public static volatile SingularAttribute<CoursesEntity, Integer> studyGroups;

}
