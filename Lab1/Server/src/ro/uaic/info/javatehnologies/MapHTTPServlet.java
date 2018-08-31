package ro.uaic.info.javatehnologies;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentSkipListMap;

@WebServlet(name = "MapHTTPServlet", urlPatterns = "/")
@WebInitParam(name = "propertiesPath", value = "map.properties")
public class MapHTTPServlet extends HttpServlet {

    private Map<String, String> map = new ConcurrentSkipListMap<>(Comparator.comparing(String::toString));
    private Properties props = new Properties();
    private OutputStream outputStream;

    @Override
    public void init() throws ServletException {
        super.init();

        String stringPath = getServletConfig().getInitParameter("propertiesPath");
        try {
            Path path = Paths.get(getServletContext().getRealPath("/WEB-INF/" + stringPath));
            outputStream = Files.newOutputStream(path);
        } catch (IOException e) {
            getServletContext().log(String.format("Couldn't create outputstream for file %s: %s", stringPath, e));
            throw new ServletException("IOException in init method");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log(request);
        String key = request.getParameter("key");
        String value = request.getParameter("value");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (key == null || value == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            store(key, value);
            if (request.getHeader("User-Agent").contains("Mozilla")) {
                showList(request, response);
            }
            else {
                try (PrintWriter writer = response.getWriter()) {
                    for (Map.Entry<String, String> entry: map.entrySet()) {
                        writer.append(String.format("%s : %s\n", entry.getKey(), entry.getValue()));
                    }
                    writer.flush();
                }
                response.setStatus(200);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

        synchronized (outputStream) {
            props.store(outputStream, null);
        }
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("map", map);
        RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
        rd.forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();

    }
}
