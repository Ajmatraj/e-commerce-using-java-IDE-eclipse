package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.CartModel;
import Model.ProductModel;
import Utils.StringUtils;

@WebServlet(urlPatterns = StringUtils.SERVLET_URL_ADD_TO_CART, asyncSupported = true)
public class AddToCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final DatabaseController dbController;

    public AddToCartServlet() {
        this.dbController = new DatabaseController();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String productId = request.getParameter("productID");
        String quantityStr = request.getParameter("quantity");

        // Validate input parameters
        if (username == null) {
            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN + "?errorMessage=User not logged in.");
            return;
        }
        
        if (productId == null || productId.isEmpty()) {
            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_PRODUCT + "?errorMessage=Product ID cannot be null or empty.");
            return;
        }
        
        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                throw new NumberFormatException("Quantity must be positive.");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_PRODUCT + "?errorMessage=Invalid quantity.");
            return;
        }

        // Fetch product details
        ProductModel product = dbController.getProductByID(productId);

        if (product == null) {
            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_PRODUCT + "?errorMessage=Product not found.");
            return;
        }

        // Fetch user info ID based on username
        int userInfoId = dbController.getUserIDByUsername(username);

        // Create CartModel with all details
        CartModel cartModel = new CartModel(
            0, // cart_Id will be auto-generated
            userInfoId,
            productId,
            quantity,
            product.getName(),
            product.getImageUrlFromPart(),
            product.getPrice()
        );
        
        boolean isAdded = dbController.addToCart(cartModel, username);

        if (isAdded) {
            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_PRODUCT + "?success=true");
        } else {
            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_PRODUCT + "?errorMessage=Unable to add product to cart.");
        }
    }
}
