<%@ page import="controller.DatabaseController" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.OrderModel" %>

<%
    // Initialize the DatabaseController and fetch the order list
    DatabaseController dbController = new DatabaseController();
    List<OrderModel> orders = dbController.getAllOrderINFO();
    request.setAttribute("orders", orders);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Details</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Add any custom styles here */
        .table-responsive {
            margin-top: 20px;
        }
    </style>
     <%@ include file="/dashboard/adminHeader.jsp" %>
</head>
<body>
    <div class="container">
        <h1 class="my-4">Order Details</h1>
        <div class="table-responsive">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total Price</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                    if (orders != null && !orders.isEmpty()) {
                        for (OrderModel order : orders) { 
                    %>
                        <tr>
                            <td><%= order.getOrderId() %></td>
                            <td><%= order.getProductId() %></td>
                            <td><%= order.getProductName() %></td>
                            <td><%= order.getProductDescription() %></td>
                            <td><%= order.getPrice() %></td>
                            <td><%= order.getQuantity() %></td>
                            <td><%= order.getTotalPrice() %></td>
                        </tr>
                    <% 
                        } 
                    } else { 
                    %>
                        <tr>
                            <td colspan="7" class="text-center">No orders found</td>
                        </tr>
                    <% 
                    } 
                    %>
                </tbody>
            </table>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
