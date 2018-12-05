package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.entities.CoursesEntity;
import ro.uaic.info.javatechnologies.optcourses.entities.MandatoryCourseEntity;
import ro.uaic.info.javatechnologies.optcourses.entities.OptionalCourseEntity;
import ro.uaic.info.javatechnologies.optcourses.models.Course;
import ro.uaic.info.javatechnologies.optcourses.models.Semester;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ro.uaic.info.javatechnologies.optcourses.repository.LecturerRepository.toLecturer;
import static ro.uaic.info.javatechnologies.optcourses.repository.LecturerRepository.toLecturerEntity;

public class CourseRepository extends DataRepository<Course, String> {

    CriteriaBuilder builder;

    public CourseRepository() {
        super();
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
    public void save(Course course) throws SQLException {
        optCoursesPU.getTransaction().begin();
        optCoursesPU.persist(toMandatoryCourseEntity(course));
        optCoursesPU.getTransaction().commit();
    }

    @Override
    public List<Course> getAll() throws SQLException, MalformedURLException {
        Query query = optCoursesPU.createQuery("SELECT e FROM MandatoryCourseEntity e");
        List<Course> courses = ((Collection<MandatoryCourseEntity>) query.getResultList()).stream().map(CourseRepository::toMandatoryCourse).collect(Collectors.toList());

        return courses;
    }

    public List<Course> getFilteredCourses(String name, Boolean optional, String studyGroups) {
        CriteriaQuery<CoursesEntity> query = builder.createQuery(CoursesEntity.class);
        Root root;
        if (optional != null) {
            if (optional.equals(Boolean.TRUE)) {
                root = query.from(OptionalCourseEntity.class);
            } else {
                root = query.from(MandatoryCourseEntity.class);
            }
        } else {
            root = query.from(CoursesEntity.class);
        }

        Predicate nameFiltering = builder.like(
                root.<String>get("name"),
                builder.parameter(String.class, "containsCondition"));
        if (!studyGroups.isEmpty()) {

            Predicate studyGroupsFiltering = builder.lessThanOrEqualTo(root.get("studyGroups"), studyGroups);
            query.where(nameFiltering, studyGroupsFiltering);
        } else {
            query.where(nameFiltering);
        }


        TypedQuery<CoursesEntity> tq = optCoursesPU.createQuery(query);
        tq.setParameter("containsCondition", "%" + name + "%");
        List<Course> courses = tq.getResultList().stream().map(CourseRepository::toMandatoryCourse).collect(Collectors.toList());
        return courses;
    }


    static Course toMandatoryCourse(CoursesEntity coursesEntity) {
        Course result = new Course();

        result.setId(String.valueOf(coursesEntity.getId()));
        result.setName(coursesEntity.getName());
        result.setSemester(Semester.valueOf(coursesEntity.getSemester().toUpperCase()));
        result.setStudyGroups(coursesEntity.getStudyGroups());
        try {
            result.setUrl(new URL(coursesEntity.getUrl()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        result.setYear(coursesEntity.getYear());
        result.setLecturer(toLecturer(coursesEntity.getLecturer()));

        return result;
    }

    static MandatoryCourseEntity toMandatoryCourseEntity(Course course) {
        MandatoryCourseEntity result = new MandatoryCourseEntity();

        result.setId(Integer.parseInt(course.getId()));
        result.setName(course.getName());
        result.setSemester(course.getSemester().getName().toUpperCase());
        result.setStudyGroups(course.getStudyGroups());
        if (course.getUrl() != null)
            result.setUrl(course.getUrl().toString());
        result.setYear(course.getYear());
        result.setLecturer(toLecturerEntity(course.getLecturer()));

        return result;
    }

    @Override
    public void updateEntities(List<Course> entities) throws SQLException {
        for (Course entity : entities) {
            save(entity);
        }
    }
}
