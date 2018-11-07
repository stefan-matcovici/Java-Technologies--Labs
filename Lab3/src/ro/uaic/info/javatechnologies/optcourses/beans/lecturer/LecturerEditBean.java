package ro.uaic.info.javatechnologies.optcourses.beans.lecturer;

import ro.uaic.info.javatechnologies.optcourses.beans.DataEdit;
import ro.uaic.info.javatechnologies.optcourses.models.Lecturer;
import ro.uaic.info.javatechnologies.optcourses.repository.LecturerRepository;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;

@Named("lecturerBean")
@RequestScoped
public class LecturerEditBean extends DataEdit<Lecturer, Integer> implements Serializable {

    @Inject
    private Event<Lecturer> lecturer;

    public LecturerEditBean() {
        super();
        entity = new Lecturer();
        repository = new LecturerRepository();
    }

    @Override
    public void submit() throws SQLException {
        super.submit();
        lecturer.fire((Lecturer)entity);
    }
}
