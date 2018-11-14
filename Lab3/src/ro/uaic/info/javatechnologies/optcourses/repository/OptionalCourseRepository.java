package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.models.OptionalCourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OptionalCourseRepository extends DataRepository<OptionalCourse, String> {

    private static final String addCourseQuery = "INSERT INTO %s.\"courses\" (name, year, semester, url, lecturer_id, study_groups, optional) VALUES (?, ?, ?, ?, ?, ?, TRUE); ";

    public OptionalCourseRepository(String schema) {
        super(schema);
    }

    @Override
    public OptionalCourse getById(String s) {
        return null;
    }

    @Override
    public void save(OptionalCourse optionalCourse) throws SQLException {
        Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement(String.format(addCourseQuery, getSchema()));
        pst.setString(1, optionalCourse.getName());
        pst.setInt(2, optionalCourse.getYear());
        pst.setString(3, optionalCourse.getSemester().getName());
        pst.setString(4, optionalCourse.getUrl() != null ? optionalCourse.getUrl().toString(): null);
        pst.setInt(5, optionalCourse.getLecturer() != null ? optionalCourse.getLecturer().getId(): 0);
        pst.setInt(6, optionalCourse.getStudyGroups());

        pst.executeUpdate();
    }

    @Override
    public List<OptionalCourse> getAll() throws SQLException {
        return null;
    }

    @Override
    public void updateEntities(List<OptionalCourse> entities) throws SQLException {

    }
}
