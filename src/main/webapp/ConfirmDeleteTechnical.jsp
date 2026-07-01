<%-- 
    Document   : ConfirmDeleteTechnical
    Created on : 28 Jun 2026, 9:01:01 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Geofence" %>

<%
    String geofenceId = request.getParameter("geofenceId");
    String boundaryName = request.getParameter("boundaryName");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Delete Geofence</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #FFF8DC;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .btn {
                padding: 8px 16px;
                border: none;
                border-radius: 6px;
                font-weight: bold;
                cursor: pointer;
                margin: 0 10px;
            }
            .btn-ok {
                background-color: crimson;
                color: white;
            }
            .btn-cancel {
                background-color: #D8BFD8;
                color: #4B0082;
            }
            .confirm-box {
                margin: 100px auto;
                width: 400px;
                padding: 20px;
                background: #fff;
                border: 1px solid #ccc;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="confirm-box">
            <h2>Confirm Delete</h2>
            <p>Are you sure you want to delete this geofence?</p>

            <form method="post" action="DeleteGeofenceServlet">
                <input type="hidden" name="geofenceId" value="<%= geofenceId%>">
                <button type="submit" class="btn-delete">Delete</button>
                <button type="button" class="btn-cancel" onclick="window.location.href = 'GeofenceHomepageServlet'">Cancel</button>
            </form>
        </div>
    </body>
</html>
