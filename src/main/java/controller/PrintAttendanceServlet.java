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

import dao.AttendanceLecturerDAO;
import dao.ClassSessionDAO;
import java.util.List;
import model.Attendance;
import model.AttendanceLecturer;
import model.ClassSession;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName;

/**
 *
 * @author USER
 */
@WebServlet(name = "PrintAttendanceServlet", urlPatterns = {"/PrintAttendanceServlet"})
public class PrintAttendanceServlet extends HttpServlet {

    private ClassSessionDAO classSessionDAO;

    @Override
    public void init() throws ServletException {
        // Initialize DAO once when servlet starts
        classSessionDAO = new ClassSessionDAO();
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
            out.println("<title>Servlet PrintAttendanceServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PrintAttendanceServlet at " + request.getContextPath() + "</h1>");
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
        try {
            int classId = Integer.parseInt(request.getParameter("classId"));
            ClassSession session = classSessionDAO.getClassSessionById(classId);

            // Set response headers
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=session_" + classId + ".pdf");

            // Create PDF document
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream content = new PDPageContentStream(document, page);

            content.beginText();
            content.setFont(new PDType1Font(FontName.HELVETICA_BOLD), 16);// font + size
            content.setLeading(20f);                         // line spacing
            content.newLineAtOffset(50, 700);
            content.showText("Class Session Report");
            content.newLine();

            content.setFont(new PDType1Font(FontName.HELVETICA), 12);
            content.showText("Subject: " + session.getSubjectName() + " (" + session.getSubjectCode() + ")");
            content.newLine();
            content.showText("Location: " + session.getBuildingName() + " - " + session.getRoomName());
            content.newLine();
            content.showText("Start: " + session.getStartTime() + " | End: " + session.getEndTime());
            content.newLine();
            content.showText("Duration: " + session.getDuration() + " hours");
            content.newLine();
            content.newLine();
            content.showText("Student Attendance:");
            content.newLine();

            for (Attendance att : session.getAttendanceRecords()) {
                content.showText(att.getUserId() + " — " + att.getStatus() + " (Recorded: " + att.getTimestamp() + ")");
                content.newLine();
            }

            content.endText();
            content.close();

            // Write PDF to response
            document.save(response.getOutputStream());
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/plain");
            response.getWriter().write("Error generating PDF: " + e.getMessage());
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
