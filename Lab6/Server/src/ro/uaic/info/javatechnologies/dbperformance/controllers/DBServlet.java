package ro.uaic.info.javatechnologies.dbperformance.controllers;

import ro.uaic.info.javatechnologies.dbperformance.listeners.AppListener;
import ro.uaic.info.javatechnologies.dbperformance.listeners.SessionListener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Hashtable;

interface DBServlet {

    String INSERT_QUERY = "INSERT INTO public.\"TABLE\" (request_time, remote_addr, request_params) VALUES (?, ?, ?)";
    String SELECT_QUERY = "SELECT * FROM public.\"TABLE\"";

    default void insert(Connection connection, java.util.Date requestDate, String remoteAddress, String requestParameters) throws SQLException {
        PreparedStatement pst = null;
        pst = connection.prepareStatement(INSERT_QUERY);
        pst.setTimestamp(1, Timestamp.from(requestDate.toInstant()));
        pst.setString(2, remoteAddress);
        pst.setString(3, requestParameters);

        pst.executeUpdate();

    }

    default void select(Connection connection) throws SQLException {
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(SELECT_QUERY);

    }

    default Connection getConnection(String connectionType) throws NamingException, SQLException {
        Connection connection = null;
        switch (connectionType) {
            case "singleton":
                connection = AppListener.getConnection();
                break;
            case "connection-per-session":
                connection = SessionListener.getConnection();
                break;
            case "connection-pool":
                Context ctx = new InitialContext();
                DataSource ds = (DataSource)ctx.lookup("jdbc/dbperformance");
                connection = ds.getConnection();
                break;
        }
        return connection;
    }
}
