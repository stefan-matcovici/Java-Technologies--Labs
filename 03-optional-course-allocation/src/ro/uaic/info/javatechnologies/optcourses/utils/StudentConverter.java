package ro.uaic.info.javatechnologies.optcourses.utils;

import ro.uaic.info.javatechnologies.optcourses.models.Student;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Student.class)
public class StudentConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        String[] attributes = value.split(",");
        Student student = null;
        student = new Student(Integer.valueOf(attributes[0]), attributes[1], attributes[2], attributes[3], Integer.valueOf(attributes[4]));
        return student;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        return value.toString();
    }

}
