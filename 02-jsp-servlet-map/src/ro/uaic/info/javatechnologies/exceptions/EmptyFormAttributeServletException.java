package ro.uaic.info.javatechnologies.exceptions;

import javax.servlet.ServletException;

public class EmptyFormAttributeServletException extends ServletException {
    public EmptyFormAttributeServletException() {
        super("All the form's attributes are required, please fill them in!");
    }
}
