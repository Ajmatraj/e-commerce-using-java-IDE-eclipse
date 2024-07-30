package controller;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.AdminLoginModel;
import Model.CartItem;
import Model.CartModel;
import Model.FeedbackModel;
import Model.LoginModel;
import Model.OrderModel;
import Model.PasswordEncryptionWithAes;
import Model.UserModel;
import Model.adminModel;
import Utils.StringUtils;
import Model.ProductModel;
public class DatabaseController {

	/**
	 * Establishes a connection to the database using pre-defined credentials and
	 * driver information.
	 * 
	 * @return A `Connection` object representing the established connection to the
	 *         database.
	 * @throws SQLException           if a database access error occurs.
	 * @throws ClassNotFoundException if the JDBC driver class is not found.
	 */
	public Connection getConnection() throws SQLException, ClassNotFoundException {

		// Load the JDBC driver class specified by the StringUtils.DRIVER_NAME constant
		Class.forName(StringUtils.DRIVER_NAME);

		// Create a connection to the database using the provided credentials
		return DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME,
				StringUtils.LOCALHOST_PASSWORD);
	}

	/**
	 * This method attempts to register a new User in the database.
	 * 
	 * @param User A `UserModel` object containing the User's information.
	 * @return An integer value indicating the registration status: - 1:
	 *         Registration successful - 0: Registration failed (no rows affected) -
	 *         -1: Internal error (e.g., ClassNotFound or SQLException)
	 * @throws SQLException           if a database access error occurs.
	 * @throws ClassNotFoundException if the JDBC driver class is not found.
	 */
	public int registerUser(UserModel User) {

		try {
			// Prepare a statement using the predefined query for User registration
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_REGISTER_USER);

			// Set the User information in the prepared statement
			stmt.setString(1, User.getfirst_name());
			stmt.setString(2, User.getlast_name());
			stmt.setDate(3, Date.valueOf(User.getDob()));
			stmt.setString(4, User.getgender());
			stmt.setString(5, User.getemail());
			stmt.setString(6, User.getphone_number());
			stmt.setString(7, User.getusername());
			stmt.setString(8, PasswordEncryptionWithAes.encrypt(User.getusername(), User.getpassword()));
			stmt.setString(9, User.getImageUrlFromPart());

			// Execute the update statement and store the number of affected rows
			int result = stmt.executeUpdate();

			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Registration successful
			} else {
				return 0; // Registration failed (no rows affected)
			}

		} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			return -1; // Internal error
		}
	}
	
	public int getStudentinfo(LoginModel loginModel) {
		 String username = loginModel.getUsername();
	        String password = loginModel.getPassword();

	        try (Connection con = getConnection()) {
	            PreparedStatement st = con.prepareStatement(StringUtils.GET_LOGIN_INFO);
	            st.setString(1, username);
	            st.setString(2, password);
	            ResultSet rs = st.executeQuery();

	            
	            if (rs.next()) {
	                return 1; // User name and password match in the database.
	            } else {
	                return 0; // No matching record found.
	            }
	        } catch (ClassNotFoundException | SQLException ex) {
	            ex.printStackTrace(); // Log the exception for debugging.
	            return -1; // Internal server error.
	        }
	}
	/**
	 * This method attempts to validate a User login by checking the username and
	 * password against a database.
	 * 
	 * @param username The username provided by the user attempting to log in.
	 * @param password The password provided by the user attempting to log in.
	 * @return An integer value indicating the login status: - 1: Login successful -
	 *         0: Username or password mismatch - -1: Username not found in the
	 *         database - -2: Internal error (e.g., SQL or ClassNotFound exceptions)
	 * @throws SQLException           if a database access error occurs.
	 * @throws ClassNotFoundException if the JDBC driver class is not found.
	 */
	public int getUserLoginInfo(LoginModel loginModel) {
		// Try-catch block to handle potential SQL or ClassNotFound exceptions
		try {
			// Prepare a statement using the predefined query for login check
			PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_LOGIN_USER_CHECK);

			// Set the username in the first parameter of the prepared statement
			st.setString(1, loginModel.getUsername());

			// Execute the query and store the result set
			ResultSet result = st.executeQuery();

			// Check if there's a record returned from the query
			if (result.next()) {
				// Get the username from the database
				String userDb = result.getString(StringUtils.USER_NAME);

				// Get the password from the database
				String encryptedPwd = result.getString(StringUtils.PASSWORD);

				String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, userDb);
				// Check if the username and password match the credentials from the database
				if (userDb.equals(loginModel.getUsername()) && decryptedPwd.equals(loginModel.getPassword())) {
					// Login successful, return 1
					return 1;
				} else {
					// Username or password mismatch, return 0
					return 0;
				}
			} else {
				// Username not found in the database, return -1
				return -1;
			}

			// Catch SQLException and ClassNotFoundException if they occur
		} catch (SQLException | ClassNotFoundException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			// Return -2 to indicate an internal error
			return -2;
		}
	}

	public Boolean checkEmailIfExists(String email) {
		// TODO: Implement logic to check if the provided email address exists in the
		// database
		// This method should likely query the database using DBController and return
		// true if the email exists, false otherwise.
		return false;
	}

	public Boolean checkNumberIfExists(String number) {
		// TODO: Implement logic to check if the provided phone number exists in the
		// database
		// This method should likely query the database using DBController and return
		// true if the phone number exists, false otherwise.
		return false;
	}

	public Boolean checkUsernameIfExists(String username) {
		// TODO: Implement logic to check if the provided username exists in the
		// database
		// This method should likely query the database using DBController and return
		// true if the username exists, false otherwise.
		return false;
	}

	
	


	public int deleteUserInfo(String username) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.QUERY_DELETE_USER);
			st.setString(1, username);
			return st.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}
	
	public int send_feedback(FeedbackModel feedback) {
		try {
			// Prepare a statement using the predefined query for sending feedback
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_SEND_FEEDBACK);

			// Set the User information in the prepared statement
			stmt.setString(1, feedback.getName());
			stmt.setString(2, feedback.getEmail());
			stmt.setString(3, feedback.getPhoneNumber());
			stmt.setString(4, feedback.getSubject());
			stmt.setString(5, feedback.getMessage());

			// Execute the update statement and store the number of affected rows
			int result = stmt.executeUpdate();

			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Registration successful
			} else {
				return 0; // Registration failed (no rows affected)
			}

		} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			return -1; // Internal error
		}
	}
	
	public int addProductToDatabase(ProductModel products) {
        String sql = "INSERT INTO products (ProductID, Name, Description, Price, imglink) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Debugging output
            System.out.println("Inserting product into database:");
            System.out.println("ProductID: " + products.getProductID());
            System.out.println("Name: " + products.getName());
            System.out.println("Description: " + products.getDescription());
            System.out.println("Price: " + products.getPrice());
            System.out.println("Image URL: " + products.getImageUrlFromPart());

            statement.setString(1, products.getProductID());
            statement.setString(2, products.getName());
            statement.setString(3, products.getDescription());
            statement.setInt(4, products.getPrice());
            statement.setString(5, products.getImageUrlFromPart());

            int result = statement.executeUpdate();

            return (result > 0) ? 1 : 0; // Return 1 for success, 0 for failure

        } catch (SQLException ex) {
            // Log the exception using a logging framework
            System.err.println("SQL Error: " + ex.getMessage());
            return -1; // Return -1 for an internal error
        } catch (Exception ex) {
            // Catch any other unexpected exceptions
            System.err.println("Unexpected Error: " + ex.getMessage());
            return -1; // Return -1 for an internal error
        }
    }
	
	// Creating get method for all product info from database.
	public List<ProductModel> getAllProductInfo() {
	    List<ProductModel> products = new ArrayList<>();
	    String sql = "SELECT * FROM products";
	    
	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) 
	    {
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            String productID = resultSet.getString("ProductID");
	            String name = resultSet.getString("Name");
	            int price = resultSet.getInt("Price");
	            String description = resultSet.getString("Description");
	            String image = resultSet.getString("imglink");

	            ProductModel product = new ProductModel();
	            product.setProductID(productID);
	            product.setName(name);
	            product.setPrice(price);
	            product.setDescription(description);
	            product.setImageUrlFromDB(image);

	            // Adding the product info to the products list.
	            products.add(product);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

	    return products;
	}
	
	
	public int deleteProductByID(String productID) {
	    String deleteOrdersSql = "DELETE FROM orders WHERE ProductID = ?";
	    String deleteProductSql = "DELETE FROM products WHERE ProductID = ?";
	    int result = 0;

	    try (Connection connection = getConnection();
	         PreparedStatement deleteOrdersStmt = connection.prepareStatement(deleteOrdersSql);
	         PreparedStatement deleteProductStmt = connection.prepareStatement(deleteProductSql)) {

	        connection.setAutoCommit(false); // Start transaction

	        // Delete related orders first
	        deleteOrdersStmt.setString(1, productID);
	        deleteOrdersStmt.executeUpdate();

	        // Delete product
	        deleteProductStmt.setString(1, productID);
	        result = deleteProductStmt.executeUpdate();

	        connection.commit(); // Commit transaction

	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace();
	        result = -1;
	    }

	    return result > 0 ? 1 : 0; // Return 1 for success, 0 for failure
	}

	
	
	
	// DatabaseController.java
	public ProductModel getProductByID(String productID) {
	    String sql = "SELECT * FROM products WHERE ProductID = ?";
	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, productID);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            String name = resultSet.getString("Name");
	            int price = resultSet.getInt("Price");
	            String description = resultSet.getString("Description");
	            String image = resultSet.getString("imglink");

	            ProductModel product = new ProductModel();
	            product.setProductID(productID);
	            product.setName(name);
	            product.setPrice(price);
	            product.setDescription(description);
	            product.setImageUrlFromDB(image);

	            return product;
	        }
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}

	
	public int updateProductByID(String productID) {
	    String sql = "SELECT FROM products WHERE ProductID = ?";
	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, productID);
	        int result = statement.executeUpdate();
	        return (result > 0) ? 1 : 0; // Return 1 for success, 0 for failure
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace();
	        return -1; // Internal error
	    }
	}
	
	
	
	public int updateProduct(ProductModel product) {
	    String sql = "UPDATE products SET Name = ?, Description = ?, Price = ?, imglink = ? WHERE ProductID = ?";
	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, product.getName());
	        statement.setString(2, product.getDescription());
	        statement.setInt(3, product.getPrice());
	        statement.setString(4, product.getImageUrlFromPart());
	        statement.setString(5, product.getProductID());

	        int result = statement.executeUpdate();
	        return (result > 0) ? 1 : 0; // Return 1 for success, 0 for failure
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace();
	        return -1; // Internal error
	    }
	}

	
	
	
	
	
