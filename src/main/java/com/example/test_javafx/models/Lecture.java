package com.example.test_javafx.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Lecture {
    private String topic;
    private String classRoom;
    private Date dateTime = new Date();
    private ArrayList<String> studentsAttendance;
    private String userName;
    private String coursName;
 String date ;

    public Lecture(String topic, String classRoom,String userName,String coursName) {
        this.topic = topic;
        this.classRoom = classRoom;
        this.studentsAttendance = new ArrayList<>();
        this.coursName = coursName;
        this.userName=userName;
        date = dateTime.toString();
    }

    public Lecture() {

    }

    @Override
    public String toString() {
//        topic,classRoom,userName,couseName

        return this.topic+","+ this.classRoom+","+this.userName+","+this.coursName;
          }

    public String getUserName() {
        return userName;
    }

    public String getCoursName() {
        return coursName;
    }

    public String getTopic() {
        return topic;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public ArrayList<String> getStudentsAttendance() {
        return studentsAttendance;
    }

    public void addStudentAttendance(String student) {
        studentsAttendance.add(student);
    }

    public void removeStudentAttendance(Student student) {
        studentsAttendance.remove(student);
    }


}
