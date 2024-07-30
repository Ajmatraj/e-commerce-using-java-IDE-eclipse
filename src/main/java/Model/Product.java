package Model;
import java.time.LocalDate;

public class Product {
    private int productId;
    private String productName;
    private int brandId; // Refers to the brand associated with this product
    private String model;
    private String description;
    private double price;
    private int stockAvailability;
    private LocalDate dateAdded;
    private int statusId; // Refers to the status of the product
    
    public Product() {}

    public Product(int productId, String productName, int brandId, String model, String description, double price, int stockAvailability, LocalDate dateAdded, int statusId) {
        this.productId = productId;
        this.productName = productName;
        this.brandId = brandId;
        this.model = model;
        this.description = description;
        this.price = price;
        this.stockAvailability = stockAvailability;
        this.dateAdded = dateAdded;
        this.statusId = statusId;
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockAvailability() {
        return stockAvailability;
    }

    public void setStockAvailability(int stockAvailability) {
        this.stockAvailability = stockAvailability;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
