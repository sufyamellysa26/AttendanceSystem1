/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Attendance;
import model.DBConnection;

public class AttendanceDAO {

    public boolean insertAttendance(Attendance record) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO attendance_student (classId, userId, latitude, longitude, status, timestamp) "
                + "VALUES (?, ?, ?, ?, ?, NOW())";
        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, record.getClassId());
            ps.setString(2, record.getUserId());
            ps.setDouble(3, record.getLatitude());
            ps.setDouble(4, record.getLongitude());
            ps.setString(5, record.getStatus());
            return ps.executeUpdate() > 0;
        }
    }

    // ✅ Retrieve attendance history for a student with class_session details
    public List<Attendance> getAttendanceHistoryByStudent(String userId) throws SQLException, ClassNotFoundException {
        List<Attendance> history = new ArrayList<>();

        String sql = "SELECT a.attendanceId, a.classId, a.userId, a.latitude, a.longitude, a.status, a.timestamp, "
                + "cs.subjectName, cs.subjectCode, cs.buildingName, cs.roomName, cs.startTime, cs.endTime, cs.duration "
                + "FROM attendance_student a "
                + "JOIN class_session cs ON a.classId = cs.classId "
                + "WHERE a.userId = ? ORDER BY cs.startTime DESC";

        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Attendance record = new Attendance();
                record.setAttendanceId(rs.getInt("attendanceId"));
                record.setClassId(rs.getInt("classId"));
                record.setUserId(rs.getString("userId"));
                record.setLatitude(rs.getDouble("latitude"));
                record.setLongitude(rs.getDouble("longitude"));
                record.setStatus(rs.getString("status"));
                record.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());

                // 🔗 Extra fields from class_session
                record.setSubjectName(rs.getString("subjectName"));
                record.setSubjectCode(rs.getString("subjectCode"));
                record.setBuildingName(rs.getString("buildingName"));
                record.setRoomName(rs.getString("roomName"));
                record.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
                record.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
                record.setDuration(rs.getInt("duration"));

                history.add(record);
            }
        }
        return history;
    }
}
