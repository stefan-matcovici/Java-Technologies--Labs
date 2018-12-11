package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.models.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
public abstract class DataRepository<T extends AbstractEntity<ID>, ID> {

    private String schema;

    @PersistenceContext(name = "OptCoursesPU")
    protected EntityManager optCoursesPU;

    public DataRepository() {
        this("public");
    }

    public DataRepository(String schema) {
        this.schema = schema;
    }

    public abstract T getById(ID id);
    public abstract void save(T t);
    public abstract List<T> getAll();
    public abstract void updateEntities(List<T> entities);

    public String getSchema() {
        return schema;
    }
    public void setSchema(String schema) {
        this.schema = schema;
    }

    public EntityManager getOptCoursesPU() {
        return optCoursesPU;
    }
    public void setOptCoursesPU(EntityManager optCoursesPU) {
        this.optCoursesPU = optCoursesPU;
    }
}
