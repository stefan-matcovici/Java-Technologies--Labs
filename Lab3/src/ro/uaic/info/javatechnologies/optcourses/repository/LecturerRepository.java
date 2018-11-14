package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.models.Lecturer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement(String.format(addLecturerQuery, getSchema()));
        pst.setString(1, lecturer.getFirstName());
        pst.setString(2, lecturer.getLastName());

        pst.executeUpdate();
    }

    @Override
    public List<Lecturer> getAll() throws SQLException {
        Connection con = getConnection();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(String.format(getAllLecturerQuery, getSchema()));
        List<Lecturer> lecturers = new ArrayList<>();
        while (rs.next()) {
            lecturers.add(new Lecturer(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname")));
        }

        return lecturers;
    }

    @Override
    public void updateEntities(List<Lecturer> entities) throws SQLException {

    }
}
