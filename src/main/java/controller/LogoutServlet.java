package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;
import Utils.StringUtils;

@WebServlet(asyncSupported = true, urlPatterns = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(LogoutServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 1. Clear specific cookies
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    // Check if the cookie is related to user authentication
                    if (StringUtils.USER.equals(cookie.getName())) {
                        cookie.setMaxAge(0); // Set the max age to 0 to delete the cookie
                        cookie.setValue(null); // Clear the cookie value
                        cookie.setPath("/"); // Set to root path if unsure
                        response.addCookie(cookie); // Add the cookie back to the response
                    }
                }
            }

            // 2. Invalidate user session (if it exists)
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate(); // Invalidate the session
            }

            // 3. Redirect to login page
            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_INDEX);
        } catch (Exception e) {
            logger.severe("Logout failed: " + e.getMessage());
            // Redirect to an error page or handle it as needed
            response.sendRedirect(request.getContextPath() + StringUtils.MESSAGE_ERROR_LOGIN);
        }
    }
}
