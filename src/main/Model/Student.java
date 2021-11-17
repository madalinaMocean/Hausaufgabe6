package main.Model;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person{
    private List<Course> enrolledCourses;

    public Student(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
        this.enrolledCourses = new ArrayList<>();
    }

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
        this.enrolledCourses = new ArrayList<>();
    }

    public Student() {
        super();
        this.enrolledCourses = new ArrayList<>();
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    @Override
    public String toString(){ // to add enrolledCourses
        String str=this.Id+" "+firstName+" "+lastName;
        return str;
    }


    //extras
    public boolean addCourse(Course course){
        if(enrolledCourses.contains(course) || totalCredits()>=30){
            return false;
        }

        enrolledCourses.add(course);
        return true;
    }

    public void removeCourse(Course course){
        enrolledCourses.remove(course);
    }

    public int totalCredits() {
        int totalNoCredits = 0;
        for (Course course : enrolledCourses) {
            totalNoCredits+=course.getCredits();
        }
        return totalNoCredits;
    }
}
