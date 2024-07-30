package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ProductModel;
import Utils.StringUtils;

/**
 * Servlet implementation class UpdateProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateProductServlet" })
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String productID = request.getParameter("productID");
	        String name = request.getParameter("name");
	        int price = Integer.parseInt(request.getParameter("price"));
	        String description = request.getParameter("description");
	        String imageUrl = request.getParameter("imageUrl");

	        ProductModel product = new ProductModel();
	        product.setProductID(productID);
	        product.setName(name);
	        product.setPrice(price);
	        product.setDescription(description);
	        product.setImageUrlFromDB(imageUrl);

	        DatabaseController dbController = new DatabaseController();
	        int result = dbController.updateProduct(product);

	        if (result > 0) {
//	            response.sendRedirect("productList.jsp"); // Redirect to product list page after update
	            request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_UPDATE);
	            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_DASH_PRODUCT + "?success=true");
	        } else {
	            response.getWriter().println("Failed to update product");
	        }
	    }
	}

