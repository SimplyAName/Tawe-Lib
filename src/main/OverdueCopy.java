package main;

/**
 * Handles the propeerties to deal with an overdue resrouce
 * @author 
 *
 */
public class OverdueCopy {

    private int copyid;
    private String dateTakenOut;
    private String dateDue;
    private String username;

    private String resourceName;
    private String resourceType;


    /**
     * @return Returns name of the resource
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * @param Sets the name of the resource
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * @return Returns the type of resource
     */
    public String getResourceType() {
        return resourceType;
    }


    /**
     * @param Sets the resource type
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }


    /**
     * @return Returns resource copy ID
     */
    public int getCopyid() {
        return copyid;
    }


    /**
     * @param copyid sets the resource copy ID
     */
    public void setCopyid(int copyid) {
        this.copyid = copyid;
    }


    /**
     * @return Returns the date the resource was taken out
     */
    public String getDateTakenOut() {
        return dateTakenOut;
    }


    /**
     * @param dateTakenOut Sets the date the resource was taken out
     */
    public void setDateTakenOut(String dateTakenOut) {
        this.dateTakenOut = dateTakenOut;
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

    /**
     * @return Gets resource due date
     */
    public String getDateDue() {
        return dateDue;
    }

}
