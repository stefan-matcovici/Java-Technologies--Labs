package ro.uaic.info.javatechnologies.optcourses.repository;

import com.sun.org.glassfish.gmbal.ManagedObject;
import ro.uaic.info.javatechnologies.optcourses.models.AbstractEntity;

import javax.sql.DataSource;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@ManagedObject
public abstract class DataRepository<T extends AbstractEntity<ID>, ID> {
    private DataSource dataSource;

    public abstract T getById(ID id);
    public abstract void save(T t) throws SQLException;
    public abstract List<T> getAll() throws SQLException, MalformedURLException;
    public abstract void updateEntities(List<T> entities) throws SQLException;

    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
