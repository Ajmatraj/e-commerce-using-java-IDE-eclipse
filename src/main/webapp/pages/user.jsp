<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <style>
.navbar-brand img {
    height: 50px;
}

.nav-links, .nav-icons {
    list-style: none;
    display: flex;
    color: aliceblue;
}

.nav-links li, .nav-icons a {
    margin-left: 20px;
}

.nav-links a {
    text-decoration: none;
    color: white;
    font-size: 16px;
}

.nav-icons img {
    height: 24px;
}

.burger {
    display: none;
    flex-direction: column;
    cursor: pointer;
}

.burger .bar {
    background-color: white;
    height: 3px;
    width: 25px;
    margin: 3px 0;
    transition: all 0.3s ease;
}

@media (max-width: 768px) {
    .nav-links, .nav-icons {
        flex-direction: column;
        position: absolute;
        right: 0;
        top: 60px;
        background-color: #333;
        width: 100%;
        display: none;
    }

    .nav-links li, .nav-icons a {
        margin: 10px;
        align-items: center;
        justify-content: center;
    }

    .burger {
        display: flex;
    }
}.navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #1c038e;
    color: white;
    padding: 10px 20px;
}



  .container{
  background-color:white;
  height:300px;
  width:1200px;
  display:grid;
  grid-template-rows: 50px 50px 50px;
  grid-template-columns: 50px 50px 50px;
  row-gap:12px;
  column-gap:397px;
  margin-right: auto;
  margin-left: auto;

  
  }
  h1{
  text-align: center;
  }
  
  .grid{
  background-color:black;
  height: 200px;
  width: 290px;
  margin-top:20px;
  border: 1px solid black;
  text-align:left;
  padding-left:5px;
  color: white;
  }

</style>
</head>
<body>
 <div class="Background">
        <!-- Content for your background div -->
   <nav class="navbar">
        <div class="navbar-brand">
            <img src="Yantra Logo.png" alt="Shop Logo">
        </div>
        <ul class="nav-links">
            <li><a href="#home">Home</a></li>
            <li><a href="#products">Products</a></li>
            <li><a href="#about">About Us</a></li>
            <li><a href="#contact">Contact</a></li>
        </ul>
        <div class="nav-icons">
            <a href="#search"><img src="search-icon.png" alt="Search"></a>
            <a href="#cart"><img src="cart-icon.png" alt="Cart"></a>
            <a href="#user"><img src="user-icon.png" alt="User"></a>
        </div>
        <button class="burger">
            <span class="bar"></span>
            <span class="bar"></span>
            <span class="bar"></span>
        </button>
    </nav>
     <h1>Manage my account</h1>
    <div class="container">
   
    <div class="grid">
    <h2>Personal information</h2>
    <p>Ram karki</p>
    <p>Ramk56@gmail.com</p>
    </div>
    <div class="grid">
    <h2>Address Book</h2>
    <p>Islington college</p>
    <p>Ram karki</p>
    <p>9837291349</p>
    </div>
    <div class="grid">
    
    <h2>Deafult Billing Address</h2>
    <p>Islington college</p>
    <p>9837291349</p>
    </div>
<table>
<thead>
</table>
    </div>
</body>
</html>