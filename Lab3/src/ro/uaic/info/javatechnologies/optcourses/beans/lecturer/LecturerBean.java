package ro.uaic.info.javatechnologies.optcourses.beans.lecturer;

import ro.uaic.info.javatechnologies.optcourses.beans.BackingBean;
import ro.uaic.info.javatechnologies.optcourses.models.Lecturer;
import ro.uaic.info.javatechnologies.optcourses.repository.LecturerRepository;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("lecturerAll")
@ApplicationScoped
public class LecturerBean extends BackingBean<Lecturer, Integer> implements Serializable {

    @EJB
    LecturerRepository lecturerRepository;

    public List<Lecturer> getLecturers() {
        return lecturerRepository.getAll();
    }
}
