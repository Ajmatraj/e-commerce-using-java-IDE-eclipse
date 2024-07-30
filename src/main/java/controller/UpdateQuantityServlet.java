package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Utils.StringUtils;

import java.sql.SQLException;

@WebServlet("/updateQuantityServlet")
public class UpdateQuantityServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final DatabaseController dbController;

    public UpdateQuantityServlet() {
        this.dbController = new DatabaseController();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String cartIdStr = request.getParameter("cartId");
        String quantityStr = request.getParameter("quantity");

        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp?errorMessage=User not logged in.");
            return;
        }

        int cartId;
        int quantity;

        try {
            cartId = Integer.parseInt(cartIdStr);
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                throw new NumberFormatException("Quantity must be positive.");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_CART + "?errorMessage=Invalid quantity.");
//            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_CART + "?success=true");
            return;
        }

        try {
            boolean isUpdated = false;
			try {
				isUpdated = dbController.updateCartQuantity(cartId, quantity);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            if (isUpdated) {
                response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_CART + "?success=true");
            } else {
                response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_CART + "?errorMessage=Unable to update quantity.");
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Consider logging this instead
            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_CART + "?errorMessage=Database error.");
        }
    }
}
