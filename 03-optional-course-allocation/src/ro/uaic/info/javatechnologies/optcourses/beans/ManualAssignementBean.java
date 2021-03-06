package ro.uaic.info.javatechnologies.optcourses.beans;

import ro.uaic.info.javatechnologies.optcourses.ejb.AssignementBean;
import ro.uaic.info.javatechnologies.optcourses.ejb.CourseCheckingBean;
import ro.uaic.info.javatechnologies.optcourses.models.OptionalCourse;
import ro.uaic.info.javatechnologies.optcourses.models.Student;
import ro.uaic.info.javatechnologies.optcourses.repository.OptionalCourseRepository;
import ro.uaic.info.javatechnologies.optcourses.repository.StudentRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named("manualAsignementBean")
@ViewScoped
public class ManualAssignementBean implements Serializable {

    @EJB
    StudentRepository studentRepository;

    @EJB
    OptionalCourseRepository optionalCourseRepository;

    @EJB
    AssignementBean assignementBean;

    @EJB
    CourseCheckingBean courseCheckingBean;

    private List<Student> studentsToChose = new ArrayList<>();
    private List<OptionalCourse> optionalCourses = new ArrayList<>();
    private Student selectedStudent;
    private OptionalCourse optionalCourse;

    @PostConstruct
    public void init() {
        studentsToChose = studentRepository.getAll();
    }

    public void onSelectedStudent() {
        assignementBean.addStudent(selectedStudent);
        studentsToChose = studentRepository.getAll().stream().filter(s -> !assignementBean.getSelectedStudents().contains(s)).collect(Collectors.toList());
        optionalCourses = optionalCourseRepository.getByYear(assignementBean.getSelectedStudents().get(0).getYear());
        selectedStudent = null;
    }

    public String getRemainingPlaces() {
        if (optionalCourse == null) {
            return "No optional course selected.";
        } else {
            return String.valueOf(courseCheckingBean.getCourseRemainingPlaces(optionalCourse));
        }
    }

    public String obtainTenant() {
        return null;
    }

    public void submit() {
        if (assignementBean.getOptionalCourse() != null && courseCheckingBean.getCourseRemainingPlaces(optionalCourse) < assignementBean.getSelectedStudents().size()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Too many students!"));
        }
        else {
            try {
                assignementBean.submit(optionalCourse);
            } catch (EJBException e) {
                System.out.println(e.getCausedByException().toString());
            }
            finally {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
                myNav.handleNavigation(facesContext, null, "index");
            }

        }
    }

    public List<Student> getSelectedStudents() {
        return assignementBean.getSelectedStudents();
    }

    public List<Student> getStudentsToChose() {
        return studentsToChose;
    }

    public void setStudentsToChose(List<Student> studentsToChose) {
        this.studentsToChose = studentsToChose;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public List<OptionalCourse> getOptionalCourses() {
        return optionalCourses;
    }

    public void setOptionalCourses(List<OptionalCourse> optionalCourses) {
        this.optionalCourses = optionalCourses;
    }

    public OptionalCourse getOptionalCourse() {
        return optionalCourse;
    }

    public void setOptionalCourse(OptionalCourse optionalCourse) {
        this.optionalCourse = optionalCourse;
    }
}
