package ro.uaic.info.javatechnologies.optcourses.repository;

import com.sun.org.glassfish.gmbal.ManagedObject;
import ro.uaic.info.javatechnologies.optcourses.models.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

@ManagedObject
public abstract class DataRepository<T extends AbstractEntity<ID>, ID> {

    private String schema;
    protected EntityManager optCoursesPU;

    public DataRepository() {
        this("public");
    }

    public DataRepository(String schema) {
        this.schema = schema;
        optCoursesPU = Persistence.createEntityManagerFactory("OptCoursesPU").createEntityManager();
    }

    public abstract T getById(ID id);
    public abstract void save(T t) throws SQLException;
    public abstract List<T> getAll() throws SQLException, MalformedURLException;
    public abstract void updateEntities(List<T> entities) throws SQLException;

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
