package main;

public class OverdueCopy {

    private int copyid;
    private String dateTakenOut;
    private String dateDue;
    private String username;

    private String resourceName;
    private String resourceType;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public int getCopyid() {
        return copyid;
    }

    public void setCopyid(int copyid) {
        this.copyid = copyid;
    }

    public String getDateTakenOut() {
        return dateTakenOut;
    }

    public void setDateTakenOut(String dateTakenOut) {
        this.dateTakenOut = dateTakenOut;
    }

    public String getDateDue() {
        return dateDue;
    }

    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
