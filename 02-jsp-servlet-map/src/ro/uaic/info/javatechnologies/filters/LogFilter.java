package ro.uaic.info.javatechnologies.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

@WebFilter(
        urlPatterns = {"/store", "/"})
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        // Find the IP of the request
        String ipAddress = request.getRemoteAddr();
        // Write something in the log
        System.out.println(String.format("\nMethod used: %s\n\"Client ip: %s\nUser Agent: %s\nClient languages: %s\nParameters: %s",
                request.getMethod(),
                request.getRemoteAddr(),
                request.getHeader("User-Agent"),
                logLocales(request.getLocales()),
                logParameterMap(request.getParameterMap())));

        chain.doFilter(req, res);
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

    @Override
    public void destroy() {

    }
}
