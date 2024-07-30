package Utils;

public class StringUtils {
	
	  
	   
	    

	// Start: DB Connection
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String LOCALHOST_URL = "jdbc:mysql://localhost:3306/phonedatabase";
	public static final String LOCALHOST_USERNAME = "root";
	public static final String LOCALHOST_PASSWORD = "";

	public static final String IMAGE_ROOT_PATH = "C:\\Users\\HP\\eclipse-workspace\\coursework\\src\\main\\webapp\\Resources\\Images";
	public static final String IMAGE_DIR_PRODUCT = "C:/" + IMAGE_ROOT_PATH + "\\addpro\\";
	public static final String IMAGE_DIR_USER = "C:/" + IMAGE_ROOT_PATH + "user\\";
	// End: DB Connection

	// Start: Queries
	public static final String QUERY_REGISTER_USER = "INSERT INTO user_info ("
			+ "first_name, last_name, birthday, gender, email, phone_number, username, password, image) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static final String QUERY_REGISTER_ADMIN = "INSERT INTO admin ("
			+ "username, password, email, image) "
			+ "VALUES (?, ?, ?, ?)";
	
	
	public static final String QUERY_SEND_FEEDBACK = "INSERT INTO feedback ("
			+ "Name, Email, PhoneNumber, Subject, Message) "
			+ "VALUES (?, ?, ?, ?, ?)";

	public static final String QUERY_LOGIN_USER_CHECK = "SELECT * FROM user_info WHERE username = ?";
	public static final String QUERY_LOGIN_ADMIN_CHECK = "SELECT * FROM admin WHERE username = ?";
	public static final String QUERY_GET_ALL_USERS = "SELECT * FROM user_info";
	public static final String QUERY_GET_USER_ID = "SELECT id FROM user_info WHERE username = ?";
	public static final String QUERY_DELETE_USER = "DELETE FROM user_info WHERE username = ?";
	public static final String GET_LOGIN_INFO = "SELECT * FROM user_info WHERE username = ? and password = ?";
	public static final String QUERY_GET_ALL_PRODUCTS = "SELECT * FROM products";
	

// End: Queries

	// Start: Parameter names
	public static final String USERNAME = "username";
	public static final String USER_NAME = "username";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String BIRTHDAY = "birthday";
	public static final String GENDER = "gender";
	public static final String EMAIL = "email";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String PASSWORD = "password";
	public static final String RETYPE_PASSWORD = "retypePassword";
	public static final String IMAGE = "image";
	// End: Parameter names
	public static final String Admin_USER_NAME = "username";
	public static final String Admin_USER_PASSWORD = "password";

	// Start: Parameter names
	public static final String FEEDBACK_NAME = "f_name";
	public static final String FEEDBACK_EMAIL = "f_email";
	public static final String FEEDBACK_PHONENUMBER = "f_phoneNumber";
	public static final String FEEDBACK_SUBJECT = "f_subject";
	public static final String FEEDBACK_FEEDBACK_MESSAGE = "f_feedbackMessage";
	// End: Parameter names

	// Start: Validation Messages
	// Register Page Messages
	public static final String MESSAGE_SUCCESS_REGISTER = "Successfully Registered!";
	public static final String MESSAGE_ERROR_REGISTER = "Please correct the form data.";
	public static final String MESSAGE_ERROR_USERNAME = "Username is already registered.";
	public static final String MESSAGE_ERROR_EMAIL = "Email is already registered.";
	public static final String MESSAGE_ERROR_PHONE_NUMBER = "Phone number is already registered.";
	public static final String MESSAGE_ERROR_PASSWORD_UNMATCHED = "Password is not matched.";
	public static final String MESSAGE_ERROR_INCORRECT_DATA = "Please correct all the fields.";
	public static final String MESSAGE_SUCCESS_SENT = "Successfully Sent!";
	public static final String MESSAGE_ERROR_SENT = "Please correct the data.";
	

	// Login Page Messages
	public static final String MESSAGE_SUCCESS_LOGIN = "Successfully LoggedIn!";
	public static final String MESSAGE_ERROR_LOGIN = "Either username or password is not correct!";
	public static final String MESSAGE_ERROR_CREATE_ACCOUNT = "Account for this username is not registered! Please create a new account.";

	// Other Messages
	public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
	public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
	public static final String MESSAGE_SUCCESS_ADD_CART = "Successfully added to cart!";
	public static final String MESSAGE_SUCCESS_UPDATE = "Successfully Updated!";
	public static final String MESSAGE_ERROR_DELETE = "Cannot delete the user!";
	
	public static final String MESSAGE_SUCCESS = "successMessage";
	public static final String MESSAGE_ERROR = "errorMessage";
	// End: Validation Messages

	// Start: JSP Route
	public static final String PAGE_URL_LOGIN = "/pages/login.jsp";
	public static final String PAGE_URL_PRODUCT = "/pages/Product.jsp";
	public static final String PAGE_URL_CART = "/pages/cart.jsp";
	public static final String PAGE_URL_INDEX = "/pages/index.jsp";
	public static final String PAGE_URL_REGISTER = "/pages/register.jsp";
	public static final String PAGE_URL_ADMIN_REGISTER = "/dashboard/RegisterAdmin.jsp";
	public static final String PAGE_URL_ADD_PRODUCT = "/dashboard/addProduct.jsp";
	public static final String PAGE_URL_ADMIN_DASH = "/dashboard/dashUserprofile.jsp";
	public static final String PAGE_URL_REGISTER_ADMIN = "/dashboard/RegisterAdmin.jsp";
	public static final String PAGE_URL_DASH_PRODUCT = "/dashboard/dashProduct.jsp";
	public static final String PAGE_URL_DELETE_USER = "/dashboard/dashUserDetails.jsp";
	public static final String PAGE_URL_WELCOME = "/pages/welcome.jsp";
	public static final String PAGE_URL_FOOTER = "/pages/footer.jsp";
	public static final String PAGE_URL_HEADER = "/pages/header.jsp";
	public static final String PAGE_URL_CONTACT = "/pages/contact.jsp";
	public static final String PAGE_URL_USER_PROFILE = "/pages/UserProfile.jsp";
	public static final String PAGE_URL_ADMIN_PRODUCT = "/dashboard/dashProduct.jsp";
	public static final String PAGE_URL_ADMIN_DASHBOARD = "/dashboard/dash.jsp";
	public static final String URL_LOGIN = "/login.jsp";
	public static final String URL_INDEX = "/index.html";
	
	// End: JSP Route

	// Start: Servlet Route
	public static final String SERVLET_URL_LOGIN = "/login";
	public static final String SERVLET_URL_ADMIN_LOGIN = "/adminLogin";
	public static final String SERVLET_URL_REGISTER = "/register";
	public static final String SERVLET_URL_ADMIN_REGISTER = "/registerAdminServlet";
	public static final String SERVLET_URL_LOGOUT = "/LogoutServlet.jsp";
	public static final String SERVLET_URL_HOME = "/home";
	public static final String SERVLET_URL_MODIFY_USER = "/modifyUser";
	public static final String SERVLET_URL_CONTACT = "/ContactServlet";
	public static final String SERVLET_URL_ADD_TO_CART = "/addToCartServlet";
	// End: Servlet Route

	// Start: Normal Text
	public static final String USER = "user";
	public static final String SUCCESS = "success";
	public static final String TRUE = "true";
	public static final String JSESSIONID = "JSESSIONID";
	public static final String LOGIN = "Login";
	public static final String LOGOUT = "Logout";
	public static final String User_MODEL = "UserModel";
	public static final String QUERY_GET_ALL_UserS = "UserLists";
	public static final String SLASH= "/";
	public static final String DELETE_ID= "deleteId";
	public static final String UPDATE_ID= "updateId";

	

	
	
	
	public static final String INSERT_ORDER_SQL = "INSERT INTO `order` (user_info_id, order_date, total_price, status) VALUES (?, ?, ?, ?)";
	public static final String INSERT_ORDER_DETAILS_SQL = "INSERT INTO `order_details` (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
    public static final String GET_USER_ID_SQL = "SELECT id FROM user_info WHERE username = ?";
    public static final String CALCULATE_TOTAL_PRICE_SQL = "SELECT SUM(price * quantity) AS total_price FROM cart WHERE user_info_id = ?";
    public static final String GET_CART_ITEMS_SQL = "SELECT * FROM cart WHERE user_info_id = ?";
    public static final String CLEAR_CART_SQL = "DELETE FROM cart WHERE user_info_id = ?";
	
	    
	// End: Normal Text
}