package main.Repository;

import main.Model.Course;
import main.Model.Teacher;

import java.sql.*;

public class CourseJDBCRepository extends CourseInMemoryRepository{

    static final String DB_URL = "jdbc:mysql://localhost/university";
    static final String USER = "root";
    static final String PASS = "Admitere2020@";
    static final String QUERY = "SELECT Id, courseName, maxEnrollment, credits FROM course";

    public CourseJDBCRepository() {
        readFromFile();
    }

    public void readFromFile() {
        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY);) {
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                Course course = new Course(rs.getInt("Id"),rs.getString("courseName"),new Teacher(),rs.getInt("maxEnrollment"),rs.getInt("credits"));
                super.add(course); //adding to repo
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(){
        try {
            Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt=conn.createStatement();

            String sqlQuery1="DELETE FROM course";
            stmt.executeUpdate(sqlQuery1);

            for (main.Model.Course Course : findAll()) { // write each course to file
                String Id = Course.getId().toString();
                String courseName = Course.getCourseName();
                int maxEnrollment = Course.getMaxEnrollment();
                int credits = Course.getCredits();

                String sqlQuery2="INSERT INTO course(Id,courseName,maxEnrollment,credits) VALUES ('"+Id+"','"+courseName+"','"+maxEnrollment+"','"+credits+"')";
                //pw.println(Course.getId()+";"+Course.getFirstName()+";"+Course.getLastName()+";"+Course.getCourses())
                stmt.executeUpdate(sqlQuery2);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Course add(Course el) {
        super.add(el);
        writeToFile();
        return el;
    }

    @Override
    public void delete(Course el) {
        super.delete(el);
        writeToFile();
    }

    @Override
    public void update(Course el, Integer integer) {
        super.update(el, integer);
        writeToFile();
    }
    
    
}
