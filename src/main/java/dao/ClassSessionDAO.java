/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.*;
import model.Attendance;
import model.ClassSession;
import model.DBConnection;

public class ClassSessionDAO {

    // ✅ Add a new class session (with geofence validation)
    public boolean addClassSession(ClassSession cs) throws SQLException, ClassNotFoundException {
        try ( Connection con = DBConnection.initializeDatabase()) {

            // 🔍 Validate geofenceId before inserting
            if (cs.getGeofenceId() != 0) {
                String checkSql = "SELECT geofence_id FROM geofence WHERE geofence_id = ?";
                try ( PreparedStatement checkPs = con.prepareStatement(checkSql)) {
                    checkPs.setInt(1, cs.getGeofenceId());
                    ResultSet rs = checkPs.executeQuery();
                    if (!rs.next()) {
                        throw new SQLException("Invalid geofenceId: " + cs.getGeofenceId());
                    }
                }
            } else {
                throw new SQLException("GeofenceId cannot be null or zero.");
            }

            String query = "INSERT INTO class_session(subjectName, subjectCode, buildingName, roomName, startTime, endTime, duration, lecturerId, geofenceId) VALUES(?,?,?,?,?,?,?,?,?)";
            try ( PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, cs.getSubjectName());
                ps.setString(2, cs.getSubjectCode());
                ps.setString(3, cs.getBuildingName());
                ps.setString(4, cs.getRoomName());
                ps.setTimestamp(5, Timestamp.valueOf(cs.getStartTime()));
                ps.setTimestamp(6, Timestamp.valueOf(cs.getEndTime()));
                ps.setInt(7, cs.getDuration());
                ps.setInt(8, cs.getLecturerId());
                ps.setInt(9, cs.getGeofenceId());

                int rows = ps.executeUpdate();
                return rows > 0;   // ✅ return true if insert succeeded
            }
        }
    }
        // ✅ Retrieve all class sessions for a lecturer (with student attendance records)
    public List<ClassSession> getClassSessionsByLecturer(int lecturerId) throws SQLException, ClassNotFoundException {
        List<ClassSession> sessions = new ArrayList<>();
        String query = "SELECT * FROM class_session WHERE lecturerId=? ORDER BY startTime DESC";

        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, lecturerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ClassSession cs = new ClassSession(
                        rs.getInt("classId"),
                        rs.getString("subjectName"),
                        rs.getString("subjectCode"),
                        rs.getString("buildingName"),
                        rs.getString("roomName"),
                        rs.getTimestamp("startTime").toLocalDateTime(),
                        rs.getTimestamp("endTime").toLocalDateTime(),
                        rs.getInt("duration"),
                        rs.getInt("lecturerId"),
                        rs.getInt("geofenceId")
                );

                // 🔗 Attach student attendance records
                cs.setAttendanceRecords(getAttendanceByClassId(cs.getClassId(), con));

                sessions.add(cs);
            }
        }
        return sessions;
    }

    // ✅ Helper: fetch attendance records for a given classId
    private List<Attendance> getAttendanceByClassId(int classId, Connection con) throws SQLException {
        List<Attendance> records = new ArrayList<>();
        String sql = "SELECT attendanceId, userId, latitude, longitude, status, timestamp "
                + "FROM attendance_student WHERE classId = ? ORDER BY timestamp ASC";

        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, classId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Attendance att = new Attendance();
                att.setAttendanceId(rs.getInt("attendanceId"));
                att.setClassId(classId);
                att.setUserId(rs.getString("userId"));
                att.setLatitude(rs.getDouble("latitude"));
                att.setLongitude(rs.getDouble("longitude"));
                att.setStatus(rs.getString("status"));
                att.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());

                records.add(att);
            }
        }
        return records;
    }

    // ✅ Retrieve all class sessions (optional, for admin/staff)
    public List<ClassSession> getAllClassSessions() throws SQLException, ClassNotFoundException {
        List<ClassSession> sessions = new ArrayList<>();
        String query = "SELECT * FROM class_session";

        try ( Connection con = DBConnection.initializeDatabase();  Statement stmt = con.createStatement();  ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ClassSession cs = new ClassSession(
                        rs.getInt("classId"),
                        rs.getString("subjectName"),
                        rs.getString("subjectCode"),
                        rs.getString("buildingName"),
                        rs.getString("roomName"),
                        rs.getTimestamp("startTime").toLocalDateTime(),
                        rs.getTimestamp("endTime").toLocalDateTime(),
                        rs.getInt("duration"),
                        rs.getInt("lecturerId"),
                        rs.getInt("geofenceId")
                );
                // optional: attach attendance if needed
                sessions.add(cs);
            }
        }
        return sessions;
    }

    // ✅ Delete a class session
    public void deleteClassSession(int classId) throws SQLException, ClassNotFoundException {
        try ( Connection con = DBConnection.initializeDatabase()) {
            String sql = "DELETE FROM class_session WHERE classId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, classId);
            ps.executeUpdate();
        }
    }

    // ✅ Find active class session by subject (with lecturer info)
    public ClassSession findActiveSession(String subjectCode) throws SQLException, ClassNotFoundException {
        try ( Connection con = DBConnection.initializeDatabase()) {
            String sql = "SELECT cs.*, l.name AS lecturerName "
                    + "FROM class_session cs "
                    + "JOIN lecturer l ON cs.lecturerId = l.lecturerId "
                    + "WHERE cs.subjectCode=? AND NOW() BETWEEN cs.startTime AND cs.endTime LIMIT 1";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, subjectCode);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ClassSession cs = new ClassSession(
                        rs.getInt("classId"),
                        rs.getString("subjectName"),
                        rs.getString("subjectCode"),
                        rs.getString("buildingName"),
                        rs.getString("roomName"),
                        rs.getTimestamp("startTime").toLocalDateTime(),
                        rs.getTimestamp("endTime").toLocalDateTime(),
                        rs.getInt("duration"),
                        rs.getInt("lecturerId"),
                        rs.getInt("geofenceId")
                );

                // ✅ Add lecturer name to the model
                cs.setLecturerName(rs.getString("lecturerName"));

                // Attach attendance records for lecturer view
                cs.setAttendanceRecords(getAttendanceByClassId(cs.getClassId(), con));

                return cs;
            }
        }
        // Return null if no active session found
        return null;
    }

    // ✅ Retrieve sessions attended by a student (via userId)
    public List<ClassSession> getClassSessionsByStudent(String userId) throws SQLException, ClassNotFoundException {
        List<ClassSession> sessions = new ArrayList<>();
        String sql = "SELECT cs.classId, cs.subjectName, cs.subjectCode, cs.buildingName, cs.roomName, "
                + "cs.startTime, cs.endTime, cs.duration, cs.lecturerId, cs.geofenceId "
                + "FROM class_session cs "
                + "JOIN attendance_student a ON cs.classId = a.classId "
                + "WHERE a.userId = ? ORDER BY cs.startTime DESC";

        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClassSession cs = new ClassSession(
                        rs.getInt("classId"),
                        rs.getString("subjectName"),
                        rs.getString("subjectCode"),
                        rs.getString("buildingName"),
                        rs.getString("roomName"),
                        rs.getTimestamp("startTime").toLocalDateTime(),
                        rs.getTimestamp("endTime").toLocalDateTime(),
                        rs.getInt("duration"),
                        rs.getInt("lecturerId"),
                        rs.getInt("geofenceId")
                );
                cs.setAttendanceRecords(getAttendanceByClassId(cs.getClassId(), con));
                sessions.add(cs);
            }
        }
        return sessions;
    }
    
    // ✅ Add this method
    public ClassSession getClassSessionById(int classId) throws SQLException, ClassNotFoundException {
        ClassSession session = null;
        String sql = "SELECT c.classId, s.subjectCode, s.subjectName, b.buildingName, c.roomName, "
                   + "c.startTime, c.endTime, TIMESTAMPDIFF(HOUR, c.startTime, c.endTime) AS duration "
                   + "FROM class_session c "
                   + "JOIN subject s ON c.subjectId = s.subjectId "
                   + "JOIN building b ON c.buildingId = b.buildingId "
                   + "WHERE c.classId = ?";

        try (Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, classId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                session = new ClassSession();
                session.setClassId(rs.getInt("classId"));
                session.setSubjectCode(rs.getString("subjectCode"));
                session.setSubjectName(rs.getString("subjectName"));
                session.setBuildingName(rs.getString("buildingName"));
                session.setRoomName(rs.getString("roomName"));
                session.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
                session.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
                session.setDuration(rs.getInt("duration"));

                // If you want attendance records too:
                session.setAttendanceRecords(getAttendanceByClassId(classId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return session;
    }

    // Optional helper to fetch attendance records
    private List<Attendance> getAttendanceByClassId(int classId) throws SQLException, ClassNotFoundException {
        List<Attendance> records = new ArrayList<>();
        String sql = "SELECT * FROM attendance_student WHERE classId = ?";
        try (Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, classId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Attendance att = new Attendance();
                att.setAttendanceId(rs.getInt("attendanceId"));
                att.setUserId(rs.getString("userId"));
                att.setStatus(rs.getString("status"));
                att.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
                records.add(att);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
}
