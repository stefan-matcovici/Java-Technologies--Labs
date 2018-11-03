package ro.uaic.info.javatechnologies.optcourses.beans;

import ro.uaic.info.javatechnologies.optcourses.models.Lecturer;
import ro.uaic.info.javatechnologies.optcourses.repository.LecturerRepository;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.SQLException;

@ManagedBean(name = "lecturerAll")
@ApplicationScoped
public class LecturerBean {
    private LecturerRepository lecturerRepository = new LecturerRepository();

    public Lecturer[] getLecturers() throws SQLException {
        return lecturerRepository.getAll().stream().toArray(Lecturer[]::new);
    }
}
