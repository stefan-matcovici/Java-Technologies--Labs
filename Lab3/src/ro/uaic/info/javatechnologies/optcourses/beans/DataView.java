package ro.uaic.info.javatechnologies.optcourses.beans;

import ro.uaic.info.javatechnologies.optcourses.models.AbstractEntity;
import ro.uaic.info.javatechnologies.optcourses.repository.DataRepository;

import java.sql.SQLException;
import java.util.List;

public class DataView<T extends AbstractEntity<ID>, ID> {
    protected List<T> entities;

    protected DataRepository<T, ID> repository;

    public List<T> getEntities() throws SQLException {
        return entities;
    }

    public void add() {

    }
}
