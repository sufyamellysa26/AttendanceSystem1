<%-- 
    Document   : ConfirmDelete
    Created on : 26 Jun 2026, 7:48:53 AM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Delete Attendance</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #FFF8DC;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .modal {
                background: #f3f3f5;
                border-radius: 12px;
                padding: 20px;
                box-shadow: 0 4px 12px rgba(0,0,0,0.2);
                text-align: center;
                color: #4B0082;
            }
            .modal h3 {
                margin-bottom: 20px;
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
        </style>
    </head>
    <body>
        <div class="modal">
            <h3>Are you sure you want to delete class session <%= request.getParameter("classId")%>?</h3>
            <form method="post" action="DeleteAttendanceServlet" style="display:inline;">
                <input type="hidden" name="classId" value="<%= request.getParameter("classId")%>">
                <input type="hidden" name="confirmed" value="yes">
                <button type="submit" class="btn btn-ok">OK</button>
            </form>
            <form method="get" action="AttendanceHistoryLecturerServlet" style="display:inline;">
                <button type="submit" class="btn btn-cancel">Cancel</button>
            </form>

        </div>
    </body>
</html>
