package ro.uaic.info.javatechnologies.services;

import ro.uaic.info.javatechnologies.exceptions.KeyAlreadyExistsServletException;
import ro.uaic.info.javatechnologies.models.Record;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class SessionRepository implements Repository {

    @Override
    public void storeRecord(HttpServletRequest request, Record record) throws ServletException {
        HttpSession session = request.getSession(true);
        List<Record> records = (List<Record>) session.getAttribute("StoreServlet.records");
        if (records == null) {
            records = new ArrayList<>();
        }

        if (records.stream().anyMatch(r -> r.getKey().equals(record.getKey()))) {
            throw new KeyAlreadyExistsServletException(record.getKey());
        }

        records.add(record);
        session.setAttribute("StoreServlet.records", records);
    }
}
