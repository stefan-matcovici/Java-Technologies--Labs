package ro.uaic.info.javatechnologies.optcourses.ejb;

import ro.uaic.info.javatechnologies.optcourses.models.OptionalCourse;
import ro.uaic.info.javatechnologies.optcourses.models.Student;
import ro.uaic.info.javatechnologies.optcourses.repository.OptionalCourseRepository;
import ro.uaic.info.javatechnologies.optcourses.repository.StudentRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class AssignementBean {

    @Inject
    private StudentRepository studentRepository;

    @Inject
    private OptionalCourseRepository courseRepository;

    private List<Student> students;
    private OptionalCourse optionalCourse;

    @PostConstruct
    public void init () {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(new Student(student.getId(), student.getFirstName(), student.getLastName(), student.getEmail(), student.getYear()));
    }

    public List<Student> getSelectedStudents() {
        return students;
    }

    public OptionalCourse getOptionalCourse() {
        return optionalCourse;
    }

    public void setOptionalCourse(OptionalCourse optionalCourse) {
        this.optionalCourse = optionalCourse;
    }

    public void submit(OptionalCourse optionalCourse) {
        for (Student student: students) {
            studentRepository.addOptionalCourseToStudent(student, courseRepository.getEntityById(optionalCourse.getId()));
        }
    }
}
