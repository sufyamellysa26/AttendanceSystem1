<%@page import="model.ClassSession"%>
<%@page import="model.Attendance"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Class History</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #FFF8DC;
                display: flex;
                flex-direction: column;
                height: 100vh;
                margin: 0;
                overflow: hidden;
            }
            .header-panel {
                width: 100%;
                background: linear-gradient(to right, #E6E6FA, #FDEFF9);
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px 20px;
                box-shadow: 0 2px 6px rgba(0,0,0,0.1);
                border-bottom: 2px solid #D8BFD8;
            }
            .header-panel h1 {
                font-size: 20px;
                margin: 0;
                color: #4B0082;
            }
            .dashboard {
                display: flex;
                flex: 1;
            }
            .sidebar {
                width: 180px;
                background: #E6E6FA;
                border-top-right-radius: 20px;
                border-bottom-right-radius: 20px;
                padding: 30px 15px;
                box-shadow: 2px 0 8px rgba(0,0,0,0.1);
            }
            .sidebar nav {
                display: flex;
                flex-direction: column;
                gap: 20px;
            }
            .nav-item {
                color: #4B0082;
                text-decoration: none;
                font-weight: bold;
                padding: 10px 15px;
                background-color: #fff;
                border-radius: 12px;
                transition: all 0.3s ease;
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            }
            .nav-item:hover {
                background-color: #D8BFD8;
                transform: scale(1.05);
                box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            }
            .main-content {
                flex: 1;
                padding: 40px;
                overflow-y: auto; /* enables scrolling */
            }

            .history-section {
                display: flex;
                flex-direction: column;
                gap: 20px;
                max-height: calc(100vh - 160px); /* keep header visible */
                overflow-y: auto; /* scroll only the history list */
                padding-right: 10px; /* space for scrollbar */
            }

            .history-cube {
                background: linear-gradient(145deg, #ffffff, #e6e6fa);
                border-radius: 12px;
                padding: 20px;
                box-shadow: 8px 8px 16px #d1d1e0,
                    -8px -8px 16px #ffffff;
                font-size: 14px;
                color: #4B0082;
            }

            .attendance-item {
                background-color: #fff;
                border-radius: 8px;
                padding: 8px 12px;
                margin: 6px 0;
                box-shadow: inset 2px 2px 6px #d1d1e0,
                    inset -2px -2px 6px #ffffff;
                font-size: 13px;
            }

            .button-group {
                margin-top: 10px;
            }
            .button-group button {
                margin-right: 8px;
                padding: 6px 12px;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                font-size: 12px;
                font-weight: bold;
            }
            .btn-delete {
                background-color: crimson;
                color: white;
            }
            .btn-pdf {
                background-color: #4B0082;
                color: white;
            }
            .modal {
                display: none; /* hidden by default */
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0,0,0,0.4);
                justify-content: center;
                align-items: center;
            }
            .modal-content {
                background: #f3f3f5;
                padding: 20px;
                border-radius: 12px;
                text-align: center;
                color: #4B0082;
                box-shadow: 0 4px 12px rgba(0,0,0,0.2);
            }
            .btn-ok {
                background-color: crimson;
                color: white;
                margin: 10px;
                padding: 8px 16px;
                border: none;
                border-radius: 6px;
            }
            .btn-cancel {
                background-color: #D8BFD8;
                color: #4B0082;
                margin: 10px;
                padding: 8px 16px;
                border: none;
                border-radius: 6px;
            }
            .status.present {
                color: green;
                font-weight: bold;
            }
            .status.outside {
                color: orange;
                font-weight: bold;
            }
            .status.absent {
                color: red;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <%@ page import="model.User" %>
        <%
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("LogIn.jsp");
                return;
            }
        %>

        <div class="header-panel">
            <h1>Class Session History</h1>
        </div>

        <div class="dashboard">
            <!-- Sidebar -->
            <div class="sidebar">
                <nav>
                    <a href="RecordAttendanceLecturerServlet" class="nav-item">Record Attendance</a>
                    <a href="AttendanceHistoryLecturerServlet" class="nav-item active">History</a>
                    <a href="UserProfileLecturerServlet" class="nav-item">Profile</a>
                    <a href="SettingsLecturer.jsp" class="nav-item">Settings</a>
                </nav>
            </div>

            <!-- Main Content -->
            <div class="main-content">
                <div class="history-section">
                    <c:choose>
                        <c:when test="${not empty classSessions}">
                            <c:forEach var="session" items="${classSessions}">
                                <div class="history-cube">
                                    <h3>${session.subjectName} (${session.subjectCode})</h3>
                                    <p><strong>Location:</strong> ${session.buildingName} - ${session.roomName}</p>
                                    <p><strong>Start:</strong> ${session.startTime} | <strong>End:</strong> ${session.endTime}</p>
                                    <p><strong>Duration:</strong> ${session.duration} hours</p>

                                    <!-- Student Attendance Records -->
                                    <c:if test="${not empty session.attendanceRecords}">
                                        <h4>Student Attendance:</h4>
                                        <c:forEach var="att" items="${session.attendanceRecords}">
                                            <div class="attendance-item">
                                                👤 ${att.userId} — 
                                                <span class="status ${att.status}">${att.status}</span>
                                                (Recorded: ${att.timestamp})
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${empty session.attendanceRecords}">
                                        <p>No student attendance recorded yet.</p>
                                    </c:if>

                                    <!-- ✅ Action Buttons -->
                                    <div class="button-group">
                                        <!-- Redirect to ConfirmDelete.jsp instead of deleting immediately -->
                                        <form action="ConfirmDelete.jsp" method="post" style="display:inline;">
                                            <input type="hidden" name="classId" value="${session.classId}">
                                            <button type="submit" class="btn-delete">Delete</button>
                                        </form>

                                        <form action="PrintAttendanceServlet" method="post" style="display:inline;">
                                            <input type="hidden" name="classId" value="${session.classId}">
                                            <button type="submit" class="btn-pdf">Print PDF</button>
                                        </form>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p style="color:#4B0082; font-weight:bold;">No class sessions found for this lecturer.</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </body>
</html>
