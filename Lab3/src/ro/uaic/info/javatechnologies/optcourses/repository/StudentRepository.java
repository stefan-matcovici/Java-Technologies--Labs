package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.entities.StudentsEntity;
import ro.uaic.info.javatechnologies.optcourses.models.Student;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    public void save(Student student) throws SQLException {
        optCoursesPU.getTransaction().begin();
        optCoursesPU.persist(toStudentEntity(student));
        optCoursesPU.getTransaction().commit();
    }

    @Override
    public List<Student> getAll() throws SQLException {
        Query query = optCoursesPU.createQuery("SELECT l FROM StudentsEntity l");
        List<Student> students = ((Collection<StudentsEntity>) query.getResultList()).stream().map(StudentRepository::toStudent).collect(Collectors.toList());

        return students;
    }

    static StudentsEntity toStudentEntity(Student student) {
        StudentsEntity result = new StudentsEntity();

        result.setId(String.valueOf(student.getId()));
        result.setFirstName(student.getFirstName());
        result.setLastName(student.getLastName());
        result.setEmail(student.getEmail());
        result.setYear(student.getYear());

        return result;
    }

    static Student toStudent(StudentsEntity entity) {
        Student student = new Student();

        student.setId(Integer.valueOf(entity.getId()));
        student.setFirstName(entity.getFirstName());
        student.setLastName(entity.getLastName());
        student.setEmail(entity.getEmail());
        student.setYear(entity.getYear());

        return student;
    }

    @Override
    public void updateEntities(List<Student> entities) throws SQLException {

    }
}
