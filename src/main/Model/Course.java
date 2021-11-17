package main.Model;

import java.util.ArrayList;
import java.util.List;

public class Course implements Identifiable<Integer>{
    private int Id;
    private String courseName;
    private Person courseTeacher;
    private int maxEnrollment;
    private List<Student> studentsEnrolled;
    private int credits;

    public Course(int id, String courseName, Person courseTeacher, int maxEnrollment, int credits) {
        this.Id = id;
        this.courseName = courseName;
        this.courseTeacher = courseTeacher;
        this.maxEnrollment = maxEnrollment;
        this.studentsEnrolled = new ArrayList<>();
        this.credits = credits;
    }

    public Course(String courseName, Person courseTeacher, int maxEnrollment, int credits) {
        this.courseName = courseName;
        this.courseTeacher = courseTeacher;
        this.maxEnrollment = maxEnrollment;
        this.studentsEnrolled = new ArrayList<>();
        this.credits = credits;
    }

//    public Course() {
//        this.Id = 0;
//        this.courseName = "";
//        this.courseTeacher = null;
//        this.maxEnrollment = 0;
//        this.studentsEnrolled = new ArrayList<>();
//        this.credits = 0;
//    }
    @Override
    public Integer getId() {
    return Id;
}
    @Override
    public void setId(Integer id) {
        Id = id;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Person getCourseTeacher() {
        return courseTeacher;
    }
    public void setCourseTeacher(Person courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }
    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public List<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }
    public void setStudentsEnrolled(List<Student> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }

    public int getCredits() {
        return credits;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString(){ // to add courseTeacher and studentsEnrolled
        String str=Id+" "+courseName+" "+maxEnrollment+" "+credits;
        return str;
    }

    //extras
    public int noAvailableSeats(){
        return maxEnrollment-studentsEnrolled.size();
    }

    public boolean hasAvailableSeats(){
        return maxEnrollment - studentsEnrolled.size() > 0;
    }

    public boolean addStudent(Student student){
        if(studentsEnrolled.contains(student)){
            return false;
        }
        studentsEnrolled.add(student);
        return true;
    }

    public void clearList(){
        studentsEnrolled.clear();
    }

}
