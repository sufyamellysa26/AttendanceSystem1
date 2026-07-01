<%-- 
    Document   : EditProfileStudent
    Author     : USER
--%>

<%@ page import="model.Student" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #FFF8DC;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .container {
                width: 375px;
                background: #fff;
                border-radius: 20px;
                box-shadow: 0 0 10px rgba(0,0,0,0.2);
                padding: 20px;
            }
            h2 {
                text-align: center;
                color: #4B0082;
                margin-bottom: 15px;
            }
            label {
                font-weight: bold;
                color: #4B0082;
                display: block;
                margin-top: 10px;
            }
            input, textarea {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                border-radius: 8px;
                border: 1px solid #ccc;
                font-size: 14px;
            }
            button {
                margin-top: 15px;
                width: 100%;
                padding: 10px;
                background-color: #4B0082;
                color: white;
                border: none;
                border-radius: 8px;
                font-size: 14px;
            }
            .nav {
                display: flex;
                justify-content: space-around;
                margin-top: 15px;
                font-size: 12px;
                color: #4B0082;
            }
            .nav span:nth-child(4) {
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Edit Profile</h2>
            <%
                Student student = (Student) session.getAttribute("student");
                if (student == null) {
                    response.sendRedirect("LogInStudent.jsp");
                }
            %>
            <form action="EditProfileStudentServlet" method="post">
                <label>Matrics Number</label>
                <input type="text" name="matric" value="<%= student.getMatric()%>" readonly />

                <label>Course Program</label>
                <input type="text" name="course" value="<%= student.getCourseProgram()%>" />

                <label>Subjects This Semester</label>
                <textarea name="subjects">
                    <%
                        if (student.getSubjects() != null) {
                            for (String subj : student.getSubjects()) {
                                out.print(subj + ", ");
                            }
                        }
                    %>
                </textarea>

                <label>Phone Number</label>
                <input type="text" name="phone" value="<%= student.getPhone()%>" />

                <label>Address</label>
                <textarea name="address"><%= student.getAddress()%></textarea>

                <button type="submit">Update Profile</button>
            </form>

            <!-- Navigation -->
            <div class="nav-cube">
                <div class="nav">
                    <a href="RecordAttendanceStudent.jsp"><span>Record</span></a>
                    <a href="AttendanceHistoryStudentServlet"><span class="active">History</span></a>
                    <a href="UserProfileStudent.jsp"><span>Profile</span></a>
                </div>
            </div>
    </body>
</html>
