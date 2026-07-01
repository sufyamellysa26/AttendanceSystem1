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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dao.ClassSessionDAO;
import javax.servlet.http.HttpSession;
import dao.GeofenceHomepageDAO;
import dao.LecturerDAO;
import dao.LocationDAO;
import java.util.List;
import javax.servlet.RequestDispatcher;
import model.ClassSession;
import model.Geofence;
import model.Lecturer;
import model.Location;
import dao.AttendanceDAO;
import dao.ClassSessionDAO;
import model.Attendance;
import model.User;
import dao.UserDAO;

/**
 *
 * @author USER
 */
@WebServlet(name = "RecordAttendanceLecturerServlet", urlPatterns = {"/RecordAttendanceLecturerServlet"})
public class RecordAttendanceLecturerServlet extends HttpServlet {

    private ClassSessionDAO classDAO = new ClassSessionDAO();

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
            out.println("<title>Servlet RecordAttendanceLecturerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RecordAttendanceLecturerServlet at " + request.getContextPath() + "</h1>");
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
        try {
            HttpSession session = request.getSession();
            Lecturer lecturer = (Lecturer) session.getAttribute("lecturer");

            if (lecturer == null) {
                throw new ServletException("Lecturer not found in session. Please log in first.");
            }

            // ✅ Load geofence list
            GeofenceHomepageDAO geoDao = new GeofenceHomepageDAO();
            List<Geofence> geofenceList = geoDao.getAllGeofences();
            request.setAttribute("geofenceList", geofenceList);

            // ✅ Load subject codes taught by this lecturer
            LecturerDAO lecturerDao = new LecturerDAO();
            List<String> subjectCodes = lecturerDao.getSubjectsByLecturer(lecturer.getLecturerId());
            request.setAttribute("subjectCodes", subjectCodes);

            RequestDispatcher rd = request.getRequestDispatcher("RecordAttendanceLecturer.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
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
        HttpSession session = request.getSession();
        Lecturer lecturer = (Lecturer) session.getAttribute("lecturer");

        if (lecturer == null) {
            throw new ServletException("Lecturer not found in session. Please log in first.");
        }

        String subjectCode = request.getParameter("subjectCode");
        int geofenceId = Integer.parseInt(request.getParameter("geofence")); // selected geofence ID
        int duration = Integer.parseInt(request.getParameter("duration"));

        try {
            // Start and end time based on current time + duration
            LocalDateTime start = LocalDateTime.now();
            LocalDateTime end = start.plusHours(duration);

            // ✅ Fetch geofence by its ID
            GeofenceHomepageDAO geoDao = new GeofenceHomepageDAO();
            Geofence geofence = geoDao.getGeofenceById(geofenceId);

            // ✅ Fetch location using geofence.locationId
            LocationDAO locationDao = new LocationDAO();
            Location location = locationDao.getLocationById(geofence.getLocationId());

            // ✅ FIX: Pass geofenceId into ClassSession
            ClassSession cs = new ClassSession(
                    0,
                    lecturer.getSubjectName(),
                    subjectCode,
                    location.getBuildingName(),
                    location.getRoomName(),
                    start,
                    end,
                    duration,
                    lecturer.getLecturerId(),
                    geofenceId // <-- THIS LINE FIXES THE ERROR
            );

            // Save to DB
            classDAO.addClassSession(cs);

            // ✅ Forward back with success flag
            request.setAttribute("success", true);

            // Reload geofence list so JSP can still render dropdown
            List<Geofence> geofenceList = geoDao.getAllGeofences();
            request.setAttribute("geofenceList", geofenceList);

            RequestDispatcher rd = request.getRequestDispatcher("RecordAttendanceLecturer.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Handles lecturer class setup using ClassSession";
    }// </editor-fold>

}
