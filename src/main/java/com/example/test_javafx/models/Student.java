package com.example.test_javafx.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {
    private String name;
    private String universityId;
    private String phoneNumber;
    private String residenceArea;
    private float gpa;
    private ArrayList<Course> courses =new ArrayList<Course>();

    public Student(String name, String universityId, String phoneNumber, String residenceArea, float gpa) {
        this.name = name;
        this.universityId = universityId;
        this.phoneNumber = phoneNumber;
        this.residenceArea = residenceArea;
        this.gpa = gpa;
    }

    public Student() {

    }

    public void addCourse(Course course){
        courses.add(course);
    }
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public String getUniversityId() {
        return universityId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getResidenceArea() {
        return residenceArea;
    }

    @Override
    public String toString() {
        return  name + ","+ universityId + ","  +phoneNumber + "," + residenceArea + ","+ gpa ;

    }
}