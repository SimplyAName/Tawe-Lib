package main;

/**
 * Create a resource with the type of resource and dates that are relevant to the object.
 */
public class UserDashResource extends Resource {

    private String type;
    private String generalDate;
    private String generalDate2;

    /**
     * Constructor for the class with one date.
     *
     * @param rID         The unique ID for the resource
     * @param title       The title of the resource
     * @param type        The type of resource.
     * @param generalDate A date for the resource
     */
    public UserDashResource(int rID, String title, String type, String generalDate) {
        super(rID, title, 0, null);
        this.type = type;
        this.generalDate = generalDate;
    }

    /**
     * Constructor for the class.
     *
     * @param rID          The unique ID for the resource
     * @param title        The title of the resource
     * @param type         The type of resource.
     * @param generalDate  A date for the resource
     * @param generalDate2 A second date for the resource
     */
    public UserDashResource(int rID, String title, String type, String generalDate, String generalDate2) {
        super(rID, title, 0, null);
        this.type = type;
        this.generalDate = generalDate;
        this.generalDate2 = generalDate2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGeneralDate() {
        return generalDate;
    }

    public void setGeneralDate(String generalDate) {
        this.generalDate = generalDate;
    }

    public String getGeneralDate2() {
        return generalDate2;
    }

    public void setGeneralDate2(String generalDate2) {
        this.generalDate2 = generalDate2;
    }
}
