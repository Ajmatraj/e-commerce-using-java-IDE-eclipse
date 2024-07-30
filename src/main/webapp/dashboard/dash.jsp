<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Profile</title>
    <%@ include file="/dashboard/adminHeader.jsp" %>
    <style>
@import url("https://fonts.googleapis.com/css?family=Poppins&display=swap");
@import url("https://fonts.googleapis.com/css?family=Bree+Serif&display=swap");

* {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}

body {
  background: #e9e9e9;
  overflow-x: hidden;
  
  font-family: "Poppins", sans-serif;
  
}

.profile-header {
  background: #fff;
  width: 100%;
  display: flex;
  height: 190px;
  position: relative;
  box-shadow: 0px 3px 4px rgba(0, 0, 0, 0.2);
}

.profile-img {
  float: left;
  width: 340px;
  height: 200px;
}

.profile-img img {
  border-radius: 50%;
  height: 230px;
  width: 230px;
  border: 5px solid #fff;
  box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.2);
  position: absolute;
  left: 50px;
  top: 20px;
  z-index: 5;
  background: #fff;
}

.profile-nav-info {
  float: left;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding-top: 60px;
}

.profile-nav-info h3 {
  font-variant: small-caps;
  font-size: 2rem;
  font-family: sans-serif;
  font-weight: bold;
}

.profile-nav-info .address {
  display: flex;
  font-weight: bold;
  color: #777;
}

.profile-nav-info .address p {
  margin-right: 5px;
}

.profile-option {
  width: 40px;
  height: 40px;
  position: absolute;
  right: 50px;
  top: 50%;
  transform: translateY(-50%);
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  transition: all 0.5s ease-in-out;
  outline: none;
  background: #414464;
}

.profile-option:hover {
  background: #fff;
  border: 1px solid #414464;
}
.profile-option:hover .notification i {
  color: #414464;
}

.profile-option:hover span {
  background: #414464;
}

.profile-option .notification i {
  color: #fff;
  font-size: 1.2rem;
  transition: all 0.5s ease-in-out;
}

.profile-option .notification .alert-message {
  position: absolute;
  top: -5px;
  right: -5px;
  background: #fff;
  color: #414464;
  border: 1px solid #414464;
  padding: 5px;
  border-radius: 50%;
  height: 20px;
  width: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 0.8rem;
  font-weight: bold;
}

.main-bd {
  width: 100%;
  display: flex;
  padding-right: 15px;
}

.profile-side {
  width: 300px;
  background: #fff;
  box-shadow: 0px 3px 5px rgba(0, 0, 0, 0.2);
  padding: 90px 30px 20px;
  font-family: "Bree Serif", serif;
  margin-left: 10px;
  z-index: 99;
}

.profile-side p {
  margin-bottom: 7px;
  color: #333;
  font-size: 14px;
}

.profile-side p i {
  color: #414464;
  margin-right: 10px;
}

.mobile-no i {
  transform: rotateY(180deg);
  color: #414464;
}

.profile-btn {
  display: flex;
}

button.chatbtn,
button.createbtn {
  border: 0;
  padding: 10px;
  width: 100%;
  border-radius: 3px;
  background: #414464;
  color: #fff;
  font-family: "Bree Serif";
  font-size: 1rem;
  margin: 5px 2px;
  cursor: pointer;
  outline: none;
  margin-bottom: 10px;
  transition: background 0.3s ease-in-out;
  box-shadow: 0px 5px 7px 0px rgba(0, 0, 0, 0.3);
}

button.chatbtn:hover,
button.createbtn:hover {
  background: rgba(288, 0, 70, 0.9);
}

button.chatbtn i,
button.createbtn i {
  margin-right: 5px;
}

.user-rating {
  display: flex;
}

.user-rating h3 {
  font-size: 2.5rem;
  font-weight: 200;
  margin-right: 5px;
  letter-spacing: 1px;
  color: #666;
}

.user-rating .no-of-user-rate {
  font-size: 0.9rem;
}

.rate {
  padding-top: 6px;
}

.rate i {
  font-size: 0.9rem;
  color: #414464;
}

.nav {
  width: 100%;
  z-index: -1;
}

.nav ul {
  display: flex;
  justify-content: space-around;
  list-style-type: none;
  height: 40px;
  background: #fff;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.3);
}

.nav ul li {
  padding: 10px;
  width: 100%;
  cursor: pointer;
  text-align: center;
  transition: all 0.2s ease-in-out;
}

.nav ul li:hover,
.nav ul li.active {
  box-shadow: 0px -3px 0px rgba(288, 0, 70, 0.9) inset;
}

.profile-body {
  width: 100%;
  z-index: -1;
}

.tab {
  display: none;
}

