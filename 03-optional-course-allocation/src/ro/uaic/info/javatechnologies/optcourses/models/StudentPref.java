package ro.uaic.info.javatechnologies.optcourses.models;

public class StudentPref extends AbstractEntity<Integer> {
    private Student student;
    private Course course;
    private Integer pos;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }
}
