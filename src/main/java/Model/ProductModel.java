package Model;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.Part;


public class ProductModel {
    private String ProductID;
    private String Name;
    private String Description;
    private int Price;
    private String imageUrlFromPart;

    // Default constructor
    public ProductModel() {
        // Default constructor
    }

    // Constructor with image link parameter
    public ProductModel(String ProductID, String Name, String Description, int Price, Part part) throws IOException {
        this.ProductID = ProductID;
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
        if (part != null) {
            this.imageUrlFromPart = getImageUrl(part);
        } else {
            this.imageUrlFromPart = "default-image.jpg";
        }
    }

    // Getters and Setters
    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public String getImageUrlFromPart() {
        return imageUrlFromPart;
    }

    public void setImageUrlFromPart(Part part) throws IOException {
        this.imageUrlFromPart = getImageUrl(part);
    }

    public void setImageUrlFromDB(String ImageUrl) {
        this.imageUrlFromPart = ImageUrl;
    }

    private String getImageUrl(Part part) throws IOException {
        String savePath = "C:\\Users\\HP\\eclipse-workspace\\coursework\\src\\main\\webapp\\Resources\\Images\\addpro";
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs(); // Create directory if it doesn't exist
        }

        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        String fileName = null;
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                fileName = s.substring(s.indexOf("=") + 2, s.length() - 1).replace("\\", "");
                break;
            }
        }

        if (fileName != null && !fileName.isEmpty()) {
            part.write(savePath + File.separator + fileName);
        } else {
            fileName = "download.png"; // Default image if no file uploaded
        }
        return fileName;
    }
}
