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
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

@WebServlet(name = "MapHTTPServlet",
        urlPatterns = "/map",
        initParams = {
                @WebInitParam(name = "propertiesPath", value = "map.properties"),
                @WebInitParam(name = "synchronisationRequired", value = "false")
        }
)

public class MapHTTPServlet extends HttpServlet {

    private Map<String, Entry> map;
    private OutputStream outputStream;
    private boolean synchronisationRequired;

    @Override
    public void init() throws ServletException {
        super.init();
//        map = new ConcurrentSkipListMap<>(Comparator.comparing(String::toString));
        map = new TreeMap<>(Comparator.comparing(String::toString));

        String stringPath = getServletConfig().getInitParameter("propertiesPath");
        try {
            Path path = Paths.get(getServletContext().getRealPath("/WEB-INF/" + stringPath));
            outputStream = new BufferedOutputStream(Files.newOutputStream(path));
        } catch (IOException e) {
            getServletContext().log(String.format("Couldn't create outputstream for file %s: %s", stringPath, e));
            throw new ServletException("IOException in init method");
        }

        synchronisationRequired = Boolean.valueOf(getServletConfig().getInitParameter("synchronisationRequired"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log(request);
        String key = request.getParameter("key");
        String value = request.getParameter("value");

        if (key == null || value == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            store(key, value, timestamp.getTime());
            if (isBrowserRequest(request)) {
                showList(request, response);
            } else {
                writeResponse(response);
                response.setStatus(200);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log(request);
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    private void writeResponse(HttpServletResponse response) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            synchronized (synchronisationRequired ? map : new Object()) {
                for (Map.Entry<String, Entry> mapEntry : map.entrySet()) {
                    writer.append(String.format("%s : %s", mapEntry.getKey(), mapEntry.getValue()));
                    writer.append("\n");
                }
            }
            writer.flush();
        }
    }

    private void store(String key, String value, long timestamp) throws IOException {
        synchronized (synchronisationRequired ? map: new Object()) {
            map.put(key, new Entry(value, timestamp));
        }
        synchronized (synchronisationRequired ? outputStream : new Object()) {
            outputStream.write(key.getBytes());
            outputStream.write(":".getBytes());
            outputStream.write(value.getBytes());
            outputStream.write("\n".getBytes());
            outputStream.flush();
        }
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("map", map);
        RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
        rd.forward(request, response);
    }

    private void log(HttpServletRequest request) {
        getServletContext().log(String.format("\nMethod used: %s\n\"Client ip: %s\nUser Agent: %s\nClient languages: %s\nParameters: %s",
                request.getMethod(),
                request.getRemoteAddr(),
                request.getHeader("User-Agent"),
                logLocales(request.getLocales()),
                logParameterMap(request.getParameterMap())));

    }

    private String logLocales(Enumeration<Locale> locales) {
        StringBuilder stringBuilder = new StringBuilder();
        while (locales.hasMoreElements()) {
            stringBuilder.append(locales.nextElement().getLanguage()).append(", ");
        }

        return stringBuilder.subSequence(0, stringBuilder.length()-2).toString();
    }

    private String logParameterMap(Map<String, String[]> parameterMap) {
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

    private boolean isBrowserRequest(HttpServletRequest request) {
        return request.getHeader("User-Agent").contains("Mozilla");
    }

    @Override
    public void destroy() {
        super.destroy();

        try {
            outputStream.flush();
            outputStream.close();
            getServletContext().log("Closed file");
        } catch (IOException e) {
            getServletContext().log(String.format("Couldn't close file stream: %s", e));
        }
    }
}
