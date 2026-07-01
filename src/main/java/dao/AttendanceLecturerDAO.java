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

import model.ClassSession;
import dao.ClassSessionDAO;
import model.AttendanceLecturer;
import model.DBConnection;

public class AttendanceLecturerDAO {

    // ✅ Record attendance into attendance_history
    public void recordAttendance(AttendanceLecturer att) throws SQLException, ClassNotFoundException {
        try ( Connection con = DBConnection.initializeDatabase()) {
            String query = "INSERT INTO attendance_history(studentName, matricNumber, location, date, time, classId, subjectName, subjectCode, status, lecturerId, duration, timestamp) VALUES(?,?,?,?,?,?,?,?,?,?,?,NOW())";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, att.getStudentName());
            ps.setString(2, att.getMatricNumber());
            ps.setString(3, att.getLocation());
            ps.setDate(4, java.sql.Date.valueOf(att.getDate()));
            ps.setTime(5, java.sql.Time.valueOf(att.getTime()));
            ps.setInt(6, att.getClassId());
            ps.setString(7, att.getSubjectName());
            ps.setString(8, att.getSubjectCode());
            ps.setString(9, att.getStatus());
            ps.setInt(10, att.getLecturerId());
            ps.setInt(11, att.getDuration());
            ps.executeUpdate();
        }
    }

    // ✅ Delete attendance
    public void deleteAttendance(int historyId) throws SQLException, ClassNotFoundException {
        try ( Connection con = DBConnection.initializeDatabase()) {
            String query = "DELETE FROM attendance_history WHERE historyId=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, historyId);
            ps.executeUpdate();
        }
    }

    // ✅ Get all attendance records for a class
    public List<AttendanceLecturer> getAttendanceByClass(int classId) throws SQLException, ClassNotFoundException {
        List<AttendanceLecturer> records = new ArrayList<>();
        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement("SELECT * FROM attendance_history WHERE classId=?")) {
            ps.setInt(1, classId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AttendanceLecturer att = new AttendanceLecturer(
                        rs.getInt("historyId"),
                        rs.getString("studentName"),
                        rs.getString("matricNumber"),
                        rs.getString("location"),
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("time").toLocalTime(),
                        rs.getInt("classId"),
                        rs.getString("subjectName"),
                        rs.getString("subjectCode"),
                        rs.getString("status"),
                        rs.getTimestamp("timestamp").toLocalDateTime() // ✅ include timestamp
                );
                att.setLecturerId(rs.getInt("lecturerId"));
                att.setDuration(rs.getInt("duration"));
                records.add(att);
            }
        }
        return records;
    }

    // ✅ Get attendance record by ID
    public AttendanceLecturer getAttendanceById(int historyId) throws SQLException, ClassNotFoundException {
        AttendanceLecturer att = null;
        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement("SELECT * FROM attendance_history WHERE historyId=?")) {
            ps.setInt(1, historyId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                att = new AttendanceLecturer(
                        rs.getInt("historyId"),
                        rs.getString("studentName"),
                        rs.getString("matricNumber"),
                        rs.getString("location"),
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("time").toLocalTime(),
                        rs.getInt("classId"),
                        rs.getString("subjectName"),
                        rs.getString("subjectCode"),
                        rs.getString("status"),
                        rs.getTimestamp("timestamp").toLocalDateTime()
                );
                att.setLecturerId(rs.getInt("lecturerId"));
                att.setDuration(rs.getInt("duration"));
            }
        }
        return att;
    }

    // ✅ Get attendance history for lecturer
    public List<AttendanceLecturer> getAttendanceHistoryByLecturer(int lecturerId) throws SQLException, ClassNotFoundException {
        List<AttendanceLecturer> records = new ArrayList<>();
        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement("SELECT * FROM class_session WHERE lecturerId=?")) {
            ps.setInt(1, lecturerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AttendanceLecturer att = new AttendanceLecturer(
                        rs.getInt("historyId"),
                        rs.getString("studentName"),
                        rs.getString("matricNumber"),
                        rs.getString("location"),
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("time").toLocalTime(),
                        rs.getInt("classId"),
                        rs.getString("subjectName"),
                        rs.getString("subjectCode"),
                        rs.getString("status"),
                        rs.getTimestamp("timestamp").toLocalDateTime()
                );
                att.setLecturerId(rs.getInt("lecturerId"));
                att.setDuration(rs.getInt("duration"));
                records.add(att);
            }
        }
        return records;
    }

    // ✅ Get all attendance history
    public List<AttendanceLecturer> getAllAttendanceHistory() throws SQLException, ClassNotFoundException {
        List<AttendanceLecturer> records = new ArrayList<>();
        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement("SELECT * FROM attendance_history")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AttendanceLecturer att = new AttendanceLecturer(
                        rs.getInt("historyId"),
                        rs.getString("studentName"),
                        rs.getString("matricNumber"),
                        rs.getString("location"),
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("time").toLocalTime(),
                        rs.getInt("classId"),
                        rs.getString("subjectName"),
                        rs.getString("subjectCode"),
                        rs.getString("status"),
                        rs.getTimestamp("timestamp").toLocalDateTime()
                );
                att.setLecturerId(rs.getInt("lecturerId"));
                att.setDuration(rs.getInt("duration"));
                records.add(att);
            }
        }
        return records;
    }

}
