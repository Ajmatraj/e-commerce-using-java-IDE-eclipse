package Model;

public class CartItem {
    private int cart_Id;
    private int user_Info_Id;
    private String productID;
    private int quantity;
    private int price;
    private String imgLink;
    private String name;

    // Default constructor
    public CartItem() {}

    // Parameterized constructor
    public CartItem(int cart_Id, int user_Info_Id, String productID, int quantity, int price, String imgLink, String name) {
        this.cart_Id = cart_Id;
        this.user_Info_Id = user_Info_Id;
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
        this.imgLink = imgLink;
        this.name = name;
    }

    // Getters and setters
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
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
