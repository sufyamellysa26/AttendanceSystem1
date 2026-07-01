/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
import java.time.LocalDateTime;
import java.util.List;

public class ClassSession {

    private int classId;
    private String subjectName;
    private String subjectCode;
    private String buildingName;
    private String roomName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int duration;
    private int lecturerId;
    private int geofenceId; // ✅ link to geofence table

    // ✅ Lecturer name (from join)
    private String lecturerName;

    // ✅ Nested attendance records for this session
    private List<Attendance> attendanceRecords;

    // --- Constructors ---
    public ClassSession() {
    }

    // Constructor without geofenceId
    public ClassSession(int classId, String subjectName, String subjectCode,
            String buildingName, String roomName,
            LocalDateTime startTime, LocalDateTime endTime,
            int duration, int lecturerId) {
        this.classId = classId;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.buildingName = buildingName;
        this.roomName = roomName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.lecturerId = lecturerId;
    }

    // Constructor with geofenceId
    public ClassSession(int classId, String subjectName, String subjectCode,
            String buildingName, String roomName,
            LocalDateTime startTime, LocalDateTime endTime,
            int duration, int lecturerId, int geofenceId) {
        this(classId, subjectName, subjectCode, buildingName, roomName,
                startTime, endTime, duration, lecturerId);
        this.geofenceId = geofenceId;
    }

    // --- Getters and Setters ---
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public int getGeofenceId() {
        return geofenceId;
    }

    public void setGeofenceId(int geofenceId) {
        this.geofenceId = geofenceId;
    }

    public List<Attendance> getAttendanceRecords() {
        return attendanceRecords;
    }

    public void setAttendanceRecords(List<Attendance> attendanceRecords) {
        this.attendanceRecords = attendanceRecords;
    }

    // ✅ Lecturer name
    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }
}
