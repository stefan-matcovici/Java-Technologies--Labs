package ro.uaic.info.javatechnologies.services;

import ro.uaic.info.javatechnologies.models.Record;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public interface Repository {
    void storeRecord(HttpServletRequest request, Record record, ResourceBundle resourceBundle) throws ServletException;
}
