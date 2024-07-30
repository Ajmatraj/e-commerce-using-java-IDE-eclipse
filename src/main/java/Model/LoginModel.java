package Model;

public class LoginModel {
	private String user_name;
	private String password;
	
	public LoginModel(String user_name, String password) {
		this.user_name = user_name;
		this.password = password;
	}
	
	public String getUsername() {
		return user_name;
	}
	public void setUsername(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}