package ro.uaic.info.javatechnologies.optcourses.beans;

import ro.uaic.info.javatechnologies.optcourses.models.Semester;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("semesterBean")
@RequestScoped
public class SemesterBean implements Serializable {

    public Semester[] getStatuses() {
        return Semester.values();
    }

}
