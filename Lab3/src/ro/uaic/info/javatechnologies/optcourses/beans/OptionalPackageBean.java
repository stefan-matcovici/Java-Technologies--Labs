package ro.uaic.info.javatechnologies.optcourses.beans;

import ro.uaic.info.javatechnologies.optcourses.models.OptionalPackage;
import ro.uaic.info.javatechnologies.optcourses.repository.OptionalPackageRepository;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.sql.SQLException;

@ManagedBean(name = "optionalPackageBean")
@RequestScoped
public class OptionalPackageBean extends DataEdit<OptionalPackage, String> {
    public OptionalPackageBean() {
        super();
        entity = new OptionalPackage();
        repository = new OptionalPackageRepository();
    }

    public void submit() throws SQLException, IOException {
        repository.save((OptionalPackage) entity);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
        myNav.handleNavigation(facesContext, null, "index");
    }
}
