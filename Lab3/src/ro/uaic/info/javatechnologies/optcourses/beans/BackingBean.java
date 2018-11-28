package ro.uaic.info.javatechnologies.optcourses.beans;

import ro.uaic.info.javatechnologies.optcourses.models.AbstractEntity;
import ro.uaic.info.javatechnologies.optcourses.repository.DataRepository;

import javax.faces.context.FacesContext;
import java.util.Map;

public abstract class BackingBean<T extends AbstractEntity<ID>, ID> {

    protected DataRepository<T, ID> repository;

    public void init() {
    }

    public String obtainTenant() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        return paramMap.get("tenant");
    }
}
