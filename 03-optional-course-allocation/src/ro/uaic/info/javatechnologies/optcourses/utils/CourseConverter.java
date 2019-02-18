package ro.uaic.info.javatechnologies.optcourses.utils;

import ro.uaic.info.javatechnologies.optcourses.models.Course;
import ro.uaic.info.javatechnologies.optcourses.models.Lecturer;
import ro.uaic.info.javatechnologies.optcourses.models.Semester;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.net.MalformedURLException;
import java.net.URL;

@FacesConverter(forClass = Course.class)
public class CourseConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        String[] attributes = value.split(",");
        int attrCount = attributes.length;

        Course course = null;
        try {
            Lecturer lecturer = new Lecturer(Integer.valueOf(attributes[attrCount - 5]), attributes[attrCount - 4], attributes[attrCount - 3], attributes[attrCount - 2], new URL(attributes[attrCount - 1]));
            course = new Course(attributes[0], attributes[1], Integer.valueOf(attributes[2]), Semester.valueOf(attributes[3].toUpperCase()), new URL(attributes[4]), lecturer, Integer.valueOf(attributes[5]));
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