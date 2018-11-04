package ro.uaic.info.javatechnologies.optcourses.beans.lecturer;

import ro.uaic.info.javatechnologies.optcourses.beans.DataEdit;
import ro.uaic.info.javatechnologies.optcourses.models.Lecturer;
import ro.uaic.info.javatechnologies.optcourses.repository.LecturerRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

@Named("lecturerBean")
@RequestScoped
public class LecturerEditBean extends DataEdit<Lecturer, Integer> implements Serializable {

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
