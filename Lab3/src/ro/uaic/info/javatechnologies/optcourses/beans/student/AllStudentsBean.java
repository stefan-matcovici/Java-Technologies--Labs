package ro.uaic.info.javatechnologies.optcourses.beans.student;

import ro.uaic.info.javatechnologies.optcourses.beans.BackingBean;
import ro.uaic.info.javatechnologies.optcourses.models.Student;
import ro.uaic.info.javatechnologies.optcourses.repository.StudentRepository;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("studentsAll")
@ApplicationScoped
public class AllStudentsBean extends BackingBean<Student, Integer> implements Serializable {

     @EJB
    private StudentRepository studentRepository;

    public AllStudentsBean() {
    }

    public List<Student> getStudents() {
        return new ArrayList<>(studentRepository.getAll());
    }
}
