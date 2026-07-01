/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.*;
import model.Geofence;
import model.DBConnection;

public class GeofenceHomepageDAO {

    private Connection conn;

    public List<Geofence> getAllGeofences() {
        List<Geofence> geofences = new ArrayList<>();
        String sql = "SELECT * FROM geofence ORDER BY geofence_id DESC";
        try ( Connection conn = DBConnection.initializeDatabase();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                geofences.add(new Geofence(
                        rs.getInt("geofence_id"),
                        rs.getString("boundary_name"),
                        rs.getInt("location_id"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude"),
                        rs.getDouble("radius"),
                        rs.getInt("staffid"), // ✅ match column name
                        rs.getInt("classid")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return geofences;
    }

    public Geofence getGeofenceById(int geofenceId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM geofence WHERE geofence_id = ? ORDER BY geofence_id DESC";
        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, geofenceId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Geofence(
                        rs.getInt("geofence_id"),
                        rs.getString("boundary_name"),
                        rs.getInt("location_id"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude"),
                        rs.getDouble("radius"),
                        rs.getInt("staffid"),
                        rs.getInt("classid")
                );
            }
        }
        return null;
    }

    public List<Geofence> getGeofencesByStaffId(int staffId) throws SQLException, ClassNotFoundException {
        List<Geofence> geofences = new ArrayList<>();
        String sql = "SELECT * FROM geofence WHERE staffid = ? ORDER BY geofence_id DESC";
        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, staffId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Geofence gf = new Geofence(
                        rs.getInt("geofence_id"),
                        rs.getString("boundary_name"),
                        rs.getInt("location_id"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude"),
                        rs.getDouble("radius"),
                        rs.getInt("staffid"),
                        rs.getInt("classid")
                );
                geofences.add(gf);
            }
        }
        return geofences;
    }

    public int insertGeofence(Geofence gf) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO geofence (boundary_name, location_id, latitude, longitude, radius, staffid) VALUES (?, ?, ?, ?, ?, ?)";
        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, gf.getBoundaryName());
            ps.setInt(2, gf.getLocationId());
            ps.setDouble(3, gf.getLatitude());
            ps.setDouble(4, gf.getLongitude());
            ps.setDouble(5, gf.getRadius());
            ps.setInt(6, gf.getStaffId()); // ✅ logged-in staff only

            ps.executeUpdate();

            // ✅ Get the auto-generated geofence_id
            try ( ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1; // return -1 if insert fails
    }

    public boolean updateGeofence(Geofence gf) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE geofence SET boundary_name=?, location_id=?, latitude=?, longitude=?, radius=? "
                + "WHERE geofence_id=? AND staffid=?";
        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, gf.getBoundaryName());
            ps.setInt(2, gf.getLocationId());   // ✅ update location
            ps.setDouble(3, gf.getLatitude());
            ps.setDouble(4, gf.getLongitude());
            ps.setDouble(5, gf.getRadius());
            ps.setInt(6, gf.getGeofenceId());
            ps.setInt(7, gf.getStaffId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteGeofence(int geofenceId, int staffId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM geofence WHERE geofence_id=? AND staffid=?";
        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, geofenceId);
            ps.setInt(2, staffId); // ✅ numeric staffId
            return ps.executeUpdate() > 0;
        }
    }

    // ✅ Fetch geofence by subject code
    public Geofence getGeofenceBySubject(String subjectCode) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.initializeDatabase();
        String query = "SELECT g.geofenceId, g.boundaryName, g.locationId, g.latitude, g.longitude, g.radius "
                + "FROM geofence g JOIN classsession c ON g.locationId = c.classId "
                + "WHERE c.subjectCode = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, subjectCode);

        ResultSet rs = ps.executeQuery();
        Geofence geofence = null;

        if (rs.next()) {
            geofence = new Geofence();
            geofence.setGeofenceId(rs.getInt("geofenceId"));
            geofence.setBoundaryName(rs.getString("boundaryName"));
            geofence.setLocationId(rs.getInt("locationId"));
            geofence.setLatitude(rs.getDouble("latitude"));
            geofence.setLongitude(rs.getDouble("longitude"));
            geofence.setRadius(rs.getDouble("radius"));
        }

        rs.close();
        ps.close();
        con.close();
        return geofence;
    }

    // ✅ Fetch geofence linked to a class session
    public Geofence getGeofenceBySession(int classId) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.initializeDatabase();
        String sql = "SELECT * FROM geofence WHERE classId=? LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, classId);
        ResultSet rs = ps.executeQuery();

        Geofence gf = null;
        if (rs.next()) {
            gf = new Geofence();
            gf.setGeofenceId(rs.getInt("geofence_id"));
            gf.setBoundaryName(rs.getString("boundary_name"));
            gf.setLocationId(rs.getInt("location_id"));
            gf.setLatitude(rs.getDouble("latitude"));
            gf.setLongitude(rs.getDouble("longitude"));
            gf.setRadius(rs.getDouble("radius"));
            gf.setStaffId(rs.getInt("staffid"));
            gf.setClassId(rs.getInt("classId")); // ✅ link geofence to class session
        }

        rs.close();
        ps.close();
        con.close();
        return gf;
    }

}
