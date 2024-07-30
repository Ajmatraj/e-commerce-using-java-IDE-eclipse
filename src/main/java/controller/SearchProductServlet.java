package controller;

import Model.ProductModel;
import Utils.StringUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchProductServlet")
public class SearchProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseController dbController;

    public SearchProductServlet() {
        this.dbController = new DatabaseController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");
        List<ProductModel> productList;

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            // Fetch products based on the search query
            productList = dbController.getProductsBySearchQuery(searchQuery);
        } else {
            // If no search query, fetch all products
            productList = dbController.getAllProductInfo();
        }

        // Set attributes and forward to the JSP
        request.setAttribute("productList", productList);
        request.getRequestDispatcher(StringUtils.PAGE_URL_PRODUCT).forward(request, response);
//        response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_PRODUCT + "?searchQuery=" + URLEncoder.encode(searchQuery, "UTF-8"));

    }
}
