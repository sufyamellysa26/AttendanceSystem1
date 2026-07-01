/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author USER
 */
import java.sql.*;
import java.util.*;
import model.Lecturer;
import model.DBConnection;

public class LecturerDAO {

    public boolean validateLogin(int lecturerId, String password) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.initializeDatabase();
        String query = "SELECT * FROM lecturer WHERE lecturerId=? AND password=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, lecturerId);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        boolean valid = rs.next();
        con.close();
        return valid;
    }

    public Lecturer getLecturerById(int lecturerId) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.initializeDatabase();
        String query = "SELECT * FROM lecturer WHERE lecturerId=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, lecturerId);
        ResultSet rs = ps.executeQuery();
        Lecturer lecturer = null;
        if (rs.next()) {
            lecturer = new Lecturer(
                    rs.getInt("lecturerId"),
                    rs.getString("name"),
                    rs.getString("password"),
                    rs.getString("department"),
                    rs.getString("subjectCode"),
                    rs.getString("subjectName"),
                    rs.getString("profilePicture")
            );
        }
        con.close();
        return lecturer;
    }

    public void updateLecturer(Lecturer lecturer) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.initializeDatabase();
        String query = "UPDATE lecturer SET department=?, subjectCode=?, subjectName=? WHERE lecturerId=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, lecturer.getDepartment());
        ps.setString(2, lecturer.getSubjectCode());
        ps.setString(3, lecturer.getSubjectName());
        ps.setInt(4, lecturer.getLecturerId());
        ps.executeUpdate();
        con.close();
    }

    public Lecturer getLecturerByUserId(String userId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM lecturer WHERE user_id = ?";
        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Lecturer(
                        rs.getInt("lecturer_id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("department"),
                        rs.getString("subject_code"),
                        rs.getString("subject_name"),
                        rs.getString("profile_picture")
                );
            }
        }
        return null;
    }

    public List<String> getSubjectsByLecturer(int lecturerId) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.initializeDatabase();
        String sql = "SELECT subjectCode FROM lecturer WHERE lecturerId = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, lecturerId);
        ResultSet rs = ps.executeQuery();

        List<String> codes = new ArrayList<>();
        while (rs.next()) {
            codes.add(rs.getString("subjectCode"));
        }
        con.close();
        return codes;
    }

}
