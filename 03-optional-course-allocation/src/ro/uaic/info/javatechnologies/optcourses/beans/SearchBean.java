package ro.uaic.info.javatechnologies.optcourses.beans;

import ro.uaic.info.javatechnologies.optcourses.models.Course;
import ro.uaic.info.javatechnologies.optcourses.repository.CourseRepository;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("searchBean")
@ViewScoped
public class SearchBean implements Serializable {
    private String name = "";
    private String studyGroups = "";
    private Boolean optional;
    private boolean useName = false;
    private boolean useOptional = false;
    private boolean useStudyGroups = false;

    @EJB
    private CourseRepository courseRepository;

    public List<Course> getFilteredCourses() {
        return courseRepository.getFilteredCourses(name, optional, studyGroups, useName, useOptional, useStudyGroups);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudyGroups() {
        return studyGroups;
    }

    public void setStudyGroups(String studyGroups) {
        this.studyGroups = studyGroups;
    }

    public Boolean getOptional() {
        return optional;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    public boolean isUseName() {
        return useName;
    }

    public void setUseName(boolean useName) {
        this.useName = useName;
    }

    public boolean isUseOptional() {
        return useOptional;
    }

    public void setUseOptional(boolean useOptional) {
        this.useOptional = useOptional;
    }

    public boolean isUseStudyGroups() {
        return useStudyGroups;
    }

    public void setUseStudyGroups(boolean useStudyGroups) {
        this.useStudyGroups = useStudyGroups;
    }
}
