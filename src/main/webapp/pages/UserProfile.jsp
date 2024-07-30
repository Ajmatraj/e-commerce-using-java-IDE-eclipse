<%@ page import="Model.UserModel"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="controller.DatabaseController"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Stylesheets/hearderFooter.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Stylesheets/style.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .profile-container {
            max-width: 800px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .profile-image {
            text-align: center;
        }
        .profile-image img {
            border-radius: 50%;
            width: 150px;
            height: 150px;
            object-fit: cover;
        }
        .profile-info {
            margin-top: 20px;
        }
        .profile-info p {
            font-size: 16px;
            color: #555;
        }
        .profile-info p span {
            font-weight: bold;
            color: #333;
        }
        .error-message {
            text-align: center;
            color: red;
        }
        .edit-button {
            display: block;
            width: 100%;
            max-width: 200px;
            margin: 20px auto;
            padding: 10px;
            text-align: center;
            background-color: #4CAF50;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }
        .edit-button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<%@ include file="/pages/header.jsp" %>
<body>
    <div class="profile-container">
        <h1>User Profile</h1>
        <%
            if (session.getAttribute("username") != null) {
                String username = (String) session.getAttribute("username");

                DatabaseController dbController = new DatabaseController();
                UserModel user = dbController.getUserByUsername(username);

                if (user != null) {
        %>
                    <div class="profile-image">
                        <img src="${pageContext.request.contextPath}/Resources/Images/Users/<%= user.getImageUrlFromPart() %>" alt="<%= user.getfirst_name() %>">
                    </div>
                    <div class="profile-info">
                        <p><span>First Name:</span> <%= user.getfirst_name() %></p>
                        <p><span>Last Name:</span> <%= user.getlast_name() %></p>
                        <p><span>Email:</span> <%= user.getemail() %></p>
                        <p><span>Phone Number:</span> <%= user.getphone_number() %></p>
                    </div>
                    <a href="${pageContext.request.contextPath}/pages/editProfile.jsp" class="edit-button">Edit Profile</a>
        <%
                } else {
        %>
                    <p class="error-message">Error retrieving user information.</p>
        <%
                }
            } else {
                response.sendRedirect("login.jsp"); // Redirect to login if not logged in
            }
        %>
    </div>
</body>
</html>
