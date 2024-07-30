package controller;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import Model.UserModel;
import Utils.StringUtils;
import Utils.ValidationUtil;

/**
 * This Servlet class handles User registration requests. It extracts User
 * information from the registration form submission, performs basic data
 * validation (to be implemented), and attempts to register the User in the
 * database using a `DBController`. The user is redirected to the login page
 * upon successful registration.
 *
 * @author Prithivi Maharjan (prithivi.maharjan18@gmail.com)
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_REGISTER })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class registerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final DatabaseController dbController;

	public registerServlet() {
		this.dbController = new DatabaseController();
	}

	/**
	 * Handles HTTP POST requests 8999999for User registration.
	 *
	 * @param request  The HttpServletRequest object containing registration form
	 *                 data.
	 * @param response The HttpServletResponse object for sending responses.
	 * @throws ServletException if a servlet-specific error occurs.
	 * @throws IOException      if an I/O error occurs.
	 */
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {

	    try {
	        // Extract User information from request parameters
	        String firstName = request.getParameter("firstName");
	        String lastName = request.getParameter("lastName");
	        LocalDate dob = LocalDate.parse(request.getParameter("birthday"));
	        String gender = request.getParameter("gender");
	        String email = request.getParameter("email");
	        String phoneNumber = request.getParameter("phoneNumber");
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        Part imagePart = request.getPart("image"); 

	        // Create a UserModel object to hold User information
	        UserModel user = new UserModel(firstName, lastName, dob, gender, email, phoneNumber, username, password, imagePart);

	        // Implement data validation here (e.g., check for empty fields, email format, etc.)
	        if (!ValidationUtil.isTextOnly(firstName) ||
	                !ValidationUtil.isTextOnly(lastName) ||
	                !ValidationUtil.isAlphanumeric(username) ||
	                !ValidationUtil.isEmail(email) ||
	                !ValidationUtil.isNumbersOnly(phoneNumber) ||
	                !ValidationUtil.isGenderMatches(gender)) {
	            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_INCORRECT_DATA);
	            request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
	            return; // Exit method to prevent further processing
	        }

	        // Call DBController to register the User
	        int result = dbController.registerUser(user);

	        if (result == 1) {
	            // Get the image file name from the User object (assuming it was extracted earlier)
	            String fileName = user.getImageUrlFromPart();

	            // Check if a filename exists (not empty or null)
//	            if (!fileName.isEmpty() && fileName != null) {
//	                // Construct the full image save path by combining the directory path and filename
//	                String savePath = StringUtils.IMAGE_DIR_USER;
//	                imagePart.write(savePath + fileName);  // Save the uploaded image to the specified path
//	            }

	            request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_REGISTER);
	            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_REGISTER+ "?success=true");
	        } else if (result == 0) {
	            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_REGISTER);
	            request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
	        } else {
	            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
	            request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
	        }
	    } catch (IOException e) {
	        // Log the exception
	        e.printStackTrace();
	        // Handle the IOException
	        request.setAttribute(StringUtils.MESSAGE_ERROR, "Error uploading file");
	        request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
	    } catch (Exception e) {
	        // Log any other unexpected exceptions
	        e.printStackTrace();
	        // Handle the exception
	        request.setAttribute(StringUtils.MESSAGE_ERROR, "Unexpected error");
	        request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
	    }
	}

}