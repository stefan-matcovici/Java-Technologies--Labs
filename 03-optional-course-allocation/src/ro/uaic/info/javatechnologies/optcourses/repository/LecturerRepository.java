package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.ejb.LogInterceptor;
import ro.uaic.info.javatechnologies.optcourses.entities.LecturersEntity;
import ro.uaic.info.javatechnologies.optcourses.models.Lecturer;
import ro.uaic.info.javatechnologies.optcourses.utils.EntityConverter;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ro.uaic.info.javatechnologies.optcourses.utils.EntityConverter.toLecturerEntity;

@Stateless
public class LecturerRepository extends DataRepository<Lecturer, Integer> {

    public LecturerRepository(String schema) {
        super(schema);
    }

    public LecturerRepository() {
    }

    @Override
    public Lecturer getById(Integer integer) {
        return null;
    }

    @Override
    public void save(Lecturer lecturer) {
        optCoursesPU.persist(toLecturerEntity(lecturer));
    }

    @Override
    @Interceptors(LogInterceptor.class)
    public List<Lecturer> getAll() {
        Query query = optCoursesPU.createQuery("SELECT l FROM LecturersEntity l");
        List<Lecturer> lecturers = ((Collection<LecturersEntity>) query.getResultList()).stream().map(EntityConverter::toLecturer).collect(Collectors.toList());

        return lecturers;
    }

    @Override
    public void updateEntities(List<Lecturer> entities) {

    }
}
