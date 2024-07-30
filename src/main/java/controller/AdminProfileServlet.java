package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.adminModel;
import java.util.List;

@WebServlet("/adminProfile")
public class AdminProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
        DatabaseController dbController = new DatabaseController();
        
        try {
            List<adminModel> adminList = dbController.getAdminDetails(username);
            request.setAttribute("adminList", adminList);
            request.getRequestDispatcher("/dashboard/dashUserprofile.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            response.sendRedirect(request.getContextPath() + "/pages/error.jsp");
        }
    }
}
