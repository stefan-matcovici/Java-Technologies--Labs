package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.models.OptionalPackage;
import ro.uaic.info.javatechnologies.optcourses.models.Semester;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OptionalPackageRepository extends DataRepository<OptionalPackage, String> {

    private static final String addOptionalPacakgeQuery = "INSERT INTO public.\"OPTIONAL_PACKAGES\" (id, year, semester) VALUES (?, ?, ?); ";
    private static final String getAllPackagesQUery = "SELECT * FROM public.\"OPTIONAL_PACKAGES\"";

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


    @Override
    public List<OptionalPackage> getAll() throws SQLException {
        Connection con = getConnection();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(getAllPackagesQUery);
        List<OptionalPackage> packages = new ArrayList<>();
        while (rs.next()) {
            packages.add(new OptionalPackage(rs.getString("id"), rs.getInt("year"),
                    Semester.valueOf(rs.getString("semester").toUpperCase())));
        }

        return packages;
    }
}
