package model;

import java.time.LocalDateTime;

public class Attendance {

    // --- Attendance record fields ---
    private int attendanceId;   // PK
    private int classId;        // FK → class_session.classId
    private String userId;      // FK → users.userId (student/lecturer login ID)
    private double latitude;
    private double longitude;
    private String status;      // present / absent / outside
    private LocalDateTime timestamp;

    // --- Extra fields from class_session ---
    private String subjectName;
    private String subjectCode;
    private String buildingName;
    private String roomName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int duration;

    // --- Constructors ---
    public Attendance() {
    }

    public Attendance(int attendanceId, int classId, String userId, double latitude, double longitude,
            String status, LocalDateTime timestamp, String subjectName, String subjectCode,
            String buildingName, String roomName, LocalDateTime startTime,
            LocalDateTime endTime, int duration) {
        this.attendanceId = attendanceId;
        this.classId = classId;
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.timestamp = timestamp;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.buildingName = buildingName;
        this.roomName = roomName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    // --- Getters and Setters ---
    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
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
}