//	public int getProductByID(String productID) {
//	    String sql = "SELECT FROM products WHERE ProductID = ?";
//	    try (Connection connection = getConnection();
//	         PreparedStatement statement = connection.prepareStatement(sql)) {
//	        statement.setString(1, productID);
//	        int result = statement.executeUpdate();
//	        return (result > 0) ? 1 : 0; // Return 1 for success, 0 for failure
//	    } catch (SQLException | ClassNotFoundException ex) {
//	        ex.printStackTrace();
//	        return -1; // Internal error
//	    }
//	}
	
	public List<UserModel> getAllUsersInfo() {
	    List<UserModel> Users = new ArrayList<>();
	    String sql = "SELECT * FROM user_info";

	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) 
	    {
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            String firstName = resultSet.getString("first_name");
	            String lastName = resultSet.getString("last_name");
	            Date dob = resultSet.getDate("birthday"); // Changed from "dob" to "birthday"
	            String email = resultSet.getString("email");
	            String gender = resultSet.getString("gender");
	            String number = resultSet.getString("phone_number");
	            String imgurl = resultSet.getString("image");
	            String userName = resultSet.getString("username");

	            UserModel user = new UserModel();
	            user.setfirst_name(firstName);
	            user.setlast_name(lastName);
	            user.setdob(dob != null ? dob.toLocalDate() : null); // Convert java.sql.Date to LocalDate
	            user.setemail(email);
	            user.setgender(gender);
	            user.setphone_number(number);
	            user.setImageUrlFromDB(imgurl);
	            user.setusername(userName);

	            Users.add(user);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    return Users;
	}


    public int deleteUserByID(String username) {
        String sql = "DELETE FROM user_info WHERE username = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            int result = statement.executeUpdate();
            return (result > 0) ? 1 : 0; // Return 1 for success, 0 for failure
        } catch (SQLException | ClassNotFoundException ex) {
            return -1; // Internal error
        }
    }

    
    
    
    public int registerAdmin(adminModel admin) {

		try {
			// Prepare a statement using the predefined query for User registration
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_REGISTER_ADMIN);

			// Set the User information in the prepared statement
			stmt.setString(1, admin.getUsername());
			stmt.setString(2, PasswordEncryptionWithAes.encrypt(admin.getUsername(), admin.getPassword()));
			stmt.setString(3, admin.getEmail());
			stmt.setString(4, admin.getImageUrlFromPart());

			// Execute the update statement and store the number of affected rows
			int result = stmt.executeUpdate();

			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Registration successful
			} else {
				return 0; // Registration failed (no rows affected)
			}

		} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			return -1; // Internal error
		}
	}
    
    
    public int getAdminLoginInfo(AdminLoginModel adminLoginModel) {
        try {
            // Prepare a statement using the predefined query for login check
            PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_LOGIN_ADMIN_CHECK);

            // Set the username in the first parameter of the prepared statement
            st.setString(1, adminLoginModel.getUsername());

            // Execute the query and store the result set
            ResultSet result = st.executeQuery();

            // Check if there's a record returned from the query
            if (result.next()) {
                // Get the username from the database
                String userDb = result.getString(StringUtils.Admin_USER_NAME);

                // Get the password from the database
                String encryptedPwd = result.getString(StringUtils.Admin_USER_PASSWORD);

                // Decrypt the password
                String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, userDb);

                // Check if the username and password match the credentials from the database
                if (userDb.equals(adminLoginModel.getUsername()) && decryptedPwd.equals(adminLoginModel.getPassword())) {
                    return 1; // Login successful
                } else {
                    return 0; // Username or password mismatch
                }
            } else {
                return -1; // Username not found in the database
            }

        } catch (SQLException | ClassNotFoundException ex) {
            // Print the stack trace for debugging purposes
            ex.printStackTrace();
            return -2; // Internal error
        }
    }
    /**
     * Checks if the username exists in the user_info table.
     *
     * @rahul username The username to check.
     * @return true if the username exists in user_info, false otherwise.
     */
    public boolean checkUsernameInUserInfo(String username) {
        String sql = "SELECT COUNT(*) FROM user_info WHERE username = ?";
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    /**
     * Checks if the username exists in the admin_info table.
     *
     * @rahul username The username to check.
     * @return true if the username exists in admin_info, false otherwise.
     */
    public boolean checkUsernameInAdminInfo(String username) {
        String sql = "SELECT COUNT(*) FROM admin WHERE username = ?";
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    
    public boolean addToCart(CartModel cartModel, String username) {
        if (cartModel == null || cartModel.getProductID() == null || username == null) {
            return false;  // Validation check to avoid inserting null values
        }
        
        String query = "INSERT INTO cart (user_info_id, ProductID, quantity, Name, imgLink, Price) " +
                       "VALUES ((SELECT id FROM user_info WHERE username = ?), ?, ?, ?, ?, ?)";

        try (Connection con = getConnection(); 
             PreparedStatement st = con.prepareStatement(query)) {
            
            st.setString(1, username);
            st.setString(2, cartModel.getProductID());
            st.setInt(3, cartModel.getQuantity());
            st.setString(4, cartModel.getName());
            st.setString(5, cartModel.getImgLink());
            st.setInt(6, cartModel.getPrice());
            
            int result = st.executeUpdate();
            return result > 0;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();  // Consider logging this instead
            return false;
        }
    }






    public List<CartModel> getCartItems(String username) {
        List<CartModel> cartItems = new ArrayList<>();
        if (username == null) {
            return cartItems;  // Return empty list if username is null
        }
        
        String query = "SELECT c.cart_id, c.user_info_id, c.ProductID, c.quantity, c.Name, c.imglink, c.Price " +
                       "FROM cart c INNER JOIN user_info u ON c.user_info_id = u.id " +
                       "WHERE u.username = ?";

        try (Connection con = getConnection(); 
             PreparedStatement st = con.prepareStatement(query)) {
            
            st.setString(1, username);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                CartModel item = new CartModel(
                    result.getInt("cart_id"),
                    result.getInt("user_info_id"),
                    result.getString("ProductID"),
                    result.getInt("quantity"),
                    result.getString("Name"),
                    result.getString("imglink"),
                    result.getInt("Price")
                );
                cartItems.add(item);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();  // Consider logging this instead
        }
        return cartItems;
    }



    public int getUserIDByUsername(String username) {
        String query = "SELECT id FROM user_info WHERE username = ?";
        try (Connection con = getConnection(); 
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 or handle error if the user is not found
    }
    
    

    
    public UserModel getUserByUsername(String username) {
        String sql = "SELECT * FROM user_info WHERE username = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserModel user = new UserModel();
                user.setfirst_name(resultSet.getString("first_name"));
                user.setlast_name(resultSet.getString("last_name"));
                user.setemail(resultSet.getString("email"));
                user.setphone_number(resultSet.getString("phone_number"));
                user.setusername(resultSet.getString("username"));
                user.setpassword(resultSet.getString("password"));
                user.setImageUrlFromDB(resultSet.getString("image"));
                // Set other fields as needed
                return user;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
  
    public List<adminModel> getAdminDetails(String username) {
        List<adminModel> admins = new ArrayList<>();
        if (username == null) {
            return admins;  // Return empty list if username is null
        }
        
        String query = "SELECT username, password, email, image FROM admin WHERE username = ?";

        try (Connection con = getConnection(); 
             PreparedStatement st = con.prepareStatement(query)) {
            
            st.setString(1, username);
            ResultSet result = st.executeQuery();
            
            while (result.next()) {
                adminModel admin = new adminModel();
                admin.setUsername(result.getString("username"));
                admin.setPassword(result.getString("password"));
                admin.setEmail(result.getString("email"));
                admin.setImageUrlFromDB(result.getString("image"));

                // Add the adminModel object to the list
                admins.add(admin);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();  // Consider logging this instead
        }
        return admins;
    }

    
    public boolean updateCartQuantity(int cartId, int quantity) throws SQLException, ClassNotFoundException {
        String query = "UPDATE cart SET quantity = ? WHERE cart_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            
            st.setInt(1, quantity);
            st.setInt(2, cartId);
            
            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        }
    }

  



    public CartModel getCartItemById(String productID) throws ClassNotFoundException {
        String sql = "SELECT * FROM cart WHERE ProductID = ?";
        CartModel cartItem = null;

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, productID);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cartItem = new CartModel();
                    cartItem.setCart_Id(rs.getInt("cart_id"));
                    cartItem.setUser_Info_Id(rs.getInt("user_info_id"));
                    cartItem.setProductID(rs.getString("ProductID"));
                    cartItem.setQuantity(rs.getInt("quantity"));
                    cartItem.setPrice(rs.getInt("Price"));
                    cartItem.setImgLink(rs.getString("image"));
                    cartItem.setName(rs.getString("name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework
        }

        return cartItem;
    }

    
    public boolean RemoveFromCart(int cart_Id) throws ClassNotFoundException {
        String sql = "DELETE FROM cart WHERE ProductID = ?";

        try (Connection connection = getConnection(); // Get database connection
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set the cart ID parameter
            pstmt.setInt(1, cart_Id);
            
            // Execute the delete operation
            int rowsAffected = pstmt.executeUpdate();
            
            // Return true if one or more rows were affected, false otherwise
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace for debugging
            return false; // Return false on exception
        }
    }


    // Method to remove an item from the cart by cart ID
    public void removeFromCart(int cart_Id) throws ClassNotFoundException {
        String sql = "DELETE FROM cart WHERE cart_id = ?";

        try (Connection connection = getConnection(); // Get database connection
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set the cart ID parameter
            pstmt.setInt(1, cart_Id);
            
            // Execute the delete operation
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception (log it or rethrow it as needed)
        }
    }

    @SuppressWarnings("unused")
	private String getProductNameById(String productId) throws ClassNotFoundException {
        String productName = "";
        String sql = "SELECT name FROM products WHERE ProductID = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, productId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                productName = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider throwing a custom exception or handling it as needed
        }

        return productName;
    }

    public boolean removeFromCartBYproductID(String productID) throws ClassNotFoundException {
        String sql = "DELETE FROM cart WHERE ProductID = ?"; // Corrected SQL statement

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, productID);
            int rowsAffected = pstmt.executeUpdate();

            // Return true if the deletion was successful (i.e., rowsAffected > 0)
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return false;
        }
    }


    public boolean removeFromCartByCartID(String cartID) throws ClassNotFoundException {
        String sql = "DELETE FROM cart WHERE cart_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, cartID);
            int rowsAffected = pstmt.executeUpdate();

            // Return true if the deletion was successful (i.e., rowsAffected > 0)
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return false;
        }
    }

    public int getUserIdByUsername(String username) throws ClassNotFoundException {
        String sql = "SELECT id FROM user_info WHERE username = ?";
        int userId = 0;

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getInt("user_id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception
        }

        return userId;
    }
    
    
    
    public List<CartItem> getCartItemsByUserId(int userId) throws ClassNotFoundException {
        List<CartItem> cartItems = new ArrayList<>();
        String sql = "SELECT * FROM cart WHERE user_info_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    CartItem item = new CartItem();
                    item.setCart_Id(rs.getInt("cart_id"));
                    item.setUser_Info_Id(rs.getInt("user_info_id"));
                    item.setProductID(rs.getString("ProductID"));
                    item.setQuantity(rs.getInt("quantity"));
                    item.setPrice(rs.getInt("price"));
                    item.setImgLink(rs.getString("imglink"));
                    item.setName(rs.getString("name"));
                    
                    cartItems.add(item);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception
        }

        return cartItems;
    }
    
    
 // Creating get method for all product info from database.
 	public List<OrderModel> getAllOrderINFO() {
 	    List<OrderModel> orders = new ArrayList<>();
 	    String sql = "SELECT * FROM orders";
 	    
 	    try (Connection connection = getConnection();
 	         PreparedStatement statement = connection.prepareStatement(sql)) 
 	    {
 	        ResultSet resultSet = statement.executeQuery();
 	        while (resultSet.next()) {
 	        	int order_Id = resultSet.getInt("order_id");
 	            String productID = resultSet.getString("ProductID");
 	            String product_name = resultSet.getString("product_name");
 	            String description = resultSet.getString("product_description");
 	            int price = resultSet.getInt("price");
 	            String username = resultSet.getString("username");
 	            int phoneNumber = resultSet.getInt("phone_number");
 	            String email = resultSet.getString("email");
 	            int quantity = resultSet.getInt("quantity");
 	            int total_price = resultSet.getInt("total_price");

 	            

 	            OrderModel order = new OrderModel();
 	           order.setOrderId(order_Id);
               order.setProductName(product_name);
               order.setProductDescription(description);
               order.setPrice(total_price);
               order.setUsername(username);
               order.setPhoneNumber(email);
               order.setEmail(email);
               order.setQuantity(quantity);
               order.setTotalPrice(price);
//              
               orders.add(order);

 	           
 	        }
 	    } catch (SQLException e) {
 	        e.printStackTrace();
 	    } catch (ClassNotFoundException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		} 

 	    return orders;
 	}
    
 	
 	
 	public List<ProductModel> getProductsBySearchQuery(String searchQuery) {
 	    List<ProductModel> products = new ArrayList<>();
 	    // Correct the SQL query
 	    String sql = "SELECT * FROM products WHERE Name LIKE ? OR Description LIKE ?";

 	    try (Connection connection = getConnection();
 	         PreparedStatement statement = connection.prepareStatement(sql)) 
 	    {
 	        String searchPattern = "%" + searchQuery + "%";
 	        statement.setString(1, searchPattern);
 	        statement.setString(2, searchPattern);

 	        ResultSet resultSet = statement.executeQuery();
 	        while (resultSet.next()) {
 	            products.add(mapProduct(resultSet));
 	        }
 	    } catch (SQLException | ClassNotFoundException e) {
 	        e.printStackTrace();
 	    }

 	    return products;
 	}

 	private ProductModel mapProduct(ResultSet resultSet) throws SQLException {
 	    ProductModel product = new ProductModel();
 	    product.setProductID(resultSet.getString("ProductID"));
 	    product.setName(resultSet.getString("Name"));
 	    product.setPrice(resultSet.getInt("Price"));
 	    product.setDescription(resultSet.getString("Description"));
 	    product.setImageUrlFromDB(resultSet.getString("imglink"));
 	    return product;
 	}

 	public boolean updateUser(UserModel user) throws ClassNotFoundException {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ?, phone_number = ?, image = ? WHERE username = ?";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setString(1, user.getfirst_name());
            pstmt.setString(2, user.getlast_name());
            pstmt.setString(3, user.getemail());
            pstmt.setString(4, user.getphone_number());
            pstmt.setString(5, user.getImageUrlFromPart());
            pstmt.setString(6, user.getusername());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Returns true if update was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Returns false if an error occurred
        }
    }

 	

    
}








