<%-- 
    Document   : UserProfileLecturer
    Created on : 7 Jun 2026, 12:28:22 AM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lecturer Profile</title>
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
                position: relative;
                z-index: 10;
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
                overflow-y: auto;
            }

            .form-cube {
                background: #fff;
                border-radius: 12px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
                padding: 30px;
                max-width: 600px;
                margin: auto;
                text-align: center;
            }

            .form-cube h2 {
                color: #4B0082;
                margin-bottom: 12px;        /* tighter spacing */
            }

            .form-cube img {
                border-radius: 50%;
                margin-bottom: 15px;
            }

            .profile-info p {
                font-size: 15px;
                color: #333;
                margin: 5px 0;              /* tighter spacing between lines */
            }

            .update-form {
                margin-top: 12px;           /* reduce gap before file input */
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
                margin-top: 10px;           /* small gap above button */
            }

            .edit-button:hover {
                background-color: #800080;
            }
        </style>
    </head>
    <body>
        <div class="header-panel">
            <h1>Lecturer Dashboard</h1>
        </div>

        <div class="dashboard">
            <!-- Sidebar -->
            <div class="sidebar">
                <nav>
                    <a href="RecordAttendanceLecturerServlet" class="nav-item">Record Attendance</a>
                    <a href="AttendanceHistoryLecturerServlet" class="nav-item">History</a>
                    <a href="UserProfileLecturerServlet" class="nav-item active">Profile</a>
                    <a href="SettingsLecturer.jsp" class="nav-item">Settings</a>
                </nav>
            </div>

            <!-- Main Content -->
            <div class="main-content">
                <div class="form-cube">
                    <h2>Lecturer Profile</h2>
                    <c:set var="lecturer" value="${lecturerProfile}" />

                    <!-- Profile Info -->
                    <div class="profile-info">
                        <img src="${lecturer.profilePicture}" alt="Profile Picture" width="150" height="150">
                        <p><strong>Name:</strong> ${lecturer.name}</p>
                        <p><strong>Department:</strong> ${lecturer.department}</p>
                        <p><strong>Subject Code:</strong> ${lecturer.subjectCode}</p>
                        <p><strong>Subject Name:</strong> ${lecturer.subjectName}</p>
                    </div>

                    <!-- Profile Picture Update -->
                    <form action="EditProfileLecturerServlet" method="post" enctype="multipart/form-data" class="update-form">
                        <label for="profilePicture">Change Profile Picture:</label>
                        <input type="file" name="profilePicture" id="profilePicture" accept="image/*" required>
                        <button type="submit" class="edit-button">Update Profile Picture</button>
                    </form>
                </div>
            </div>
        </div>

        <script>
            function logout() {
                if (confirm("Do you want to log out?")) {
                    window.location.href = "LogIn.jsp";
                }
            }
        </script>
    </body>
</html>
