package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.models.AbstractEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DataRepository<T extends AbstractEntity<ID>, ID> {
    public abstract T getById(ID id);
    public abstract void save(T t) throws SQLException;

    protected Connection getConnection() {
        Connection con = null;
        String url = "jdbc:postgresql://localhost:5432/optcourses";
        String user = "postgres";
        String password = "root";

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection completed.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        }
        return con;
    }
}
