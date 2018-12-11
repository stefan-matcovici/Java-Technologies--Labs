package ro.uaic.info.javatechnologies.optcourses.beans.course;

import org.primefaces.event.FlowEvent;
import ro.uaic.info.javatechnologies.optcourses.beans.DataEdit;
import ro.uaic.info.javatechnologies.optcourses.models.OptionalCourse;
import ro.uaic.info.javatechnologies.optcourses.repository.OptionalCourseRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("optionalCourseEditbean")
@ViewScoped
public class OptionalCourseEditBean extends DataEdit<OptionalCourse, String> {

    private boolean skip;

    @EJB
    OptionalCourseRepository courseRepository;

    public OptionalCourseEditBean() {
        super();
        entity = new OptionalCourse();
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