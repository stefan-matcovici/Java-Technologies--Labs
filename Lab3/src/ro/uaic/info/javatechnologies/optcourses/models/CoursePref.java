package ro.uaic.info.javatechnologies.optcourses.models;

public class CoursePref {
    String course;
    Double preferencePercent;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Double getPreferencePercent() {
        return preferencePercent;
    }

    public void setPreferencePercent(Double preferencePercent) {
        this.preferencePercent = preferencePercent;
    }
}
