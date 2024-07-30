<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Stylesheets/viewcart.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Stylesheets/hearderFooter.css">


</head>
<body>
	
	<div class="product-container">

        <div class="product-image">
            <img src="${pageContext.request.contextPath}/Resources/Images/products/MT223.jpeg" alt="Men's Fashion T Shirt">
          
            <div class="thumbnail-images">
                <img src="${pageContext.request.contextPath}/Resources/Images/products/MT223.jpeg" alt="Blue T-Shirt">
                <img src="${pageContext.request.contextPath}/Resources/Images/products/Picture2.png" alt="White T-Shirt">
                <img src="${pageContext.request.contextPath}/Resources/Images/products/iphone-card-40-government-202403.jpeg" alt="Red T-Shirt">
                <img src="${pageContext.request.contextPath}/Resources/Images/products/Picture3.png" alt="Green T-Shirt">
            </div>
        </div>

        <div class="product-details">
            <h1>Men's Fashion T Shirt</h1>
            <p class="price">$139.00</p>
           
            <div class="quantity">
                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" min="1" value="1">
            </div>
            <button class="add-to-cart">Add to Cart</button>
            <div class="product-description">
                <h2>Product Details</h2>
                <p>The Gildan Ultra Cotton T-shirt is made from a substantial 6.0 oz. per sq. yd. fabric constructed from 100% cotton, this classic fit preshrunk jersey knit provides unmatched comfort with each wear. Featuring a taped neck and shoulder, and a seamless double-needle collar, and available in a range of colors, it offers it all in the ultimate head-turning package.</p>
            </div>
        </div>
        
   

        <div class="new-pro">
            <h1>NEW PRODUCT</h1>
            <h4>Designed to be loved.</h4>
            <div class="product-quick-view">
                <figure class="snip1581"><img src="${pageContext.request.contextPath}/Resources/Images/products/iphone-card-40-all-magsafe-202403.jpeg" alt="profile-sample2"/>
                    <figcaption>
                      <h3 class="title1">The</h3>
                      <h3 class="title2">NEW</h3>
                      <h3 class="title3">Collection</h3>
                    </figcaption><a href="${pageContext.request.contextPath}/pages/Product.jsp"></a>
                  </figure>
                  <figure class="snip1581 hover"><img src="${pageContext.request.contextPath}/Resources/Images/products/store-card-40-veteran-202405.jpeg" alt="profile-sample7"/>
                    <figcaption>
                      <h3 class="title1">The</h3>
                      <h3 class="title2">NEW</h3>
                      <h3 class="title3">Collection</h3>
                    </figcaption><a href="${pageContext.request.contextPath}/pages/Product.jsp"></a>
                  </figure>
                  <figure class="snip1581"><img src="${pageContext.request.contextPath}/Resources/Images/products/iphone-card-40-all-magsafe-202403.jpeg" alt="profile-sample6"/>
                    <figcaption>
                      <h3 class="title1">The</h3>
                      <h3 class="title2">NEW</h3>
                      <h3 class="title3">Collection</h3>
                    </figcaption><a href="${pageContext.request.contextPath}/pages/Product.jsp"></a>
                  </figure>
            </div>

        </div>
        
        <script type="text/javascript">
        $(document).ready(function() {
            function updateTotalPrice(element) {
                var $item = element.closest('.item');
                var pricePerUnit = parseFloat($item.find('.price').text().replace('$', ''));
                var quantity = parseInt($item.find('.num').text());
                var totalPrice = pricePerUnit * quantity;
                $item.find('.total-price').text('$' + totalPrice.toFixed(2));
                updateTotalAmount();
            }

            function updateTotalAmount() {
                var totalAmount = 0;
                $('.item').each(function() {
                    var totalPrice = parseFloat($(this).find('.total-price').text().replace('$', ''));
                    totalAmount += totalPrice;
                });
                $('#total-amount').text('$' + totalAmount.toFixed(2));
            }

            $('.plus').on('click', function() {
                var $this = $(this);
                var $num = $this.siblings('.num');
                var value = parseInt($num.text());

                if (value < 100) {
                    value++;
                    value = (value < 10) ? "0" + value : value;
                    $num.text(value);
                    updateTotalPrice($this);
                }
            });

            $('.minus').on('click', function() {
                var $this = $(this);
                var $num = $this.siblings('.num');
                var value = parseInt($num.text());

                if (value > 1) {
                    value--;
                    value = (value < 10) ? "0" + value : value;
                    $num.text(value);
                    updateTotalPrice($this);
                }
            });

            $('.delete-btn').on('click', function() {
                var $this = $(this);
                $this.closest('.item').remove();
                updateTotalAmount();
            });

            // Initial total amount calculation
            updateTotalAmount();
        });

        </script>
<script src="${pageContext.request.contextPath}/js/cart.js"></script>

</body>
</html>