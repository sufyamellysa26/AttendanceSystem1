/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AttendanceDAO;
import dao.ClassSessionDAO;
import dao.GeofenceHomepageDAO;   // ✅ new DAO for geofence
import javax.servlet.http.HttpSession;
import model.Attendance;
import model.ClassSession;
import model.Geofence;    // ✅ model for geofence
import model.User;

/**
 *
 * @author USER
 */
@WebServlet(name = "RecordAttendanceServlet", urlPatterns = {"/RecordAttendanceServlet"})
public class RecordAttendanceServlet extends HttpServlet {

    private AttendanceDAO attendanceDAO = new AttendanceDAO();
    private ClassSessionDAO classSessionDAO = new ClassSessionDAO();

    // Utility: Haversine formula for distance in meters
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371000; // meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RecordAttendanceServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RecordAttendanceServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // ✅ Now safely assign them
            String userId = request.getParameter("userId");
            String password = request.getParameter("password");
            String subjectCode = request.getParameter("subjectCode");
            //String latStr = request.getParameter("lat");
            // String lonStr = request.getParameter("lon");

            // 🔍 Debug: print raw parameters before using them
            System.out.println("DEBUG: raw userId=" + request.getParameter("userId"));
            System.out.println("DEBUG: raw password=" + request.getParameter("password"));
            System.out.println("DEBUG: raw subjectCode=" + request.getParameter("subjectCode"));
            //System.out.println("DEBUG: raw lat=" + request.getParameter("lat"));
            // System.out.println("DEBUG: raw lon=" + request.getParameter("lon"));

            // 🔍 Debug logs
            System.out.println("DEBUG: userId=" + userId + ", subjectCode=" + subjectCode);
            // System.out.println("DEBUG: lat=" + latStr + ", lon=" + lonStr);

            // ✅ Differentiate between null and empty
            if (userId == null) {
                out.write("{\"status\":\"error\",\"message\":\"Parameter userId is MISSING (not sent).\"}");
                return;
            } else if (userId.trim().isEmpty()) {
                out.write("{\"status\":\"error\",\"message\":\"Parameter userId is EMPTY (sent but blank).\"}");
                return;
            }

            if (subjectCode == null) {
                out.write("{\"status\":\"error\",\"message\":\"Parameter subjectCode is MISSING.\"}");
                return;
            } else if (subjectCode.trim().isEmpty()) {
                out.write("{\"status\":\"error\",\"message\":\"Parameter subjectCode is EMPTY.\"}");
                return;
            }

            //if (latStr == null || lonStr == null) {
            //    out.write("{\"status\":\"error\",\"message\":\"Latitude/Longitude are MISSING.\"}");
            //   return;
            // } else if (latStr.trim().isEmpty() || lonStr.trim().isEmpty()) {
            //    out.write("{\"status\":\"error\",\"message\":\"Latitude/Longitude are EMPTY.\"}");
            //    return;
            //}
//
            // double latitude = Double.parseDouble(latStr);
            // double longitude = Double.parseDouble(lonStr);
            // ✅ Find active class session for subject
            ClassSession activeSession = classSessionDAO.findActiveSession(subjectCode);
            System.out.println("DEBUG: activeSession=" + activeSession);

            if (activeSession == null) {
                out.write("{\"status\":\"error\",\"message\":\"No active class session found for subject.\"}");
                return;
            }

            // Fetch geofence linked to this session
            // GeofenceHomepageDAO gfDao = new GeofenceHomepageDAO();
            // Geofence gf = gfDao.getGeofenceById(activeSession.getGeofenceId());
            // System.out.println("DEBUG: geofence=" + gf);
            // if (gf == null) {
            //   out.write("{\"status\":\"error\",\"message\":\"No geofence found for this session.\"}");
            // return;
            //  }
            // ✅ Create attendance record
            Attendance record = new Attendance();
            record.setClassId(activeSession.getClassId());
            record.setUserId(userId);
            // Temporarily skip lat/lon
            record.setLatitude(0.0);
            record.setLongitude(0.0);

// ✅ Always set a status so DB insert works
            record.setStatus("present");  // or "test", "manual", etc.
            // record.setLatitude(latitude);
            // record.setLongitude(longitude);
//
            // ✅ Geofence validation
            // double distance = calculateDistance(latitude, longitude, gf.getLatitude(), gf.getLongitude());
            // if (distance <= gf.getRadius()) {
            //     record.setStatus("present");
            // } else {
            //      record.setStatus("outside");
            // }

            // ✅ Insert attendance
            boolean success = attendanceDAO.insertAttendance(record);

            if (success) {
                out.write("{\"status\":\"success\",\"message\":\"Attendance for " + subjectCode
                        + " with " + activeSession.getLecturerName()
                        + " recorded successfully.\"}");

            } else {
                out.write("{\"status\":\"error\",\"message\":\"Failed to record attendance.\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.write("{\"status\":\"error\",\"message\":\"Exception: " + e.getMessage() + "\"}");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
