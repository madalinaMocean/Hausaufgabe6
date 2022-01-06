package main.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Model.Student;
import main.Repository.CourseJDBCRepository;
import main.Repository.StudentJDBCRepository;
import main.Repository.TeacherJDBCRepository;

import java.io.IOException;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class StudentView extends Application {

    Student userStudent;

    public static void main(String[] args){
        launch();
    }

    public static void runStudentView(){
        launch();
    }


    @Override
    public void start(Stage primaryStage) {
        final String DB_URL = "jdbc:mysql://localhost/university";
        final String USER = "root";
        final String PASS = "Admitere2020@";
        TeacherJDBCRepository teacherJDBCRepository = new TeacherJDBCRepository();
        CourseJDBCRepository courseJDBCRepository = new CourseJDBCRepository();
        StudentJDBCRepository studentJDBCRepository = new StudentJDBCRepository();

        RegistrationService registrationService = new RegistrationService(courseJDBCRepository,studentJDBCRepository,teacherJDBCRepository);


        GridPane layoutLogin = new GridPane();
        Scene sceneLogin = new Scene(layoutLogin,450,150);
        primaryStage.setScene(sceneLogin);
        layoutLogin.setHgap(10);
        layoutLogin.setVgap(10);
        primaryStage.setTitle("Student Manager");
        Label loginLabel = new Label("Student Id:");
        TextField loginIdField = new TextField();
        Button buttonLogin = new Button();
        buttonLogin.setText("Login");
        layoutLogin.add(loginLabel,1,1);
        layoutLogin.add(loginIdField,2,1);
        layoutLogin.add(buttonLogin,1,2);
        GridPane layoutStudent = new GridPane();
        layoutStudent.setHgap(10);
        layoutStudent.setVgap(10);
        Button buttonRegister = new Button();
        buttonRegister.setText("Register");
        TextField fieldRegister = new TextField();
        layoutStudent.add(fieldRegister,1,2);
        layoutStudent.add(buttonRegister,2,2);



        Scene sceneStudent = new Scene(layoutStudent,450,150);
        buttonLogin.setOnAction(e-> {
            try {
                userStudent = registrationService.studentRepository.findById(parseInt(loginIdField.getText()));
            } catch (Exception ex) {
                userStudent = null;
            }
            if(userStudent!=null){
                Label userInfo = new Label("Student Id: "+userStudent.getId()+"\n First Name: "+userStudent.getFirstName()+"\n Last Name: " + userStudent.getLastName());
                Label userCredits = new Label("\nStudent Credits: "+ userStudent.totalCredits());
                layoutStudent.add(userInfo,1,1);
                layoutStudent.add(userCredits,2,1);
                buttonRegister.setOnAction(e2-> {
                    registrationService.register(registrationService.courseRepository.findById(parseInt(fieldRegister.getText())), userStudent);
                    userStudent=registrationService.studentRepository.findById(userStudent.getId());
                    userCredits.setText("\nStudent Credits: "+ userStudent.totalCredits());});
                primaryStage.setScene(sceneStudent);}
        });
        primaryStage.show();
    }
}