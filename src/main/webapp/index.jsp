<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Electronics Gadget Shop</title>
    
    
    <link rel="stylesheet" 
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Stylesheets/hearderFooter.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Stylesheets/style.css">
    
    
   
  </head>
  <%@include file="/pages/header.jsp"%>
 
 
  <body>
  
  
<!-- Slideshow container hero section strat-->
<div class="slideshow-container">
  
  <!-- Full-width images with number and caption text -->
  <div class="mySlides fade">
    <img src="${pageContext.request.contextPath}/Resources/Images/products/hero-img-1.avif" style="width:100%">
    <div class="text">
      <div class="back-effect">
      <h1>iPhone</h1>
      <P>Makes your life more like it</P>
      <div class="header-btn">
        <button>Hover me</button>
      </div>
    </div>
  </div>
  </div>
  
  <div class="mySlides fade">
    <img src="${pageContext.request.contextPath}/Resources/Images/products/galaxy-s24-ultra.avif" style="width:100%">
    <div class="text">
      <div class="back-effect">
      <P>Slim Body</P>
      <h1>Samsung</h1>
      <div class="header-btn">
        <button>Hover me</button>
      </div>
    </div>
    </div>
  </div>
  
  <div class="mySlides fade">
    <img src="${pageContext.request.contextPath}/Resources/Images/products/OnePlus.jpg">
    <div class="text">
      <div class="back-effect">
      <P>The wait is over!</P>
      <h1>Hop on to the trend</h1>
      <h1>With your OnePlus</h1>
      <div class="header-btn">
        <button>Hover me</button>
      </div>
    </div>
    </div>
  </div>

  <!-- Next and previous buttons -->
  <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
  <a class="next" onclick="plusSlides(1)">&#10095;</a>

</div>


<!-- The dots/circles -->
<div style="text-align:center" class="dot-net-btn">
  <span class="dot" onclick="currentSlide(1)"></span>
  <span class="dot" onclick="currentSlide(2)"></span>
  <span class="dot" onclick="currentSlide(3)"></span>
</div>

<!-- Slideshow container hero section end-->
<script type="text/javascript">
document.addEventListener("DOMContentLoaded", function() {
	  const buttons = document.querySelectorAll('.clickButtons');
	  const paragraphs = document.querySelectorAll('.textC');
	  
	  // Initially hide all paragraphs
	  paragraphs.forEach(paragraph => paragraph.style.display = 'none');
	  
	  buttons.forEach((button, index) => {
	    button.addEventListener('click', function() {
	      // Hide all paragraphs
	      paragraphs.forEach(paragraph => paragraph.style.display = 'none');
	      
	      // Show the clicked paragraph
	      paragraphs[index].style.display = 'block';
	    });
	  });
	});

</script>

 <!-- section-1  -->
 <div class="sec-contrainer">
  <div class="sec-containerTxt">
    <h5>Its the qualitly that matters!</h5>
    <h1>Shop all latest offers and innovations</h1>
    <p>Technology Beyond Boundaries</p>
  </div>
  <div class="firstcard">
    <div class="sec-containerCard-1">
      <div class="card-1">
        <div class="cardimage-1">
            <figure class="card-images">
              <img src="${pageContext.request.contextPath}/Resources/Images/products/galaxy-s24-ultra.jpg" alt="" />         
            <div>
              <a href="#"><img class="left-icon" src="${pageContext.request.contextPath}/Resources/Images/products/icons8-plus.svg" alt=""></i></a>
            </div>			
          </figure>
        <div class="cardtxt-1">
          <h3>Samsung Galaxy S24 Ultra</h3>
          <p>ProVisual Exngine </p>
          <p>Capture details that rival reality with 200MP Camera</p>
        </div>
      </div>
    </div>
    </div>

    <div class="sec-containerCard-1">
      <div class="card-1">
        <div class="cardimage-1">
          <figure class="card-images">
            <img src="${pageContext.request.contextPath}/Resources/Images/products/iPhone-15-Pro-Max-Tren-tay-9.jpg" alt="" />         
          <div>
            <a href="#"><img class="left-icon" src="${pageContext.request.contextPath}/Resources/Images/products/icons8-search.svg" alt=""></i></a>
          </div>			
        </figure>        
      </div>
        <div class="cardtxt-1">
          <h3>iPhone 15 Pro</h3>
          <p>A17 Pro chip</p>
          <p>The biggest redesign in the history of Apple GPUs</p>
        </div>
      </div>
    </div>

    <div class="sec-containerCard-1">
      <div class="card-1">
        <div class="cardimage-1">
          <figure class="card-images">
            <img src="${pageContext.request.contextPath}/Resources/Images/products/OnePlus.jpg" alt="" />         
          <div>
            <a href="#"><img class="left-icon" src="${pageContext.request.contextPath}/Resources/Images/products/icons8-checkmark.svg" alt=""></a>
          </div>			
        </figure>  
        </div>
        <div class="cardtxt-1">
          <h3>OnePlus 12</h3>
          <p>114° ultra-wide camera</p>
          <p>Capture crystal-clear moments framed in perfection</p>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- section-1 end here  -->
      
