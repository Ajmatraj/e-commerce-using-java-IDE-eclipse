package controller;

import Model.ProductModel;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    DatabaseController dbController;
    public ProductServlet() {
        this.dbController = new DatabaseController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	List<ProductModel> productList = dbController.getAllProductInfo();

        if (productList == null || productList.isEmpty()) { 
            request.setAttribute("errorMessage", "No products found");
            request.getRequestDispatcher("/error.jsp").forward(request, response); 
        } else {
            request.setAttribute("productList", productList); 
            request.getRequestDispatcher("/product.jsp").forward(request, response);
        }
    }
}