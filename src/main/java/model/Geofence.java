/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Geofence {

    private int geofenceId;
    private String boundaryName;
    private int locationId;
    private double latitude;
    private double longitude;
    private double radius;
    private int staffId;     // FK → staff/lecturer who owns this geofence
    private int classId;     // ✅ new: link geofence to a class session

    // Constructor with all fields
    public Geofence(int geofenceId, String boundaryName, int locationId,
            double latitude, double longitude, double radius,
            int staffId, int classId) {
        this.geofenceId = geofenceId;
        this.boundaryName = boundaryName;
        this.locationId = locationId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.staffId = staffId;
        this.classId = classId;
    }

    public Geofence() {
        // No-arg constructor for frameworks and manual instantiation
    }

    // --- Getters and Setters ---
    public int getGeofenceId() {
        return geofenceId;
    }

    public void setGeofenceId(int geofenceId) {
        this.geofenceId = geofenceId;
    }

    public String getBoundaryName() {
        return boundaryName;
    }

    public void setBoundaryName(String boundaryName) {
        this.boundaryName = boundaryName;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
