package ro.uaic.info.javatechnologies.exceptions;

import java.util.ServiceConfigurationError;

public class EmptyFormAttributeServletException extends ServiceConfigurationError {
    public EmptyFormAttributeServletException() {
        super("All the form's attributes are required, please fill them in!");
    }
}
