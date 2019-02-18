package ro.uaic.info.javatechnologies.exceptions;

import javax.servlet.ServletException;

public class KeyAlreadyExistsServletException extends ServletException {
    public KeyAlreadyExistsServletException(String key) {
        super(String.format("Key %s is already stored, please choose another one", key));
    }
}
