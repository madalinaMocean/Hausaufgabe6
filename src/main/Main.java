package main;

import main.Repository.*;
import main.Model.Course;
import main.Model.Student;
import main.Model.Teacher;
import main.UI.ExceptionService;
import main.UI.RegistrationService;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;


import static main.UI.StudentView.runStudentView;
import static main.UI.TeacherView.runTeacherView;

public class Main {
    public static void main(String[] args) throws ExceptionService {

        CourseJDBCRepository courseRepository = new CourseJDBCRepository();
        StudentJDBCRepository studentRepository= new StudentJDBCRepository();
        TeacherJDBCRepository teacherRepository= new TeacherJDBCRepository();

        RegistrationService registrationSystem = new RegistrationService(courseRepository,studentRepository,teacherRepository);

        //runTeacherView();
        //runStudentView();


        while(true) {

            System.out.println("Choose operation type:");
            System.out.println("0 to Exit");
            System.out.println("1 for Courses:");
            System.out.println("2 for Teachers:");
            System.out.println("3 for Students:");
            System.out.println("Enter choice:");
            Scanner scan = new Scanner(System.in);
            int layer1 = scan.nextInt();

            if (layer1 == 0) {
                break;
            } else if (layer1 == 1) {
                System.out.println("Pick operation on Courses:");
                System.out.println("1 for adding a Course:");
                System.out.println("2 for finding Course by name:");
                System.out.println("3 for finding Course by credits:");
                System.out.println("4 for sorting Courses by credits:");
                System.out.println("5 for sorting Courses by maxEnrollment:");
                System.out.println("6 for retrieving Courses with free places:");
                System.out.println("7 for printing Courses list:");
                System.out.println("8 for deleting a Course:");
                System.out.println("Enter choice:");
                Scanner scan2 = new Scanner(System.in);
                int layer2 = scan2.nextInt();

                if (layer2 == 1) {

                    Scanner scan3 = new Scanner(System.in);

                    System.out.println("Enter course id:");
                    int id=Integer.parseInt(scan3.nextLine());

                    System.out.println("Enter course name:");
                    String name=scan3.nextLine();

                    System.out.println("Enter course max seats:");
                    int seats=Integer.parseInt(scan3.nextLine());

                    System.out.println("Enter course credits:");
                    int credits=Integer.parseInt(scan3.nextLine());

                    registrationSystem.addCourse(id,name,new Teacher(),seats,credits);

                    System.out.println(registrationSystem.getAllCourses());
                } else if (layer2 == 2) {
                    System.out.println("Enter the name you want to find course by:");
                    Scanner scan3 = new Scanner(System.in);
                    String name = scan3.nextLine();
                    System.out.println(name);
                    System.out.println(registrationSystem.courseRepository.findByName(name));
                } else if (layer2 == 3) {
                    System.out.println("Enter the number of credits you want to find course by:");
                    Scanner scan3 = new Scanner(System.in);
                    int noCredits = scan3.nextInt();
                    System.out.println(registrationSystem.courseRepository.findByCredits(noCredits));
                } else if (layer2 == 4) {
                    System.out.println(registrationSystem.courseRepository.sortByCredits());
                } else if (layer2==5){
                    System.out.println(registrationSystem.courseRepository.sortByMaxEnrollment());
                } else if (layer2==6){
                    System.out.println(registrationSystem.retrieveCoursesWithFreePlaces());
                }
                else if (layer2==7){
                    System.out.println(registrationSystem.getAllCourses());
                }
                else if (layer2==8){
                    Scanner scan3 = new Scanner(System.in);

                    System.out.println("Enter existing Course id you want to delete:");
                    int id=Integer.parseInt(scan3.nextLine());

                    System.out.println("Enter existing Course name:");
                    String name=scan3.nextLine();

                    Course course= registrationSystem.courseRepository.findByName(name).get(0);
                    //registrationSystem.addTeacher(id,firstname,lastname);
                    registrationSystem.deleteCourse(course);

                    System.out.println(registrationSystem.getAllCourses());
                }

            }


            else if (layer1 == 2) {
                System.out.println("Pick operation on Teachers:");
                System.out.println("1 for adding a Teacher:");
                System.out.println("2 for claiming course by Teacher:");
                System.out.println("3 for deleting Course by Teacher:");
                System.out.println("4 for finding Teacher by firstname:");
                System.out.println("5 for finding Teacher by lastname:");
                System.out.println("6 for finding Teacher by fullname:");
                System.out.println("7 for printing Teacher list:");
                System.out.println("8 for deleting a Teacher:");
                System.out.println("Enter choice:");
                Scanner scan2 = new Scanner(System.in);
                int layer2 = scan2.nextInt();

                if (layer2==1){
                    Scanner scan3 = new Scanner(System.in);

                    System.out.println("Enter teacher id:");
                    int id=Integer.parseInt(scan3.nextLine());

                    System.out.println("Enter teacher firstname:");
                    String firstname=scan3.nextLine();

                    System.out.println("Enter teacher lastname:");
                    String lastname=scan3.nextLine();

                    registrationSystem.addTeacher(id,firstname,lastname);

                    System.out.println(registrationSystem.getAllTeachers());

                }
                else if(layer2==2){
                    Scanner scan3 = new Scanner(System.in);

                    System.out.println("Enter the first name of the teacher that claims the course:");
                    String firstname=scan3.nextLine();

                    System.out.println("Enter the last name of the teacher that claims the course:");
                    String lastname=scan3.nextLine();

                    System.out.println("Enter the name of the course that is assigned to the previously given teacher:");
                    String courseName=scan3.nextLine();

                    Teacher teacher = registrationSystem.teacherRepository.findByFullName(firstname, lastname).get(0);
                    Course course=registrationSystem.courseRepository.findByName(courseName).get(0);

                    registrationSystem.teacherClaimCourse(course,teacher);
                }
                else if(layer2==3){
                    Scanner scan3 = new Scanner(System.in);

                    System.out.println("Enter the first name of the teacher that wants to delete a course from his course list:");
                    String firstname=scan3.nextLine();

                    System.out.println("Enter the last name of the teacher that wants to delete a course from his course list:");
                    String lastname=scan3.nextLine();

                    System.out.println("Enter the name of the course that is to be deleted:");
                    String courseName=scan3.nextLine();

                    Teacher teacher = registrationSystem.teacherRepository.findByFullName(firstname, lastname).get(0);
                    Course course=registrationSystem.courseRepository.findByName(courseName).get(0);

                    registrationSystem.deleteCourseByTeacher(teacher,course);
                }
                else if(layer2==4){
                    Scanner scan3 = new Scanner(System.in);

                    System.out.println("Enter the teacher firstname you want to find teacher by:");
                    String firstname = scan3.nextLine();
                    System.out.println(firstname);
                    System.out.println(registrationSystem.teacherRepository.findByFirstName(firstname));
                }
                else if(layer2==5){
                    Scanner scan3 = new Scanner(System.in);

                    System.out.println("Enter the teacher lastname you want to find teacher by:");
                    String lastname = scan3.nextLine();
                    System.out.println(lastname);
                    System.out.println(registrationSystem.teacherRepository.findByLastName(lastname));
                }
                else if(layer2==6){
                    Scanner scan3 = new Scanner(System.in);

                    System.out.println("You will be going to enter the teacher full name that you want to find teacher by. First, enter his first name:");
                    String firstname = scan3.nextLine();
                    System.out.println(firstname);

                    System.out.println("Now enter teacher lastname:");
                    String lastname = scan3.nextLine();
                    System.out.println(lastname);

                    System.out.println(registrationSystem.teacherRepository.findByFullName(firstname,lastname));
                }
                else if (layer2==7){
                    System.out.println(registrationSystem.getAllTeachers());
                }
                else if (layer2==8){
                    Scanner scan3 = new Scanner(System.in);

                    System.out.println("Enter existing teacher id you want to delete:");
                    int id=Integer.parseInt(scan3.nextLine());

                    System.out.println("Enter existing teacher firstname:");
                    String firstname=scan3.nextLine();

                    System.out.println("Enter existing teacher lastname:");
                    String lastname=scan3.nextLine();

                    Teacher teacher= registrationSystem.teacherRepository.findByFullName(firstname, lastname).get(0);
                    //registrationSystem.addTeacher(id,firstname,lastname);
                    registrationSystem.deleteTeacher(teacher);

                    System.out.println(registrationSystem.getAllTeachers());
                }

            }
            else if(layer1==3){
                System.out.println("Pick operation on Students:");
                System.out.println("1 for adding a Student:");
                System.out.println("2 for registering Student to a Course:");
                System.out.println("3 for retrieving students enrolled for a course:");
                System.out.println("4 for finding Student by firstname:");
                System.out.println("5 for finding Student by lastname:");
                System.out.println("6 for finding Student by fullname:");
                System.out.println("7 for sorting students by total credits:");
                System.out.println("8 for printing Student list:");
                System.out.println("9 for deleting a Student:");
                Scanner scan2 = new Scanner(System.in);
                int layer2 = scan2.nextInt();

                if (layer2==1){
                    Scanner scan3 = new Scanner(System.in);

                    System.out.println("Enter student id:");
                    int id=Integer.parseInt(scan3.nextLine());

                    System.out.println("Enter student firstname:");
                    String firstname=scan3.nextLine();

                    System.out.println("Enter student lastname:");
                    String lastname=scan3.nextLine();

                    registrationSystem.addStudent(id,firstname,lastname);

                    System.out.println(registrationSystem.getAllStudents());

                }
                else if(layer2==2){
                    Scanner scan3 = new Scanner(System.in);

                    System.out.println("Enter the name of the course you want to register a student to:");
                    String courseName=scan3.nextLine();

                    System.out.println("Enter the first name of the student you want to register to the course:");
                    String firstname=scan3.nextLine();

                    System.out.println("Enter the last name of the student you want to register to the course:");
                    String lastname=scan3.nextLine();

                    Student student = registrationSystem.studentRepository.findByFullName(firstname,lastname).get(0);
                    Course course=registrationSystem.courseRepository.findByName(courseName).get(0);

                    registrationSystem.register(course,student);

                    System.out.println(student.getEnrolledCourses());
                }
                else if(layer2==3){
                    Scanner scan3 = new Scanner(System.in);

                    System.out.println("Enter the course name of which participants you want to see:");
                    String courseName=scan3.nextLine();

                    Course course=registrationSystem.courseRepository.findByName(courseName).get(0);

                    System.out.println(registrationSystem.retrieveStudentsEnrolledForACourse(course));

                }
                else if(layer2==4){
                    Scanner scan3 = new Scanner(System.in);

                    System.out.println("Enter the student firstname you want to find student by:");
                    String firstname = scan3.nextLine();
                    System.out.println(firstname);
                    System.out.println(registrationSystem.studentRepository.findByFirstName(firstname));
                }
                else if(layer2==5){
                    Scanner scan3 = new Scanner(System.in);

                    System.out.println("Enter the student lastname you want to find student by:");
                    String lastname = scan3.nextLine();
                    System.out.println(lastname);
                    System.out.println(registrationSystem.studentRepository.findByLastName(lastname));
                }
                else if(layer2==6){
                    Scanner scan3 = new Scanner(System.in);

                    System.out.println("You will be going to enter the student full name that you want to find student by. First, enter his first name:");
                    String firstname = scan3.nextLine();
                    System.out.println(firstname);

                    System.out.println("Now enter student lastname:");
                    String lastname = scan3.nextLine();
                    System.out.println(lastname);

                    System.out.println(registrationSystem.studentRepository.findByFullName(firstname,lastname));
                }
                else if(layer2==7){
                    System.out.println(registrationSystem.studentRepository.sortByTotalCredits());
                }
                else if(layer2==8){
                    System.out.println(registrationSystem.getAllStudents());
                }
                else if (layer2==9){
                    Scanner scan3 = new Scanner(System.in);

                    System.out.println("Enter existing student id you want to delete:");
                    int id=Integer.parseInt(scan3.nextLine());

                    System.out.println("Enter existing student firstname:");
                    String firstname=scan3.nextLine();

                    System.out.println("Enter existing student lastname:");
                    String lastname=scan3.nextLine();

                    Student student= registrationSystem.studentRepository.findByFullName(firstname, lastname).get(0);
                    //registrationSystem.addTeacher(id,firstname,lastname);
                    registrationSystem.deleteStudent(student);

                    System.out.println(registrationSystem.getAllStudents());
                }
            }
        }
    }
}
