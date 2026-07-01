/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.*;
import model.Location;
import model.DBConnection;

public class LocationDAO {

    private int geofenceId;

    // public List<Location> getAllLocations() {
    // List<Location> locations = new ArrayList<>();
    //String sql = "SELECT * FROM Location";
    // try ( Connection conn = DBConnection.initializeDatabase();  PreparedStatement pstmt = conn.prepareStatement(sql);  ResultSet rs = pstmt.executeQuery()) {
    //  while (rs.next()) {
    //    Location loc = new Location(
    //         locations.add(new Location(
    //                 rs.getInt("location_id"),
    //               rs.getString("building_name"),
    //               rs.getString("room_name")
    //       )));
    //            locations.add(loc);
    //        }
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //    return locations;
    //}
    public Location getLocationById(int locationId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM location WHERE location_id = ?";
        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, locationId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Location(
                        rs.getInt("location_id"),
                        rs.getString("building_name"),
                        rs.getString("room_name")
                );
            }
        }
        return null;
    }

    public List<Location> getAllLocations() throws SQLException, ClassNotFoundException {
        List<Location> locations = new ArrayList<>();
        String sql = "SELECT location_id, building_name, room_name FROM location";
        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Location loc = new Location(
                        rs.getInt("location_id"),
                        rs.getString("building_name"),
                        rs.getString("room_name")
                );
                locations.add(loc);
            }
        }
        return locations;
    }

    public List<Location> getLocationsWithoutGeofence() throws SQLException, ClassNotFoundException {
        List<Location> locations = new ArrayList<>();
        String sql = "SELECT l.location_id, l.building_name, l.room_name "
                + "FROM location l "
                + "WHERE l.location_id NOT IN (SELECT location_id FROM geofence)";
        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Location loc = new Location(
                        rs.getInt("location_id"),
                        rs.getString("building_name"),
                        rs.getString("room_name")
                );
                locations.add(loc);
            }
        }
        return locations;
    }

    public List<Location> getAvailableLocationsForEdit(int currentLocationId) throws SQLException, ClassNotFoundException {
        List<Location> locations = new ArrayList<>();
        String sql = "SELECT l.location_id, l.building_name, l.room_name "
                + "FROM location l "
                + "WHERE l.location_id = ? "
                + "   OR l.location_id NOT IN (SELECT location_id FROM geofence)";
        try ( Connection con = DBConnection.initializeDatabase();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, currentLocationId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Location loc = new Location(
                        rs.getInt("location_id"),
                        rs.getString("building_name"),
                        rs.getString("room_name")
                );
                locations.add(loc);
            }
        }
        return locations;
    }

}
