/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class Lecturer {

    private int lecturerId;
    private String name;
    private String password;
    private String department;
    private String subjectCode;
    private String subjectName;
    private String profilePicture; // optional if you store image path/URL

    // Constructor
    public Lecturer(int lecturerId, String name, String password,
            String department, String subjectCode, String subjectName,
            String profilePicture) {
        this.lecturerId = lecturerId;
        this.name = name;
        this.password = password;
        this.department = department;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.profilePicture = profilePicture;
    }

    // Getters and setters
    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "Lecturer{"
                + "lecturerId=" + lecturerId
                + ", name='" + name + '\''
                + ", department='" + department + '\''
                + ", subjectCode='" + subjectCode + '\''
                + ", subjectName='" + subjectName + '\''
                + '}';
    }

}
