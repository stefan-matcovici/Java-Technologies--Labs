package ro.uaic.info.javatechnologies.optcourses.beans.course;

import ro.uaic.info.javatechnologies.optcourses.beans.DataView;
import ro.uaic.info.javatechnologies.optcourses.models.Course;
import ro.uaic.info.javatechnologies.optcourses.repository.CourseRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("courseViewBean")
@RequestScoped
public class CourseViewBean extends DataView<Course, String> {

    @EJB
    private CourseRepository courseRepository;

    public CourseViewBean() {
        super();
    }

    @PostConstruct
    public void init() {
        super.init();
        repository = courseRepository;
        entities = courseRepository.getAll();

    }
}
