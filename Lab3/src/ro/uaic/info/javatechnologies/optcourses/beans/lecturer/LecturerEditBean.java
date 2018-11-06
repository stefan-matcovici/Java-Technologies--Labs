package ro.uaic.info.javatechnologies.optcourses.beans.lecturer;

import ro.uaic.info.javatechnologies.optcourses.beans.DataEdit;
import ro.uaic.info.javatechnologies.optcourses.models.Lecturer;
import ro.uaic.info.javatechnologies.optcourses.repository.LecturerRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("lecturerBean")
@RequestScoped
public class LecturerEditBean extends DataEdit<Lecturer, Integer> implements Serializable {

    public LecturerEditBean() {
        super();
        entity = new Lecturer();
        repository = new LecturerRepository();
    }
}
