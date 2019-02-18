package ro.uaic.info.javatechnologies.optcourses.beans.lecturer;

import ro.uaic.info.javatechnologies.optcourses.beans.DataEdit;
import ro.uaic.info.javatechnologies.optcourses.models.Lecturer;
import ro.uaic.info.javatechnologies.optcourses.repository.LecturerRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;

@Named("lecturerBean")
@ViewScoped
public class LecturerEditBean extends DataEdit<Lecturer, Integer> implements Serializable {

    @EJB
    private LecturerRepository lecturerRepository;

    public LecturerEditBean() {
        super();
        entity = new Lecturer();
    }

    @PostConstruct
    public void init() {
        repository = lecturerRepository;
    }

    @Override
    public void submit() throws SQLException {
        super.submit();
    }
}
