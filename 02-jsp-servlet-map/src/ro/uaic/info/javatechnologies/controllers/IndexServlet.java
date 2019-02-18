package ro.uaic.info.javatechnologies.controllers;

import ro.uaic.info.javatechnologies.models.Categories;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "IndexServlet", urlPatterns = "/")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categories", new Categories(new String[]{"English", "Romanian", "German", "French"}));
        getServletContext().getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
    }
}
