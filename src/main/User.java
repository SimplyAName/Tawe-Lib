package main;

import javafx.scene.image.Image;

public class User {

    private String username;
    private String firstName;
    private String lastName;
    private String addressLine;
    private String postcode;
    private String phoneNumber;
    private Image profileImage;
    private int fineBalance;

    public User(){

    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Image getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }

    public int getFineBalance() {
        return fineBalance;
    }

    public void setFineBalance(int fineBalance) {
        this.fineBalance = fineBalance;
    }
}
