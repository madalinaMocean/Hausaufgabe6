package main.UI;

import main.Model.*;
import main.Repository.*;
import org.junit.jupiter.api.Test;

import java.sql.*;
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

    @Test
    void jdbcTest() throws ExceptionService {

        final String DB_URL = "jdbc:mysql://localhost/university";
        final String USER = "root";
        final String PASS = "Admitere2020@";
        final String QUERY1 = "select count(*) as count from course ;";
        final String QUERY2 = "select count(*) as count from teacher ;";
        final String QUERY3 = "select count(*) as count from student ;";
        final String deleteQUERY1 = "delete from course where course.id=99;";
        final String deleteQUERY2=" delete from teacher where teacher.id=99;";
        final String deleteQUERY3 ="delete from student where student.id=99;";

        CourseJDBCRepository courseJDBCRepository = new CourseJDBCRepository();
        StudentJDBCRepository studentJDBCRepository= new StudentJDBCRepository();
        TeacherJDBCRepository teacherJDBCRepository= new TeacherJDBCRepository();

        RegistrationService registrationSystem = new RegistrationService(courseJDBCRepository,studentJDBCRepository,teacherJDBCRepository);

        registrationSystem.addStudent(99,"daria","dob");

        registrationSystem.addTeacher(99,"andreea","car");

        registrationSystem.addCourse(99,"map",null,6,6);


        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt1 = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            ResultSet rs1 = stmt1.executeQuery(QUERY1);){

            while (rs1.next()) {
                assert(rs1.getInt("count")==1);
            }


            stmt2.executeUpdate(deleteQUERY1);
            stmt2.executeUpdate(deleteQUERY2);
            stmt2.executeUpdate(deleteQUERY3);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}