package ro.uaic.info.javatechnologies.optcourses.beans.course;

import org.primefaces.event.FlowEvent;
import ro.uaic.info.javatechnologies.optcourses.beans.DataEdit;
import ro.uaic.info.javatechnologies.optcourses.models.Course;
import ro.uaic.info.javatechnologies.optcourses.repository.CourseRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("courseEditBean")
@ViewScoped
public class CourseEditBean extends DataEdit<Course, String> {

    private boolean skip;

    @EJB
    private CourseRepository courseRepository;

    public CourseEditBean() {
        super();
        entity = new Course();
    }

    @PostConstruct
    public void init() {
        super.init();
        repository = courseRepository;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }
}
