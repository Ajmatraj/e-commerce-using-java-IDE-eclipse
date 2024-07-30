package controller;

import Model.OrderModel;
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
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/viewOrderDetails")
public class ViewOrderDetailsServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/phonedatabase";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @SuppressWarnings("unused")
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN);
            return;
        }

        List<OrderModel> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    OrderModel order = new OrderModel();
                    order.setOrderId(resultSet.getInt("order_id"));
                    order.setProductId(resultSet.getString("product_id"));
                    order.setProductName(resultSet.getString("product_name"));
                    order.setProductDescription(resultSet.getString("product_description"));
                    order.setPrice(resultSet.getInt("price"));
                    order.setQuantity(resultSet.getInt("quantity"));
                    order.setTotalPrice(resultSet.getInt("total_price"));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }

        // Check if orders is not null
        if (orders == null) {
            orders = new ArrayList<>(); // Initialize as an empty list if null
        }

        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/WEB-INF/views/orderDetails.jsp").forward(request, response);
    }

}
