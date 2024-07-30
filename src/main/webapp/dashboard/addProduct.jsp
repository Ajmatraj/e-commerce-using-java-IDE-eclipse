
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="Utils.StringUtils"%>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
    <%@ include file="/dashboard/adminHeader.jsp" %>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f8f8;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 50%;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }
        input[type="text"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 10px;
            margin: 6px 0 12px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .success-message, .error-message {
            text-align: center;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 4px;
        }
        .success-message {
            background-color: #dff0d8;
            color: #3c763d;
        }
        .error-message {
            background-color: #f2dede;
            color: #a94442;
        }
    </style>
</head>
<body>
    <div class="container">
        <%
            // Check if the URL contains the 'success' query parameter
            String successParam = request.getParameter("success");
            boolean success = "true".equalsIgnoreCase(successParam); // Ignore case to ensure case-insensitive comparison

            if (success) {
        %>
            <div class="success-message">Product added successfully!</div>
        <%
            }

            // Check if there's an error message to display
            String errorMessage = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
            if (errorMessage != null && !errorMessage.isEmpty()) {
        %>
            <div class="error-message"><%= errorMessage %></div>
        <%
            }
        %>
        <h2>Add New Product</h2>
        <form action="${pageContext.request.contextPath}/AddProductServlet" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="productID">Product ID:</label>
                <input type="text" id="productID" name="productID" required>
            </div>
            <div class="form-group">
                <label for="productName">Product Name:</label>
                <input type="text" id="productName" name="productName" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" rows="4" placeholder="Enter product description" required></textarea>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" id="price" name="price" required>
            </div>
            <div class="form-group">
                <label for="productImage">Product Image:</label>
                <input type="file" id="productImage" name="productImage" required>
            </div>
            <input type="submit" value="Add Product">
        </form>
    </div>
</body>
</html>
