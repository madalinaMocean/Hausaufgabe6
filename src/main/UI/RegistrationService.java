package main.UI;

import main.Repository.Inheritable.CourseRepository;
import main.Model.*;
import main.Repository.Inheritable.StudentRepository;
import main.Repository.Inheritable.TeacherRepository;
import main.Repository.RepositoryException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegistrationService {

    public CourseRepository courseRepository;
    public StudentRepository studentRepository;
    public TeacherRepository teacherRepository;

    public RegistrationService(CourseRepository courseRepository,StudentRepository studentRepository,TeacherRepository teacherRepository){
        this.courseRepository=courseRepository;
        this.studentRepository=studentRepository;
        this.teacherRepository=teacherRepository;
    }

    public void addCourse(int id, String courseName, Person courseTeacher, int maxEnrollment, int credits) throws ExceptionService {
        try {
            Course course = new Course(id, courseName, courseTeacher, maxEnrollment, credits);
            courseRepository.add(course);
        }
        catch(RepositoryException e){
            throw  new ExceptionService("Course could not be created or added!");
        }
    }

    public void addStudent(int id, String firstName, String lastName) throws ExceptionService {
        try {
            Student student = new Student( id,  firstName, lastName);
            studentRepository.add(student);
        }
        catch(RepositoryException e){
            throw  new ExceptionService("Student could not be created or added!");
        }
    }

    public void addTeacher(int id, String firstName, String lastName) throws ExceptionService {
        try {
            Teacher teacher = new Teacher( id,  firstName, lastName);
            teacherRepository.add(teacher);
        }
        catch(RepositoryException e){
            throw  new ExceptionService("Teacher could not be created or added!");
        }

    }


    public boolean register( Course course,Student student){
        if (!course.hasAvailableSeats()){
            System.out.println("Course if fully booked! Try registering to another Course!");
            return false;
        }

        if(course.getStudentsEnrolled().contains(student)){
            System.out.println("Student already registered");
            return false;
        }

        if(!course.addStudent(student) && !student.addCourse(course)){
            System.out.println("Student could not be registered");
            return false;
        }

        System.out.println("Student successfully registered!");
        return true;
    }

    public void teacherClaimCourse(Course course, Teacher teacher){
        course.setCourseTeacher(teacher);
        teacher.addCourse(course);
        System.out.println("Teacher successfully claimed course!");
    }


    public List<Course> retrieveCoursesWithFreePlaces() {
        List<Course> freePlacesCourses = new ArrayList<>();
        for (Course course : courseRepository.getAll()) {
            if (course.hasAvailableSeats()) {
                freePlacesCourses.add(course);
            }
        }
        return freePlacesCourses;
    }

    public List<Student> retrieveStudentsEnrolledForACourse(Course course){
        return course.getStudentsEnrolled();
    }

    public List<Course> getAllCourses(){
        return new ArrayList<>(courseRepository.getAll());
    }

    public List<Teacher> getAllTeachers(){
        return new ArrayList<>(teacherRepository.getAll());
    }

    public List<Student> getAllStudents(){
        return new ArrayList<>(studentRepository.getAll());
    }


    public boolean deleteCourseByTeacher(Teacher teacher, Course course){
        if (!teacher.getCourses().contains(course)){
            System.out.println("Teacher does not run this course!");
            return false;
        }
        for (Student student:course.getStudentsEnrolled()) {
            student.removeCourse(course);
        }
        course.clearList();
        course.setCourseTeacher(null);
        teacher.removeCourse(course);

        return true;
    }
    // course to teacher controller function

}

