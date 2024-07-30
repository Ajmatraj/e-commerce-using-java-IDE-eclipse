package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utils.StringUtils;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productID = request.getParameter("productID");
        DatabaseController dbController = new DatabaseController();

        int result = dbController.deleteProductByID(productID);
        if (result == 1) {
            request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_DELETE);
            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_DASH_PRODUCT + "?success=true");
        } else if (result == 0) {
            response.sendRedirect("errorPage.jsp?message=Failed%20to%20delete%20product"); // Redirect to an error page in case of failure
        } else {
            response.sendRedirect("errorPage.jsp?message=Internal%20server%20error"); // Internal error case
        }
    }
}
