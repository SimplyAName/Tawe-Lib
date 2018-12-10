package main;

/**
 * This class hold all history related data about a copy.
 */
public class CopyHistory {

    private String copyTitle;
    private String copyBorrowed;
    private String copyReturned;
    private String borrowedBy;

    public CopyHistory(String copyTitle, String copyBorrowed, String copyReturned, String borrowedBy) {
        this.copyTitle = copyTitle;
        this.copyBorrowed = copyBorrowed;
        this.copyReturned = copyReturned;
        this.borrowedBy = borrowedBy;
    }

    public String getCopyTitle() {
        return copyTitle;
    }

    public String getCopyBorrowed() {
        return copyBorrowed;
    }

    public String getCopyReturned() {
        return copyReturned;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }
}