.tab {
  padding: 20px;
  width: 100%;
  text-align: center;
}

@media (max-width: 1100px) {
  .profile-side {
    width: 250px;
    padding: 90px 15px 20px;
  }

  .profile-img img {
    height: 200px;
    width: 200px;
    left: 50px;
    top: 50px;
  }
}

@media (max-width: 900px) {
  body {
    margin: 0 20px;
  }

  .profile-header {
    display: flex;
    height: 100%;
    flex-direction: column;
    text-align: center;
    padding-bottom: 20px;
  }

  .profile-img {
    float: left;
    width: 100%;
    height: 200px;
  }

  .profile-img img {
    position: relative;
    height: 200px;
    width: 200px;
    left: 0px;
  }

  .profile-nav-info {
    text-align: center;
  }

  .profile-option {
    right: 20px;
    top: 75%;
    transform: translateY(50%);
  }

  .main-bd {
    flex-direction: column;
    padding-right: 0;
  }

  .profile-side {
    width: 100%;
    text-align: center;
    padding: 20px;
    margin: 5px 0;
  }

  .profile-nav-info .address {
    justify-content: center;
  }

  .user-rating {
    justify-content: center;
  }
}

@media (max-width: 400px) {
  body {
    margin: 5;
  }

  .profile-header h3 {
  }

  .profile-option {
    width: 30px;
    height: 30px;
    position: absolute;
    right: 15px;
    top: 83%;
  }

  .profile-option .notification .alert-message {
    top: -3px;
    right: -4px;
    padding: 4px;
    height: 15px;
    width: 15px;
    font-size: 0.7rem;
  }

  .profile-nav-info h3 {
    font-size: 1.9rem;
  }

  .profile-nav-info .address p,
  .profile-nav-info .address span {
    font-size: 0.7rem;
  }
}
#see-more-bio,
#see-less-bio {
  color: blue;
  cursor: pointer;
  text-transform: lowercase;
}
.tab h1 {
  font-family: "Bree Serif", sans-serif;
  display: flex;
  justify-content: center;
  margin: 20px auto;
}

</style>
    
</head>
<body>

<!-- dashboard start -->
<div class="container">
    <c:choose>
        <c:when test="${not empty adminList}">
            <c:forEach var="admin" items="${adminList}">
                <div class="profile-header">
                    <div class="profile-img">
                        <img src="${pageContext.request.contextPath}/Resources/Images/admin/${admin.imageUrlFromDB}" width="200" alt="Profile Image">
                    </div>
                    <div class="profile-nav-info">
                        <h3 class="user-name">${admin.username}</h3>
                        <div class="address">
                            <p id="state" class="state">New York,</p>
                            <span id="country" class="country">USA.</span>
                        </div>
                    </div>
                </div>

                <div class="main-bd">
                    <div class="left-side">
                        <div class="profile-side">
                            <p class="user-mail"><i class="fa fa-envelope"></i> ${admin.email}</p>
                            <div class="user-bio">
                                <h3>Bio</h3>
                                <p class="bio">
                                    <!-- You can dynamically add more content here -->
                                </p>
                            </div>
                            <div class="profile-btn">
                                <button class="chatbtn" id="chatBtn"><i class="fa fa-comment"></i> EDIT PROFILE</button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p>No admin details available.</p>
        </c:otherwise>
    </c:choose>
</div>
<!-- dashboard end here -->

<script type="text/javascript">
    $(".nav ul li").click(function() {
        $(this).addClass("active").siblings().removeClass("active");
    });

    const tabBtn = document.querySelectorAll(".nav ul li");
    const tab = document.querySelectorAll(".tab");

    function tabs(panelIndex) {
        tab.forEach(function(node) {
            node.style.display = "none";
        });
        tab[panelIndex].style.display = "block";
    }
    tabs(0);

    let bio = document.querySelector(".bio");
    const bioMore = document.querySelector("#see-more-bio");
    const bioLength = bio.innerText.length;

    function bioText() {
        bio.oldText = bio.innerText;

        bio.innerText = bio.innerText.substring(0, 100) + "...";
        bio.innerHTML += `<span onclick='addLength()' id='see-more-bio'>See More</span>`;
    }

    bioText();

    function addLength() {
        bio.innerText = bio.oldText;
        bio.innerHTML += "&nbsp;" + `<span onclick='bioText()' id='see-less-bio'>See Less</span>`;
        document.getElementById("see-less-bio").addEventListener("click", () => {
            document.getElementById("see-less-bio").style.display = "none";
        });
    }

    if (document.querySelector(".alert-message") && document.querySelector(".alert-message").innerText > 9) {
        document.querySelector(".alert-message").style.fontSize = ".7rem";
    }
</script>
</body>
</html>
 --%>