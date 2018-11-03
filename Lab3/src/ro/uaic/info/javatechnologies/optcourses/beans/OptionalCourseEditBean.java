package ro.uaic.info.javatechnologies.optcourses.beans;

import org.primefaces.event.FlowEvent;
import ro.uaic.info.javatechnologies.optcourses.models.Course;
import ro.uaic.info.javatechnologies.optcourses.models.OptionalCourse;
import ro.uaic.info.javatechnologies.optcourses.repository.OptionalCourseRepository;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.sql.SQLException;

@ManagedBean(name = "optionalCourseEditbean")
@ViewScoped
public class OptionalCourseEditBean extends DataEdit<OptionalCourse, String> {
    private boolean skip;


    public OptionalCourseEditBean() {
        super();
        entity = new OptionalCourse();
        repository = new OptionalCourseRepository();
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public void save() {
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + ((Course) entity).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void submit() throws SQLException, IOException {
        repository.save((OptionalCourse) entity);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
        myNav.handleNavigation(facesContext, null, "index");
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }
}