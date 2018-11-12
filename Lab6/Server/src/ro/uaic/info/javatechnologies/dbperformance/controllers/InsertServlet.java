package ro.uaic.info.javatechnologies.dbperformance.controllers;

import javax.naming.NamingException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@WebServlet(name = "InsertServlet",
        urlPatterns = "/insert",
        initParams = @WebInitParam(name = "connection-type", value = "connection-pool"))
public class InsertServlet extends HttpServlet implements DBServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Connection connection = getConnection(getInitParameter("connection-type"));
            insert(connection, new Date(), req.getRemoteHost(), paramsToString(req.getParameterMap()));
            connection.close();
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException | NamingException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }


    private String paramsToString(Map<String, String[]> parameterMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            stringBuilder.append(entry.getKey())
                    .append(": ");
            for (String value : entry.getValue()) {
                stringBuilder.append(value)
                        .append(" ");
            }
        }

        return stringBuilder.toString();
    }

}