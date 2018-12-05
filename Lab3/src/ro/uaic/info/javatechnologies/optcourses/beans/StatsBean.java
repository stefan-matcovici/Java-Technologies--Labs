package ro.uaic.info.javatechnologies.optcourses.beans;

import ro.uaic.info.javatechnologies.optcourses.models.CoursePreferenceAmongStudents;
import ro.uaic.info.javatechnologies.optcourses.models.Student;
import ro.uaic.info.javatechnologies.optcourses.repository.OptionalCourseRepository;
import ro.uaic.info.javatechnologies.optcourses.repository.StudentRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("statsBean")
@ViewScoped
public class StatsBean implements Serializable {

    private StudentRepository studentRepository = new StudentRepository();
    OptionalCourseRepository optionalCourseRepository = new OptionalCourseRepository();

    public List<Student> getIncompleteStudents() {
        return studentRepository.getStudentsWithIncompletePreferencesList();
    }

    public List<CoursePreferenceAmongStudents> getCoursePreferenceAmongStudents() {
        return optionalCourseRepository.getCoursesPreferenesAmongStudents();
    }
}
