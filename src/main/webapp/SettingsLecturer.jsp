<%-- 
    Document   : SettingsLecturer
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Settings</title>
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
                max-width: 500px;
                margin: auto;
            }

            .form-cube h2 {
                text-align: center;
                color: #4B0082;
                margin-bottom: 20px;
            }

            label {
                display: block;
                margin-top: 15px;
                font-weight: bold;
                color: #4B0082;
            }

            select, button {
                padding: 8px;
                margin-top: 5px;
                width: 100%;
                border-radius: 8px;
                border: 1px solid #ccc;
            }

            .btn-save {
                background: #4B0082;
                color: white;
                border: none;
                font-weight: bold;
                cursor: pointer;
                transition: background-color 0.3s ease;
                margin-top: 20px;
            }

            .btn-save:hover {
                background-color: #800080;
            }

            .btn-back {
                background: gray;
                color: white;
                border: none;
                font-weight: bold;
                cursor: pointer;
                margin-top: 10px;
            }

            .btn-back:hover {
                background-color: #555;
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
            <h1>Settings</h1>
        </div>

        <div class="dashboard">
            <!-- Sidebar -->
            <div class="sidebar">
                <nav>
                    <a href="RecordAttendanceLecturerServlet" class="nav-item">Record Attendance</a>
                    <a href="AttendanceHistoryLecturerServlet" class="nav-item">History</a>
                    <a href="UserProfileLecturerServlet" class="nav-item">Profile</a>
                    <a href="SettingsLecturer.jsp" class="nav-item">Settings</a>
                </nav>
            </div>


            <!-- Main Content -->
            <div class="main-content">
                <div class="form-cube">
                    <h2>Settings</h2>

                    <!-- Logout button triggers modal -->
                    <button type="button" class="btn-save" onclick="openLogoutModal()">Logout</button>

                    <!-- Modal structure -->
                    <div id="logoutModal" class="modal">
                        <div class="modal-content">
                            <h3>Are you sure you want to log out?</h3>
                            <form method="get" action="LogoutServlet" style="display:inline;">
                                <button type="submit" class="btn-ok">OK</button>
                            </form>
                            <button type="button" class="btn-cancel" onclick="closeLogoutModal()">Cancel</button>
                        </div>
                    </div>

                    <script>
                        function openLogoutModal() {
                            document.getElementById("logoutModal").style.display = "flex";
                        }
                        function closeLogoutModal() {
                            document.getElementById("logoutModal").style.display = "none";
                        }
                    </script>
                </div>
            </div>
        </div>
    </body>
</html>
