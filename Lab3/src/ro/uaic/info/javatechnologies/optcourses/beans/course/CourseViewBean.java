package ro.uaic.info.javatechnologies.optcourses.beans.course;

import ro.uaic.info.javatechnologies.optcourses.beans.DataView;
import ro.uaic.info.javatechnologies.optcourses.models.Course;
import ro.uaic.info.javatechnologies.optcourses.repository.CourseRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.net.MalformedURLException;
import java.sql.SQLException;

@Named("courseViewBean")
@RequestScoped
public class CourseViewBean extends DataView<Course, String> {
    public CourseViewBean() throws SQLException, MalformedURLException {
        super();
        repository = new CourseRepository();
        entities = repository.getAll();
    }
}
