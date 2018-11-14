package ro.uaic.info.javatechnologies.optcourses.beans.course;

import ro.uaic.info.javatechnologies.optcourses.beans.DataView;
import ro.uaic.info.javatechnologies.optcourses.models.Course;
import ro.uaic.info.javatechnologies.optcourses.repository.CourseRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.net.MalformedURLException;
import java.sql.SQLException;

@Named("courseViewBean")
@RequestScoped
public class CourseViewBean extends DataView<Course, String> {
    public CourseViewBean() {
        super();
        repository = new CourseRepository(obtainTenant());
    }

    @PostConstruct
    public void init() {
        super.init();
        try {
            entities = repository.getAll();
        } catch (SQLException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
