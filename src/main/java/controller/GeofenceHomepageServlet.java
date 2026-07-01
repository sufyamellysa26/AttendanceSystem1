package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.GeofenceHomepageDAO;
import java.sql.SQLException;
import model.Geofence;
import javax.servlet.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import model.User;
import model.TechnicalStaff;

@WebServlet(urlPatterns = {"/GeofenceHomepageServlet"})
public class GeofenceHomepageServlet extends HttpServlet {

    private GeofenceHomepageDAO geofenceDAO;

    public void init() throws ServletException {
        geofenceDAO = new GeofenceHomepageDAO();
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
            out.println("<title>Servlet GeofenceHomepageServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GeofenceHomepageServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(false);
        TechnicalStaff staff = (TechnicalStaff) session.getAttribute("staff");

        if (staff == null) {
            response.sendRedirect("LogIn.jsp?error=Please log in first");
            return;
        }

        try {
            GeofenceHomepageDAO dao = new GeofenceHomepageDAO();
            List<Geofence> geofences = dao.getGeofencesByStaffId(staff.getStaffId());
            request.setAttribute("geofences", geofences);
            request.getRequestDispatcher("GeofenceHomepage.jsp").forward(request, response);
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

        HttpSession session = request.getSession(false);
        TechnicalStaff staff = (TechnicalStaff) (session != null ? session.getAttribute("staff") : null);

        if (staff == null) {
            response.sendRedirect("LogIn.jsp?error=Please log in first");
            return;
        }

        String action = request.getParameter("action");

        try {
            int staffId = staff.getStaffId(); // ✅ numeric staffId

            if ("add".equalsIgnoreCase(action)) {
                Geofence gf = new Geofence();
                gf.setGeofenceId(0); // auto-generated
                gf.setBoundaryName(request.getParameter("boundaryName"));
                gf.setLocationId(Integer.parseInt(request.getParameter("locationId")));
                gf.setLatitude(Double.parseDouble(request.getParameter("latitude")));
                gf.setLongitude(Double.parseDouble(request.getParameter("longitude")));
                gf.setRadius(Double.parseDouble(request.getParameter("radius")));
                gf.setStaffId(staffId);
                // gf.setClassId(classId); // optional if linking to class session

                geofenceDAO.insertGeofence(gf);

            } else if ("edit".equalsIgnoreCase(action)) {
                Geofence gf = new Geofence();
                gf.setGeofenceId(Integer.parseInt(request.getParameter("geofenceId")));
                gf.setBoundaryName(request.getParameter("boundaryName"));
                gf.setLocationId(Integer.parseInt(request.getParameter("locationId")));
                gf.setLatitude(Double.parseDouble(request.getParameter("latitude")));
                gf.setLongitude(Double.parseDouble(request.getParameter("longitude")));
                gf.setRadius(Double.parseDouble(request.getParameter("radius")));
                gf.setStaffId(staffId);
                // gf.setClassId(classId); // optional if linking to class session

                geofenceDAO.updateGeofence(gf);

            } else if ("delete".equalsIgnoreCase(action)) {
                int geofenceId = Integer.parseInt(request.getParameter("geofenceId"));
                boolean deleted = geofenceDAO.deleteGeofence(geofenceId, staffId);

                if (!deleted) {
                    request.setAttribute("error", "Failed to delete geofence.");
                    request.getRequestDispatcher("ErrorTechnicalStaff.jsp").forward(request, response);
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error processing geofence action: " + e.getMessage());
            request.getRequestDispatcher("ErrorTechnicalStaff.jsp").forward(request, response);
            return;
        }

        // ✅ Always redirect back to homepage after action
        response.sendRedirect("GeofenceHomepageServlet");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet for displaying geofence homepage";
    }
}
