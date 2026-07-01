<%-- 
    Document   : AttendanceHistoryStudent
    Author     : USER
--%>

<%@ page import="java.util.List" %>
<%@ page import="model.ClassSession" %>
<%@ page import="model.Attendance" %>
<%@ page import="model.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Attendance History</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #FFF8DC;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }

            .container {
                width: 375px;
                height: 620px;
                background-color: #fff;
                border-radius: 12px;
                box-shadow: 0 0 10px rgba(0,0,0,0.2);
                padding: 20px;
                display: flex;
                flex-direction: column;
            }

            .header-panel {
                display: flex;
                justify-content: space-between;
                align-items: center;
                background-color: #E6E6FA;
                padding: 10px 15px;
                border-radius: 10px;
                box-shadow: 0 0 5px rgba(0,0,0,0.1);
            }

            .icon-left, .icon-right {
                width: 28px;
                height: 28px;
                object-fit: contain;
            }

            h2 {
                text-align: center;
                color: #4B0082;
                margin: 20px 0 10px;
            }
            .card-section {
                flex: 1;
                overflow-y: auto;
                margin-bottom: 20px;
            }
            .card {
                background-color: #f2f2f2;
                padding: 12px;
                border-radius: 10px;
                margin-bottom: 12px;
                box-shadow: 0 0 5px rgba(0,0,0,0.1);
            }
            .card h3 {
                margin: 0;
                color: #4B0082;
                font-size: 14px;
            }
            .card p {
                margin: 5px 0;
                font-size: 12px;
            }
            .nav-cube {
                background-color: #E6E6FA;
                border-radius: 10px;
                box-shadow: 0 0 8px rgba(0,0,0,0.15);
                padding: 12px;
            }

            .nav {
                display: flex;
                justify-content: space-around;
                align-items: center;
                font-size: 12px;
                color: #4B0082; /* Deep purple text */
            }

            .nav span {
                padding: 6px 10px;
                border-radius: 6px;
                transition: all 0.3s ease;
                color: #4B0082;
            }

            .nav span:hover {
                background-color: #D8BFD8; /* Light purple hover */
                color: #800080;
                border: 1px solid #4B0082;
                cursor: pointer;
                transform: scale(1.05);
                box-shadow: 0 0 5px rgba(0,0,0,0.2);
            }

            .nav span.active {
                border: 2px solid #4B0082; /* Purple border for active tab */
                background-color: transparent;
                font-weight: bold;
            }

            .nav a {
                text-decoration: none;
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
                response.sendRedirect("LogIn.jsp"); // force login if session expired
                return;
            }
            List<Attendance> attendanceHistory = (List<Attendance>) request.getAttribute("attendanceHistory");
        %>


        <div class="container">

            <!-- Header Panel -->
            <div class="header-panel">
                <a href="SettingStudent.jsp" style="text-decoration:none; font-size:20px; color:#4B0082;">⚙️</a>
                <img src="images.png" alt="UMT Logo" class="icon-right" />
            </div>

            <!-- Title -->
            <h2>Attendance History</h2>

            <!-- Attendance Cards -->
            <div class="card-section">
                <%
                    if (attendanceHistory != null && !attendanceHistory.isEmpty()) {
                        for (Attendance att : attendanceHistory) {
                            String statusClass = att.getStatus().equalsIgnoreCase("present") ? "present"
                                    : att.getStatus().equalsIgnoreCase("outside") ? "outside"
                                    : "absent";
                %>
                <div class="card">
                    <h3><%= att.getSubjectName()%> (<%= att.getSubjectCode()%>)</h3>
                    <p><strong>Location:</strong> <%= att.getBuildingName()%> - <%= att.getRoomName()%></p>
                    <p><strong>Date:</strong> <%= att.getStartTime().toLocalDate()%></p>
                    <p><strong>Time:</strong> <%= att.getStartTime().toLocalTime()%> → <%= att.getEndTime().toLocalTime()%></p>
                    <p><strong>Status:</strong> <span class="status <%= statusClass%>"><%= att.getStatus()%></span></p>
                    <p><strong>Recorded At:</strong> <%= att.getTimestamp()%></p>
                    <p><strong>Coordinates:</strong> Lat: <%= att.getLatitude()%>, Lon: <%= att.getLongitude()%></p>
                </div>
                <%
                    }
                } else {
                %>
                <div class="card">
                    <h3>No Attendance Records Found</h3>
                    <p>You have not recorded any attendance yet.</p>
                </div>
                <%
                    }
                %>
            </div>

            <!-- Navigation Panel -->
            <div class="nav-cube">
                <div class="nav">
                    <a href="RecordAttendanceStudent.jsp"><span>Record</span></a>
                    <a href="AttendanceHistoryStudentServlet"><span class="active">History</span></a>
                    <a href="UserProfileStudent.jsp"><span>Profile</span></a>
                </div>
            </div>
        </div>
    </body>
</html>
