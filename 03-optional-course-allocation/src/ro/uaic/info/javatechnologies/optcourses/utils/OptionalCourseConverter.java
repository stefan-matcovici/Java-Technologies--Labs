package ro.uaic.info.javatechnologies.optcourses.utils;

import ro.uaic.info.javatechnologies.optcourses.models.OptionalCourse;
import ro.uaic.info.javatechnologies.optcourses.models.Semester;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.net.MalformedURLException;
import java.net.URL;

@FacesConverter(forClass = OptionalCourse.class)
public class OptionalCourseConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        String[] attributes = value.split(",");
        int attrCount = attributes.length;

        OptionalCourse course = null;
        try {
            course = new OptionalCourse(attributes[0], attributes[1], Integer.valueOf(attributes[2]), Semester.valueOf(attributes[3].toUpperCase()), new URL(attributes[4]), Integer.valueOf(attributes[5]));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        return value.toString();
    }
}
