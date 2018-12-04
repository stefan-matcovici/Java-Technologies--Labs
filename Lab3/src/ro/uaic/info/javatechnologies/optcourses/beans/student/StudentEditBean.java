package ro.uaic.info.javatechnologies.optcourses.beans.student;

import ro.uaic.info.javatechnologies.optcourses.beans.DataEdit;
import ro.uaic.info.javatechnologies.optcourses.models.Student;
import ro.uaic.info.javatechnologies.optcourses.repository.StudentRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;


@Named("studentEditBean")
@ViewScoped
public class StudentEditBean extends DataEdit<Student, Integer> implements Serializable {

    public StudentEditBean() {
        super();
        entity = new Student();
        repository = new StudentRepository();
    }

    @Override
    public void submit() throws SQLException {
        super.submit();
    }
}