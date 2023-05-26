package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.Course;
import com.example.test_javafx.models.DataModel;
import com.example.test_javafx.models.Student;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    DataModel dataModel = new DataModel();
    Navigation navigation = new Navigation();
    @FXML
    private AnchorPane rootPane1;
    @FXML
    private TextField name;
    @FXML
    private TextField gpa;
    @FXML
    private TextField id;
    @FXML
    private TextField phone;
    @FXML
    private TextField address;
    @FXML
    ComboBox<String> comboBoxForCourse;
    @FXML
    TableView <Student> table;
    @FXML
    TableColumn nameTa;
    @FXML
    TableColumn universityId;
    @FXML
    TableColumn gpaTa;
    @FXML
    TableColumn phoneNumber;

    public void onAdd() {
        Student student = new Student(name.getText(), id.getText(), phone.getText(), address.getText(), Float.parseFloat(gpa.getText()));
        dataModel.addStudent(student);
        dataModel.getCourseByname(getCourseNameSelected()).addStudent(student);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("The student has been added successfully");
        alert.setTitle("Done");
        alert.setContentText("Name: " + name.getText() + ", GPA: " + gpa.getText());
        alert.showAndWait();
        dataModel.save_Students();
    }



    public String getCourseNameSelected(){
        return  comboBoxForCourse.getValue();
    }
    public void onBack() {
        navigation.navigateTo(rootPane1, navigation.TEACHER_FXML);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //set TextFiled to accept Numbers Only
        gpa.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.matches("\\d*")) {
                gpa.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        ArrayList<String> courseforTeatcher = new ArrayList<String>();
        for (Course s : DataModel.getTeatcherByUsername(dataModel.getWhoTeatcher()).getCourse()) {
            courseforTeatcher.add(s.getCourseName());

        }
        comboBoxForCourse.getItems().addAll(courseforTeatcher);
        comboBoxForCourse.setOnAction(event -> {
            // الحصول على الـ ArrayList المحدد
            String selectedString = comboBoxForCourse.getValue();

            nameTa.setCellValueFactory(new PropertyValueFactory<>("name"));
            universityId.setCellValueFactory(new PropertyValueFactory<>("universityId"));
            gpaTa.setCellValueFactory(new PropertyValueFactory<>("gpa"));
            phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));


            table.setItems(FXCollections.observableArrayList(dataModel.getCourseByname(getCourseNameSelected()).getStudents()));


        });


    }

    public void onDeleat(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if(table.getItems().isEmpty()){

            alert.setHeaderText("you are not selected course");
            alert.setTitle("eroor");
            alert.setContentText("select the course");
            alert.showAndWait();
        }
        else if (table.getSelectionModel().isEmpty()){

            alert.setHeaderText("you are not selected course");
            alert.setTitle("eroor");
            alert.setContentText("select the course");
            alert.showAndWait();
        }
        else{
            int rowIndix = table.getSelectionModel().getSelectedIndex();
            if(rowIndix==0){}else{
                table.getItems().remove(rowIndix);
                dataModel.deleteStudentByName(table.getItems().get(rowIndix).getName());}
        }}
    public void onShowStudents() {
        navigation.navigateTo(rootPane1, navigation.SHOW_STUDENTS_FXML);
    }
}