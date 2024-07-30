<%@page import="Model.LoginModel"%>
<%@page import="Utils.StringUtils"%>
<%@page import="controller.DatabaseController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>header</title>
    <link rel="stylesheet" 
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>

<body>
    <nav>
        <div class="logo">
            <img src="${pageContext.request.contextPath}/Resources/Images/products/icons8-plus.svg" alt="logo" />
        </div>

        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/pages/index.jsp">Home</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/pages/Product.jsp">Product</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/pages/contact.jsp">Contact Us</a>
            </li>
            
        </ul>

        <div class="serarchbar">
            <form class="form-sec" action="${pageContext.request.contextPath}/SearchProductServlet" method="get">
			    <input type="text" name="searchQuery" placeholder="Search for products..." value="${param.searchQuery}">
                <button type="submit">
                    <img src="${pageContext.request.contextPath}/Resources/Images/products/icons8-search.svg" alt="">
                </button>
            </form>
            
            

        </div>
        
        <div class="cart-wish">
            
            <div class="cart-list-sec">
                <a href="${pageContext.request.contextPath}/pages/cart.jsp"><i class="fa fa-cart-arrow-down"></i></a>
            </div>
            <div class="user-login-sec">
                <% if (session.getAttribute("username") != null) { %>
                    <a href="${pageContext.request.contextPath}/pages/UserProfile.jsp"><i class="fa fa-user" aria-hidden="true"></i></a>
                <% } else { %>
                    <a href="${pageContext.request.contextPath}/pages/login.jsp"><i class="fa fa-sign-in" aria-hidden="true"></i></a>
                <% } %>
            </div>
            
             <div class="user-login-sec">
                <% if (session.getAttribute("username") != null) { %>
                    <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
                        <button type="submit" class="add-cart-bye btn-1">Logout</button>
                    </form>
                    
                <% } %>
            </div>
        </div>

        <div class="hamburger">
            <span class="line"></span>
            <span class="line"></span>
            <span class="line"></span>
        </div>
    </nav>

    <!-- phone navbar start -->
    <div class="menubar">
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/pages/index.jsp">Home</a>
            </li>
            <li>
                <a href="#">Services</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/pages/Product.jsp">Product</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/pages/contact.jsp">Contact Us</a>
            </li>
            <li>
                <% if (session.getAttribute("username") != null) { %>
                    <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
                        <button type="submit" class="add-cart-bye btn-1">Logout</button>
                    </form>
                <% } else { %>
                    <a href="${pageContext.request.contextPath}/pages/login.jsp" >Login</a>
                <% } %>
            </li>
        </ul>
    </div>
    <!-- phone navbar end -->

    <script src="${pageContext.request.contextPath}/js/index.js"></script>
</body>
</html>
