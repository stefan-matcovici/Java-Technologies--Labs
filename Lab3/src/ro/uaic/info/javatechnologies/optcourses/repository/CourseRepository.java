package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.models.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CourseRepository extends DataRepository<Course, String> {

    private static final String addCourseQuery = "INSERT INTO public.\"COURSES\" (name, year, semester, url, lecturer_id, study_groups) VALUES (?, ?, ?, ?, ?, ?); ";

    @Override
    public Course getById(String s) {
        return null;
    }

    @Override
    public void save(Course course) throws SQLException {
        Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement(addCourseQuery);
        pst.setString(1, course.getName());
        pst.setInt(2, course.getYear());
        pst.setString(3, course.getSemester().getName());
        pst.setString(4, course.getUrl() != null ? course.getUrl().toString(): null);
        pst.setInt(5, course.getLecturer() != null ? course.getLecturer().getId(): 0);
        pst.setInt(6, course.getStudyGroups());

        pst.executeUpdate();
    }

    @Override
    public List<Course> getAll() {
        return null;
    }
}
