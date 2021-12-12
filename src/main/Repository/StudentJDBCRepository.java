package main.Repository;

import main.Model.Student;
import main.Model.Student;

import java.sql.*;

public class StudentJDBCRepository extends StudentInMemoryRepository {
    static final String DB_URL = "jdbc:mysql://localhost/university";
    static final String USER = "root";
    static final String PASS = "Admitere2020@";
    static final String QUERY = "SELECT Id, firstName, lastName FROM student";

    public StudentJDBCRepository() {
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
                Student student = new Student(rs.getInt("Id"), rs.getString("firstName"), rs.getString("lastName"));
                super.add(student); //adding to repo
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(){
        try {
            Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt=conn.createStatement();


            for (main.Model.Student Student : findAll()) { // write each student to file
                String Id = Student.getId().toString();
                String firstName = Student.getFirstName();
                String lastName = Student.getLastName();

                String sqlQuery2="INSERT INTO student(Id,firstName,lastName) VALUES ('"+Id+"','"+firstName+"','"+lastName+"')";
                stmt.executeUpdate(sqlQuery2);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Student add(Student el) {
        super.add(el);
        writeToFile();
        return el;
    }

    @Override
    public void delete(Student el) {
        super.delete(el);
        writeToFile();
    }

    @Override
    public void update(Student el, Integer integer) {
        super.update(el, integer);
        writeToFile();
    }
}
