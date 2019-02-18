package ro.uaic.info.javatechnologies.optcourses.ejb;

import ro.uaic.info.javatechnologies.optcourses.models.OptionalCourse;
import ro.uaic.info.javatechnologies.optcourses.repository.OptionalCourseRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CourseCheckingBean {

    @Inject
    private OptionalCourseRepository courseRepository;

    public int getCourseRemainingPlaces(OptionalCourse course) {
        OptionalCourse repositoryCourseById = courseRepository.getCourseById(course.getId());

        return  repositoryCourseById.getRemainingPlaces();
    }
}
