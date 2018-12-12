package ro.uaic.info.javatechnologies.optcourses.ejb;

import ro.uaic.info.javatechnologies.optcourses.models.Course;
import ro.uaic.info.javatechnologies.optcourses.models.OptionalCourse;
import ro.uaic.info.javatechnologies.optcourses.repository.OptionalCourseRepository;

import javax.ejb.Stateful;
import javax.inject.Inject;

@Stateful
public class AssignementBean {

    @Inject
    private OptionalCourseRepository courseRepository;

    public int getCourseRemainingPlaces(Course course) {
        OptionalCourse repositoryCourseById = courseRepository.getCourseById(course.getId());

        return  repositoryCourseById.getRemainingPlaces();
    }
}