<!-- section-2 start  -->
<div class="section-2">
  <div class="section-2-images">
    <img src="${pageContext.request.contextPath}/Resources/Images/products/magsafe__bfji5hb1mqsy_xlarge.jpg" alt="" />
  </div>
  <div class="section-2-txt">
    <div class="sec-linkes-2">
      <h5>lable</h5>
      <h2>Learning Something New,</h2>
      <h2>Grow your Skills</h2>
      <div class="title-link-to-taggle">
        <li class="clickButtons">Courses</li>
        |
        <li class="clickButtons">Blogs</li>
        |
        <li class="clickButtons">Prices</li>
      </div>
      <div class="para-to-taggle textCon">
        <br />
        <p id="blueOne" class="textC">
          It is a long established fact that a reader will be distracted by
          the readable content of a page when looking at its layout. The
          point of using Lorem Ipsum is that it has a more-or-less normal
          distribution of letters, as opposed
        </p>
        <p id="redOne" class="textC">
          Lorem Ipsum is simply dummy text of the printing and typesetting
          industry. Lorem Ipsum has been the industry's standard dummy text
          ever since the 1500s, when an unknown printer took a galley of
          type and scrambled it to make.
        </p>
        <p id="greenOne" class="textC">
          dummy text of the printing and typesetting
          industry. Lorem Ipsum has been the industry's standard dummy text
          ever since the 1500s, when an unknown printer took a galley of
          type and scrambled it to make.
        </p>
      </div>

      <button class="learn-more">
        <span class="circle" aria-hidden="true">
          <span class="icon arrow"></span>
        </span>
        <span class="button-text">View More</span>
      </button>
    </div>
  </div>
</div>


 <!-- galley start  -->
 <div class="galler-section">
  <h1>OUR GALLERY</h1>
  <p>Lorem ipsum dolor sit</p>
  <div class="gallery">
    <figure class="gallery__item gallery__item--1">
      <img src="${pageContext.request.contextPath}/Resources/Images/products/launch_meta-image_Samsung-S24-Ultra_EN.png" alt="" class="gallery__img" />
    </figure>
    <figure class="gallery__item gallery__item--2">
      <img src="${pageContext.request.contextPath}/Resources/Images/products/iPhone-15-Pro-Max-Tren-tay-9.jpg" alt="" class="gallery__img" />
    </figure>
    <figure class="gallery__item gallery__item--3">
      <img src="${pageContext.request.contextPath}/Resources/Images/products/galaxy-s24-ultra.jpg" alt="" class="gallery__img" />
    </figure>
    <figure class="gallery__item gallery__item--4">
      <img src="${pageContext.request.contextPath}/Resources/Images/products/image-175.jpg" alt="" class="gallery__img" />
    </figure>
    <figure class="gallery__item gallery__item--5">
      <img src="${pageContext.request.contextPath}/Resources/Images/products/launch_meta-image_Samsung-S24-Ultra_EN.png" alt="" class="gallery__img" />
    </figure>
    <figure class="gallery__item gallery__item--6">
      <img src="${pageContext.request.contextPath}/Resources/Images/products/Nothing-phone-2.jpg" alt="" class="gallery__img" />
    </figure>
  </div>
</div>
<!-- galley end  -->


 <%@include file="/pages/footer.jsp"%>

 
<script src="${pageContext.request.contextPath}/js/index.js"></script>
</body>
</html>
