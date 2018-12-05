package ro.uaic.info.javatechnologies.optcourses.beans;

import ro.uaic.info.javatechnologies.optcourses.models.Course;
import ro.uaic.info.javatechnologies.optcourses.repository.CourseRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("searchBean")
@ViewScoped
public class SearchBean implements Serializable {
    private CourseRepository courseRepository = new CourseRepository();
    private String name = "";
    private String studyGroups = "";
    private Boolean optional;

    public List<Course> getFilteredCourses() {
        return courseRepository.getFilteredCourses(name, optional, studyGroups);
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
}
