package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.models.Lecturer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LecturerRepository extends DataRepository<Lecturer, Integer> {

    private static final String addLecturerQuery = "INSERT INTO public.\"LECTURERS\" (firstname, lastname) VALUES (?, ?); ";

    @Override
    public Lecturer getById(Integer integer) {
        return null;
    }

    @Override
    public void save(Lecturer lecturer) throws SQLException {
        Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement(addLecturerQuery);
        pst.setString(1, lecturer.getFirstName());
        pst.setString(2, lecturer.getLastName());

        pst.executeUpdate();
    }
}
