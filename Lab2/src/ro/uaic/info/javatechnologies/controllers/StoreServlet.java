package ro.uaic.info.javatechnologies.controllers;

import ro.uaic.info.javatechnologies.exceptions.EmptyFormAttributeServletException;
import ro.uaic.info.javatechnologies.exceptions.WrongCaptchaServletException;
import ro.uaic.info.javatechnologies.models.Record;
import ro.uaic.info.javatechnologies.services.Repository;
import ro.uaic.info.javatechnologies.services.SessionRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "StoreServlet", urlPatterns = "/store")
public class StoreServlet extends HttpServlet {
    private Repository repository;

    @Override
    public void init() throws ServletException {
        super.init();
        repository = new SessionRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String captchaString = request.getParameter("captcha");
        isCaptchaStringMatching(captchaString, request);

        String category = request.getParameter("categorySelect");
        String key = request.getParameter("key");
        String name = request.getParameter("name");

        if (category == null || category.isEmpty() || key == null || key.isEmpty() || name == null || name.isEmpty()) {
            throw new EmptyFormAttributeServletException();
        } else {
            Record record = new Record();
            record.setCategory(category);
            record.setKey(key);
            record.setName(name);
            repository.storeRecord(request, record);

            Cookie cookie = new Cookie("StoreServlet.category", category);
            cookie.setMaxAge(30 * 60);
            response.addCookie(cookie);

            getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
        }
    }

    private void isCaptchaStringMatching(String captchaString, HttpServletRequest request) throws ServletException {
        HttpSession session = request.getSession(true);
        if (!captchaString.equals(session.getAttribute("CaptchaServlet.captchaString"))) {
            throw new WrongCaptchaServletException();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
    }
}