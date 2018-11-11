package ro.uaic.info.javatechnologies.optcourses.beans;

import ro.uaic.info.javatechnologies.optcourses.models.AbstractEntity;
import ro.uaic.info.javatechnologies.optcourses.repository.DataRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

public abstract class BackingBean<T extends AbstractEntity<ID>, ID> {
    @Resource(name = "opt-courses")
    DataSource dataSource;

    public void init() {
        if (repository != null) {
            repository.setDataSource(dataSource);
        }
    }

    protected DataRepository<T, ID> repository;
}
