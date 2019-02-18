package ro.uaic.info.javatechnologies.optcourses.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener()
public class SessionCounter implements HttpSessionListener {
    private static int users = 0;
    /* Session Creation Event */
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        users ++;
    }
    /* Session Invalidation Event */
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        users --;
    }
    public static int getConcurrentUsers() {
        return users;
    }
}
