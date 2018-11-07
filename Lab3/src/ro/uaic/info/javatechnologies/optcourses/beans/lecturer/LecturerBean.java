package ro.uaic.info.javatechnologies.optcourses.beans.lecturer;

import ro.uaic.info.javatechnologies.optcourses.models.Lecturer;
import ro.uaic.info.javatechnologies.optcourses.repository.LecturerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named("lecturerAll")
@ApplicationScoped
public class LecturerBean implements Serializable {
    private List<Lecturer> cache;

    private final Logger log = Logger.getLogger(this.getClass().getName());

    public LecturerBean() throws SQLException {
        LecturerRepository lecturerRepository = new LecturerRepository();
        cache = new ArrayList<>(lecturerRepository.getAll());
        log.info("Initializing lecturer cache from database");
    }

    public List<Lecturer> getLecturers() {
        return cache;
    }

    public void addLecturer(@Observes Lecturer lecturer) {
        cache.add(lecturer);
        log.info(String.format("Receiving event with newly added lecturer: %s", lecturer));
    }
}
