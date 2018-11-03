package ro.uaic.info.javatechnologies.optcourses.beans;

import org.primefaces.event.FlowEvent;
import ro.uaic.info.javatechnologies.optcourses.models.Course;
import ro.uaic.info.javatechnologies.optcourses.repository.CourseRepository;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.sql.SQLException;

@ManagedBean(name = "courseEditBean")
@ViewScoped
public class CourseEditBean extends DataEdit<Course, String>  {
    private boolean skip;


    public CourseEditBean() {
        super();
        entity = new Course();
        repository = new CourseRepository();
    }

    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }

    public void save() {
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + ((Course)entity).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void submit() throws SQLException, IOException {
        repository.save((Course) entity);
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
