package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.entities.StudentPrefsEntity;
import ro.uaic.info.javatechnologies.optcourses.models.StudentPref;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ro.uaic.info.javatechnologies.optcourses.repository.CourseRepository.toMandatoryCourse;
import static ro.uaic.info.javatechnologies.optcourses.repository.CourseRepository.toMandatoryCourseEntity;
import static ro.uaic.info.javatechnologies.optcourses.repository.StudentRepository.toStudent;
import static ro.uaic.info.javatechnologies.optcourses.repository.StudentRepository.toStudentEntity;

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
    public void save(StudentPref studentPref) throws SQLException {
        optCoursesPU.getTransaction().begin();
        optCoursesPU.persist(toStudentPrefEntity(studentPref));
        optCoursesPU.getTransaction().commit();
    }

    @Override
    public List<StudentPref> getAll() throws SQLException {
        Query query = optCoursesPU.createQuery("SELECT l FROM LecturersEntity l");
        List<StudentPref> students = ((Collection<StudentPrefsEntity>) query.getResultList()).stream().map(StudentsPrefRepository::toStudentPref).collect(Collectors.toList());

        return students;
    }

    static StudentPrefsEntity toStudentPrefEntity(StudentPref studentPref) {
        StudentPrefsEntity result = new StudentPrefsEntity();

        result.setCoursesByOptCourseId(toMandatoryCourseEntity(studentPref.getCourse()));
        result.setStudentsByStudentId(toStudentEntity(studentPref.getStudent()));
        result.setPos(studentPref.getPos());

        return result;
    }

    static StudentPref toStudentPref(StudentPrefsEntity entity) {
        StudentPref student = new StudentPref();

        student.setCourse(toMandatoryCourse(entity.getCoursesByOptCourseId()));
        student.setStudent(toStudent(entity.getStudentsByStudentId()));
        student.setPos(entity.getPos());

        return student;
    }

    @Override
    public void updateEntities(List<StudentPref> entities) throws SQLException {

    }
}