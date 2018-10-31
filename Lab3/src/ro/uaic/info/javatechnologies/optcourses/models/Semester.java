package ro.uaic.info.javatechnologies.optcourses.models;

public enum Semester {
    FALL("fall"),
    SPRING("spring");

    private final String name;

    private Semester(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }
}
