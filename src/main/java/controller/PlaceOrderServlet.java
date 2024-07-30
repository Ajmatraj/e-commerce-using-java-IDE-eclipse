package controller;

import Model.ProductModel;
import Utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
@WebServlet("/placeOrderServlet")
public class PlaceOrderServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/phonedatabase";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productID = request.getParameter("productID");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String username = (String) request.getSession().getAttribute("username");
        String phoneNumber = request.getParameter("phoneNumber"); // Ideally fetch from user profile
        String email = request.getParameter("email"); // Ideally fetch from user profile

        // Fetch product details
        ProductModel product = getProductDetails(productID);
        
        if (product != null) {
            int totalPrice = product.getPrice() * quantity;

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "INSERT INTO orders (productID, product_name, product_description, price, username, phone_number, email, quantity, total_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, product.getProductID());
                    statement.setString(2, product.getName());
                    statement.setString(3, product.getDescription());
                    statement.setInt(4, product.getPrice());
                    statement.setString(5, username);
                    statement.setString(6, phoneNumber);
                    statement.setString(7, email);
                    statement.setInt(8, quantity);
                    statement.setInt(9, totalPrice);
                    statement.executeUpdate();
                }
                response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_CART + "?success=true");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_CART + "?errorMessage=Order%20could%20not%20be%20placed");
            }
        } else {
            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_CART + "?errorMessage=Invalid%20Product%20ID");
        }
    }

    private ProductModel getProductDetails(String productID) throws IOException {
        ProductModel product = null;
        String sql = "SELECT * FROM products WHERE ProductID = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, productID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Create a ProductModel object and populate it with data from the database
                    String name = resultSet.getString("Name");
                    String description = resultSet.getString("Description");
                    int price = resultSet.getInt("Price");
                    String imageUrlFromDB = resultSet.getString("imglink");

                    product = new ProductModel(productID, name, description, price, null); // Pass `null` for `Part` if not available
                    product.setImageUrlFromDB(imageUrlFromDB); // Set image URL from the database
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception as needed
        }

        return product;
    }
}
