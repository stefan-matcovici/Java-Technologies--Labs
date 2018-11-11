package ro.uaic.info.javatechnologies.dbperformance.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.Connection;

@WebListener()
public class SessionListener implements HttpSessionListener, ConnectionFactory {
    private static Connection connection;

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        connection = createConnection();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }

    public static Connection getConnection() {
        return connection;
    }
}
