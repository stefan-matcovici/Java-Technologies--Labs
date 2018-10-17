package ro.uaic.info.javatechnologies.controllers;

import ro.uaic.info.javatechnologies.models.Record;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StoreServlet", urlPatterns = "/store")
public class StoreServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("categorySelect");
        String key = request.getParameter("key");
        String name = request.getParameter("name");
        String captchaString = request.getParameter("captcha");

        Record record = new Record();
        record.setCategory(category);
        record.setKey(key);
        record.setName(name);


        HttpSession session = request.getSession(true);

        if (!captchaString.equals(session.getAttribute("CaptchaServlet.captchaString"))) {
            throw new ServletException("Captcha string different from what you have entered");
        }

        List<Record> records = (List<Record>) session.getAttribute("StoreServlet.records");
        if (records == null) {
            records = new ArrayList<>();
        }

        if (records.stream().anyMatch(r -> r.getKey().equals(key))) {
            throw new ServletException(String.format("Key %s already exists. Please choose another one!", key));
        }

        records.add(record);
        session.setAttribute("StoreServlet.records", records);

        if (category == null || key == null || name == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            Cookie cookie = new Cookie("StoreServlet.category", category);
            cookie.setMaxAge(30 * 60);
            response.addCookie(cookie);
            getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
    }
}
