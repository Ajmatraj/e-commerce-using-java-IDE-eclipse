<%@ page import="Utils.StringUtils" %>
<%@ page import="Model.UserModel" %>
<%@ page import="Utils.ValidationUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



    

<%
String contextPath = request.getContextPath();
String errMsg = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
String successMsg = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);
String username = (String) request.getAttribute(StringUtils.USERNAME);
String successParam = request.getParameter(StringUtils.SUCCESS);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>


<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Stylesheets/login.css">
	<style type="text/css">
		.error-msg {
    background-color: #ffe6e6;
    color: #a00;                
    padding: 10px;              
    border: 1px solid #a00;     
    border-radius: 5px;         
    text-align: center;         
    font-weight: bold;              
    width: 100%;                 
}
@charset "ISO-8859-1";

body {
    font-family: sans-serif;
    margin: 0;
    padding: 20px;
    background-image: url("../Resources/Images/products/image-175.jpg"); 
   background-size: cover;
    background-repeat: no-repeat;
    height: 100vh;
    display: flex;
    align-items: center;
}

.login-box {

	width: 300px;
	height: 330px;
	background-color: #ffffff91;
	border-radius: 10px;
	box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
	margin: 70px auto;
	padding: 30px;
}


h2 {
    text-align: center;
    margin-bottom: 20px;
}
.row {
    display: flex;
    margin-bottom: 10px;
}

.col {
    flex: 1;
    margin-right: 20px;
}

label {
    display: block;
    margin-bottom: 5px;
}

input, select {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
    background-color: #ffffff91;
}

select {
    appearance: none;
}

button {
    background-color: #414464;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

button:hover {
    background-color: #414464;
}

.error-message {
	color: red;
	text-align: center;
}

.success-message {
	color: green;
	text-align: center;
}
	</style>
</head>
<body>
    <div class="login-box">
        <h2>Login</h2>
        <form action="<%= contextPath + StringUtils.SERVLET_URL_LOGIN %>" method="post">
            <div class="row">
                <div class="col">
                    <label for="username">Username:</label> <input type="text" id="username" name="username"
                        value="<% if (username != null && !username.isBlank()) { out.println(username); } %>"
                        required>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <label for="password">Password:</label> <input type="password" id="password" name="password"
                        required>
                </div>
            </div>
            <button type="submit" class="login-button">Login</button>
        </form>
        <div>
        <div> <br> 
        <span>Don't have an account? </span><a href="<%=contextPath + StringUtils.PAGE_URL_REGISTER%>">Register here</a></div>

        <% if (errMsg != null) { %>
        <p class="error-msg">
            <%= errMsg %>
        </p>
        <% } %>

        <% if (successParam != null && successParam.equals(StringUtils.TRUE)) { %>
        <h2 class="success-msg">Registration Successful!</h2>
        <% } %>
    </div>
</body>
</html>
