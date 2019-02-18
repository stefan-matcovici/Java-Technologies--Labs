package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.entities.StudentPrefsEntity;
import ro.uaic.info.javatechnologies.optcourses.models.StudentPref;
import ro.uaic.info.javatechnologies.optcourses.utils.EntityConverter;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ro.uaic.info.javatechnologies.optcourses.utils.EntityConverter.toStudentPrefEntity;

@Stateless
public class StudentsPrefRepository extends DataRepository<StudentPref, Integer> {

    public StudentsPrefRepository() {
    }

    public StudentsPrefRepository(String schema) {
        super(schema);
    }

    @Override
    public StudentPref getById(Integer integer) {
        return null;
    }

    @Override
    public void save(StudentPref studentPref) {
        optCoursesPU.persist(toStudentPrefEntity(studentPref));
    }

    @Override
    public List<StudentPref> getAll() {
        Query query = optCoursesPU.createQuery("SELECT l FROM LecturersEntity l");
        List<StudentPref> students = ((Collection<StudentPrefsEntity>) query.getResultList()).stream().map(EntityConverter::toStudentPref).collect(Collectors.toList());

        return students;
    }

    @Override
    public void updateEntities(List<StudentPref> entities) {

    }
}