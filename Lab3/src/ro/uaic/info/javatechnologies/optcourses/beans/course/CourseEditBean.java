package ro.uaic.info.javatechnologies.optcourses.beans.course;

import org.primefaces.event.FlowEvent;
import ro.uaic.info.javatechnologies.optcourses.beans.DataEdit;
import ro.uaic.info.javatechnologies.optcourses.models.Course;
import ro.uaic.info.javatechnologies.optcourses.repository.CourseRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("courseEditBean")
@ViewScoped
public class CourseEditBean extends DataEdit<Course, String> {
    private boolean skip;


    public CourseEditBean() {
        super();
        entity = new Course();
        repository = new CourseRepository();
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
