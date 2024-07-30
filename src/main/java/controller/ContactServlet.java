package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.FeedbackModel;
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
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_CONTACT })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class ContactServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final DatabaseController dbController;

	public ContactServlet() {
		this.dbController = new DatabaseController();
	}

	/**
	 * Handles HTTP POST requests for User registration.
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
	        String Name = request.getParameter(StringUtils.FEEDBACK_NAME);
	        String Email = request.getParameter(StringUtils.FEEDBACK_EMAIL);
	        String PhoneNumber = request.getParameter(StringUtils.FEEDBACK_PHONENUMBER);
	        String Subject = request.getParameter(StringUtils.FEEDBACK_SUBJECT);
	        String Message = request.getParameter(StringUtils.FEEDBACK_FEEDBACK_MESSAGE);

	        // Create a UserModel object to hold User information
	        FeedbackModel feedback = new FeedbackModel(Name, Email, PhoneNumber, Subject, Message);

	        // Implement data validation here (e.g., check for empty fields, email format, etc.)
	        if (!ValidationUtil.isTextOnly(Name) ||
	                !ValidationUtil.isEmail(Email) ||
	                !ValidationUtil.isNumbersOnly(PhoneNumber) ||
	                !ValidationUtil.isTextOnly(Subject) ||
	                !ValidationUtil.isTextOnly(Message)) {
	            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_INCORRECT_DATA);
	            request.getRequestDispatcher(StringUtils.PAGE_URL_CONTACT).forward(request, response);
	            return; // Exit method to prevent further processing
	        }

	        // Call DBController to register the User
	        int result = dbController.send_feedback(feedback);

	        if (result == 1) {
	            request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_SENT);
	            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_CONTACT+ "?success=true");
	        } else if (result == 0) {
	            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SENT);
	            request.getRequestDispatcher(StringUtils.PAGE_URL_CONTACT).forward(request, response);
	        } else {
	            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
	            request.getRequestDispatcher(StringUtils.PAGE_URL_CONTACT).forward(request, response);
	        }
	    } catch (IOException e) {
	        // Log the exception
	        e.printStackTrace();
	        // Handle the IOException
	        request.setAttribute(StringUtils.MESSAGE_ERROR, "Error uploading file");
	        request.getRequestDispatcher(StringUtils.PAGE_URL_CONTACT).forward(request, response);
	    } catch (Exception e) {
	        // Log any other unexpected exceptions
	        e.printStackTrace();
	        // Handle the exception
	        request.setAttribute(StringUtils.MESSAGE_ERROR, "Unexpected error");
	        request.getRequestDispatcher(StringUtils.PAGE_URL_CONTACT).forward(request, response);
	    }
	}

}