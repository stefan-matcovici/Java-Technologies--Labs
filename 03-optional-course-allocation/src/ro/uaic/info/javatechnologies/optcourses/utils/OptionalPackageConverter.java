package ro.uaic.info.javatechnologies.optcourses.utils;

import ro.uaic.info.javatechnologies.optcourses.models.Course;
import ro.uaic.info.javatechnologies.optcourses.models.OptionalPackage;
import ro.uaic.info.javatechnologies.optcourses.models.Semester;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.ArrayList;
import java.util.List;

@FacesConverter(forClass= OptionalPackage.class)
public class OptionalPackageConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        String[] attributes = value.split(",");
        List<Course> courses = new ArrayList<>();

        OptionalPackage optionalPackage = new OptionalPackage(attributes[0], attributes[1], Integer.valueOf(attributes[2]), Semester.valueOf(attributes[3].toUpperCase()));
        for (int i=4;i<attributes.length;i+=1) {
            courses.add(new Course(attributes[i]));
        }
        optionalPackage.setCourses(courses);
        return optionalPackage;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        return value.toString();
    }
}
