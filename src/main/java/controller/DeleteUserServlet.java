package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import Utils.StringUtils;

@SuppressWarnings("serial")
@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        if (username == null || username.trim().isEmpty()) {
            response.sendRedirect("errorPage.jsp?error=Invalid username");
            return;
        }

        DatabaseController dbController = new DatabaseController();
        try {
            int result = dbController.deleteUserByID(username); // Correct method call
            if (result > 0) {
                request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_DELETE);
                response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_DELETE_USER + "?success=true");
            } else {
                request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_DELETE);
                response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_DELETE_USER + "?error=User not found");
            }
        } catch (Exception e) {
            response.sendRedirect("errorPage.jsp?error=An unexpected error occurred");
        } finally {
            // Close or release any resources if necessary
        }
    }
}
