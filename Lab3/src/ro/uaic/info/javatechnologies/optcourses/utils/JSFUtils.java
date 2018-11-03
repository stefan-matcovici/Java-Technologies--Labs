package ro.uaic.info.javatechnologies.optcourses.utils;

import javax.faces.application.Application;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import java.util.Locale;
import java.util.ResourceBundle;

public class JSFUtils {

    public static FacesContext getFacesContext(){
        return FacesContext.getCurrentInstance();
    }

    public static Application getApplication(){
        return getFacesContext().getApplication();
    }

    public static UIViewRoot getViewRoot(){
        return getFacesContext().getViewRoot();
    }

    public static void setLocale(Locale locale){
        getViewRoot().setLocale(locale);
    }

    public static ResourceBundle getResourceBundle(String key){
        return getApplication().getResourceBundle(getFacesContext(), key);
    }
}
