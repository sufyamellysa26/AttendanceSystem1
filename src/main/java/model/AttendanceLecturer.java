/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AttendanceLecturer {

    private int attendanceId;
    private String studentName;
    private String matricNumber;
    private String location;
    private LocalDate date;
    private LocalTime time;
    private int classId;
    // New fields to match attendance_history table
    private String subjectName;
    private String subjectCode;
    private String status;
    private int lecturerId;
    private int duration;
    private LocalDateTime timestamp;

    // Constructor for fetching records from DB
    public AttendanceLecturer(int attendanceId, String studentName, String matricNumber,
            String location, LocalDate date, LocalTime time,
            int classId, String subjectName, String subjectCode,
            String status, LocalDateTime timestamp) {
        this.attendanceId = attendanceId;
        this.studentName = studentName;
        this.matricNumber = matricNumber;
        this.location = location;
        this.date = date;
        this.time = time;
        this.classId = classId;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.status = status;
        this.timestamp = timestamp;
    }

    // Overloaded constructor for inserting new records
    public AttendanceLecturer(String studentName, String matricNumber, String location,
            LocalDate date, LocalTime time, int classId,
            String subjectName, String subjectCode,
            String status, int lecturerId, int duration) {
        this.studentName = studentName;
        this.matricNumber = matricNumber;
        this.location = location;
        this.date = date;
        this.time = time;
        this.classId = classId;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.status = status;
        this.lecturerId = lecturerId;
        this.duration = duration;
    }
    // Constructor for attendance_history table

    public AttendanceLecturer(int attendanceId, String matricNumber, String subjectCode,
            double latitude, double longitude, LocalDateTime timestamp) {
        this.attendanceId = attendanceId;
        this.matricNumber = matricNumber;
        this.subjectCode = subjectCode;
        this.location = latitude + "," + longitude; // optional: store as string
        this.timestamp = timestamp;
    }

    // Getters and setters
    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMatricNumber() {
        return matricNumber;
    }

    public void setMatricNumber(String matricNumber) {
        this.matricNumber = matricNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "AttendanceLecturer{"
                + "attendanceId=" + attendanceId
                + ", studentName='" + studentName + '\''
                + ", matricNumber='" + matricNumber + '\''
                + ", location='" + location + '\''
                + ", date=" + date
                + ", time=" + time
                + ", classId=" + classId
                + ", subjectName='" + subjectName + '\''
                + ", subjectCode='" + subjectCode + '\''
                + ", status='" + status + '\''
                + ", lecturerId=" + lecturerId
                + ", duration=" + duration
                + ", timestamp=" + timestamp
                + '}';
    }
}
