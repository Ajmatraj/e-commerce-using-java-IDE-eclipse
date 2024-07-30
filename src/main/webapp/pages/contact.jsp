<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contact Us page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"> 

  <%@include file="/pages/header.jsp"%>

</head>
<style>

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

.container {
    max-width: 800px;
    margin: 0 auto;
}

.contact-wrapper {
	margin: 10px;
    display: flex;
    flex-direction: column; 
    align-items: stretch;
    background-color: #f8f9ff;
    border-radius: 40px;
}

.message-form,
.information {
    width: 100%; 
    background-color: transparent;
    padding: 40px;
    margin-top: 30px;
    text-align: center;
}
.message-form{
    border-top-left-radius: 8px;
    border-bottom-left-radius: 8px;
}
.information{
    border-top-right-radius: 8px;
    border-bottom-right-radius: 8px;
}

.message-form h3,
.information h3 {
    font-size: 24px;
    margin-bottom: 20px;
    color: #414464;
}

.form-group {
    margin-bottom: 20px;
}

input,
textarea {
    width: calc(100% - 24px); 
    padding: 12px;
    border-radius: 8px;
    border: none;
    background-color: white;
    color: #333;
}

textarea {
    height: 100px;
}

input:focus,
textarea:focus {
    outline: none;
    box-shadow: 0 0 8px #414464;
}

button {
    display: inline-block;
    padding: 12px 24px;
    background-color: #414464;
    color: #fff;
    border: none;
    cursor: pointer;
    border-radius: 8px;
}

button:hover {
    background-color: black;
    color: #414464;
}

.information p {
    margin-bottom: 10px;
    color: #414464;
}

.location-container {
    box-sizing: border-box;
    text-align: center;
    margin-top: 40px;
    margin-bottom: 40px; 
    font-size: 20px;
    background-color: white;
    padding: 20px;
    border-radius: 8px;
}

.location-container h3 {
    font-size: 24px;
    margin-bottom: 40px;
    margin-top: 40px;
    color: #414464;
}

@media only screen and (min-width: 600px) {
    .contact-wrapper {
        flex-direction: row; 
    }

    .message-form,
    .information {
        width: 50%; 
    
    }
}
@media only screen and (max-width: 600px) {
    .location-container iframe {
        width: 100%;
        height: 300px; 
    }
    .location-container {
        width: 564px
    }
    
}
</style>
<body>
    <div class="container">
        <div class="contact-wrapper">
            <div class="message-form">
                <h3>Send Us a Message</h3>
                <form action="${pageContext.request.contextPath}/ContactServlet" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <input type="text" name="f_name" placeholder="Name" required="required">
                    </div>
                    <div class="form-group">
                        <input type="email" name="f_email" placeholder="Email" required="required">
                    </div>
                    <div class="form-group">
                        <input type="number" name="f_phoneNumber" placeholder="Phone Number" required="required">
                    </div>
                    <div class="form-group">
                        <input type="text" name="f_subject" placeholder="Subject" required="required"> 
                    </div>
                    <div class="form-group">
                        <textarea name="f_feedbackMessage" placeholder="Your Message" required="required"></textarea>
                    </div>
                    <button type="submit" value="Send">Send Message</button>
                </form>
            </div>
            <div class="information">
                <h3><i class="fas fa-phone"></i>Contact</h3>
                <p>Phone: +977 9808237846</p>
                <p>Email: abcd123@shop.com</p>
                <h3><i class="fas fa-map"></i>Address</h3>
                <p>Sankhamul, Lalitpur, Nepal</p>
                <h3><i class="fas fa-envelope"></i>Shop hours</h3>
                <p>SAT-SUN: 10:00AM-8:00PM</p>
                <p>MON-FRI: 9:00AM-8:00PM</p>
            </div>
        </div>
    </div>
    <section class="location-container">
        <div>
            <h3><i class="fas fa-map-marker-alt "></i>Our Location</h3>
            <p><iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3533.1971861731918!2d85.32778407525282!3d27.680299776198108!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x39eb19c0c2f610ef%3A0x9f813e4fddf80c54!2z4KS24KSC4KSW4KSu4KWB4KSyIOCkquClgeCksg!5e0!3m2!1sne!2snp!4v1713866323740!5m2!1sne!2snp" width="800" height="450" style="border:0;"></iframe></p>
        </div>
    </section>
    
      <%@include file="/pages/footer.jsp"%>
</body>
</html>
