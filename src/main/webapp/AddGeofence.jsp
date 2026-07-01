<%-- 
    Document   : AddGeofence
    Created on : 16 May 2026, 10:34:36 PM
    Author     : USER
--%>

<%@ page import="java.util.*, model.Geofence, model.Location" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Geofence</title>
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

            .button-group {
                display: flex;
                justify-content: space-between;
                gap: 10px;
                margin-top: 20px;
            }

            .btn-submit {
                background: #4B0082;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 8px;
                font-size: 14px;
                font-weight: bold;
                cursor: pointer;
                transition: background-color 0.3s ease;
                flex: 1;
            }

            .btn-submit:hover {
                background-color: #800080;
            }

            .btn-back {
                background: gray;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 8px;
                font-size: 14px;
                font-weight: bold;
                cursor: pointer;
                transition: background-color 0.3s ease;
                flex: 1;
            }

            .btn-back:hover {
                background-color: #555;
            }
        </style>
    </head>
    <body>
        <div class="header-panel">
            <h1>Add Geofence Boundary</h1>
        </div>

        <div class="main-content">
            <div class="form-cube">
                <h2>Add Geofence Boundary</h2>
                <form method="post" action="AddGeofenceServlet">
                    <input type="hidden" name="action" value="add">

                    <label>Boundary Name:</label>
                    <input type="text" name="boundaryName" required>

                    <label for="location">Choose Location:</label>
                    <select name="locationId" id="location" required>
                        <c:forEach var="loc" items="${locations}">
                            <option value="${loc.locationId}">
                                ${loc.buildingName} - ${loc.roomName}
                            </option>
                        </c:forEach>
                    </select>


                    <label for="radius">Radius (meters):</label>
                    <input type="number" name="radius" id="radius" min="1" required>

                    <c:if test="${not empty error}">
                        <p style="color:red; font-weight:bold;">${error}</p>
                    </c:if>

                    <label>Latitude:</label>
                    <input type="text" name="latitude" required>

                    <label>Longitude:</label>
                    <input type="text" name="longitude" required>

                    <div class="button-group">
                        <button type="submit" class="btn-submit">Set Up Geofence</button>
                        <button type="button" class="btn-back" onclick="window.location.href = 'GeofenceHomepageServlet'">Back</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
