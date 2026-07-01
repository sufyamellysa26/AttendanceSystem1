/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private int id;                 // ✅ PK from student table
    private String userId;          // ✅ FK → users.userId
    private String name;
    private String matric;          // student matric number
    private String courseProgram;
    private int semester;
    private List<String> subjects; // ✅ always List<String>
    private String phone;
    private String address;
    private String password;
    private String profilePicture;  // ✅ optional profile picture

    // ✅ Default constructor initializes subjects list
    public Student() {
        this.subjects = new ArrayList<>();
    }

    // ✅ Full constructor
    public Student(int id, String name, String matric, String courseProgram,
            int semester, String password, String phone, String address,
            List<String> subjects) {
        this.id = id;
        this.name = name;
        this.matric = matric;
        this.courseProgram = courseProgram;
        this.semester = semester;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.subjects = (List<String>) ((subjects != null) ? subjects : new ArrayList<>());
    }

    // --- Getters and Setters ---
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatric() {
        return matric;
    }

    public void setMatric(String matric) {
        this.matric = matric;
    }

    public String getCourseProgram() {
        return courseProgram;
    }

    public void setCourseProgram(String courseProgram) {
        this.courseProgram = courseProgram;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = (List<String>) ((subjects != null) ? subjects : new ArrayList<>());
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
