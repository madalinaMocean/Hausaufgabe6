package main.UI;

import main.Model.*;
import main.Repository.CourseInMemoryRepository;
import main.Repository.StudentInMemoryRepository;
import main.Repository.TeacherInMemoryRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceTest {

    CourseInMemoryRepository courseInMemoryRepository = new CourseInMemoryRepository();
    StudentInMemoryRepository studentInMemoryRepository= new StudentInMemoryRepository();
    TeacherInMemoryRepository teacherInMemoryRepository= new TeacherInMemoryRepository();

    RegistrationService registrationSystem = new RegistrationService(courseInMemoryRepository,studentInMemoryRepository,teacherInMemoryRepository);

    Teacher teacher1 = new Teacher("Dan", "Doe");
    Teacher teacher2 = new Teacher("Mary", "Jane");

    Course course1 = new Course(2,"APM", teacher1, 1, 6);
    Course course2 = new Course(3,"DSA", teacher2, 3, 6);

    Student student1 = new Student(1,"James", "Blonde");
    Student student2 = new Student(4,"Jane", "Flower");

    @Test
    void register() {
        assert(registrationSystem.register(course1,student1));
        assert(!registrationSystem.register(course1,student2));
        assert(!registrationSystem.register(course1,student1));
    }

    @Test
    void retrieveCoursesWithFreePlaces() {
        assert(registrationSystem.retrieveCoursesWithFreePlaces().isEmpty());
        registrationSystem.courseRepository.add(course1);
        registrationSystem.register(course1,student1);
        assert(registrationSystem.retrieveCoursesWithFreePlaces().isEmpty());
        registrationSystem.courseRepository.add(course2);
        assert(registrationSystem.retrieveCoursesWithFreePlaces().size()==1);
    }

    @Test
    void retrieveStudentsEnrolledForACourse() {
        registrationSystem.courseRepository.add(course1);
        registrationSystem.register(course1,student1);
        assert(registrationSystem.retrieveStudentsEnrolledForACourse(course1).size()==1);
    }

    @Test
    void getAllCourses() {
        registrationSystem.courseRepository.add(course1);
        registrationSystem.courseRepository.add(course2);
        assert(registrationSystem.getAllCourses().size()==2);
    }

    @Test
    void deleteCourseByTeacher() {
        registrationSystem.courseRepository.add(course1); // add course to registration
        registrationSystem.register(course1,student1);
        List<Course> t1c = new ArrayList<Course>();
        t1c.add(course1);
        teacher1.setCourses(t1c);
        assert(!teacher1.getCourses().isEmpty());
        registrationSystem.deleteCourseByTeacher(teacher1, course1);
        assert(teacher1.getCourses().isEmpty());
    }
}