package main;

import javafx.scene.image.Image;


/**
 * Creates a user.
 * @author Michael Loney
 */
public class User {

    private String username;
    private String firstName;
    private String lastName;
    private String addressLine;
    private String postcode;
    private String phoneNumber;
    private Image profileImage;
    private int fineBalance;

    /**
     * For creating an empty user.
     */
    public User() {
    }

    /**
     * Create a user will all data filled.
     * @param username username of the user.
     * @param firstName first name of the user.
     * @param lastName last name of the user.
     * @param addressLine address of the user minus postcode.
     * @param postcode postcode of the user.
     * @param phoneNumber phone number of the user.
     * @param profileImage profile image of the user.
     * @param fineBalance The remaining fine amount to be payed off by the user.
     */
    public User(String username, String firstName, String lastName, String addressLine, String postcode, String phoneNumber, Image profileImage, int fineBalance) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressLine = addressLine;
        this.postcode = postcode;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.fineBalance = fineBalance;
    }

    /**
     * Gets the username of the user.
     * @return The users username;
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     * @param username New username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the first name of the user.
     * @return First name of the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     * @param firstName New first name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     * @return Last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     * @param lastName New last name of the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the address without the postcode of the user.
     * @return The users address.
     */
    public String getAddressLine() {
        return addressLine;
    }

    /**
     * Sets the address of the user.
     * @param addressLine New address of the user.
     */
    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    /**
     * Gets the postcode of the user.
     * @return Postcode of the user.
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Sets the postcode of the user.
     * @param postcode New postcode of the user.
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * Gets the phone number of the user.
     * @return Phone number of the user.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the user.
     * @param phoneNumber New phone number of the user.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the profile image of the user.
     * @return An image of the users profile image.
     */
    public Image getProfileImage() {
        return profileImage;
    }

    /**
     * Sets the users profile picture.
     * @param profileImage The users new profile picture.
     */
    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }

    /**
     * Gets the remaining fine balance of the user.
     * @return lastName.
     */
    public int getFineBalance() {
        return fineBalance;
    }

    /**
     * Sets the remaining fine balance of the user.
     * @param fineBalance The new fine balance of the user.
     */
    public void setFineBalance(int fineBalance) {
        this.fineBalance = fineBalance;
    }
}
