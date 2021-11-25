package main;

import main.Repository.CourseInMemoryRepository;
import main.Repository.StudentFileRepository;
import main.Model.Course;
import main.Model.Student;
import main.Model.Teacher;
import main.Repository.StudentInMemoryRepository;
import main.Repository.TeacherInMemoryRepository;
import main.UI.ExceptionService;
import main.UI.RegistrationService;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ExceptionService {
        /*Student s = new Student(0,"dan","podina");
        Student s2 = new Student(1,"alex","dan");
        Student s3 = new Student("dennis","mada");
        Course c = new Course(1, "c1", new Teacher(), 1, 1);
        s.addCourse(c);
        StudentFileRepository sfr =  new StudentFileRepository("test.txt");
//        sfr.add(s);
//        sfr.add(s2);
        sfr.update(s3, 3);
        System.out.println(sfr.getAll());
//        StudentInMemoryRepository studentInMemoryRepository = new StudentInMemoryRepository();
//        studentInMemoryRepository.add(s);
//        studentInMemoryRepository.add(s2);
//        System.out.println(studentInMemoryRepository.getAll());
//        studentInMemoryRepository.update(s3, 1);
//        System.out.println(studentInMemoryRepository.getAll());

         */
        CourseInMemoryRepository courseRepository = new CourseInMemoryRepository();
        StudentInMemoryRepository studentRepository= new StudentInMemoryRepository();
        TeacherInMemoryRepository teacherRepository= new TeacherInMemoryRepository();

        RegistrationService registrationSystem = new RegistrationService(courseRepository,studentRepository,teacherRepository);

        registrationSystem.addCourse(1, "c1", new Teacher(), 1, 1);
        registrationSystem.addCourse(2, "c2", new Teacher(), 4, 3);
        registrationSystem.addCourse(3, "c3", new Teacher(), 2, 5);
        registrationSystem.addCourse(4, "c4", new Teacher(), 3, 6);


        Student s = new Student(0,"dan","podina");

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

            }
            //else{

            //}


        }

    }
}
