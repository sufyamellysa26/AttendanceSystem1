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

import dao.UserDAO;
import dao.LecturerDAO;
import dao.StudentDAO;
import dao.TechnicalStaffDAO;
import java.sql.SQLException;
import model.User;
import model.Lecturer;
import model.Student;
import model.TechnicalStaff;

@WebServlet(name = "LogInServlet", urlPatterns = {"/LogInServlet"})
public class LogInServlet extends HttpServlet {
    
    private UserDAO userDao = new UserDAO();
    private StudentDAO studentDao = new StudentDAO(); // ✅ use fixed StudentDAO
    
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
            out.println("<title>Servlet LogInLecturerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LogInLecturerServlet at " + request.getContextPath() + "</h1>");
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
        
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        try {
            // ✅ Validate login
            User user = userDao.validateLogin(userId, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                // ✅ Redirect based on role
                switch (user.getRole().toLowerCase()) {
                    case "student":
                        Student student = studentDao.getStudentByUserId(userId);
                        if (student != null) {
                            user.setStudentId(student.getId());
                            session.setAttribute("student", student);
                        }
                        response.sendRedirect("AttendanceHistoryStudentServlet");
                        break;

                    case "lecturer":
                        Lecturer lecturer = userDao.getLecturerByUserId(userId);
                        if (lecturer != null) {
                            user.setLecturerId(lecturer.getLecturerId());
                            session.setAttribute("lecturer", lecturer);
                        }
                        response.sendRedirect("AttendanceHistoryLecturerServlet");
                        break;

                    case "technicalstaff":   // ✅ matches DAO + DB
                        TechnicalStaff staff = userDao.getStaffByUserId(userId);
                        if (staff != null) {
                            user.setStaffId(staff.getStaffId());
                            session.setAttribute("technicalstaff", staff);
                        }
                        response.sendRedirect("GeofenceHomepageServlet");
                        break;
                    default:
                        response.sendRedirect("LogIn.jsp?error=UnknownRole");
                        break;
                }
            } else {
                response.sendRedirect("LogIn.jsp?error=Invalid credentials");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("LogIn.jsp?error=ServerError");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
         return "Handles login and redirects based on role";
    }// </editor-fold>

}
