package ro.uaic.info.javatechnologies.dbperformance.listeners;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConnectionFactory {
    public default Connection createConnection() {
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/dbperformance";
        String user = "postgres";
        String password = "root";

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection completed.");
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        return connection;
    }
}
