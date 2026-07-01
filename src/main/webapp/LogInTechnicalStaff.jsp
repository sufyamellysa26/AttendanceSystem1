<%-- 
    Document   : LogInTechnicalStaff
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Technical Staff Login</title>
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
                padding: 15px 20px;
                box-shadow: 0 2px 6px rgba(0,0,0,0.1);
                border-bottom: 2px solid #D8BFD8;
                position: relative;
                z-index: 10;
            }

            .header-panel h1 {
                font-size: 22px;
                margin: 0;
                color: #4B0082;
            }

            .main-content {
                flex: 1;
                display: flex;
                justify-content: center;
                align-items: center;
                padding: 40px;
            }

            .form-cube {
                background: #fff;
                border-radius: 12px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
                padding: 30px;
                max-width: 400px;
                width: 100%;
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

            input {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                border: 1px solid #ccc;
                border-radius: 8px;
            }

            .btn-login {
                background: #4B0082;
                color: white;
                padding: 10px;
                width: 100%;
                border: none;
                border-radius: 8px;
                font-size: 14px;
                font-weight: bold;
                margin-top: 20px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .btn-login:hover {
                background-color: #800080;
            }

            .btn-back {
                background: gray;
                color: white;
                padding: 10px;
                width: 100%;
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
        </style>
    </head>
    <body>
        <div class="header-panel">
            <h1>Technical Staff Login</h1>
        </div>

        <div class="main-content">
            <div class="form-cube">
                <h2>Login</h2>
                <form method="post" action="LogInTechnicalStaffServlet">
                    <label for="staffName">Staff Name:</label>
                    <input type="text" id="staffName" name="staffName" required>

                    <label for="staffPassword">Password:</label>
                    <input type="staffPassword" id="staffPassword" name="staffPassword" required>

                    <button type="submit" class="btn-login">Login</button>
                </form>

                <%-- Example: Display error message dynamically if login fails --%>
                <%
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    if (errorMessage != null) {
                %>
                <p style="color:red; text-align:center;"><%= errorMessage%></p>
                <%
                    }
                %>
            </div>
    </body>
</html>
