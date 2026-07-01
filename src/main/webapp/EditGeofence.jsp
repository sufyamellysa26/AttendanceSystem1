<%-- 
    Document   : EditGeofence
    Created on : 19 May 2026, 2:46:19 AM
    Author     : USER
--%>

<%@ page import="java.util.*, model.Geofence, model.Location" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Geofence</title>
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
            }

            .btn-update {
                background: orange;
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
                background-color: darkorange;
            }

            .btn-back {
                background: gray;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 8px;
                font-size: 14px;
                font-weight: bold;
                margin-top: 10px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .btn-back:hover {
                background-color: #555;
            }

            .button-group {
                display: flex;
                justify-content: space-between;
                gap: 10px;
            }
        </style>
    </head>
    <body>
        <div class="header-panel">
            <h1>Edit Geofence Boundary</h1>
        </div>

        <div class="main-content">
            <div class="form-cube">
                <h2>Edit Geofence Boundary</h2>
                <form method="post" action="GeofenceHomepageServlet">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="geofenceId" value="<%= ((Geofence) request.getAttribute("geofence")).getGeofenceId()%>">

                    <%
                        Geofence geofence = (Geofence) request.getAttribute("geofence");
                    %>

                    <label>Boundary Name:</label>
                    <input type="text" name="boundaryName" value="<%= geofence.getBoundaryName()%>" required>

                    <label>Location:</label>
                    <select name="locationId" required>
                        <%
                            List<Location> locations = (List<Location>) request.getAttribute("locations");
                            if (locations != null) {
                                for (Location loc : locations) {
                                    String selected = (geofence.getLocationId() == loc.getLocationId()) ? "selected" : "";
                        %>
                        <option value="<%= loc.getLocationId()%>" <%= selected%>>
                            <%= loc.getBuildingName()%> - <%= loc.getRoomName()%>
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>

                    <label>Radius (meters):</label>
                    <input type="number" name="radius" value="<%= (geofence.getRadius() > 0 ? geofence.getRadius() : 1)%>" min="1" required>

                    <label>Latitude:</label>
                    <input type="text" name="latitude" value="<%= geofence.getLatitude()%>" required>

                    <label>Longitude:</label>
                    <input type="text" name="longitude" value="<%= geofence.getLongitude()%>" required>

                    <div class="button-group">
                        <button type="submit" class="btn-update">Update Geofence</button>
                        <button type="button" class="btn-back" onclick="window.location.href = 'GeofenceHomepageServlet'">Back</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
