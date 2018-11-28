package ro.uaic.info.javatechnologies.optcourses.utils;

import ro.uaic.info.javatechnologies.optcourses.models.Lecturer;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.net.MalformedURLException;
import java.net.URL;

@FacesConverter(forClass= Lecturer.class)
public class LecturerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        String[] attributes = value.split(",");
        Lecturer lecturer = null;
        try {
           lecturer = new Lecturer(Integer.valueOf(attributes[0]), attributes[1], attributes[2], attributes[3], new URL(attributes[4]));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return lecturer;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        return value.toString();
    }

}