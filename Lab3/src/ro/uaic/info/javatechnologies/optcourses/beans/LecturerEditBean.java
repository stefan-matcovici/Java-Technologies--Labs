package ro.uaic.info.javatechnologies.optcourses.beans;

import ro.uaic.info.javatechnologies.optcourses.models.Lecturer;
import ro.uaic.info.javatechnologies.optcourses.repository.LecturerRepository;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.sql.SQLException;

@ManagedBean(name = "lecturerBean")
@RequestScoped
public class LecturerEditBean extends DataEdit<Lecturer, Integer> {

    public LecturerEditBean() {
        super();
        entity = new Lecturer();
        repository = new LecturerRepository();
    }

    public void submit() throws SQLException, IOException {
        repository.save((Lecturer) entity);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
        myNav.handleNavigation(facesContext, null, "index");
    }
}
