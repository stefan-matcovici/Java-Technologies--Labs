package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.models.Lecturer;
import ro.uaic.info.javatechnologies.optcourses.models.OptionalPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OptionalPackageRepository extends DataRepository<OptionalPackage, String> {

    private static final String addOptionalPacakgeQuery = "INSERT INTO public.\"OPTIONAL_PACKAGES\" (id, year, semester) VALUES (?, ?, ?); ";

    @Override
    public OptionalPackage getById(String s) {
        return null;
    }

    @Override
    public void save(OptionalPackage optionalPackage) throws SQLException {
        Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement(addOptionalPacakgeQuery);
        pst.setString(1, optionalPackage.getId());
        pst.setInt(2, optionalPackage.getYear());
        pst.setString(3, optionalPackage.getSemester().getName());

        pst.executeUpdate();
    }
}
