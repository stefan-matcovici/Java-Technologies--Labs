package ro.uaic.info.javatechnologies.exceptions;

import javax.servlet.ServletException;

public class WrongCaptchaServletException extends ServletException {
    public WrongCaptchaServletException() {
        super("The captcha you entered is different from the one from the picture, please try again!");
    }
}
