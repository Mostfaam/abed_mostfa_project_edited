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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class attendsController implements Initializable {
    @FXML
    public TableView<Student> table;
    @FXML
     private   ListView<String> listCourseInAttend;

    @FXML
    private AnchorPane rootPane;
    public TableColumn name;
    @FXML
    public TextField id ;
    @FXML
  ListView<String> lsitAutoComplete;

    public TableColumn gpa;

    public TableColumn phoneNumber;
    public TableColumn universityId;

   String namecourseSelected = "pro";

    Navigation navigation = new Navigation();
    DataModel model = new DataModel();

    public void autoComplete(KeyEvent event) {
        lsitAutoComplete.setVisible(true);
       ArrayList<String> options = new ArrayList<String>();
        String text = id.getText();
        if(text.isEmpty()) {
           lsitAutoComplete.setVisible(false);
        }
        else {
        for (Student word : model.getCourseByname(namecourseSelected).getStudents()) {

            if (word.getName().startsWith(text)) {
             options.add(word.getName());



            }
        }



          lsitAutoComplete.setItems(FXCollections.observableArrayList(options));

    }}
public void inti(){
    listCourseInAttend.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
            namecourseSelected=listCourseInAttend.getSelectionModel().getSelectedItem();
        }
    });
}
    public void initialize(URL url, ResourceBundle rb) {


        //make sure the property value factory should be exactly same as the getStudentId from your model class
ArrayList<String> courseforTeatcher = new ArrayList<String>();
        for(Course s : DataModel.getTeatcherByUsername(model.getWhoTeatcher()).getCourse()){
                courseforTeatcher.add(s.getCourseName());

        }
       name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        universityId.setCellValueFactory(new PropertyValueFactory<>("universityId"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        table.setItems(FXCollections.observableArrayList(model.getCourseByname(namecourseSelected).getStudents()));
        listCourseInAttend.getItems().addAll(courseforTeatcher);

    }
    public void back(ActionEvent actionEvent) {
        navigation.navigateTo(rootPane,navigation.TEACHER_FXML);
    }

    public void atend(ActionEvent actionEvent) {
    }
}