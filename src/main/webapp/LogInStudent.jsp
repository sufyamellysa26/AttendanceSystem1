<%-- 
    Document   : LogInStudent
    Created on : 7 Jun 2026, 11:18:31 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Login</title>
        <style>
            body {
                font-family: Arial;
                background:#f8f8f8;
                display:flex;
                justify-content:center;
                align-items:center;
                height:100vh;
            }
            .login-box {
                background:#fff;
                padding:20px;
                border-radius:8px;
                box-shadow:0 0 10px rgba(0,0,0,0.2);
                width:300px;
            }
            .login-box h2 {
                text-align:center;
                margin-bottom:20px;
            }
            .login-box input {
                width:100%;
                padding:10px;
                margin:8px 0;
                border:1px solid #ccc;
                border-radius:5px;
            }
            .login-box button {
                width:100%;
                padding:10px;
                background:#4B0082;
                color:#fff;
                border:none;
                border-radius:5px;
                cursor:pointer;
            }
            .login-box button:hover {
                background:#800080;
            }
        </style>
    </head>
    <body>
        <div class="login-box">
            <h2>Student Login</h2>
            <form action="LogInStudentServlet" method="post">
                <input type="text" name="matric" placeholder="Matric Number" required />
                <input type="password" name="password" placeholder="Password" required />
                <button type="submit">Login</button>
            </form>
        </div>
    </body>
</html>
