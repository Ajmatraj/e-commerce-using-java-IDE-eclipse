package Model;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.Part;

public class adminModel {
	
	private String username;
	private String password;
	private String email;
	private String imageUrlFromPart;
	
	
	
	public adminModel() {
		
	}
	
	
	
	
	public adminModel(String username, String password, String email, Part part) {
		super();
		
		this.username = username;
		this.password = password;
		this.email = email;
		if (part != null) {
			try {
				this.imageUrlFromPart = getImageUrl(part);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
            this.imageUrlFromPart = "default-image.jpg"; // Provide a default image URL if no image is uploaded
        }
	}




	




	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getImageUrlFromPart() {
		return imageUrlFromPart;
	}

	public void setImageUrlFromPart(Part part) {
		try {
			this.imageUrlFromPart = getImageUrl(part);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 public void setImageUrlFromDB(String ImageUrl) {
	        this.imageUrlFromPart = ImageUrl;
	    }

	    private String getImageUrl(Part part) throws IOException {
	        String savePath = "C:\\Users\\HP\\eclipse-workspace\\coursework\\src\\main\\webapp\\Resources\\Images\\adminPhoto";
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
	