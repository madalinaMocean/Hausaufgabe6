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
import main.Model.Course;
import main.Model.Person;
import main.Model.Student;
import main.Model.Teacher;
import main.Repository.CourseJDBCRepository;
import main.Repository.StudentJDBCRepository;
import main.Repository.TeacherJDBCRepository;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class TeacherView extends Application {

    final String DB_URL = "jdbc:mysql://localhost/university";
    final String USER = "root";
    final String PASS = "Admitere2020@";
    TeacherJDBCRepository teacherJDBCRepository = new TeacherJDBCRepository();
    CourseJDBCRepository courseJDBCRepository = new CourseJDBCRepository();
    StudentJDBCRepository studentJDBCRepository = new StudentJDBCRepository();

    RegistrationService registrationService = new RegistrationService(courseJDBCRepository,studentJDBCRepository,teacherJDBCRepository);

    Teacher userTeacher;
    ObservableList<Pair<Integer,Integer>> enrollments;

    public static void main(String[] args){

        launch();
    }

    public static void runTeacherView(){
        launch();
    }


    @Override
    public void start(Stage primaryStage)  {


        GridPane layoutLogin = new GridPane();
        Scene sceneLogin = new Scene(layoutLogin,250,80);
        primaryStage.setScene(sceneLogin);
        AtomicReference<ArrayList<Pair<Integer, Integer>>> enrolledStudents= new AtomicReference<>(new ArrayList<>());
        layoutLogin.setHgap(10);
        layoutLogin.setVgap(10);
        primaryStage.setTitle("Teacher Manager");
        Label loginLabel = new Label("Teacher Id:");
        TextField loginIdField = new TextField();
        Button buttonLogin = new Button();
        buttonLogin.setText("Login");
        layoutLogin.add(loginLabel,1,1);
        layoutLogin.add(loginIdField,2,1);
        layoutLogin.add(buttonLogin,1,2);
        GridPane layoutTeacher = new GridPane();
        layoutTeacher.setHgap(10);
        layoutTeacher.setVgap(10);
        Scene sceneTeacher = new Scene(layoutTeacher,700,700);
        Button buttonRefresh = new Button();
        buttonRefresh.setText("Refresh");
        layoutTeacher.add(buttonRefresh,2,2);
        TextField courseIdField = new TextField();
        layoutTeacher.add(courseIdField,1,2);

        ListView<Student> listViewStudents = new ListView();
        listViewStudents.setPrefWidth(600);
        listViewStudents.setPrefHeight(600);
        layoutTeacher.add(listViewStudents,1,3);



        buttonLogin.setOnAction(e-> {
            try {
                userTeacher = registrationService.teacherRepository.findById(parseInt(loginIdField.getText()));
            } catch (Exception ex) {
                userTeacher = null;
            }
            if(userTeacher!=null){
                Label userInfo = new Label("Teacher Id: "+userTeacher.getId()+" First Name: "+userTeacher.getFirstName()+" Last Name: " + userTeacher.getLastName());
                layoutTeacher.add(userInfo,1,1);
                primaryStage.setScene(sceneTeacher);
                buttonRefresh.setOnAction(e2->{
                    listViewStudents.getItems().clear();
                    if(!courseIdField.getText().equals(""))
                        if(enrolledStudents(parseInt(courseIdField.getText()),userTeacher.getId())!=null)
                            for(int s: enrolledStudents(parseInt(courseIdField.getText()),userTeacher.getId())) {
                                listViewStudents.getItems().add(registrationService.studentRepository.findById(s));
                            }

                });
            }
        });
        primaryStage.show();

    }


    public List<Integer> enrolledStudents(int courseId,int teacherId){

        Course c = null;
        c = registrationService.courseRepository.findById(courseId);
        if(c==null)
            return null;
        if(c.getCourseTeacher().getId()!=teacherId)
            return null;
        return c.getStudentsEnrolled().stream()
                .map(Person::getId)  // 2
                .collect(Collectors.toList()); // 3;

    }

}