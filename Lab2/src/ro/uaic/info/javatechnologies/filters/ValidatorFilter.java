package ro.uaic.info.javatechnologies.filters;

import ro.uaic.info.javatechnologies.exceptions.EmptyFormAttributeServletException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(
        urlPatterns = {"/store"},
        initParams = {@WebInitParam(name = "defaultCategory", value = "default-category")}
)
public class ValidatorFilter implements Filter {
    private String defaultCategory;

    @Override
    public void init(FilterConfig filterConfig) throws  ServletException {
        this.defaultCategory = filterConfig.getInitParameter("defaultCategory");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String key = servletRequest.getParameter("key");
        String name = servletRequest.getParameter("name");

        if (key == null || key.isEmpty() || name == null || name.isEmpty()) {
            ((HttpServletResponse)servletResponse).sendRedirect("/");
        } else {
            String category = servletRequest.getParameter("categorySelect");
            String defaultCategory = this.defaultCategory;
            if (category == null || category.isEmpty()) {
                ServletRequest decoratedRequest = new HttpServletRequestWrapper((HttpServletRequest) servletRequest) {
                    @Override
                    public String getParameter(String name) {
                        if (name.equals("categorySelect")) {
                            return defaultCategory;
                        }
                        return super.getParameter(name);
                    }
                };
                filterChain.doFilter(decoratedRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
