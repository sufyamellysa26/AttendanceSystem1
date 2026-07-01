<%-- 
    Document   : EditProfileLecturer
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Lecturer Profile</title>
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
            }

            .form-cube h2 {
                text-align: center;
                color: #4B0082;
                margin-bottom: 20px;
            }

            label {
                display: block;
                margin-top: 12px;
                font-weight: bold;
                color: #4B0082;
            }

            input {
                padding: 8px;
                width: 100%;
                border: 1px solid #ccc;
                border-radius: 8px;
                margin-top: 5px;
            }

            .btn-update {
                background: #4B0082;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 8px;
                font-size: 14px;
                font-weight: bold;
                margin-top: 20px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .btn-update:hover {
                background-color: #800080;
            }
        </style>
    </head>
    <body>
        <div class="header-panel">
            <h1>Edit Lecturer Profile</h1>
        </div>

        <div class="dashboard">
            <!-- Sidebar -->
            <div class="sidebar">
                <nav>
                    <a href="RecordAttendanceLecturer.jsp" class="nav-item">Record Attendance</a>
                    <a href="AttendanceHistoryLecturerServlet" class="nav-item">History</a>
                    <a href="UserProfileLecturerServlet" class="nav-item">Profile</a>
                    <a href="SettingsLecturer.jsp" class="nav-item">Settings</a>
                </nav>
            </div>

            <!-- Main Content -->
            <div class="main-content">
                <div class="form-cube">
                    <h2>Edit Profile</h2>
                    <form method="post" action="EditProfileLecturerServlet">
                        <!-- Hidden field for lecturer ID -->
                        <input type="hidden" name="lecturerId" value="<%= request.getAttribute("lecturerId")%>">

                        <label>Full Name:</label>
                        <input type="text" name="name" value="<%= request.getAttribute("lecturerName")%>" readonly>

                        <label>Staff ID:</label>
                        <input type="text" name="staffId" value="<%= request.getAttribute("lecturerId")%>" readonly>

                        <label>Department:</label>
                        <input type="text" name="department" value="<%= request.getAttribute("department")%>" required>

                        <label>Subject Code:</label>
                        <input type="text" name="subjectCode" value="<%= request.getAttribute("subjectCode")%>" required>

                        <label>Subject Name:</label>
                        <input type="text" name="subjectName" value="<%= request.getAttribute("subjectName")%>" required>

                        <button type="submit" class="btn-update">Update Profile</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
