package src;

import javafx.scene.image.Image;

import java.util.Date;

public class Librarian extends User {

    private int staffNumber;
    private Date employmentDate;

    public Librarian(){

    }

    public Librarian(int staffNumber, Date employmentDate) {
        this.staffNumber = staffNumber;
        this.employmentDate = employmentDate;
    }

    public Librarian(String username, String firstName, String lastName, String addressLine, String postcode, String phoneNumber, Image profileImage, int fineBalance, int staffNumber, Date employmentDate) {
        super(username, firstName, lastName, addressLine, postcode, phoneNumber, profileImage, fineBalance);
        this.staffNumber = staffNumber;
        this.employmentDate = employmentDate;
    }

    public int getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(int staffNumber) {
        this.staffNumber = staffNumber;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }
}
