package ro.uaic.info.javatechnologies.dbperformance.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener
public class AppListener implements ServletContextListener, ConnectionFactory {
    private static Connection connection;

    public void contextInitialized(ServletContextEvent ce) {
        connection = createConnection();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    public static Connection getConnection() {
        return connection;
    }
}
