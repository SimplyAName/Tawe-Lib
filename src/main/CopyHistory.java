package main;

/**
 * This class hold all history related data about a copy.
 */
public class CopyHistory {

    private String copyTitle;
    private String copyBorrowed;
    private String copyReturned;
    private String borrowedBy;

    /**
     * 
     * @param copyTitle The title of the copied resource
     * @param copyBorrowed The time the copy has borrowed
     * @param copyReturned The time the copy has been returned
     * @param borrowedBy The time when the copy has been borrowed
     */
    public CopyHistory(String copyTitle, String copyBorrowed, String copyReturned, String borrowedBy) {
        this.copyTitle = copyTitle;
        this.copyBorrowed = copyBorrowed;
        this.copyReturned = copyReturned;
        this.borrowedBy = borrowedBy;
    }

    /*
     * Gets the title of the resource
     */
    public String getCopyTitle() {
        return copyTitle;
    }

    /*
     * Gets the copy of the borrowed resource
     */
    public String getCopyBorrowed() {
        return copyBorrowed;
    }

    /*
     * Gets the returned copy
     */
    public String getCopyReturned() {
        return copyReturned;
    }

    /*
     * Gets the copies borrowed by date
     */
    public String getBorrowedBy() {
        return borrowedBy;
    }
}
