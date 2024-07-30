<%@ page import="java.util.List" %>
<%@ page import="controller.DatabaseController" %>
<%@ page import="Model.ProductModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    // Initialize the DatabaseController and fetch the product list
    DatabaseController dbController = new DatabaseController();
    List<ProductModel> products = dbController.getAllProductInfo();
    request.setAttribute("products", products);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Stylesheets/headerFooter.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Stylesheets/product.css">
    <%@ include file="/dashboard/adminHeader.jsp" %>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .sec-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .table-container {
            overflow-x: auto;
        }
        @media (max-width: 768px) {
            table, th, td {
                display: block;
                width: 100%;
            }
            thead {
                display: none;
            }
            tr {
                margin-bottom: 10px;
                border-bottom: 2px solid #ddd;
                display: block;
            }
            td {
                display: flex;
                justify-content: space-between;
                padding: 10px;
               border: 1px solid #ddd;
                position: relative;
                text-align: left;
            }
            td::before {
                content: attr(data-label);
                position: absolute;
                left: 0;
                width: 50%;
                padding-left: 10px;
                font-weight: bold;
                text-align: left;
                white-space: nowrap;
            }
        }
         .deleteProductButton ,
         .editProductButton{
            display: inline-block; /* Allows the anchor tag to behave like a button */
            background-color: #414464; /* Button background color */
            color: white; /* Text color */
            text-decoration: none; /* Remove underline */
            border: none; /* Remove default border */
            border-radius: 4px; /* Rounded corners */
            padding: 10px 20px; /* Padding */
            font-size: 16px; /* Font size */
            cursor: pointer; /* Cursor style */
            transition: background-color 0.3s; /* Transition for background color */
            text-align: center; /* Center text */
        }
        .addpro{
        margin: 10px
        }

        .deleteProductButton:hover {
            background-color: #333; /* Background color on hover */
        }

        .deleteProductButton:active {
            background-color: #555; /* Background color on click */
        }
        .from-btn{
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        gap:10px
        }
    </style>
</head>
<body>
    <c:if test="${not empty products}">
        <div class="sec-container">
            <h1>Product List</h1>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Image</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Description</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="prod" items="${products}">
                            <tr>
                                <td><img src="${pageContext.request.contextPath}/Resources/Images/addpro/${prod.imageUrlFromPart}" alt="${prod.name}" style="width: 100px; height: auto;"></td>
                                <td>${prod.name}</td>
                                <td>$${prod.price}</td>
                                <td>${prod.description}</td>
                                <td>
                                <div class="from-btn">
                    				<form action="${pageContext.request.contextPath}/dashboard/editProduct.jsp" method="get">
							            <input type="hidden" name="productID" value="${prod.productID}">
							            <button type="submit" class="editProductButton">Edit</button>
							        </form>
                    				<form action="${pageContext.request.contextPath}/deleteProduct" method="post">
                        					<input type="hidden" name="productID" value="${prod.productID}">
                        					<button type="submit" class="deleteProductButton">Delete</button>
                    				</form>
                    				</div>
                    			</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                
            </div>
        </div>
    </c:if>

    <c:if test="${empty products}">
        <p>No products available.</p>
        
    </c:if>
    
   <a href="${pageContext.request.contextPath}/dashboard/addProduct.jsp" class="deleteProductButton addpro">Add Product</a>

    <%@ include file="/pages/footer.jsp" %>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
</body>
</html>
