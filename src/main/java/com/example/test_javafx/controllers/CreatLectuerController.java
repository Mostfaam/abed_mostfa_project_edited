package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.Course;
import com.example.test_javafx.models.DataModel;
import com.example.test_javafx.models.Lecture;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.lang.reflect.Array;
import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreatLectuerController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    TextField topic;
    @FXML
    TextField classRoom;
    @FXML
    TextField userName ;
    @FXML
    TextField coursName;
    @FXML
    private ListView<String> list;

    @FXML
    Navigation navigation = new Navigation();
    DataModel dataModel =new DataModel();
    String courseNameSelected;
    public void back(ActionEvent actionEvent) {
        navigation.navigateTo(anchorPane,navigation.TEACHER_FXML);

    }


    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> courseforTeatcher = new ArrayList<String>();
        for(Course s : DataModel.getTeatcherByUsername(dataModel.getWhoTeatcher()).getCourse()){
            courseforTeatcher.add(s.getCourseName());

        }
    list.getItems().addAll(courseforTeatcher);
    list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
            courseNameSelected=list.getSelectionModel().getSelectedItem();
        }
    });
    }

                public void creatLectuer(ActionEvent actionEvent) {
        //(String topic, String classRoom, LocalDateTime dateTime,String userName,String coursName)
        Lecture lecture = new Lecture(topic.getText(),classRoom.getText(),userName.getText(),courseNameSelected);
           dataModel.getLecture().add(lecture);
           dataModel.getCourseByname(courseNameSelected).addLecture(lecture);
           dataModel.saveLectures();
    }
}