package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.entities.OptionalCourseEntity;
import ro.uaic.info.javatechnologies.optcourses.models.OptionalCourse;
import ro.uaic.info.javatechnologies.optcourses.models.Semester;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import static ro.uaic.info.javatechnologies.optcourses.repository.LecturerRepository.toLecturer;
import static ro.uaic.info.javatechnologies.optcourses.repository.LecturerRepository.toLecturerEntity;

public class OptionalCourseRepository extends DataRepository<OptionalCourse, String> {

    private static final String addCourseQuery = "INSERT INTO %s.\"courses\" (name, year, semester, url, lecturer_id, study_groups, optional) VALUES (?, ?, ?, ?, ?, ?, TRUE); ";

    public OptionalCourseRepository() {
    }

    public OptionalCourseRepository(String schema) {
        super(schema);
    }

    @Override
    public OptionalCourse getById(String s) {
        return null;
    }

    @Override
    public void save(OptionalCourse optionalCourse) throws SQLException {
        optCoursesPU.getTransaction().begin();
        optCoursesPU.persist(toEntity(optionalCourse));
        optCoursesPU.getTransaction().commit();
    }

    @Override
    public List<OptionalCourse> getAll() throws SQLException {
        return null;
    }

    private OptionalCourse toModel(OptionalCourseEntity coursesEntity) {
        OptionalCourse result = new OptionalCourse();

        result.setName(coursesEntity.getName());
        result.setSemester(Semester.valueOf(coursesEntity.getSemester()));
        result.setStudyGroups(coursesEntity.getStudyGroups());
        // result.setOptionalPackage(toOptionalPackage(coursesEntity.getPackageEntity()));

        try {
            result.setUrl(new URL(coursesEntity.getUrl()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        result.setYear(coursesEntity.getYear());
        result.setLecturer(toLecturer(coursesEntity.getLecturer()));

        return result;
    }

    private OptionalCourseEntity toEntity(OptionalCourse course) {
        OptionalCourseEntity result = new OptionalCourseEntity();

        result.setName(course.getName());
        result.setSemester(course.getSemester().getName());
        result.setStudyGroups(course.getStudyGroups());
        if (course.getUrl() != null)
            result.setUrl(course.getUrl().toString());
        result.setYear(course.getYear());
        result.setLecturer(toLecturerEntity(course.getLecturer()));
        // result.setPackageEntity(toOptionalPackageEntity(course.getOptionalPackage()));

        return result;
    }



    @Override
    public void updateEntities(List<OptionalCourse> entities) throws SQLException {

    }
}
