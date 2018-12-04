package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.entities.LecturersEntity;
import ro.uaic.info.javatechnologies.optcourses.models.Lecturer;

import javax.persistence.Query;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LecturerRepository extends DataRepository<Lecturer, Integer> {

    private static final String addLecturerQuery = "INSERT INTO %s.\"lecturers\" (firstname, lastname) VALUES (?, ?); ";
    private static final String getAllLecturerQuery = "SELECT * FROM %s.\"lecturers\"";

    public LecturerRepository(String schema) {
        super(schema);
    }

    @Override
    public Lecturer getById(Integer integer) {
        return null;
    }

    @Override
    public void save(Lecturer lecturer) throws SQLException {
        optCoursesPU.getTransaction().begin();
        optCoursesPU.persist(toLecturerEntity(lecturer));
        optCoursesPU.getTransaction().commit();
    }

    @Override
    public List<Lecturer> getAll() throws SQLException {
        Query query = optCoursesPU.createQuery("SELECT l FROM LecturersEntity l");
        List<Lecturer> lecturers = ((Collection<LecturersEntity>) query.getResultList()).stream().map(LecturerRepository::toLecturer).collect(Collectors.toList());

        return lecturers;
    }

    static LecturersEntity toLecturerEntity(Lecturer lecturer) {
        LecturersEntity result = new LecturersEntity();

        if (lecturer.getId() != null) {
            result.setId(lecturer.getId());
        }
        result.setFirstName(lecturer.getFirstName());
        result.setLastName(lecturer.getLastName());
        result.setEmail(lecturer.getEmail());
        result.setUrl(lecturer.getUrl().toString());

        return result;
    }

    static Lecturer toLecturer(LecturersEntity entity) {
        Lecturer lecturer = new Lecturer();

        lecturer.setId(entity.getId());
        lecturer.setFirstName(entity.getFirstName());
        lecturer.setLastName(entity.getLastName());
        lecturer.setEmail(entity.getEmail());
        try {
            lecturer.setUrl(new URL(entity.getUrl()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return lecturer;
    }

    @Override
    public void updateEntities(List<Lecturer> entities) throws SQLException {

    }
}
