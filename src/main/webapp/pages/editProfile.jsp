<%@ page import="Model.UserModel"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="controller.DatabaseController"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Profile</title>
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
        .edit-profile-container {
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
        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }
        label {
            font-weight: bold;
            color: #333;
        }
        input, button {
            padding: 10px;
            font-size: 16px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<%@ include file="/pages/header.jsp" %>
<body>
    <div class="edit-profile-container">
        <h1>Edit Profile</h1>
        <%
            if (session.getAttribute("username") != null) {
                String username = (String) session.getAttribute("username");

                DatabaseController dbController = new DatabaseController();
                UserModel user = dbController.getUserByUsername(username);

                if (user != null) {
        %>
                    <form action="${pageContext.request.contextPath}/UpdateProfileServlet" method="post" enctype="multipart/form-data">
                        <label for="first_name">First Name:</label>
                        <input type="text" id="first_name" name="first_name" value="<%= user.getfirst_name() %>" required>

                        <label for="last_name">Last Name:</label>
                        <input type="text" id="last_name" name="last_name" value="<%= user.getlast_name() %>" required>

                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" value="<%= user.getemail() %>" required>

                        <label for="phone_number">Phone Number:</label>
                        <input type="text" id="phone_number" name="phone_number" value="<%= user.getphone_number() %>" required>

                        <label for="image">Profile Image:</label>
                        <input type="file" id="image" name="image" accept="image/*">

                        <button type="submit">Update Profile</button>
                    </form>
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
