<%@ page import="java.util.List" %>
<%@ page import="Model.adminModel" %>
<%@ page import="controller.DatabaseController" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    // Initialize the DatabaseController and fetch admin details
    DatabaseController dbController = new DatabaseController();
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
        return;
    }
    List<adminModel> adminList = dbController.getAdminDetails(username);
    request.setAttribute("adminList", adminList);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/dashboard/adminHeader.jsp" %>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Profile</title>
    <style>
        /* Add your styles here */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            color: #414464;
        }
        .container {
            max-width: 90%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        .profile-header {
            display: flex;
            align-items: center;
            border-bottom: 1px solid #ccc;
            padding-bottom: 20px;
            flex-wrap: wrap;
        }
        .profile-header img {
            border-radius: 50%;
            margin-right: 20px;
            border: 2px solid #414464;
            flex-shrink: 0;
        }
        .profile-header h2 {
            margin: 0;
            color: #414464;
            flex: 1;
        }
        .profile-details {
            padding: 20px 0;
        }
        .profile-details div {
            margin-bottom: 10px;
            display: flex;
            flex-wrap: wrap;
        }
        .profile-details label {
            font-weight: bold;
            color: #414464;
            flex: 1;
            min-width: 120px;
        }
        .profile-details span {
            color: #414464;
            flex: 2;
        }

        @media (max-width: 768px) {
            .profile-header {
                flex-direction: column;
                align-items: flex-start;
                text-align: center;
            }
            .profile-header img {
                margin: 0 auto 10px;
            }
            .profile-details label {
                min-width: auto;
                flex: 1 0 100%;
                text-align: left;
            }
            .profile-details span {
                flex: 1 0 100%;
                text-align: left;
            }
        }

        @media (max-width: 480px) {
            .profile-details div {
                font-size: 14px;
            }
            .profile-header img {
                width: 80px;
                height: 80px;
            }
            .profile-details label, .profile-details span {
                font-size: 16px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <c:choose>
            <c:when test="${not empty adminList}">
                <c:forEach var="admin" items="${adminList}">
                    <header class="profile-header">
                        <img src="${pageContext.request.contextPath}/Resources/Images/admin/${admin.imageUrlFromPart != null ? admin.imageUrlFromPart : 'default.png'}" alt="Profile Image" width="100" height="100">
                        <h2>Admin Profile</h2>
                    </header>
                    <section class="profile-details">
                        <div>
                            <label for="admin-id">Username:</label>
                            <span id="admin-id">${admin.username}</span>
                        </div>
                        <div>
                            <label for="admin-email">Email:</label>
                            <span id="admin-email">${admin.email}</span>
                        </div>
                    </section>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p>No admin details available.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
