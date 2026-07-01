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
import java.util.Arrays;

import dao.StudentDAO;
import java.util.List;
import javax.servlet.http.HttpSession;
import model.Student;

/**
 *
 * @author USER
 */
@WebServlet(name = "EditProfileStudentServlet", urlPatterns = {"/EditProfileStudentServlet"})
public class EditProfileStudentServlet extends HttpServlet {

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
            out.println("<title>Servlet EditProfileStudentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProfileStudentServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        String matric = request.getParameter("matric");

        if (matric == null || matric.isEmpty()) {
            response.sendRedirect("LogInStudent.jsp");
            return;
        }

        try {
            String courseProgram = request.getParameter("course");
            String subjectsStr = request.getParameter("subjects");
            List<String> subjects = Arrays.asList(subjectsStr.split(","));
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            Student student = new Student();
            student.setMatric(matric);
            student.setCourseProgram(courseProgram);
            student.setSubjects(subjects);
            student.setPhone(phone);
            student.setAddress(address);

            StudentDAO dao = new StudentDAO();
            boolean updated = dao.updateStudentProfile(student);

            if (updated) {
                session.setAttribute("student", dao.getStudentProfile(matric));
                response.sendRedirect("UserProfileStudent.jsp");
            } else {
                response.getWriter().println("<h3>❌ Failed to update profile.</h3>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3>❌ Error updating profile.</h3>");
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
