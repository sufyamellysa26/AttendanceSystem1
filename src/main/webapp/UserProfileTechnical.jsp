<%-- 
    Document   : UserProfileTechnical
    Created on : 28 Jun 2026, 3:00:27 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Technical Staff Profile</title>
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

            .profile-container {
                max-width: 600px;
                margin: 80px auto;
                background: #fff;
                padding: 40px;
                border-radius: 16px;
                box-shadow: 0 6px 18px rgba(0,0,0,0.15);
            }

            .header-panel {
                width: 100%;
                background: linear-gradient(to right, #E6E6FA, #FDEFF9);
                display: flex;
                justify-content: center;
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

            h2 {
                color: #4B0082;
                margin-bottom: 20px;
                text-align: center;
            }

            .profile-item {
                margin: 12px 0;
                font-size: 16px;
            }

            .profile-item strong {
                color: #800080;
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
        </style>
    </head>
    <body>
        <!-- ✅ Navigation panel -->
        <div class="nav-panel">
            <ul>
                <li><a href="UserProfileTechnicalServlet" class="active">Profile</a></li>
                <li><a href="GeofenceHomepageServlet">Geofence</a></li>
                <li><a href="SettingTechnicalStaff.jsp">Settings</a></li>
            </ul>
        </div>

        <div class="profile-container">
            <h2>Technical Staff Profile</h2>
            <div class="profile-item"><strong>ID:</strong> ${staff.staffId}</div>
            <div class="profile-item"><strong>Name:</strong> ${staff.staffName}</div>
            <div class="profile-item"><strong>Role:</strong> ${staff.staffRole}</div>
        </div>
    </body>
</html>
