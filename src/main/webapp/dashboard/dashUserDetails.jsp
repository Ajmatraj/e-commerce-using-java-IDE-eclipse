<%@ page import="java.util.List" %>
<%@ page import="controller.DatabaseController" %>
<%@ page import="Model.UserModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    // Initialize the DatabaseController and fetch the user list
    DatabaseController dbController = new DatabaseController();
    List<UserModel> users = dbController.getAllUsersInfo();
    request.setAttribute("users", users);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Details</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <%@ include file="/dashboard/adminHeader.jsp" %>
    <style>
        .sec-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        .updateUserButton, .deleteUserButton {
            border-radius: 4px;
            border: none;
            text-align: center;
            font-size: 17px;
            padding: 5px;
            width: 90px;
            transition: all 0.5s;
            cursor: pointer;
            margin: 10px;
        }
        .updateUserButton {
            background-color: #414464;
            color: white;
        }
        .deleteUserButton {
            background-color: red;
            color: white;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            overflow-x: auto;
            display: block;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        @media (max-width: 768px) {
            table {
                display: block;
                overflow-x: auto;
                white-space: nowrap;
            }
            th, td {
                display: block;
                text-align: right;
                padding: 10px;
            }
            th {
                display: none;
            }
            td {
                position: relative;
                padding-left: 50%;
            }
            td::before {
                content: attr(data-label);
                position: absolute;
                left: 0;
                width: 50%;
                padding-left: 10px;
                font-weight: bold;
                white-space: nowrap;
                text-align: left;
            }
            .form-btn{
            display: flex;
            flex-direction: row;
            flex-wrap: wrap;
            }
        }
    </style>
</head>
<body>
    <c:if test="${not empty users}">
        <div class="sec-container">
            <table>
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>DOB</th>
                        <th>Gender</th>
                        <th>Email</th>
                        <th>Phone Number</th>
                        <th>Username</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td data-label="First Name">${user.first_name}</td>
                            <td data-label="Last Name">${user.last_name}</td>
                            <td data-label="DOB">${user.dob}</td>
                            <td data-label="Gender">${user.gender}</td>
                            <td data-label="Email">${user.email}</td>
                            <td data-label="Phone Number">${user.phone_number}</td>
                            <td data-label="Username">${user.username}</td>
                            <td data-label="Actions">
                            <div class="form-btn">
                                <form action="${pageContext.request.contextPath}/deleteUser" method="post" style="display:inline;">
                                    <input type="hidden" name="username" value="${user.username}">
                                    <button type="submit" class="deleteUserButton">Delete</button>
                                </form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
    <c:if test="${empty users}">
        <p>No users available.</p>
    </c:if>
</body>
</html>
