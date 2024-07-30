package Model;

public class OrderModel {
    private int orderId;              // Unique identifier for each order
    private String productId;        // Product ID
    private String productName;      // Product Name
    private String productDescription; // Product Description
    private int price;               // Price of the product
    private String username;         // Username of the person placing the order
    private String phoneNumber;      // Phone number of the person placing the order
    private String email;            // Email address of the person placing the order
    private int quantity;            // Quantity of the product ordered
    private double totalPrice;       // Total price for the quantity of the product

    // Default constructor
    public OrderModel() {
    }

    // Parameterized constructor
    public OrderModel(int orderId, String productId, String productName, String productDescription, 
                      int price, String username, String phoneNumber, String email, 
                      int quantity, double totalPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
