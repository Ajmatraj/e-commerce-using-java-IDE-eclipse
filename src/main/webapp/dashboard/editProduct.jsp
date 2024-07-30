<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controller.DatabaseController" %>
<%@ page import="Model.ProductModel" %>

<%
    String productID = request.getParameter("productID");
    DatabaseController dbController = new DatabaseController();
    ProductModel product = dbController.getProductByID(productID);
    request.setAttribute("product", product);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Stylesheets/form.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .form-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }

        input[type="text"], textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        textarea {
            resize: vertical;
            height: 100px;
        }

        .submitButton {
            display: inline-block;
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .submitButton:hover {
            background-color: #45a049;
        }

        @media (max-width: 768px) {
            .form-container {
                width: 90%;
                margin: 20px auto;
            }

            input[type="text"], textarea {
                padding: 8px;
                font-size: 14px;
            }

            .submitButton {
                padding: 12px;
                font-size: 18px;
            }
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h1>Edit Product</h1>
        <form action="${pageContext.request.contextPath}/UpdateProductServlet" method="post">
            <input type="hidden" name="productID" value="${product.productID}">
            
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${product.name}" required>
            
            <label for="price">Price:</label>
            <input type="text" id="price" name="price" value="${product.price}" required>
            
            <label for="description">Description:</label>
            <textarea id="description" name="description" required>${product.description}</textarea>

            <label for="imageUrl">Product Image:</label>
            <input type="file" id="imageUrl" name="imageUrl" required>
            
            <button type="submit" class="submitButton">Update</button>
        </form>
    </div>
</body>
</html>
