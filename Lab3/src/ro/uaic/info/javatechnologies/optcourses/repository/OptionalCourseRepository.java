package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.entities.CoursesEntity;
import ro.uaic.info.javatechnologies.optcourses.entities.OptionalCourseEntity;
import ro.uaic.info.javatechnologies.optcourses.models.CoursePreferenceAmongStudents;
import ro.uaic.info.javatechnologies.optcourses.models.OptionalCourse;
import ro.uaic.info.javatechnologies.optcourses.utils.EntityConverter;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ro.uaic.info.javatechnologies.optcourses.utils.EntityConverter.toOptionalCourse;
import static ro.uaic.info.javatechnologies.optcourses.utils.EntityConverter.toOptionalCourseEntity;

@Stateless
public class OptionalCourseRepository extends DataRepository<OptionalCourse, String> {

    public OptionalCourseRepository() {
    }

    public OptionalCourseRepository(String schema) {
        super(schema);
    }

    @Override
    public OptionalCourse getById(String s) {
        Query query = optCoursesPU.createQuery("SELECT c FROM OptionalCourseEntity c where c.id = " + s);
        List<OptionalCourse> courses = ((Collection<OptionalCourseEntity>) query.getResultList()).stream().map(EntityConverter::toOptionalCourse).collect(Collectors.toList());

        return courses.get(0);
    }

    public List<OptionalCourse> getByYear(int year) {
        Query query = optCoursesPU.createQuery("SELECT c FROM OptionalCourseEntity c where c.year = " + year);
        List<OptionalCourse> courses = ((Collection<OptionalCourseEntity>) query.getResultList()).stream().map(EntityConverter::toOptionalCourse).collect(Collectors.toList());

        return courses;
    }

    @Override
    public void save(OptionalCourse optionalCourse) {
        optCoursesPU.persist(toOptionalCourseEntity(optionalCourse));
    }

    @Override
    public List<OptionalCourse> getAll() {
        return null;
    }

    public OptionalCourse getCourseById(String id) {
        Query query = optCoursesPU.createQuery("SELECT e FROM OptionalCourseEntity e where e.id = " + id);

        return toOptionalCourse((OptionalCourseEntity) query.getResultList().get(0));
    }

    public List<CoursePreferenceAmongStudents> getCoursesPreferenesAmongStudents() {
        Query query = optCoursesPU.createQuery("SELECT course, count(course.id) FROM CoursesEntity course join StudentPrefsEntity pref where pref.coursesByOptCourseId=course group by course");
        List resultList = query.getResultList();
        List<CoursePreferenceAmongStudents> coursePreferenceAmongStudents = new ArrayList<>();
        int size = resultList.size();
        for (int i = 0; i < size; i++) {
            Object[] currentObject = (Object[]) resultList.get(i);
            CoursePreferenceAmongStudents coursePreferenceAmongStudent = new CoursePreferenceAmongStudents();
            coursePreferenceAmongStudent.setCourse(EntityConverter.toMandatoryCourse((CoursesEntity) currentObject[0]));
            coursePreferenceAmongStudent.setPreference(100 * ((Long) currentObject[1]).doubleValue() / size);

            coursePreferenceAmongStudents.add(coursePreferenceAmongStudent);
        }
        return coursePreferenceAmongStudents;
    }

    private CoursePreferenceAmongStudents toCoursePreferenceAmongStudents(CoursesEntity c) {
        CoursePreferenceAmongStudents coursePreferenceAmongStudents = new CoursePreferenceAmongStudents();
        coursePreferenceAmongStudents.setCourse(EntityConverter.toMandatoryCourse(c));
        return null;
    }


    @Override
    public void updateEntities(List<OptionalCourse> entities) {

    }
}
