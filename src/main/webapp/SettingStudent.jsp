<%-- 
    Document   : SettingStudent
    Created on : 9 Jun 2026, 3:31:03 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Settings</title>
        <style>
            /* Reuse your existing style */
            body {
                font-family: Arial, sans-serif;
                background-color: #FFF8DC;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }

            .container {
                width: 375px;
                height: 620px;
                background-color: #fff;
                border-radius: 12px;
                box-shadow: 0 0 10px rgba(0,0,0,0.2);
                padding: 20px;
                display: flex;
                flex-direction: column;
            }

            .header-panel {
                display: flex;
                justify-content: center;
                align-items: center;
                background-color: #E6E6FA;
                padding: 10px 15px;
                border-radius: 10px;
                box-shadow: 0 0 5px rgba(0,0,0,0.1);
                margin-bottom: 20px;
            }

            .header-panel h2 {
                margin: 0;
                color: #4B0082;
            }

            .form-cube {
                background: #fff;
                border-radius: 10px;
                box-shadow: 0 0 8px rgba(0,0,0,0.15);
                padding: 20px;
                text-align: center;
            }

            .form-cube button {
                width: 100%;
                padding: 12px;
                background-color: #4B0082;
                color: white;
                border: none;
                border-radius: 6px;
                font-size: 14px;
                font-weight: bold;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .form-cube button:hover {
                background-color: #800080;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <!-- Header -->
            <div class="header-panel">
                <h2>Settings</h2>
            </div>

            <!-- Content -->
            <div class="form-cube">
                <button onclick="confirmLogout()">Logout</button>
            </div>
        </div>

        <script>
            function confirmLogout() {
                if (confirm("Are you sure you want to log out?")) {
                    window.location.href = "LogoutServlet";
                }
            }
        </script>
    </body>
</html>
