package ro.uaic.info.javatechnologies.optcourses.beans;

import org.primefaces.context.RequestContext;
import ro.uaic.info.javatechnologies.optcourses.models.OptionalPackage;
import ro.uaic.info.javatechnologies.optcourses.repository.OptionalPackageRepository;
import ro.uaic.info.javatechnologies.optcourses.utils.JSFUtils;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.sql.SQLException;

@ManagedBean(name = "optionalPackageBean")
@RequestScoped
public class OptionalPackageEditBean extends DataEdit<OptionalPackage, String> {
    public OptionalPackageEditBean() {
        super();
        entity = new OptionalPackage();
        repository = new OptionalPackageRepository();
    }

    public void submit() throws SQLException, IOException {
        repository.save((OptionalPackage) entity);

        String message = JSFUtils.getResourceBundle("msgs").getString("lecturer.added");
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, message, message);
        RequestContext.getCurrentInstance().showMessageInDialog(facesMessage);

        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
        myNav.handleNavigation(facesContext, null, "index");

    }
}