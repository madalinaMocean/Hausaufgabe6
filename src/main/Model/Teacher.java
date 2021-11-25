package main.Model;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person{
    private List<Course> courses;

    public Teacher(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
        this.courses = new ArrayList<>();
    }

    public Teacher(String firstName, String lastName) {
        super(firstName, lastName);
        this.courses = new ArrayList<>();
    }

    public Teacher() {
        super();
        this.courses = new ArrayList<>();
    }

    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString(){ // to add List<Course> courses
        String str=this.Id+" "+firstName+" "+lastName;
        return str;
    }

    //extras
    public void removeCourse(Course course){
        courses.remove(course);
    }

    public void addCourse(Course course){
        courses.add(course);
    }
}
