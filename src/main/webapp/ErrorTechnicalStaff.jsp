<%-- 
    Document   : ErrorTechnicalStaff
    Created on : 20 May 2026, 1:12:18 AM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #FFF0F5;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .error-box {
                background: #fff;
                border: 2px solid #DC143C;
                border-radius: 12px;
                padding: 30px;
                max-width: 500px;
                text-align: center;
                box-shadow: 0 0 10px rgba(0,0,0,0.2);
            }
            h1 {
                color: #DC143C;
            }
            p {
                margin: 15px 0;
                color: #333;
            }
            .btn-home {
                background: #DC143C;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 8px;
                font-size: 14px;
                font-weight: bold;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            .btn-home:hover {
                background-color: #a10f2d;
            }
        </style>
    </head>
    <body>
        <div class="error-box">
            <h1>Oops! Something went wrong</h1>
            <p><%= request.getAttribute("error") != null ? request.getAttribute("error") : "Unknown error occurred."%></p>
            <button class="btn-home" onclick="window.location.href = 'GeofenceHomepageServlet'">Go Home</button>
        </div>
    </body>
</html>
