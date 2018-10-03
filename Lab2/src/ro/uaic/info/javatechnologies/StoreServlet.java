package ro.uaic.info.javatechnologies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StoreServlet")
public class StoreServlet extends HttpServlet {
    private List<Record> records;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        String key = request.getParameter("key");
        String name = request.getParameter("name");

        if (category == null || key == null || name == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        else {
            request.setAttribute("records", records);
        }
        getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
