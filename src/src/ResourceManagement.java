package src;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
import main.Database;



/*
 *
 * 	VERY INCOMPLETE
 */
public class ResourceManagement {

//	private int fineCost;
//
//	public boolean generateBookFine(int daysOverdue, String userID) {
//		ResultSet checkedResource = sample.Database.query("SELECT from out_tb1, WHERE username = '" + userID + "' AND dueDate = '" + daysOverdue + "' + "';");
//				return  checkedResource.next();
//		}
//
//
//	public boolean generateLaptopFine() {
//
//	}
//
//	public boolean generateDVDFine() {
//
//	}
//
//	public boolean isResourceRequested() {
//
//	}
//
//	public boolean borrowReservedResource() {
//
//
//	}

    //Allows user to borrow a resource. If there are any outstanding fines however, the system will disallow borrowing until
    // fine is 0 (no outstanding fines)
    public boolean borrowCopy(String username, int copyid) {
        try {
            ResultSet rejectBorrow = Database.query("SELECT * FROM user_tbl WHERE username = '" + username + "' " +
                    "AND balance > 0;");
            if (!rejectBorrow.next()) {

                Database.edit("INSERT INTO out_tbl VALUES(NULL, " + copyid + ", NOW(), NULL, '" + username + "');");

                JOptionPane.showMessageDialog(null, "Borrowing successful");

            } else {
                JOptionPane.showMessageDialog(null, "Borrowing unsuccessful. " +
                        "You have outstanding fines. Please visit a librarian");
            }
        } catch (Exception e) {
        } return false;

    }

    //Checks to see if a resource is available or unavailable and gives a message
    public boolean resourceAvailable(int copyid) {
        try {
            ResultSet checkAvailable = Database.query("SELECT copyid FROM copy_tbl WHERE copyid = " + copyid + " " +
                    "AND active = TRUE AND NOT EXISTS (SELECT * FROM out_tbl WHERE  copyid = " + copyid + ") AND " +
                    "NOT EXISTS (SELECT * FROM reservation_tbl WHERE copyid = " + copyid + ");");

            if(checkAvailable.next()) {
                JOptionPane.showMessageDialog(null,"Resource is available");
            } else {
                JOptionPane.showMessageDialog(null, "Resource is unavailable");
            }

        } catch(Exception e) {
        } return false;
    }

    //Allows returning of a resource. If the resource is overdue it will notify the user to go to a librarian to pay a fine.
    public boolean returnCopy(String username, int copyid) {
        try {
            ResultSet returnResource = Database.query("SELECT * FROM out_tbl WHERE duedate < NOW() AND  +" +
                    "username = '" + username + "' AND copyid = " + copyid + " ;");

            Database.edit( "INSERT INTO historic_tbl VALUES (NULL, " + copyid + ", datefrom, NOW(), '" + username + "');");

            if(returnResource.next()) {
                try {

                    ResultSet overdueResource = Database.query("SELECT * FROM request_tbl, copy_tbl WHERE copy_tbl.resourceid = request_tbl.resourceid " +
                            "ORDER BY request_tbl.date;");

                    Database.edit("INSERT INTO reservation_tbl VALUES (NULL, " + copyid + ", NOW(), '" + username + "');");

                    JOptionPane.showMessageDialog(null, "Resource is overdue, please go to a librarian");
                } catch (Exception e) {
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null,"Return successful");
            }

            Database.edit("DELETE FROM out_tbl WHERE username = '" + username + "' AND copyid = " + copyid + " ;");
        } catch (Exception e) {
            return false;
        } return true;
    }

    //Allows requesting of a copy of a resource if a copy is not available
    public boolean requestCopy(String username, int copyid, int resourceid) {
        try {
            Database.edit("INSERT INTO request_tbl VALUES (NULL, " + resourceid + ", NOW(),'" + username + "');");

            ResultSet requestResource = Database.query("SELECT * FROM out_tbl, copy_tbl WHERE " +
                    "out_tbl.copyid = copy_tbl.copyid AND " +
                    "copy_tbl.resourceid = " + resourceid + " AND duedate = NULL ORDER BY datefrom;");

            JOptionPane.showMessageDialog(null,"Request successful");

            return requestResource.next();
        } catch(Exception e) {
        } return false;
    }


    //The user will be able to pick up his/her reserved copy of a resource once the requested resource has been returned
    public boolean pickupCopy (int copyid) {
        try {
            ResultSet pickupResource = Database.query("SELECT * FROM request_tbl, copy_tbl WHERE " +
                    "request_tbl.resourceid = copy_tbl.resourceid AND copyid = " + copyid + ";");


            if (pickupResource.next()) {
                JOptionPane.showMessageDialog(null,"You have successfully borrowed this resource");
            } else {
                JOptionPane.showMessageDialog(null,"This resource has either not been requested or is currently unavailable");

            }
        } catch (Exception e) {
        } return false;
    }

}



//ResultSet checkExistingFines = sample.Database.query("SELECT FROM fine_tb1 WHERE username = '"+ userID + "' AND amount = '"+ fineToPay + "' ';" );

