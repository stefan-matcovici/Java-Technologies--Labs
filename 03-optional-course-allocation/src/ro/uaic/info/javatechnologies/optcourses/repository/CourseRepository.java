package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.entities.CoursesEntity;
import ro.uaic.info.javatechnologies.optcourses.entities.MandatoryCourseEntity;
import ro.uaic.info.javatechnologies.optcourses.entities.OptionalCourseEntity;
import ro.uaic.info.javatechnologies.optcourses.models.Course;
import ro.uaic.info.javatechnologies.optcourses.utils.EntityConverter;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ro.uaic.info.javatechnologies.optcourses.utils.EntityConverter.toMandatoryCourseEntity;

@Stateless
public class CourseRepository extends DataRepository<Course, String> {

    CriteriaBuilder builder;

    public CourseRepository() {
    }

    @PostConstruct
    public void init() {
        builder = optCoursesPU.getCriteriaBuilder();
    }

    public CourseRepository(String schema) {
        super(schema);
    }

    @Override
    public Course getById(String s) {
        return null;
    }

    @Override
    public void save(Course course) {
        optCoursesPU.persist(toMandatoryCourseEntity(course));
    }

    @Override
    public List<Course> getAll() {
        Query query = optCoursesPU.createQuery("SELECT e FROM MandatoryCourseEntity e");
        List<Course> courses = ((Collection<MandatoryCourseEntity>) query.getResultList()).stream().map(EntityConverter::toMandatoryCourse).collect(Collectors.toList());

        return courses;
    }

    public List<Course> getFilteredCourses(String name, Boolean optional, String studyGroups, boolean useName, boolean useOptional, boolean useStudyGroups) {
        CriteriaQuery<CoursesEntity> query = builder.createQuery(CoursesEntity.class);
        Root root;
        if (useOptional) {
            if (optional.equals(Boolean.TRUE)) {
                root = query.from(OptionalCourseEntity.class);
            } else {
                root = query.from(MandatoryCourseEntity.class);
            }
        } else {
            root = query.from(CoursesEntity.class);
        }

        Predicate nameFiltering = builder.like(root.get("name"), "%" + name + "%");
        Predicate studyGroupsFiltering = builder.lessThanOrEqualTo(root.get("studyGroups"), studyGroups);
        Predicate filter = builder.conjunction();
        if (useOptional) {
            filter = builder.and(studyGroupsFiltering);
        }
        if (useName) {
            filter = builder.and(nameFiltering);
        }

        query.where(filter);
        TypedQuery<CoursesEntity> tq = optCoursesPU.createQuery(query);
        List<Course> courses = tq.getResultList().stream().map(EntityConverter::toMandatoryCourse).collect(Collectors.toList());
        return courses;
    }

    @Override
    public void updateEntities(List<Course> entities) {
        for (Course entity : entities) {
            save(entity);
        }
    }
}
