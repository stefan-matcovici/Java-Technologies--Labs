package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.entities.MandatoryCourseEntity;
import ro.uaic.info.javatechnologies.optcourses.models.Course;
import ro.uaic.info.javatechnologies.optcourses.models.Semester;

import javax.persistence.Query;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ro.uaic.info.javatechnologies.optcourses.repository.LecturerRepository.toLecturer;
import static ro.uaic.info.javatechnologies.optcourses.repository.LecturerRepository.toLecturerEntity;

public class CourseRepository extends DataRepository<Course, String> {

    private static final String addCourseQuery = "INSERT INTO %s.\"courses\" (name, year, semester, url, lecturer_id, study_groups) VALUES (?, ?, ?, ?, ?, ?); ";
    private static final String getAllCoursesQuery = "SELECT * FROM %s.\"courses\"";
    private static final String updateEntityQuery = "UPDATE %s.\"courses\" SET name=?, year=?, semester=?, url=?, study_groups=? WHERE id=?;";

    public CourseRepository() {

    }

    public CourseRepository(String schema) {
        super(schema);
    }

    @Override
    public Course getById(String s) {
        return null;
    }

    @Override
    public void save(Course course) throws SQLException {
        optCoursesPU.getTransaction().begin();
        optCoursesPU.persist(toEntity(course));
        optCoursesPU.getTransaction().commit();
    }

    @Override
    public List<Course> getAll() throws SQLException, MalformedURLException {
        Query query = optCoursesPU.createQuery("SELECT e FROM MandatoryCourseEntity e");
        List<Course> courses = ((Collection<MandatoryCourseEntity>) query.getResultList()).stream().map(this::toMandatoryCourse).collect(Collectors.toList());

        return courses;
    }

    private Course toMandatoryCourse(MandatoryCourseEntity coursesEntity) {
        Course result = new Course();

        result.setName(coursesEntity.getName());
        result.setSemester(Semester.valueOf(coursesEntity.getSemester()));
        result.setStudyGroups(coursesEntity.getStudyGroups());
        try {
            result.setUrl(new URL(coursesEntity.getUrl()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        result.setYear(coursesEntity.getYear());
        result.setLecturer(toLecturer(coursesEntity.getLecturer()));

        return result;
    }

    private MandatoryCourseEntity toEntity(Course course) {
        MandatoryCourseEntity result = new MandatoryCourseEntity();

        result.setName(course.getName());
        result.setSemester(course.getSemester().getName());
        result.setStudyGroups(course.getStudyGroups());
        if (course.getUrl() != null)
            result.setUrl(course.getUrl().toString());
        result.setYear(course.getYear());
        result.setLecturer(toLecturerEntity(course.getLecturer()));

        return result;
    }

    @Override
    public void updateEntities(List<Course> entities) throws SQLException {
        for (Course entity : entities) {
            save(entity);
        }
    }
}
