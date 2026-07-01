<%-- 
    Document   : GeofenceHomepage
    Created on : 16 May 2026, 10:08:35 PM
    Author     : USER
--%>

<%@ page import="java.util.*, model.Geofence" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Geofence Management</title>
        <link rel="stylesheet" href="styles.css"> 
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

            .btn-add {
                background: #4B0082;
                color: white;
                padding: 8px 12px;
                border: none;
                border-radius: 8px;
                cursor: pointer;
                font-weight: bold;
                margin-top: 15px;
            }

            .btn-add:hover {
                background-color: #800080;
            }

            .geofence-list {
                margin-top: 20px;
            }

            .geofence-item {
                background-color: #f3f3f5;
                border-radius: 10px;
                padding: 15px;
                box-shadow: 0 0 5px rgba(0,0,0,0.1);
                margin-bottom: 10px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                color: #4B0082;
            }

            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown span {
                font-size: 20px;
                cursor: pointer;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                right: 0;
                background-color: #fff;
                min-width: 120px;
                box-shadow: 0 2px 6px rgba(0,0,0,0.2);
                border-radius: 8px;
                z-index: 1;
            }

            .dropdown-content a {
                display: block;
                padding: 8px 12px;
                text-decoration: none;
                font-size: 13px;
            }

            .dropdown-content a.edit {
                color: orange;
                font-weight: bold;
            }

            .dropdown-content a.delete {
                color: red;
                font-weight: bold;
            }

            .dropdown:hover .dropdown-content {
                display: block;
            }

            /* Popup styling */
            .popup {
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background: #fff;
                border: 1px solid #ccc;
                padding: 20px;
                z-index: 1000;
                border-radius: 12px;
                box-shadow: 0 0 10px rgba(0,0,0,0.2);
                text-align: center;
            }

            .popup button {
                padding: 8px 14px;
                border: none;
                border-radius: 8px;
                cursor: pointer;
                font-weight: bold;
                margin: 5px;
            }

            .popup .delete-btn {
                background: red;
                color: white;
            }

            .popup .cancel-btn {
                background: orange;
                color: white;
            }

            .btn-logout {
                position: absolute;
                right: 20px;
                background: crimson;
                color: white;
                border: none;
                padding: 8px 14px;
                border-radius: 8px;
                cursor: pointer;
                font-weight: bold;
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
        </style><!-- keep your CSS -->
    </head>
    <body>

        <%
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("LogIn.jsp?error=PleaseLogin");
        return;
    }
%>

        <%@ page import="model.User" %>

        <!-- ✅ Navigation panel -->
        <div class="nav-panel">
            <ul>
                <li><a href="UserProfileTechnicalServlet" class="<%= request.getRequestURI().contains("ProfileServlet") ? "active" : ""%>">Profile</a></li>
                <li><a href="GeofenceHomepageServlet" class="<%= request.getRequestURI().contains("GeofenceHomepageServlet") ? "active" : ""%>">Geofence</a></li>
                <li><a href="SettingTechnicalStaff.jsp" class="<%= request.getRequestURI().contains("SettingsServlet") ? "active" : ""%>">Settings</a></li>
            </ul>
        </div>


        <div class="header-panel">
            <h1>Geofence Management</h1>
        </div>

        <div class="main-content">
            <div class="form-cube">
                <h2>Geofence Boundaries</h2>

                <button class="btn-add" onclick="window.location.href = 'AddGeofenceServlet'">
                    + Add Geofence
                </button>

                <div class="geofence-list">
                    <%
                        List<Geofence> geofences = (List<Geofence>) request.getAttribute("geofences");
                        if (geofences != null) {
                            for (Geofence gf : geofences) {
                    %>
                    <div class="geofence-item">
                        <span><%= gf.getBoundaryName()%> (Radius: <%= gf.getRadius()%>m)</span>
                        <div class="dropdown">
                            <span>⋮</span>
                            <div class="dropdown-content">
                                <a href="EditGeofenceServlet?geofenceId=<%= gf.getGeofenceId()%>" class="edit">Edit</a>

                                <a href="ConfirmDeleteTechnical.jsp?geofenceId=<%= gf.getGeofenceId()%>">Delete</a>
                            </div>
                        </div>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>

        <script>
            function confirmDelete(id) {
                if (confirm("Are you sure you want to delete this geofence?")) {
                    var form = document.createElement("form");
                    form.method = "post";
                    form.action = "GeofenceHomepageServlet";

                    var actionInput = document.createElement("input");
                    actionInput.type = "hidden";
                    actionInput.name = "action";
                    actionInput.value = "delete";
                    form.appendChild(actionInput);

                    var idInput = document.createElement("input");
                    idInput.type = "hidden";
                    idInput.name = "geofenceId";
                    idInput.value = id;
                    form.appendChild(idInput);

                    document.body.appendChild(form);
                    form.submit();
                }
            }
        </script>
    </body>
</html>
