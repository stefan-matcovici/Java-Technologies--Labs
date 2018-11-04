package ro.uaic.info.javatechnologies.optcourses.beans;

import ro.uaic.info.javatechnologies.optcourses.models.AbstractEntity;
import ro.uaic.info.javatechnologies.optcourses.repository.DataRepository;

import javax.validation.Valid;
import java.io.Serializable;

public abstract class DataEdit<T extends AbstractEntity<ID>, ID> implements Serializable {
    @Valid
    protected AbstractEntity<ID> entity;

    protected DataRepository<T, ID> repository;

    public AbstractEntity<ID> getEntity() {
        return entity;
    }
}
