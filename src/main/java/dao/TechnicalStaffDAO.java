package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import model.TechnicalStaff;
import java.sql.*;
import model.DBConnection;

public class TechnicalStaffDAO {

    public TechnicalStaff validateLogin(String staffName, String staffpassword) {
        TechnicalStaff staff = null;
        String sql = "SELECT * FROM TechnicalStaff WHERE staffname=? AND staffpassword=?";

        try ( Connection conn = DBConnection.initializeDatabase();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, staffName);
            pstmt.setString(2, staffpassword);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("staffid");
                String name = rs.getString("staffname");
                String role = rs.getString("staffrole");
                staff = new TechnicalStaff(id, name, staffpassword, role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staff;
    }

    //  FIXED VERSION:
    public void insertTechnicalStaff(TechnicalStaff staff, String userId) {
        String sql = "INSERT INTO TechnicalStaff(staffid, staffname, staffpassword, staffrole, userId) VALUES(?,?,?,?,?)";
        try ( Connection conn = DBConnection.initializeDatabase();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, staff.getStaffId());
            pstmt.setString(2, staff.getStaffName());
            pstmt.setString(3, staff.getStaffPassword());
            pstmt.setString(4, staff.getStaffRole());
            pstmt.setString(5, userId); // ✅ Saves the alphanumeric login ID (e.g., "T54321")
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
