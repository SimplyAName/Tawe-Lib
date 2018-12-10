package main;

import javafx.scene.image.Image;

import java.util.Date;

/**
 * Creates a librarian.
 *
 * @author Michael Loney
 */
public class Librarian extends User {

    private int staffNumber;
    private Date employmentDate;

    /**
     * To create an empty librarian.
     */
    public Librarian(){

    }

    /**
     * A Librarian with only their staff number and employment date.
     * @param staffNumber The staff number of the user.
     * @param employmentDate The employment date of the user
     */
    public Librarian(int staffNumber, Date employmentDate) {
        this.staffNumber = staffNumber;
        this.employmentDate = employmentDate;
    }

    /**
     * Create a librarian will all data filled.
     * @param username username of the librarian.
     * @param firstName First name of the librarian.
     * @param lastName Last name of the librarian.
     * @param addressLine Address of the librarian.
     * @param postcode Postcode of the librarian.
     * @param phoneNumber Phone number of the librarian.
     * @param profileImage Profile image for the librarian.
     * @param fineBalance Fine balance for the librarian.
     * @param staffNumber The staff number of the librarian.
     * @param employmentDate The employment date of the librarian.
     */
    public Librarian(String username, String firstName, String lastName, String addressLine, String postcode, String phoneNumber, Image profileImage, int fineBalance, int staffNumber, Date employmentDate) {
        super(username, firstName, lastName, addressLine, postcode, phoneNumber, profileImage, fineBalance);
        this.staffNumber = staffNumber;
        this.employmentDate = employmentDate;
    }

    /**
     * Gets the staff number of the librarian.
     * @return The staff number.
     */
    public int getStaffNumber() {
        return staffNumber;
    }

    /**
     * Sets the staff number of the librarian
     * @param staffNumber New staff number
     */
    public void setStaffNumber(int staffNumber) {
        this.staffNumber = staffNumber;
    }

    /**
     * Get the day the librarian was employed
     * @return The employment date.
     */
    public Date getEmploymentDate() {
        return employmentDate;
    }
}
