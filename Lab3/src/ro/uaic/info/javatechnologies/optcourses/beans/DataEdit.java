package ro.uaic.info.javatechnologies.optcourses.beans;

import ro.uaic.info.javatechnologies.optcourses.models.AbstractEntity;
import ro.uaic.info.javatechnologies.optcourses.repository.DataRepository;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.validation.Valid;
import java.io.Serializable;
import java.sql.SQLException;

public abstract class DataEdit<T extends AbstractEntity<ID>, ID> implements Serializable {
    @Valid
    protected AbstractEntity<ID> entity;

    protected DataRepository<T, ID> repository;

    public AbstractEntity<ID> getEntity() {
        return entity;
    }

    public void submit() throws SQLException {
        repository.save((T) entity);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
        myNav.handleNavigation(facesContext, null, "index");
    }
}
