package ro.uaic.info.javatechnologies.optcourses.beans.lecturer;

import ro.uaic.info.javatechnologies.optcourses.beans.BackingBean;
import ro.uaic.info.javatechnologies.optcourses.models.Lecturer;
import ro.uaic.info.javatechnologies.optcourses.repository.LecturerRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named("lecturerAll")
@ApplicationScoped
public class LecturerBean extends BackingBean<Lecturer, Integer> implements Serializable {
    private List<Lecturer> cache;

    private final Logger log = Logger.getLogger(this.getClass().getName());

    public LecturerBean() throws SQLException {
        repository = new LecturerRepository(obtainTenant());
    }

    @PostConstruct
    public void init() {
        super.init();
        try {
            cache = new ArrayList<>(repository.getAll());
        } catch (SQLException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public List<Lecturer> getLecturers() {
        return cache;
    }

    public void addLecturer(@Observes Lecturer lecturer) {
        cache.add(lecturer);
        log.info(String.format("Receiving event with newly added lecturer: %s", lecturer));
    }
}
