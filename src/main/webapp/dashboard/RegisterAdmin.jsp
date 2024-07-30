<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Admin</title>
<link rel="stylesheet" type="text/css" href="../Stylesheets/register.css" />
<style type="text/css">
    .success-message {
        background-color: #e6ffe6; 
        color: #006400;             
        text-align: center;         
        padding: 10px;               
        border-radius: 5px;          
        font-weight: bold;           
        margin: 10px 0;              
        width: 100%;                 
    }
    .error-message {
        background-color: #ffe6e6; 
        color: #ff0000;             
        text-align: center;         
        padding: 10px;               
        border-radius: 5px;          
        font-weight: bold;           
        margin: 10px 0;              
        width: 100%;                 
    }
</style>
</head>
<body>
    <div class="container">
        <%
            String successParam = request.getParameter("success");
            boolean success = "true".equalsIgnoreCase(successParam);

            if (success) {
        %>
            <div class="success-message">Registration successful!</div>
        <%
            }

            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null && !errorMessage.isEmpty()) {
        %>
            <div class="error-message"><%= errorMessage %></div>
        <%
            }
        %>
        <h1>Admin Registration Form</h1>
		<form action="${pageContext.request.contextPath}/registerAdminServlet" method="post" enctype="multipart/form-data">
            <div class="row">
                <div class="col">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="col">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="col">
                    <label for="retypePassword">Retype Password:</label>
                    <input type="password" id="retypePassword" name="retypePassword" required>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <label for="image">Profile Picture:</label>
                    <input type="file" id="image" name="image">
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <button type="submit">Submit</button>
                </div>
            </div>
        </form>
        <div class="row">
            <div class="col">
                <span>Already have an account? </span><a href="${pageContext.request.contextPath}/pages/login.jsp">Login</a>
            </div>
        </div>
    </div>
</body>
</html>
