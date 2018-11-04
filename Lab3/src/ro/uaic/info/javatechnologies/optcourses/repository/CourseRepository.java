package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.models.Course;
import ro.uaic.info.javatechnologies.optcourses.models.Lecturer;
import ro.uaic.info.javatechnologies.optcourses.models.Semester;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository extends DataRepository<Course, String> {

    private static final String addCourseQuery = "INSERT INTO public.\"COURSES\" (name, year, semester, url, lecturer_id, study_groups) VALUES (?, ?, ?, ?, ?, ?); ";
    private static final String getAllCoursesQuery = "SELECT * FROM public.\"COURSES\"";

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
    public List<Course> getAll() throws SQLException, MalformedURLException {
        Connection con = getConnection();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(getAllCoursesQuery);
        List<Course> courses = new ArrayList<>();
        while (rs.next()) {
            courses.add(new Course(rs.getString("id"),
                    rs.getString("name"),
                    rs.getInt("year"),
                    Semester.valueOf(rs.getString("semester").toUpperCase()),
                    new URL(rs.getString("url")),
                    new Lecturer(),
                    rs.getInt("study_groups")));
        }

        return courses;
    }
}
