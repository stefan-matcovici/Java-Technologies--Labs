package ro.uaic.info.javatechnologies.dbperformance.controllers;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "SelectServlet",
        urlPatterns = "/select",
        initParams = @WebInitParam(name = "connection-type", value = "singleton"))
public class SelectServlet extends HttpServlet implements DBServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Connection connection = getConnection(getInitParameter("connection-type"));
            select(connection);
            connection.close();
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException | NamingException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
