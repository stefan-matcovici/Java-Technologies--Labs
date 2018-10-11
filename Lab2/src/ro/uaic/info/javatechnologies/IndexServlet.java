package ro.uaic.info.javatechnologies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "IndexServlet")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log("Hello");
        getServletContext().log("Hello");
        request.setAttribute("categories", new Categories(new String[]{"Category1", "Category2", "Category3"}));

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}