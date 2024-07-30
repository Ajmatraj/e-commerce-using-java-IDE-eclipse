package Model;

public class FeedbackModel {
	private String Name;
	private String Email;
	private String PhoneNumber;
	private String Subject;
	private String Message;
	
	public FeedbackModel(String name, String email, String phoneNumber, String subject, String message) {
		super();
		Name = name;
		Email = email;
		PhoneNumber = phoneNumber;
		Subject = subject;
		Message = message;
	}
	
	public FeedbackModel()
	{}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	
	
}