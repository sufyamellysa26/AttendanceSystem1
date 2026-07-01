<%-- 
    Document   : UserProfileStudent
    Author     : USER
--%>

<%@ page import="model.Student" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
        <%
            Student student = (Student) session.getAttribute("student");
        %>

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
                justify-content: space-between;
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

            .profile-top {
                text-align: center;
                margin: 20px 0 10px;
            }

            .profile-icon {
                width: 60px;
                height: 60px;
                border-radius: 50%;
                background-color: #D8BFD8;
                display: inline-block;
                margin-bottom: 8px;
            }

            .welcome {
                font-size: 14px;
                color: #4B0082;
                font-weight: bold;
            }

            .profile-section {
                flex: 1;
                display: flex;
                flex-direction: column;
                gap: 12px;
                overflow-y: auto;
            }

            .profile-cube {
                background-color: #f3f3f5;
                border-radius: 10px;
                padding: 12px;
                box-shadow: 0 0 5px rgba(0,0,0,0.1);
                font-size: 14px;
                color: #4B0082;
            }

            .profile-cube strong {
                display: block;
                font-size: 13px;
                margin-bottom: 4px;
            }

            .subjects-list {
                margin-top: 8px;
                padding-left: 20px;
            }

            .subjects-list li {
                font-size: 13px;
                margin-bottom: 4px;
                color: #4B0082;
            }

            .edit-button {
                padding: 10px 20px;
                background-color: #4B0082;
                color: white;
                border: none;
                border-radius: 8px;
                font-size: 14px;
                font-weight: bold;
                transition: background-color 0.3s ease;
                cursor: pointer;
                margin-top: 5px;
                display: inline-block;    /* ✅ Prevent full-width stretching */
            }

            .edit-button:hover {
                background-color: #800080;
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

            .error {
                color: crimson;
                font-weight: bold;
                text-align: center;
            }
        </style>
    </head>
    <body>

        <%
            // ✅ Safely get student from session
            student = (Student) session.getAttribute("student");
            if (student == null) {
                response.sendRedirect("LogIn.jsp?error=Please log in as student");
                return;
            }
        %>

        <div class="container">

            <!-- Header Panel -->
            <div class="header-panel">
                <a href="SettingStudent.jsp" style="text-decoration:none; font-size:20px; color:#4B0082;">⚙️</a>
                <img src="images.png" alt="UMT Logo" class="icon-right" />
            </div>

            <!-- Profile Icon + Welcome -->
            <div class="profile-top">
                <div class="profile-icon">
                    <c:set var="student" value="${profileProfile}" />
                </div>
                <div class="welcome">
                    Welcome, <%= student.getMatric()%>
                </div>
            </div>

            <!-- Profile Section -->
            <div class="profile-section">
                <div class="profile-cube"><strong>NAME</strong> <%= student.getName()%></div>
                <div class="profile-cube"><strong>MATRIC NUMBER</strong> <%= student.getMatric()%></div>
                <div class="profile-cube"><strong>COURSE PROGRAM</strong> <%= student.getCourseProgram()%></div>
                <div class="profile-cube"><strong>SUBJECTS THIS SEMESTER</strong>
                    <ul>
                        <%
                            List<String> subjects = student.getSubjects();
                            if (subjects != null) {
                                for (String subj : subjects) {
                        %>
                        <li><%= subj%></li>
                            <%
                                }
                            } else {
                            %>
                        <li>No subjects found</li>
                            <% }%>
                    </ul>
                </div>
            </div>

            <!-- Only profile picture editable -->
            <form action="UploadProfilePictureServlet" method="post" enctype="multipart/form-data" class="update-form"style="text-align: center">
                <input type="file" name="profilePic" id="profilePic" accept="image/*" required style="text-align: center"/>
                <button type="submit" class="edit-button" style="text-align: center">Update Picture</button>
            </form>

            <!-- Navigation Panel -->
            <div class="nav-cube">
                <div class="nav">
                    <a href="RecordAttendanceStudent.jsp"><span>Record</span></a>
                    <a href="AttendanceHistoryStudentServlet"><span>History</span></a>
                    <a href="UserProfileStudent.jsp"><span class="active">Profile</span></a>
                </div>
            </div>
        </div>
    </body>
</html>
