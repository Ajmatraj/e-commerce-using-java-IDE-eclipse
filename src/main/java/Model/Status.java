package Model;

public class Status {
    private int statusId;
    private String statusName;

    public Status(int statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    // Getters and Setters
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}

