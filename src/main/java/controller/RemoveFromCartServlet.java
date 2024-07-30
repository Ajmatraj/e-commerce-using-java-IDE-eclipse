package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.CartModel;
import Utils.StringUtils;

@SuppressWarnings("serial")
@WebServlet("/removeFromCartServlet")
public class RemoveFromCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        DatabaseController dbController = new DatabaseController();
        
        // Get the productID and quantity from the request
        String productID = request.getParameter("productID");
       
        
        
        try {
            // Fetch cart item
            CartModel cartItem = dbController.getCartItemById(productID);
            if (cartItem == null) {
                throw new ServletException("Cart item not found.");
            }
            
           

            // Remove item from cart
            dbController.removeFromCart(cartItem.getCart_Id());
            
        } catch (Exception e) {
            e.printStackTrace();
            
            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_CART + "?errorMessage=Error processing removed cart:");

            return;
        }
        
        // Redirect to cart page with success message
        response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_CART + "?success=true");
    }
}
