<%-- 
    Document   : RecordAttendanceStudent
    Author     : USER
--%>

<%@page import="model.ClassSession"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Record Attendance</title>
        <style>
            /* CSS styling section */
            body {
                font-family: Arial, sans-serif;   /* Sets font */
                background-color: #FFF8DC;        /* Light background color */
                display: flex;                    /* Use flexbox layout */
                justify-content: center;          /* Center horizontally */
                align-items: center;              /* Center vertically */
                height: 100vh;                    /* Full viewport height */
                margin: 0;                        /* Remove default margin */
            }

            .container {
                width: 375px;                     /* Fixed width (mobile-friendly) */
                min-height: 620px;                /* Minimum height */
                background-color: #fff;           /* White background */
                border-radius: 12px;              /* Rounded corners */
                box-shadow: 0 0 10px rgba(0,0,0,0.2);/* Drop shadow */
                padding: 20px;                    /* Inner spacing */
                display: flex;                    /* Flexbox layout */
                flex-direction: column;           /* Stack children vertically */
                justify-content: space-between;   /* Spread header, form, nav evenly */
            }

            .header-panel {
                display: flex;                    /* Flexbox layout */
                justify-content: space-between;   /* Space icons apart */
                align-items: center;              /* Align vertically */
                background-color: #E6E6FA;        /* Lavender background */
                padding: 10px 15px;               /* Inner spacing */
                border-radius: 10px;              /* Rounded corners */
                box-shadow: 0 0 5px rgba(0,0,0,0.1);/* Subtle shadow */
            }

            .icon-left, .icon-right {
                width: 28px;                      /* Icon size */
                height: 28px;
                object-fit: contain;              /* Keep aspect ratio */
            }

            .form-cube {
                background: #fff;
                border-radius: 10px;
                box-shadow: 0 0 8px rgba(0,0,0,0.15);
                padding: 20px;
                margin: 20px 0;
                text-align: center;       /* Center content */
            }

            .form-cube h2 {
                text-align: center;               /* Center heading */
                color: #4B0082;                   /* Indigo text */
                margin-bottom: 20px;              /* Space below heading */
            }

            form {
                display: flex;
                flex-direction: column;
                align-items: center;      /* ✅ Center inputs and button */
                gap: 15px;
            }

            input, button {
                width: 90%;               /* ✅ Same width for both */
                max-width: 300px;         /* ✅ Limit width for neatness */
                padding: 12px;
                border-radius: 6px;
                border: 1px solid #ccc;
                font-size: 14px;
                margin: 0 auto;           /* ✅ Center horizontally */
                display: block;
            }

            button {
                background-color: #4B0082;
                color: white;
                font-weight: bold;
                cursor: pointer;
                border: none;
                transition: background-color 0.3s ease;
            }

            button:hover {
                background-color: #800080;        /* Darker purple on hover */
                cursor: pointer;                  /* Pointer cursor */
            }

            .nav-cube {
                background-color: #E6E6FA;        /* Lavender background */
                border-radius: 10px;              /* Rounded corners */
                box-shadow: 0 0 8px rgba(0,0,0,0.15);/* Shadow */
                padding: 12px;                    /* Inner spacing */
            }

            .nav {
                display: flex;                    /* Flexbox layout */
                justify-content: space-around;    /* Spread items evenly */
                align-items: center;              /* Align vertically */
                font-size: 12px;                  /* Small text */
                color: #4B0082;                   /* Indigo text */
            }

            .nav span {
                padding: 6px 10px;                /* Inner spacing */
                border-radius: 6px;               /* Rounded corners */
                transition: all 0.3s ease;        /* Smooth hover effect */
                color: #4B0082;                   /* Indigo text */
            }

            .nav span:hover {
                background-color: #D8BFD8;        /* Light purple hover */
                color: #800080;                   /* Darker purple text */
                border: 1px solid #4B0082;        /* Indigo border */
                cursor: pointer;                  /* Pointer cursor */
                transform: scale(1.05);           /* Slight zoom */
                box-shadow: 0 0 5px rgba(0,0,0,0.2); /* Shadow */
            }

            .nav span.active {
                border: 2px solid #4B0082;        /* Highlight active tab */
                background-color: transparent;
                font-weight: bold;                /* Bold text */
            }

            .nav a {
                text-decoration: none;            /* Remove underline from links */
            }

            .notification {
                padding: 12px;
                border-radius: 8px;
                margin-bottom: 15px;
                font-size: 13px;
            }
            .active-session {
                background-color: #E6FFE6;
                border: 1px solid #4CAF50;
                color: #2E8B57;
            }
            .no-session {
                background-color: #FFF0F0;
                border: 1px solid #FF0000;
                color: #B22222;
            }
            h2 {
                text-align: center;   /* Center horizontally */
                margin: 20px 0 10px;    /* Add space below */
                font-size: 22px;      /* Optional: adjust size */
                font-weight: bold;    /* Optional: make it bold */
                color: #4B0082;       /* Optional: match your indigo theme */
            }
        </style>
    </head>
    <body>

        <%
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("LogIn.jsp");
                return;
            }
            ClassSession activeSession = (ClassSession) request.getAttribute("activeSession");
        %>
        <div class="container">

            <!-- Header Panel -->
            <div class="header-panel">
                <a href="SettingStudent.jsp" style="text-decoration:none; font-size:20px; color:#4B0082;">⚙️</a>
                <img src="images.png" alt="UMT Logo" class="icon-right" />
            </div>

            <!-- Title -->
            <h2>Record Attendance</h2>
            <!-- Lecturer Session Notification -->
            <%
                if (activeSession != null) {
            %>
            <div class="notification active-session">
                ✅ Attendance session is active for 
                <strong><%= activeSession.getSubjectName()%> (<%= activeSession.getSubjectCode()%>)</strong><br>
                Location: <%= activeSession.getBuildingName()%> - <%= activeSession.getRoomName()%><br>
                Time: <%= activeSession.getStartTime()%> → <%= activeSession.getEndTime()%>
            </div>
            <%
            } else {
            %>
            <!-- <div class="notification no-session">
                ⚠️ No active lecturer session found. Please wait until your lecturer starts the class.
            </div>-->
            <%
                }
            %>

            <!-- Form Cube -->
            <div class="form-cube">
                <form id="attendanceForm" action="RecordAttendanceServlet" method="post" onsubmit="return attachLocation();">
                    <input type="text" name="userId" placeholder="Enter your User ID" />
                    <input type="password" name="password" placeholder="Enter your password" >
                    <input type="text" name="subjectCode" placeholder="Enter subject code" />
                    <!--   <input type="hidden" name="lat" id="latField" />
                      <input type="hidden" name="lon" id="lonField" />-->
                    <button type="submit">Submit Attendance</button>
                </form>

            </div>

            <!-- Popup Notification -->
            <div id="notificationModal" style="display:none; position:fixed; top:50%; left:50%; transform:translate(-50%, -50%); background:#E6E6FA; padding:20px; border-radius:10px; box-shadow:0 4px 10px rgba(0,0,0,0.3); text-align:center; z-index:2000;">
                <p id="notifMessage" style="font-weight:bold;"></p>
                <button onclick="closeNotification()" style="margin-top:15px; padding:8px 16px; background:#4B0082; color:white; border:none; border-radius:5px; cursor:pointer;">OK</button>
            </div>

            <!-- Navigation Panel -->
            <div class="nav-cube">
                <div class="nav">
                    <a href="RecordAttendanceStudent.jsp"><span class="active">Record</span></a>
                    <a href="AttendanceHistoryStudentServlet"><span>History</span></a>
                    <a href="UserProfileStudent.jsp"><span>Profile</span></a>
                </div>
            </div>
        </div>

        <script>
            function showNotification(message, status) {
                const modal = document.getElementById("notificationModal");
                const msg = document.getElementById("notifMessage");

                msg.innerText = message;

                // Style based on status
                if (status === "success") {
                    msg.style.color = "green";
                } else if (status === "warning") {
                    msg.style.color = "orange";
                } else {
                    msg.style.color = "red";
                }

                modal.style.display = "block";
            }

            function closeNotification() {
                document.getElementById("notificationModal").style.display = "none";
            }

            function attachLocation() {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(function (position) {
                        document.getElementById("latField").value = position.coords.latitude.toFixed(7);
                        document.getElementById("lonField").value = position.coords.longitude.toFixed(7);

                        // Submit via AJAX to servlet
                        fetch("RecordAttendanceServlet", {
                            method: "POST",
                            body: new FormData(document.getElementById("attendanceForm"))
                        })
                                .then(response => response.json())
                                .then(data => {
                                    const subject = document.querySelector("input[name='subjectCode']").value;

                                    if (data.status === "success" || data.status === "present") {
                                        showNotification("✅ Your attendance for " + subject + " has been recorded successfully.", "success");
                                    } else if (data.status === "outside") {
                                        showNotification("⚠️ Your attendance for " + subject + " is outside the class.", "warning");
                                    } else {
                                        showNotification("❌ " + data.message, "error");
                                    }
                                })
                                .catch(err => {
                                    showNotification("❌ Error submitting attendance", "error");
                                });

                    }, function (err) {
                        showNotification("❌ Location is required for attendance. Please allow access.", "error");
                    }, {enableHighAccuracy: true, timeout: 10000});

                    return false; // prevent normal form submit
                } else {
                    showNotification("❌ Geolocation not supported", "error");
                    return false;
                }
            }
        </script>

    </body>
</html>
