<%-- 
    Document   : LogInLecturer
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unified Login</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #FFF8DC;
                display: flex;
                justify-content: center;   /* ✅ centers horizontally */
                align-items: center;       /* ✅ centers vertically */
                flex-direction: column;
                height: 100vh;
                margin: 0;
            }

            .login-box {
                background: #fff;
                padding: 60px 40px;          /* Bigger padding */
                border-radius: 20px;
                box-shadow: 0 8px 20px rgba(0,0,0,0.15);
                width: 350px;
                text-align: center;   /* ✅ Center all content */
                animation: popIn 0.6s ease;
            }

            @keyframes popIn {
                from {
                    transform: scale(0.8);
                    opacity: 0;
                }
                to {
                    transform: scale(1);
                    opacity: 1;
                }
            }

            .login-box h2 {
                margin-bottom: 20px;
                color: #6a0dad;
                font-size: 28px;             /* ✅ Larger heading */
                text-align: center;   /* ✅ Center heading */
            }

            .login-box form {
                display: flex;
                flex-direction: column;
                align-items: center;  /* ✅ Center inputs and button */
                gap: 18px;                   /* More space between inputs */
            }
            .login-box input,
            .login-box button {
                width: 95%;                  /* ✅ Wider inputs and button */
                max-width: 350px;            /* ✅ Bigger max width */
                margin: 0 auto;       /* ✅ Center horizontally */
                display: block;
            }
            .login-box input {
                padding: 15px;               /* ✅ Larger input padding */
                border-radius: 12px;
                border: 2px solid #d8bfd8;
                font-size: 16px;             /* ✅ Bigger text */
                transition: 0.3s;
            }

            .login-box input:focus {
                border-color: #4B0082;
                box-shadow: 0 0 8px #d8bfd8;
            }

            .login-box button {
                padding: 14px;               /* ✅ Larger input padding */
                background: #4B0082;
                color: #fff;
                border: none;
                border-radius: 12px;
                font-size: 18px;             /* ✅ Bigger text */
                font-weight: bold;
                cursor: pointer;
                transition: background 0.3s ease;
            }

            .login-box button:hover {
                background: #800080;
            }

            .message {
                margin-top: 15px;
                font-size: 15px;             /* ✅ Slightly larger text */
                padding: 10px;               /* ✅ Bigger padding */
                border-radius: 8px;
                width: 95%;                  /* ✅ Same width as inputs */
                max-width: 280px;
                margin-left: auto;    /* ✅ Center horizontally */
                margin-right: auto;
                text-align: center;   /* ✅ Center text inside */
            }
            .error {
                background-color: #ffe6e6;
                color: crimson;
                border: 1px solid crimson;
            }
            .success {
                background-color: #e6ffe6;
                color: green;
                border: 1px solid green;
            }

            .btn-login:hover {
                background-color: #800080;
            }
        </style>
    </head>
    <body>
        <div class="login-box">
            <h2>Login</h2>
            <form action="LogInServlet" method="post">
                <input type="text" name="userId" placeholder="User ID" required />
                <input type="password" name="password" placeholder="Password" required />
                <button type="submit">Login</button>
            </form>

            <!-- ✅ Error or success feedback -->
            <%
                String error = request.getParameter("error");
                String success = request.getParameter("success");
                if (error != null) {
            %>
            <p class="message error"><%= error%></p>
            <%
            } else if (success != null) {
            %>
            <p class="message success"><%= success%></p>
            <%
                }
            %>
        </div>
    </body>
</html>
