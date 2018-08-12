package ro.uaic.info.stefanmatcovici;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentSkipListMap;

@WebServlet(name = "SimpleHttpServlet", urlPatterns = "/")
public class SimpleHttpServlet extends HttpServlet {

    private Map<String, String> map = new ConcurrentSkipListMap<>(Comparator.comparing(String::toString));
    private Properties props = new Properties();
    private String path;

    @Override
    public void init() throws ServletException {
        super.init();

        path = getServletContext().getRealPath("/properties/map.properties").trim();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log(request);
        String key = request.getParameter("key");
        String value = request.getParameter("value");

        if (key == null || value == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            store(key, value);
            showList(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log(request);
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    private void log(HttpServletRequest request) {
        getServletContext().log(String.format("Method used: %s\n\"Client ip: %s\nUser Agent: %s\nClient language:" +
                        " %s\nParameters: %s", request.getMethod(), request.getRemoteAddr(),
                request.getHeader("User-Agent"), request.getLocale().toString(),
                request.getParameterMap().toString()));

    }

    private void store(String key, String value) throws IOException {
        map.put(key, value);
        props.setProperty(key, value);
        props.store(new FileWriter(path), null);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("map", map);
        RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
        rd.forward(request, response);
    }
}
