<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>header</title>
  <link rel="stylesheet" 
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
  
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Stylesheets/product.css" />
</head>

<style>
@import url("https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap");

@import url('https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap');

:root{
    --bg-color: #2a2a2a;
    --second-bg-color:#202020;
    --text-color: #fff;
    --second-color: #ccc;
    --h-color: #9da0ae;
    --p-color: #26283b;
    --main-color: #414464;
    --big-font: clamp(30px, 5vw, 90px);
    --h1-font: clamp(35px, 4vw, 50px);
    --h2-font: clamp(16px, 2vw, 50px);
    --h3-font: clamp(14px, 2vw, 40px);
    --h4-font: clamp(14px, 2vw, 30px);
    --h5-font: clamp(14px, 1vw, 20px);
    --p-font: clamp(12px, 5vw, 15px);
    /* --p-font: clamp(14px,3vh,35px); */

}   

*{
    padding: 0;
    margin: 0;
    text-decoration: none;
    list-style: none;
    box-sizing: border-box;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  }
  body{
    padding: 0;
    margin: 0;
    font-family: "Poppins", serif;
    }
	
.logo-img{
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
}
.nav-sec-1{
  display: flex;
  flex-direction: column;
}
nav {
  padding: 5px 5%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
  z-index: 1;
}
nav .logo {
  display: flex;
  align-items: center;
}
nav .logo img {
  height: 25px;
  width: auto;
  margin-right: 10px;
}


nav ul {
  list-style: none;
  display: flex;
}
nav ul li {
  margin-left: 1.5rem;
}
nav ul li a {
  text-decoration: none;
  color: #000;
  font-size: 95%;
  font-weight: 400;
  padding: 4px 8px;
  border-radius: 5px;
}

nav ul li a:hover {
  background-color: #f5f5f5;
}

/* Style for the cart-wish container */
.cart-wish {
  display: flex;
  justify-content: center;
  align-items: center;
}

.wish-list-sec i{
  margin-right: 10px; 
  color: #f42b2b; 
  cursor: pointer;
}

.cart-list-sec i{
  margin-right: 10px; 
  color: #000000; 
  cursor: pointer;
}
.user-login-sec{
  margin-right: 10px; 
  color: #000000; 
  cursor: pointer;
}




.hamburger {
  display: none;
  cursor: pointer;
}

.hamburger .line {
  width: 25px;
  height: 1px;
  background-color: #c7c2c2;
  display: block;
  margin: 7px auto;
  transition: all 0.3s ease-in-out;
}
.hamburger-active {
  transition: all 0.3s ease-in-out;
  transition-delay: 0.6s;
  transform: rotate(45deg);
}

.hamburger-active .line:nth-child(2) {
  width: 0px;
}

.hamburger-active .line:nth-child(1),
.hamburger-active .line:nth-child(3) {
  transition-delay: 0.3s;
}

.hamburger-active .line:nth-child(1) {
  transform: translateY(12px);
}

.hamburger-active .line:nth-child(3) {
  transform: translateY(-5px) rotate(90deg);
}

.menubar {
  position: absolute;
  top: 0;
  left: -60%;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  width: 60%;
  height: 100vh;
  padding: 20% 0;
  background: rgba(255, 255, 255);
  transition: all 0.5s ease-in;
  z-index: 2;
}
.active {
  left: 0;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
}

.menubar ul {
  padding: 0;
  list-style: none;
}
.menubar ul li {
  margin-bottom: 32px;
}

.menubar ul li a {
  text-decoration: none;
  color: #000;
  font-size: 95%;
  font-weight: 400;
  padding: 5px 10px;
  border-radius: 5px;
}

.menubar ul li a:hover {
  background-color: #f5f5f5;
}
@media screen and (max-width: 790px) {
  .hamburger {
    display: block;
  }
  nav ul {
    display: none;
  }
}

.dropdown {
  display: inline-block;
  position: relative;
  z-index: 10;
}

.drop{
  border:none;
  border-radius:5px;
  font-size: 95%;
  font-weight: 400;
  padding: 4px 8px;
  border-radius: 5px;
  cursor:pointer;
}

button:hover{
  background-color:#ddd;
}

.dropdown-options {
  display: none;
  position: absolute;
  overflow: auto;
  background-color:#fff;
  border-radius:5px;
}

.dropdown:hover .dropdown-options {
  display: block;
}

.dropdown-options a {
  display: block;
  color: #000000;
  text-decoration: none;
  font-size: 95%;
  font-weight: 400;
  border-radius: 5px;
  padding: 14px 18px;
 
}

.dropdown-options a:hover {
  color: #000000;
  background-color:  #f5f5f5;
  border-radius:5px;
}




.form-sec button {
  border: none;
  background: none;
  color: #f8f8fe;
}
.form-sec button img{
  width: 17px;
  margin-top: 3px;
}
/* styling of whole input container */
.form-sec {
  position: relative;
  width: 200px;
  height: 40px;
  display: flex;
  align-items: center;
  padding-inline: 0.8em;
  border-radius: 30px;
  transition: border-radius 0.5s ease;
}
/* styling of Input */
.s-input {
  font-size: 0.9rem;
  background-color: transparent;
  width: 100%;
  height: 100%;
  padding-inline: 0.5em;
  padding-block: 0.7em;
  border: none;
}
/* styling of animated border */
.form-sec:before {
  content: "";
  position: absolute;
  background: var(--h-color);
  transform: scaleX(0);
  transform-origin: center;
  width: 100%;
  height: 2px;
  left: 0;
  bottom: 0;
  border-radius: 1px;
  transition: transform 0.3s ease;
}
/* Hover on Input */
.form-sec:focus-within {
  border-radius: 1px;
}

input:focus {
  outline: none;
}
/* here is code of animated border */
.form-sec:focus-within:before {
  transform: scale(1);
}
/* styling of close button */
/* == you can click the close button to remove text == */
.reset {
  border: none;
  background: none;
  opacity: 0;
  visibility: hidden;
}
/* close button shown when typing */
s-input:not(:placeholder-shown) ~ .reset {
  opacity: 1;
  visibility: visible;
}
	
</style>
<body>
	
	
 <!-- footer start  -->
 <footer class="footer-sec">
  <div class="footer">
    <div class="footer-logo">
      <h6>Logo</h6>
      <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit.</p>
      <div class="c-links">
        <li class="contact-links">address: kathmandu</li>
        <li class="contact-links">contact: +977-980000000</li>
        <li class="contact-links">email: Something@gmail.com</li>
      </div>
    </div>
    <div class="footer-links">
      <h3>Quick Links</h3>
      <div class="links-contact-footer">
        <li>links</li>
        <li>links</li>
        <li>links</li>
        <li>links</li>
      </div>
    </div>
    <div class="footer-contact">
      <h3>Contact</h3>
      <div class="links-contact-footer">
        <li><a href="${pageContext.request.contextPath}/index.jsp">home</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/Product.jsp">Product</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/login.jsp">Login</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/contact.jsp">contact</a></li>
      </div>
    </div>
    <div class="footer-find">
      <h3>Find Us</h3>
      <div class="find-sec">
        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
        <div class="email-sec">
          <div class="input-container">
            <input
              type="text"
              name="text"
              class="input"
              placeholder="Enter Mail"
            />
            <div class="highlight"></div>
          </div>
          <button>
            <span>SUBMIT</span>
          </button>
        </div>
      </div>
    </div>
  </div>
</footer>
<!-- footer end  -->


	
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
    
</body>
</html>