package Model;

public class LoginResult {
    private int status;
    private String role;

    public LoginResult(int status, String role) {
        this.status = status;
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public String getRole() {
        return role;
    }
}
