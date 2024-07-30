package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.adminModel;
import Utils.StringUtils;
import Utils.ValidationUtil;

/**
 * Servlet implementation class registerAdminServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = {StringUtils.SERVLET_URL_ADMIN_REGISTER})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class registerAdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final DatabaseController dbController;

    public registerAdminServlet() {
        this.dbController = new DatabaseController();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            Part imagePart = request.getPart("image");

            adminModel admin = new adminModel(username, password, email, imagePart);

            if (!ValidationUtil.isAlphanumeric(username) || !ValidationUtil.isEmail(email)) {
                request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_INCORRECT_DATA);
                request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER_ADMIN).forward(request, response);
                return;
            }

            int result = dbController.registerAdmin(admin);

            if (result == 1) {
                response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_REGISTER_ADMIN + "?success=true");
            } else if (result == 0) {
                request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_REGISTER);
                request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER_ADMIN).forward(request, response);
            } else {
                request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
                request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER_ADMIN).forward(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute(StringUtils.MESSAGE_ERROR, "Error uploading file");
            request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER_ADMIN).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute(StringUtils.MESSAGE_ERROR, "Unexpected error");
            request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER_ADMIN).forward(request, response);
        }
    }
}
