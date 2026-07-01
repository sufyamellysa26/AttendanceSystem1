/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class TechnicalStaff {

    private int staffId;
    private String staffname;
    private String staffpassword;
    private String staffrole;

    // Constructor
    public TechnicalStaff(int staffId, String staffname, String staffpassword, String staffrole) {
        this.staffId = staffId;
        this.staffname = staffname;
        this.staffpassword = staffpassword;
        this.staffrole = staffrole;
    }

    public TechnicalStaff() {
        // No-arg constructor for frameworks
    }

    // Getters and setters
    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffname;
    }

    public void setStaffName(String name) {
        this.staffname = name;
    }

    public String getStaffPassword() {
        return staffpassword;
    }

    public void setStaffPassword(String password) {
        this.staffpassword = password;
    }

    public String getStaffRole() {
        return staffrole;
    }

    public void setStaffRole(String role) {
        this.staffrole = role;
    }

    @Override
    public String toString() {
        return "TechnicalStaff{"
                + "staffId=" + staffId
                + ", name='" + staffname + '\''
                + ", role='" + staffrole + '\''
                + '}';
    }
}
