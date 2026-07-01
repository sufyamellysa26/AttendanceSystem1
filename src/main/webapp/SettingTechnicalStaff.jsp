<%-- 
    Document   : SettingTechnicalStaff
    Created on : 28 Jun 2026, 2:47:21 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Technical Staff Settings</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #FFF8DC;
                margin: 0;
                padding: 0;
            }

            .settings-container {
                max-width: 600px;
                margin: 80px auto;
                background: #fff;
                padding: 40px;
                border-radius: 16px;
                box-shadow: 0 6px 18px rgba(0,0,0,0.15);
                text-align: center;
            }

            h2 {
                color: #4B0082;
                margin-bottom: 20px;
            }

            .btn-logout {
                background: crimson;
                color: #fff;
                border: none;
                padding: 12px 20px;
                border-radius: 8px;
                font-size: 16px;
                font-weight: bold;
                cursor: pointer;
                transition: background 0.3s ease;
            }

            .btn-logout:hover {
                background: darkred;
            }

            .nav-panel {
                width: 100%;
                background: linear-gradient(to right, #E6E6FA, #FDEFF9);
                border-bottom: 2px solid #D8BFD8;
                box-shadow: 0 2px 6px rgba(0,0,0,0.1);
                position: sticky;
                top: 0;
                z-index: 100;
            }

            .nav-panel ul {
                list-style: none;
                margin: 0;
                padding: 10px 20px;
                display: flex;
                justify-content: center;
                gap: 40px;
            }

            .nav-panel ul li {
                display: inline;
                position: relative;
            }

            .nav-panel ul li a {
                text-decoration: none;
                font-weight: bold;
                color: #4B0082;
                font-size: 16px;
                padding: 8px 12px;
                transition: all 0.3s ease;
                border-radius: 6px;
            }

            /* Hover effect */
            .nav-panel ul li a:hover {
                color: #fff;
                background-color: #800080;
            }

            /* Active page highlight */
            .nav-panel ul li a.active {
                color: #fff;
                background-color: #4B0082;
                box-shadow: 0 2px 6px rgba(0,0,0,0.2);
            }
            .nav-panel ul li a:hover::after {
                width: 100%;
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
        <script>
            function confirmLogout() {
                if (confirm("Are you sure you want to log out?")) {
                    window.location.href = "LogoutTechnicalServlet";
                }
            }
        </script>
    </head>
    <body>
        <!-- ✅ Navigation panel -->
        <div class="nav-panel">
            <ul>
                <li><a href="UserProfileTechnicalServlet">Profile</a></li>
                <li><a href="GeofenceHomepageServlet">Geofence</a></li>
                <li><a href="SettingTechnicalStaff.jsp" class="active">Settings</a></li>
            </ul>
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
    </body>
</html>
