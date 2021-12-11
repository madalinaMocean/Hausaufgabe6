package main.Repository;

import main.Model.Teacher;

import java.sql.*;


public class TeacherJDBCRepository extends TeacherInMemoryRepository{
    static final String DB_URL = "jdbc:mysql://localhost/university";
    static final String USER = "root";
    static final String PASS = "Admitere2020@";
    static final String QUERY = "SELECT ID, firstName, lastName FROM teacher";

    public void main(String[] args) {
        // Open a connection
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);) {
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                System.out.print("Id: " + rs.getInt("Id"));
                System.out.print(", firstName: " + rs.getString("firstName"));
                System.out.print(", lastName: " + rs.getString("lastName"));
                System.out.println();
                Teacher t1= new Teacher(rs.getInt("Id"),rs.getString("firstName"),rs.getString("lastName"));
                super.add(t1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    private void readFromFile(){

    }
    private void writeToFile(){

    }
    @Override
    public Teacher add(Teacher el) {
        el.setId(getNextId());
        super.add(el);
        writeToFile();
        return el;
    }

    @Override
    public void delete(Teacher el) {
        super.delete(el);
        writeToFile();
    }

    @Override
    public void update(Teacher el, Integer integer) {
        super.update(el, integer);
        writeToFile();
    }


}
