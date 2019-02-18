package ro.uaic.info.javatechnologies.optcourses.beans.optionalPackage;

import ro.uaic.info.javatechnologies.optcourses.beans.DataEdit;
import ro.uaic.info.javatechnologies.optcourses.models.CoursePref;
import ro.uaic.info.javatechnologies.optcourses.models.OptionalPackage;
import ro.uaic.info.javatechnologies.optcourses.repository.OptionalPackageRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("optionalPackageBean")
@ViewScoped
public class OptionalPackageEditBean extends DataEdit<OptionalPackage, String> {

    private List<CoursePref> coursePrefs = new ArrayList<>();
    private String newPref;
    private Double newPercent;
    private Double remainingPercent;
    private boolean courseNotDone;

    @EJB
    private OptionalPackageRepository optionalPackageRepository;

    public OptionalPackageEditBean() {
        super();
        entity = new OptionalPackage();
        courseNotDone = true;
    }

    @PostConstruct
    public void init() {
        repository = optionalPackageRepository;
    }

    public void addPref() {
        CoursePref coursePref = new CoursePref();
        coursePref.setCourse(newPref);
        coursePref.setPreferencePercent(newPercent);

        coursePrefs.add(coursePref);
        newPref = null;
        newPercent = null;
    }

    public List<CoursePref> getCoursePrefs() {
        return coursePrefs;
    }

    public void setCoursePrefs(List<CoursePref> coursePrefs) {
        this.coursePrefs = coursePrefs;
    }

    public void setNewPref(String newPref) {
        this.newPref = newPref;
    }

    public void setNewPercent(Double newPercent) {
        this.newPercent = newPercent;
    }

    public String getNewPref() {
        return newPref;
    }

    public Double getNewPercent() {
        return newPercent;
    }

    public double getRemainingPercent() {
        double result = 1 - coursePrefs.stream().map(CoursePref::getPreferencePercent).mapToDouble(Double::doubleValue).sum();
        if (result == 0) {
            courseNotDone = false;
        }
        return result;
    }

    public void setRemainingPercent(double remainingPercent) {
        this.remainingPercent = remainingPercent;
    }

    public boolean isCourseNotDone() {
        return courseNotDone;
    }

    public void setCourseNotDone(boolean courseNotDone) {
        this.courseNotDone = courseNotDone;
    }
}
