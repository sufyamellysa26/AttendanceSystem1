/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author USER
 */
import model.User;
import java.sql.*;
import model.Lecturer;
import model.DBConnection;
import model.Lecturer;
import model.Student;
import model.TechnicalStaff;

public class UserDAO {

    // ✅ Validate login for any user (student, lecturer, staff)
    public User validateLogin(String userId, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT userId, name, role FROM users WHERE userId=? AND password=?";
        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(sql)) {
           
            // 🔍 Debug: show what parameters are being passed in
        System.out.println("DEBUG: SQL → " + sql);
        System.out.println("DEBUG: Parameters → userId=" + userId + ", password=" + password);
        
            ps.setString(1, userId);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("DEBUG: Found user → userId=" + rs.getString("userId")
                        + ", name=" + rs.getString("name")
                        + ", role=" + rs.getString("role"));
                User user = new User();
                user.setUserId(rs.getString("userId"));
                user.setName(rs.getString("name"));
                user.setRole(rs.getString("role"));

                // ✅ Attach numeric PK depending on role
                switch (user.getRole().toLowerCase()) {
                    case "lecturer":
                        user.setLecturerId(getLecturerIdByUserId(userId, con));
                        break;
                    case "student":
                        user.setStudentId(getStudentIdByUserId(userId, con));
                        break;
                    case "technicalstaff":   // ✅ fixed here
                        user.setStaffId(getStaffIdByUserId(userId, con));
                        break;
                }
                // 🔍 Debug:show final role after object creation
                System.out.println("DEBUG: Returning User object with role=" + user.getRole());
                return user;
            } else {
                System.out.println("DEBUG: No user found for given credentials.");
            }
        }
        return null;
    }

    // ✅ Fetch lecturerId by userId
    private int getLecturerIdByUserId(String userId, Connection con) throws SQLException {
        String sql = "SELECT lecturerId FROM lecturer WHERE userId=?";
        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("lecturerId");
            }
        }
        return -1;
    }

    // ✅ Fetch studentId by userId
    private int getStudentIdByUserId(String userId, Connection con) throws SQLException {
        String sql = "SELECT id FROM student WHERE userId=?";
        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1;
    }

    // ✅ Fetch staffId by userId
    private int getStaffIdByUserId(String userId, Connection con) throws SQLException {
        String sql = "SELECT staffId FROM technicalstaff WHERE userId=?";
        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("staffId");
            }
        }
        return -1;
    }

    // ✅ Fetch full Lecturer details
    public Lecturer getLecturerByUserId(String userId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM lecturer WHERE userId=?";
        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Lecturer(
                        rs.getInt("lecturerId"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("department"),
                        rs.getString("subjectCode"),
                        rs.getString("subjectName"),
                        rs.getString("profilePicture")
                );
            }
        }
        return null;
    }

    // ✅ Fetch full Student details
    public Student getStudentByUserId(String userId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM student WHERE userId=?";
        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setPassword(rs.getString("password"));
                student.setMatric(rs.getString("matric"));
                student.setCourseProgram(rs.getString("courseProgram"));
                student.setSemester(rs.getInt("semester"));
                student.setProfilePicture(rs.getString("profilePicture"));
                student.setUserId(rs.getString("userId"));
                return student;
            }
        }
        return null;
    }

    // ✅ Fetch full Technical Staff details
    public TechnicalStaff getStaffByUserId(String userId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM technicalstaff WHERE userId=?";
        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new TechnicalStaff(
                        rs.getInt("staffid"),
                        rs.getString("staffname"),
                        rs.getString("staffpassword"),
                        rs.getString("staffrole")
                );
            }
        }
        return null;
    }

}
