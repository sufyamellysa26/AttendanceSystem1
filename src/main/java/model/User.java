/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class User {

    private String userId;     // e.g. "L67890", "S12345", "T54321"
    private String name;
    private String password;
    private String role;       // "lecturer", "student", "staff"

    // ✅ Numeric PKs for linking to lecturer/student/staff tables
    private int lecturerId;
    private int studentId;
    private int staffId;

    // --- Constructors ---
    public User() {
    }

    // Basic constructor
    public User(String userId, String name, String role) {
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

    // Full constructor with IDs
    public User(String userId, String name, String password, String role,
            int lecturerId, int studentId, int staffId) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.role = role;
        this.lecturerId = lecturerId;
        this.studentId = studentId;
        this.staffId = staffId;
    }

    // --- Getters and Setters ---
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }
}
