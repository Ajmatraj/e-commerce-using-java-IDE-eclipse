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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Stylesheets/headerFooter.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Stylesheets/product.css">
    
    <script>
        function showAlert(message) {
            alert(message);
        }
    </script>
    
    <%@ include file="/pages/header.jsp" %>
</head>

<style>
/* General Styles */
.sec-container {
    padding: 20px;
}

.sec-containerTxt {
    text-align: center;
    margin-bottom: 20px;
}

.product-container {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    justify-content: center;
}

.product-card {
    border: 1px solid #ddd;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    overflow: hidden;
    width: calc(33.333% - 20px); /* Three cards per row */
    margin: 10px;
    box-sizing: border-box;
    transition: transform 0.3s ease;
}

.product-card:hover {
    transform: scale(1.02);
}

.product-image img {
    width: 100%;
    height: auto;
}

.product-details {
    padding: 15px;
}

.buy-btn {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 15px;
}

.buy-pro, .add-cart-bye {
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 10px 15px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 14px;
    margin: 5px;
    cursor: pointer;
}

/* Responsive Styles */
@media (max-width: 1200px) {
    .product-card {
        width: calc(25% - 20px); /* Four cards per row */
    }
}

@media (max-width: 900px) {
    .product-card {
        width: calc(33.333% - 20px); /* Three cards per row */
    }
}

@media (max-width: 600px) {
    .product-card {
        width: calc(50% - 20px); /* Two cards per row */
    }
}

@media (max-width: 400px) {
    .product-card {
        width: calc(100% - 20px); /* One card per row */
    }
}

</style>
<body>



<c:if test="${not empty productList}">
<!-- Section 1 -->
    <div class="sec-container">
        <div class="sec-containerTxt">
            <h5>It's the quality that matters!</h5>
            <h1>Shop all latest offers and innovations</h1>
            <p>Technology Beyond Boundaries</p>
        </div>
   <div class="firstcard">
                <c:forEach var="prod" items="${productList}">
                    <!-- Product Card -->
                    <div class="sec-containerCard-1">
                        <div class="card-1">
                            <div class="cardimage-1">
                                <figure class="card-images">
                                    <img src="${pageContext.request.contextPath}/Resources/Images/addpro/${prod.imageUrlFromPart}" alt="${prod.name}" />
                                    <div>
                                        <a href="#"><img class="left-icon" src="${pageContext.request.contextPath}/Resources/Images/products/icons8-plus.svg" alt="Plus Icon"></a>
                                    </div>
                                </figure>
                            </div>
                            <div class="cardtxt-1">
                                <h3>${prod.name}</h3>
                                <p>Price: $${prod.price}</p>
                                <p>${prod.description}</p>
                                <div class="buy-btn">
                                    <a href="../pages/viewCart.html" class="buy-pro btn-1">Buy</a>
                                    <form action="${pageContext.request.contextPath}/addToCartServlet" method="post">
                                        <input type="hidden" name="productID" value="${prod.productID}">
                                        <input type="hidden" name="quantity" value="1">
                                        <button type="submit" class="add-cart-bye btn-1">Add To Cart</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End Product Card -->
        </c:forEach>
    </div>
    </div>
</c:if>

<c:if test="${empty productList}">
   <!-- Section 1 -->
    <div class="sec-container">
        <div class="sec-containerTxt">
            <h5>It's the quality that matters!</h5>
            <h1>Shop all latest offers and innovations</h1>
            <p>Technology Beyond Boundaries</p>
        </div>

        <c:if test="${not empty products}">
            <div class="firstcard">
                <c:forEach var="prod" items="${products}">
                    <!-- Product Card -->
                    <div class="sec-containerCard-1">
                        <div class="card-1">
                            <div class="cardimage-1">
                                <figure class="card-images">
                                    <img src="${pageContext.request.contextPath}/Resources/Images/addpro/${prod.imageUrlFromPart}" alt="${prod.name}" />
                                    <div>
                                        <a href="#"><img class="left-icon" src="${pageContext.request.contextPath}/Resources/Images/products/icons8-plus.svg" alt="Plus Icon"></a>
                                    </div>
                                </figure>
                            </div>
                            <div class="cardtxt-1">
                                <h3>${prod.name}</h3>
                                <p>Price: $${prod.price}</p>
                                <p>${prod.description}</p>
                                <div class="buy-btn">
                                    <a href="../pages/viewCart.html" class="buy-pro btn-1">Buy</a>
                                    <form action="${pageContext.request.contextPath}/addToCartServlet" method="post">
                                        <input type="hidden" name="productID" value="${prod.productID}">
                                        <input type="hidden" name="quantity" value="1">
                                        <button type="submit" class="add-cart-bye btn-1">Add To Cart</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End Product Card -->
                </c:forEach>
            </div>
        </c:if>

        <c:if test="${empty products}">
            <p>No products available.</p>
        </c:if>
    </div>
    <!-- Section 1 end here -->
</c:if>

 

<%@ include file="/pages/footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/index.js"></script>
</body>
</html>
