<%-- 
    Document   : RecordAttendanceLecturer
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Set Up Class</title>
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

            input, select {
                padding: 8px;
                width: 100%;
                border: 1px solid #ccc;
                border-radius: 8px;
                margin-top: 5px;
                margin-bottom: 15px; /* replaces <br><br> */
            }

            .btn-setup {
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

            .btn-setup:hover {
                background-color: #800080;
            }

            /* Success Modal */
            .modal {
                display: none;
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
                background-color: #4B0082;
                color: white;
                margin: 10px;
                padding: 8px 16px;
                border: none;
                border-radius: 6px;
                font-weight: bold;
            }
            .btn-cancel {
                background-color: #D8BFD8;
                color: #4B0082;
                margin: 10px;
                padding: 8px 16px;
                border: none;
                border-radius: 6px;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div class="header-panel">
            <h1>Set Up Class Boundaries</h1>
        </div>

        <div class="dashboard">
            <!-- Sidebar -->
            <div class="sidebar">
                <nav>
                    <a href="RecordAttendanceLecturerServlet" class="nav-item active">Record Attendance</a>
                    <a href="AttendanceHistoryLecturerServlet" class="nav-item">History</a>
                    <a href="UserProfileLecturerServlet" class="nav-item">Profile</a>
                    <a href="SettingsLecturer.jsp" class="nav-item">Settings</a>
                </nav>
            </div>

            <!-- Main Content -->
            <div class="main-content">
                <div class="form-cube">
                    <h2>Set Up Class Boundaries</h2>
                    <form method="post" action="RecordAttendanceLecturerServlet">
                        <!-- Subject Code -->
                        <!-- Subject Code Dropdown -->
                        <label for="subjectCode">Subject Code:</label>
                        <select name="subjectCode" id="subjectCode" required>
                            <c:forEach var="code" items="${subjectCodes}">
                                <option value="${code}">${code}</option>
                            </c:forEach>
                        </select>


                        <!-- Geofence Dropdown -->
                        <label for="geofence">Choose Geofence:</label>
                        <select name="geofence" id="geofence" required>
                            <c:forEach var="geo" items="${geofenceList}">
                                <option value="${geo.geofenceId}">
                                    ${geo.boundaryName}
                                </option>
                            </c:forEach>
                        </select>

                        <!-- Duration Dropdown -->
                        <label for="duration">Duration:</label>
                        <select name="duration" id="duration" required>
                            <option value="1">1 Hour</option>
                            <option value="2">2 Hours</option>
                            <option value="3">3 Hours</option>
                        </select><br><br>

                        <!-- Setup Class Button -->
                        <button type="submit" class="btn-setup">Set Up Class</button>
                    </form>
                </div>
            </div>
        </div>

        <!-- Success Modal -->
        <div id="successModal" class="modal">
            <div class="modal-content">
                <h3>Class session set up successfully!</h3>
                <form method="get" action="AttendanceHistoryLecturerServlet">
                    <button type="submit" class="btn-ok">Go to History</button>
                </form>
                <button type="button" class="btn-cancel" onclick="closeSuccessModal()">Stay Here</button>
            </div>
        </div>

        <script>
            function closeSuccessModal() {
                document.getElementById("successModal").style.display = "none";
            }

            // Auto-show modal if success flag is set
        </script>

        <c:if test="${success == true}">
            <script>
                document.getElementById("successModal").style.display = "flex";
                // Auto-close after 3 seconds and redirect
                setTimeout(function () {
                    window.location.href = "AttendanceHistoryLecturerServlet";
                }, 3000);
            </script>
        </c:if>
    </body>
</html>
