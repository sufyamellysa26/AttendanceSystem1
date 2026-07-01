/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.*;

import model.Student;
import model.DBConnection;

public class StudentDAO {

    // ✅ Fetch student profile by matric number
    public Student getStudentProfile(String matric) throws SQLException, ClassNotFoundException {
        String query = "SELECT id, userId, name, matric, courseProgram, semester, subjects, phone, address, password "
                + "FROM student WHERE matric = ?";

        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, matric);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setUserId(rs.getString("userId"));
                student.setName(rs.getString("name"));
                student.setMatric(rs.getString("matric"));
                student.setCourseProgram(rs.getString("courseProgram"));
                student.setSemester(rs.getInt("semester"));
                student.setPassword(rs.getString("password"));

                // ✅ Subjects stored as comma-separated string
                String subjectsStr = rs.getString("subjects");
                if (subjectsStr != null && !subjectsStr.trim().isEmpty()) {
                    student.setSubjects(new ArrayList<String>(Arrays.asList(subjectsStr.split(","))));
                } else {
                    student.setSubjects(new ArrayList<String>());
                }

                student.setPhone(rs.getString("phone"));
                student.setAddress(rs.getString("address"));

                return student;
            } else {
                throw new SQLException("No student found with matric: " + matric);
            }
        }
    }

    // ✅ Update student profile (excluding password)
    public boolean updateStudentProfile(Student student) throws SQLException, ClassNotFoundException {
        String query = "UPDATE student SET courseProgram=?, semester=?, subjects=?, phone=?, address=? WHERE matric=?";

        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, student.getCourseProgram());
            ps.setInt(2, student.getSemester());

            String subjectsStr = (student.getSubjects() != null && !student.getSubjects().isEmpty())
                    ? String.join(",", student.getSubjects())
                    : "";
            ps.setString(3, subjectsStr);

            ps.setString(4, student.getPhone());
            ps.setString(5, student.getAddress());
            ps.setString(6, student.getMatric());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    // ✅ Fetch student by userId (for login/session use)
    public Student getStudentByUserId(String userId) throws SQLException, ClassNotFoundException {
        String query = "SELECT id, userId, name, matric, courseProgram, semester, subjects, phone, address, password "
                + "FROM student WHERE userId = ?";

        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setUserId(rs.getString("userId"));
                student.setName(rs.getString("name"));
                student.setMatric(rs.getString("matric"));
                student.setCourseProgram(rs.getString("courseProgram"));
                student.setSemester(rs.getInt("semester"));
                student.setPassword(rs.getString("password"));

                String subjectsStr = rs.getString("subjects");
                if (subjectsStr != null && !subjectsStr.trim().isEmpty()) {
                    student.setSubjects(new ArrayList<String>(Arrays.asList(subjectsStr.split(","))));
                } else {
                    student.setSubjects(new ArrayList<String>());
                }

                student.setPhone(rs.getString("phone"));
                student.setAddress(rs.getString("address"));
                return student;
            } else {
                throw new SQLException("No student found with userId: " + userId);
            }
        }
    }
}
