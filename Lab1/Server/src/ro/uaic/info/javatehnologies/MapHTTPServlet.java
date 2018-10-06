package ro.uaic.info.javatehnologies;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

@WebServlet(name = "MapHTTPServlet",
        urlPatterns = "/map",
        initParams = {
                @WebInitParam(name = "propertiesPath", value = "map.properties")
        }
)

public class MapHTTPServlet extends HttpServlet {

    private Map<String, Entry> map;
    private Properties props;
    private OutputStream outputStream;

    @Override
    public void init() throws ServletException {
        super.init();
//        map = new ConcurrentSkipListMap<>(Comparator.comparing(String::toString));
        map = new TreeMap<>(Comparator.comparing(String::toString));
        props = new Properties();

        String stringPath = getServletConfig().getInitParameter("propertiesPath");
        try {
            Path path = Paths.get(getServletContext().getRealPath("/WEB-INF/" + stringPath));
            outputStream = new BufferedOutputStream(Files.newOutputStream(path));
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
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            store(key, value, timestamp.getTime());
            if (request.getHeader("User-Agent").contains("Mozilla")) {
                showList(request, response);
            } else {
                try (PrintWriter writer = response.getWriter()) {
                    for (Map.Entry<String, Entry> mapEntry : map.entrySet()) {
                        writer.append(String.format("%s : %s", mapEntry.getKey(), mapEntry.getValue()));
                        writer.append("\n");
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
        getServletContext().log(String.format("\nMethod used: %s\n\"Client ip: %s\nUser Agent: %s\nClient language:" +
                        " %s\nParameters: %s", request.getMethod(), request.getRemoteAddr(),
                request.getHeader("User-Agent"), request.getLocale().toString(),
                logParameterMap(request.getParameterMap())));

    }

    private String logParameterMap(Map<String, String[]> parameterMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String[]> entry: parameterMap.entrySet()) {
            stringBuilder.append(entry.getKey())
                    .append(": ");
            for (String value: entry.getValue()) {
                stringBuilder.append(value)
                        .append(" ");
            }
        }

        return stringBuilder.toString();
    }

    private void store(String key, String value, long timestamp) throws IOException {
        map.put(key, new Entry(value, timestamp));
        props.setProperty(key, value);
        props.store(outputStream, null);

    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("map", map);
        RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
        rd.forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();

        try {
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            getServletContext().log(String.format("Couldn't close file stream: %s", e));
        }

        getServletContext().log("Closed file");
    }
}
