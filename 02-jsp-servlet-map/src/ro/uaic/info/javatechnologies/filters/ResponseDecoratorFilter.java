package ro.uaic.info.javatechnologies.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Pattern;

@WebFilter(urlPatterns = {"/store", "/"})
public class ResponseDecoratorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse)servletResponse) {
            private CharArrayWriter output = new CharArrayWriter();

            @Override
            public PrintWriter getWriter(){
                // Hide the original writer
                return new PrintWriter(output);
            }

            public String toString() {
                return output.toString();
            }

        };
        //Send the decorated object as a replacement for the original response
        filterChain.doFilter(servletRequest, wrapper);
        //Get the dynamically generated content from the decorator
        String content = wrapper.toString();
        // Modify the content
        StringWriter sw = new StringWriter();
        Pattern prelude = Pattern.compile("<body>(.*?)<", Pattern.DOTALL);
        Pattern coda = Pattern.compile(">(.*?)</body>", Pattern.DOTALL);
        sw.write(coda.matcher(prelude.matcher(content)
                .replaceAll("<body>$1<h1 style=\"text-align:center\">Java Tehnologies Lab2</h1><"))
                .replaceAll(">$1<footer style=\"width:100%; text-align:center;\">Created by Stefan Matcovici</footer></body>"));

        //Send the modified content using the original response
        PrintWriter out = servletResponse.getWriter();
        out.write(sw.toString());
    }

    @Override
    public void destroy() {

    }
}
