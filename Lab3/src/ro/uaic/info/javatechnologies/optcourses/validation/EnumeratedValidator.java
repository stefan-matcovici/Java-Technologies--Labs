package ro.uaic.info.javatechnologies.optcourses.validation;

import ro.uaic.info.javatechnologies.optcourses.models.Semester;

import javax.faces.validator.FacesValidator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class EnumeratedValidator implements ConstraintValidator<Enumerated, String> {
    public void initialize(Enumerated constraint) {
    }

    public boolean isValid(String semesterString, ConstraintValidatorContext context) {
        return Arrays.stream(Semester.values()).anyMatch(semester -> semester.getName().equals(semesterString));
    }
}
