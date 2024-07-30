package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.AdminLoginModel;
import Model.LoginModel;
import Utils.StringUtils;

@WebServlet(urlPatterns = StringUtils.SERVLET_URL_LOGIN, asyncSupported = true)
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final DatabaseController dbController;

    public LoginServlet() {
        this.dbController = new DatabaseController();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LoginModel loginModel = new LoginModel(username, password);
        AdminLoginModel adminModel = new AdminLoginModel(username, password);

        // Validate login credentials
        int userLoginResult = dbController.getUserLoginInfo(loginModel);
        int adminLoginResult = dbController.getAdminLoginInfo(adminModel);

        if (userLoginResult == 1) {
            // Regular user login successful
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_WELCOME);
        } else if (adminLoginResult == 1) {
            // Admin login successful
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_ADMIN_PRODUCT);
        } else {
            // Login failed
            request.setAttribute("errorMessage", "Invalid username or password.");
            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN);
        }
    }
}
