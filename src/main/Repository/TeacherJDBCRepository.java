package main.Repository;

import main.Model.Teacher;

import java.sql.*;


public class TeacherJDBCRepository extends TeacherInMemoryRepository{
    static final String DB_URL = "jdbc:mysql://localhost/university";
    static final String USER = "root";
    static final String PASS = "Admitere2020@";
    static final String QUERY = "SELECT Id, firstName, lastName FROM teacher";

    public TeacherJDBCRepository() {
        readFromFile();
    }

    public void readFromFile() {
        // Open a connection
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);) {
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                Teacher teacher= new Teacher(rs.getInt("Id"),rs.getString("firstName"),rs.getString("lastName"));
                super.add(teacher); //adding to repo
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void writeToFile(){
        try {
            Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt=conn.createStatement();

            for (Teacher Teacher : findAll()) { // write each teacher to file
                String Id = Teacher.getId().toString();
                String firstName = Teacher.getFirstName();
                String lastName = Teacher.getLastName();

                String sqlQuery2="INSERT INTO teacher(Id,firstName,lastName) VALUES ('"+Id+"','"+firstName+"','"+lastName+"')";
                stmt.executeUpdate(sqlQuery2);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Teacher add(Teacher el) {
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
