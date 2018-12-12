package ro.uaic.info.javatechnologies.optcourses.utils;

import ro.uaic.info.javatechnologies.optcourses.entities.*;
import ro.uaic.info.javatechnologies.optcourses.models.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

public class EntityConverter {

    public static LecturersEntity toLecturerEntity(Lecturer lecturer) {
        LecturersEntity result = new LecturersEntity();

        if (lecturer.getId() != null) {
            result.setId(lecturer.getId());
        }
        result.setFirstName(lecturer.getFirstName());
        result.setLastName(lecturer.getLastName());
        result.setEmail(lecturer.getEmail());
        result.setUrl(lecturer.getUrl().toString());

        return result;
    }

    public static Lecturer toLecturer(LecturersEntity entity) {
        Lecturer lecturer = new Lecturer();

        lecturer.setId(entity.getId());
        lecturer.setFirstName(entity.getFirstName());
        lecturer.setLastName(entity.getLastName());
        lecturer.setEmail(entity.getEmail());
        try {
            lecturer.setUrl(new URL(entity.getUrl()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return lecturer;
    }


    public static OptionalPackage toOptionalPackage(PackagesEntity entity) {
        OptionalPackage result = new OptionalPackage();
        result.setId(String.valueOf(entity.getId()));
        result.setCode(entity.getCode());
        result.setYear(entity.getYear());
        result.setSemester(Semester.valueOf(entity.getSemester().toUpperCase()));
        result.setCode(entity.getCode());
        result.setCourses(entity.getOptionalCourseEntities().stream().map(EntityConverter::toMandatoryCourse).collect(Collectors.toList()));

        return result;
    }

    public static PackagesEntity toOptionalPackageEntity(OptionalPackage optionalPackage) {
        PackagesEntity result = new PackagesEntity();
        if (optionalPackage.getId() != null)
            result.setId(Integer.parseInt(optionalPackage.getId()));
        result.setCode(optionalPackage.getCode());
        result.setYear(optionalPackage.getYear());
        result.setSemester(optionalPackage.getSemester().getName());
        result.setCode(optionalPackage.getCode());

        return result;
    }

    public static OptionalCourse toOptionalCourse(OptionalCourseEntity coursesEntity) {
        OptionalCourse result = new OptionalCourse();

        result.setId(String.valueOf(coursesEntity.getId()));
        result.setName(coursesEntity.getName());
        result.setSemester(Semester.valueOf(coursesEntity.getSemester().toUpperCase()));
        result.setStudyGroups(coursesEntity.getStudyGroups());
        result.setOptionalPackage(toOptionalPackage(coursesEntity.getPackageEntity()));
        result.setRemainingPlaces(coursesEntity.getRemainingPlaces());

        try {
            result.setUrl(new URL(coursesEntity.getUrl()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        result.setYear(coursesEntity.getYear());
        result.setLecturer(toLecturer(coursesEntity.getLecturer()));

        return result;
    }

    public static OptionalCourseEntity toOptionalCourseEntity(OptionalCourse course) {
        OptionalCourseEntity result = new OptionalCourseEntity();

        result.setId(Integer.parseInt(course.getId()));
        result.setName(course.getName());
        result.setSemester(course.getSemester().getName());
        result.setStudyGroups(course.getStudyGroups());
        if (course.getUrl() != null)
            result.setUrl(course.getUrl().toString());
        result.setYear(course.getYear());
        result.setLecturer(toLecturerEntity(course.getLecturer()));
        result.setPackageEntity(toOptionalPackageEntity(course.getOptionalPackage()));
        result.setRemainingPlaces(course.getRemainingPlaces());

        return result;
    }

    public static Course toMandatoryCourse(CoursesEntity coursesEntity) {
        Course result = new Course();

        result.setId(String.valueOf(coursesEntity.getId()));
        result.setName(coursesEntity.getName());
        result.setSemester(Semester.valueOf(coursesEntity.getSemester().toUpperCase()));
        result.setStudyGroups(coursesEntity.getStudyGroups());
        try {
            result.setUrl(new URL(coursesEntity.getUrl()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        result.setYear(coursesEntity.getYear());
        result.setLecturer(toLecturer(coursesEntity.getLecturer()));

        return result;
    }

    public static MandatoryCourseEntity toMandatoryCourseEntity(Course course) {
        MandatoryCourseEntity result = new MandatoryCourseEntity();

        result.setId(Integer.parseInt(course.getId()));
        result.setName(course.getName());
        result.setSemester(course.getSemester().getName().toUpperCase());
        result.setStudyGroups(course.getStudyGroups());
        if (course.getUrl() != null)
            result.setUrl(course.getUrl().toString());
        result.setYear(course.getYear());
        result.setLecturer(toLecturerEntity(course.getLecturer()));

        return result;
    }

    public static StudentsEntity toStudentEntity(Student student) {
        StudentsEntity result = new StudentsEntity();

        result.setId(String.valueOf(student.getId()));
        result.setFirstName(student.getFirstName());
        result.setLastName(student.getLastName());
        result.setEmail(student.getEmail());
        result.setYear(student.getYear());

        return result;
    }

    public static Student toStudent(StudentsEntity entity) {
        Student student = new Student();

        student.setId(Integer.valueOf(entity.getId()));
        student.setFirstName(entity.getFirstName());
        student.setLastName(entity.getLastName());
        student.setEmail(entity.getEmail());
        student.setYear(entity.getYear());

        return student;
    }


    public static StudentPrefsEntity toStudentPrefEntity(StudentPref studentPref) {
        StudentPrefsEntity result = new StudentPrefsEntity();

        result.setCoursesByOptCourseId(toMandatoryCourseEntity(studentPref.getCourse()));
        result.setStudentsByStudentId(toStudentEntity(studentPref.getStudent()));
        result.setPos(studentPref.getPos());

        return result;
    }

    public static StudentPref toStudentPref(StudentPrefsEntity entity) {
        StudentPref student = new StudentPref();

        student.setCourse(toMandatoryCourse(entity.getCoursesByOptCourseId()));
        student.setStudent(toStudent(entity.getStudentsByStudentId()));
        student.setPos(entity.getPos());

        return student;
    }

}
