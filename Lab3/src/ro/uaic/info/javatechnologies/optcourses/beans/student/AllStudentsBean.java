package ro.uaic.info.javatechnologies.optcourses.beans.student;

import ro.uaic.info.javatechnologies.optcourses.beans.BackingBean;
import ro.uaic.info.javatechnologies.optcourses.models.Student;
import ro.uaic.info.javatechnologies.optcourses.repository.StudentRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Named("studentsAll")
@ApplicationScoped
public class AllStudentsBean extends BackingBean<Student, Integer> implements Serializable {

    public AllStudentsBean() throws SQLException {
        repository = new StudentRepository();
    }

    public List<Student> getStudents() {
        try {
            return new ArrayList<>(repository.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
