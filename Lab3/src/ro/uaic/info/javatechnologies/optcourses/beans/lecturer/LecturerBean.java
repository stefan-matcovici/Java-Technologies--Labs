package ro.uaic.info.javatechnologies.optcourses.beans.lecturer;

import ro.uaic.info.javatechnologies.optcourses.models.Lecturer;
import ro.uaic.info.javatechnologies.optcourses.repository.LecturerRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Named("lecturerAll")
@RequestScoped
public class LecturerBean implements ActionListener, Serializable {
    private List<Lecturer> cache = new ArrayList<>();

    public LecturerBean() throws SQLException {
        LecturerRepository lecturerRepository = new LecturerRepository();
        cache = new ArrayList<>(lecturerRepository.getAll());
    }


    public List<Lecturer> getLecturers() throws SQLException {
        return cache;
    }

    public void sayHi() {
        System.out.println("hi");
    }

    @Override
    public void processAction(ActionEvent actionEvent) throws AbortProcessingException {


    }
}
