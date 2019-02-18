package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.entities.OptionalCourseEntity;
import ro.uaic.info.javatechnologies.optcourses.entities.StudentsEntity;
import ro.uaic.info.javatechnologies.optcourses.models.Student;
import ro.uaic.info.javatechnologies.optcourses.utils.EntityConverter;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ro.uaic.info.javatechnologies.optcourses.utils.EntityConverter.toStudentEntity;

@Stateless
public class StudentRepository extends DataRepository<Student, Integer> {

    public StudentRepository() {
    }

    public StudentRepository(String schema) {
        super(schema);
    }

    @Override
    public Student getById(Integer integer) {
        return null;
    }

    @Override
    public void save(Student student) {
        optCoursesPU.persist(toStudentEntity(student));
    }

    @Override
    public List<Student> getAll() {
        Query query = optCoursesPU.createQuery("SELECT l FROM StudentsEntity l");
        List<Student> students = ((Collection<StudentsEntity>) query.getResultList()).stream().map(EntityConverter::toStudent).collect(Collectors.toList());

        return students;
    }

    public StudentsEntity getEntityById(String id) {
        Query query = optCoursesPU.createQuery("SELECT e FROM StudentsEntity e where e.id = " + id);

        return (StudentsEntity) query.getResultList().get(0);
    }

    public void addOptionalCourseToStudent(Student student, OptionalCourseEntity optionalCourse) {
        StudentsEntity studentsEntity = getEntityById(String.valueOf(student.getId()));
        studentsEntity.getOptionalCourses().add(optionalCourse);
        optCoursesPU.merge(studentsEntity);
    }

    public List<Student> getStudentsWithIncompletePreferencesList() {
        Query query = optCoursesPU.createQuery("SELECT student FROM StudentsEntity student, PackagesEntity package where " +
                "package.year = student.year and " +
                "(select count(courses.id) from OptionalCourseEntity courses where courses.packageEntity = package) != (select count(prefs.id) from StudentPrefsEntity prefs where prefs.studentsByStudentId = student)" +
                "group by student");
        List<Student> students = ((Collection<StudentsEntity>) query.getResultList()).stream().map(EntityConverter::toStudent).collect(Collectors.toList());

        return students;
    }

    @Override
    public void updateEntities(List<Student> entities) {

    }
}
