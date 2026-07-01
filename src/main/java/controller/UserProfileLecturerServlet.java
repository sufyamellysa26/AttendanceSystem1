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
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import dao.LecturerDAO;
import model.Lecturer;

/**
 *
 * @author USER
 */
@WebServlet(name = "UserProfileLecturerServlet", urlPatterns = {"/UserProfileLecturerServlet"})
public class UserProfileLecturerServlet extends HttpServlet {

    private LecturerDAO lecturerDAO = new LecturerDAO();

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
            out.println("<title>Servlet UserProfileLecturerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserProfileLecturerServlet at " + request.getContextPath() + "</h1>");
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
        String idParam = request.getParameter("id"); // get ?id= from URL
        HttpSession session = request.getSession(false);
        Lecturer lecturer = (session != null) ? (Lecturer) session.getAttribute("lecturer") : null;

        LecturerDAO dao = new LecturerDAO();
        Lecturer profile = null;
        int lecturerId;

        try {
            if (idParam != null) {
                // ✅ If ?id= is provided, use that
                lecturerId = Integer.parseInt(idParam);
                profile = dao.getLecturerById(lecturerId);
            } else if (lecturer != null) {
                // ✅ Otherwise, use logged-in lecturer
                lecturerId = lecturer.getLecturerId();
                profile = dao.getLecturerById(lecturerId);
            } else {
                // ✅ Fallback: default lecturer (ID 1)
                lecturerId = 1;
                profile = dao.getLecturerById(lecturerId);
            }

            if (profile == null) {
                throw new ServletException("No lecturer found with ID " + lecturerId);
            }

            request.setAttribute("lecturerProfile", profile);
            RequestDispatcher rd = request.getRequestDispatcher("UserProfileLecturer.jsp");
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
        response.sendRedirect("UserProfileLecturerServlet");
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
