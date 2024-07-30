<%@ page import="java.util.List" %>
<%@ page import="controller.DatabaseController" %>
<%@ page import="Model.CartModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    // Initialize the DatabaseController and fetch cart items
    DatabaseController dbController = new DatabaseController();
    List<CartModel> cartItems = dbController.getCartItems((String)session.getAttribute("username")); // Fetch cart items based on the logged-in user
    request.setAttribute("cartItems", cartItems);
%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="/pages/header.jsp"%>
    <meta charset="UTF-8">
    <title>Cart Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Stylesheets/cart.css">
    <style>
        /* General Styles */
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }

        table th, table td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }

        table th {
            background-color: #f4f4f4;
        }

        .product-image img {
            width: 100px;
            height: auto;
        }

        .btn {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 5px 10px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 12px;
            margin: 5px;
            cursor: pointer;
        }

        .btn-remove {
            background-color: #f44336;
        }

        .btn-update {
            background-color: #008CBA;
        }

        .btn-place-order {
            background-color: #ff9800;
        }

        .responsive-table {
            overflow-x: auto;
        }

        @media (max-width: 600px) {
            table, thead, tbody, th, td, tr {
                display: block;
            }
            
            th {
                display: none;
            }

            tr {
                margin-bottom: 1rem;
                border-bottom: 2px solid #ddd;
            }

            td {
                position: relative;
                padding-left: 50%;
                white-space: nowrap;
                text-align: right;
            }

            td::before {
                content: attr(data-label);
                position: absolute;
                left: 0;
                width: 45%;
                padding-left: 10px;
                font-weight: bold;
                text-align: left;
            }
        }
    </style>
</head>
<body>
<% 
String successMessage = request.getParameter("successMessage");
String errorMessage = request.getParameter("errorMessage");

if (successMessage != null) {
    out.println("<div class='alert alert-success'>" + successMessage + "</div>");
}

if (errorMessage != null) {
    out.println("<div class='alert alert-danger'>" + errorMessage + "</div>");
}
%>

<div class="responsive-table">
    <table>
        <thead>
            <tr>
                <th>Image</th>
                <th>Product ID</th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${cartItems}">
                <tr>
                    <td class="product-image">
                        <img src="${pageContext.request.contextPath}/Resources/Images/addpro/${item.imgLink}" alt="${item.name}">
                    </td>
                    <td>${item.productID}</td>
                    <td>${item.name}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/updateQuantityServlet" method="post">
                            <input type="hidden" name="cartId" value="${item.cart_Id}">
                            <input type="number" name="quantity" value="${item.quantity}" min="1" style="width: 60px;">
                            <button type="submit" class="btn btn-update">Update</button>
                        </form>
                    </td>
                    <td>$${item.price}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/removeFromCartServlet" method="post">
                            <input type="hidden" name="productID" value="${item.productID}">
                            <button type="submit" class="btn btn-remove">Remove</button>
                        </form>

                        <!-- Place Order Button for Each Product -->
                        <form action="${pageContext.request.contextPath}/placeOrderServlet" method="post">
                            <input type="hidden" name="productID" value="${item.productID}">
                            <input type="hidden" name="quantity" value="${item.quantity}">
                            
                            <!-- Include phone number and email from UserModel stored in session -->
                            <input type="hidden" name="phoneNumber" value="${sessionScope.userModel.phone_number}">
                            <input type="hidden" name="email" value="${sessionScope.userModel.email}">
                            
                            <button type="submit" class="btn btn-place-order">Place Order</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="4"><strong>Total Price</strong></td>
                <td class="total-price"><strong>$0.00</strong></td>
                <td></td>
            </tr>
            <tr>
                <td colspan="4"><strong>Total Products</strong></td>
                <td class="total-products"><strong>0</strong></td>
                <td></td>
            </tr>
        </tfoot>
    </table>
</div>

<c:if test="${empty cartItems}">
    <p>Your cart is empty.</p>
</c:if>

<!-- Include footer or other content -->

<script>
    document.addEventListener("DOMContentLoaded", function() {
        // Function to update the total price and product count
        function updateCartSummary() {
            let totalPrice = 0;
            let totalProducts = 0;
            
            // Get all rows in the cart table body
            const rows = document.querySelectorAll("table tbody tr");

            rows.forEach(row => {
                // Get the quantity and price from the row
                const quantity = parseInt(row.querySelector("input[name='quantity']").value, 10);
                const price = parseFloat(row.querySelector("td:nth-child(5)").textContent.replace('$', ''));
                
                // Calculate the total for the row and add it to the total price
                const rowTotal = quantity * price;
                totalPrice += rowTotal;
                
                // Increment the product count
                totalProducts += quantity;
            });

            // Display the total price and product count
            document.querySelector(".total-price strong").textContent = `$${totalPrice.toFixed(2)}`;
            document.querySelector(".total-products strong").textContent = totalProducts;
        }

        // Initial calculation
        updateCartSummary();

        // Update the total price and product count when quantity changes
        document.querySelectorAll("input[name='quantity']").forEach(input => {
            input.addEventListener("change", updateCartSummary);
        });
    });
</script>
<%@include file="/pages/footer.jsp"%>
<script src="${pageContext.request.contextPath}/js/index.js"></script>
</body>
</html>
