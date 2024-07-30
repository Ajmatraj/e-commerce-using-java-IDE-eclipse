package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.ProductModel;
import Utils.StringUtils;

@WebServlet(asyncSupported = true, urlPatterns = {"/AddProductServlet"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final DatabaseController dbController = new DatabaseController();

    public AddProductServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String productId = request.getParameter("productID");
            String name = request.getParameter("productName");
            String description = request.getParameter("description");
            int price = Integer.parseInt(request.getParameter("price"));
            Part image = request.getPart("productImage");

            
            if (productId == null || productId.isEmpty()) {
                throw new IllegalArgumentException("ProductID cannot be null or empty");
            }

            // Create a ProductModel object to hold product information
            ProductModel product = new ProductModel(productId, name, description, price, image);

            // Call dbController to add the product to the database
            int result = dbController.addProductToDatabase(product);

            if (result == 1) {
                // Get the image file name from the ProductModel object
                try {
                    String fileName = product.getImageUrlFromPart();
                    System.out.println("Image file name: " + fileName);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_REGISTER);
                response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_ADD_PRODUCT + "?success=true");
            } else if (result == 0) {
                request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_REGISTER);
                request.getRequestDispatcher(StringUtils.PAGE_URL_ADD_PRODUCT).forward(request, response);
            } else {
                request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
                request.getRequestDispatcher(StringUtils.PAGE_URL_ADD_PRODUCT).forward(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute(StringUtils.MESSAGE_ERROR, "Error uploading file");
            request.getRequestDispatcher(StringUtils.PAGE_URL_ADD_PRODUCT).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute(StringUtils.MESSAGE_ERROR, "Unexpected error");
            request.getRequestDispatcher(StringUtils.PAGE_URL_ADD_PRODUCT).forward(request, response);
        }
    }
}
