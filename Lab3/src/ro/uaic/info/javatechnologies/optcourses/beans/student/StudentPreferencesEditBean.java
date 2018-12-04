package ro.uaic.info.javatechnologies.optcourses.beans.student;

import ro.uaic.info.javatechnologies.optcourses.beans.DataEdit;
import ro.uaic.info.javatechnologies.optcourses.models.Course;
import ro.uaic.info.javatechnologies.optcourses.models.OptionalPackage;
import ro.uaic.info.javatechnologies.optcourses.models.Student;
import ro.uaic.info.javatechnologies.optcourses.models.StudentPref;
import ro.uaic.info.javatechnologies.optcourses.repository.OptionalCourseRepository;
import ro.uaic.info.javatechnologies.optcourses.repository.OptionalPackageRepository;
import ro.uaic.info.javatechnologies.optcourses.repository.StudentsPrefRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Named("studentPreferencesEditBean")
@ViewScoped
public class StudentPreferencesEditBean extends DataEdit<StudentPref, Integer> implements Serializable {

    private OptionalPackageRepository optionalPackageRepository = new OptionalPackageRepository();
    private OptionalCourseRepository optionalCourseRepository = new OptionalCourseRepository();
    private List<OptionalPackage> packageList;
    private List<Course> courseList;
    private List<Integer> posList;
    private Student student;
    private OptionalPackage optionalPackage;
    private Course course;

    public StudentPreferencesEditBean() {
        super();
        entity = new StudentPref();
        repository = new StudentsPrefRepository();
    }

    public void getOnStudentChange() throws SQLException {
        packageList = optionalPackageRepository.getByYear(((StudentPref)entity).getStudent().getYear());
    }

    public void getGetOnPackageChange() {
        courseList = optionalPackage.getCourses().stream().map(course1 -> optionalCourseRepository.getCourseById(course1.getId())).collect(Collectors.toList());
        posList = IntStream.rangeClosed(1, courseList.size()).boxed().collect(Collectors.toList());
    }

    public void onPosChange() {
        Student student = ((StudentPref)entity).getStudent();
    }

    public List<OptionalPackage> getPackageList() {
        return packageList;
    }

    public void setPackageList(List<OptionalPackage> packageList) {
        this.packageList = packageList;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public OptionalPackage getOptionalPackage() {
        return optionalPackage;
    }

    public void setOptionalPackage(OptionalPackage optionalPackage) {
        this.optionalPackage = optionalPackage;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<Integer> getPosList() {
        return posList;
    }

    public void setPosList(List<Integer> posList) {
        this.posList = posList;
    }
}