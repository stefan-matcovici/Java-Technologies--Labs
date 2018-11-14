package ro.uaic.info.javatechnologies.optcourses.beans;

import ro.uaic.info.javatechnologies.optcourses.models.AbstractEntity;
import ro.uaic.info.javatechnologies.optcourses.repository.DataRepository;

import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import java.util.Map;

public abstract class BackingBean<T extends AbstractEntity<ID>, ID> {
    @Resource(name = "opt-courses")
    DataSource dataSource;

    public void init() {
        if (repository != null) {
            repository.setDataSource(dataSource);
        }
    }

    public String obtainTenant() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        return paramMap.get("tenant");
    }

    protected DataRepository<T, ID> repository;
}
