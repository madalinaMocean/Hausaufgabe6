package main;

import main.Repository.StudentFileRepository;
import main.Model.Course;
import main.Model.Student;
import main.Model.Teacher;

public class Main {
    public static void main(String[] args) {
        Student s = new Student(0,"dan","podina");
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
    }
}
