package Model;

public class CartModel {
    private int cart_Id;           // Unique identifier for each cart entry
    private int user_Info_Id;      // User ID to associate with the cart
    private String ProductID;      // Product ID
    private int quantity;          // Quantity of the product
    private String Name;           // Name of the product
    private String imgLink;        // Image URL or path of the product
    private int Price;             // Price of the product

    // Default constructor
    public CartModel() {
    }

    // Parameterized constructor
    public CartModel(int cart_Id, int user_Info_Id, String productID, int quantity, String name, String imgLink, int price) {
        this.cart_Id = cart_Id;
        this.user_Info_Id = user_Info_Id;
        this.ProductID = productID;
        this.quantity = quantity;
        this.Name = name;
        this.imgLink = imgLink;
        this.Price = price;
    }

    // Getters and Setters
    public int getCart_Id() {
        return cart_Id;
    }

    public void setCart_Id(int cart_Id) {
        this.cart_Id = cart_Id;
    }

    public int getUser_Info_Id() {
        return user_Info_Id;
    }

    public void setUser_Info_Id(int user_Info_Id) {
        this.user_Info_Id = user_Info_Id;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        this.ProductID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        this.Price = price;
    }
}
