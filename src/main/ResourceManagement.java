package main;


import javax.swing.JOptionPane;
import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.*;

import main.Database;


/*
 *
 * 	VERY INCOMPLETE
 */
public class ResourceManagement {

//	private int fineCost;
//
//	public boolean generateBookFine(int daysOverdue, String userID) {
//		ResultSet checkedResource = Database.query("SELECT from out_tb1, WHERE username = '" + userID + "' AND dueDate = '" + daysOverdue + "' + "';");
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
//fine is 0 (no outstanding fines)
    public boolean borrowCopy(String username, int copyid) {
        try {
            ResultSet rejectBorrow = Database.query("SELECT * FROM user_tbl WHERE username = '" + username + "' " +
                    "AND balance > 0;");
            if (!rejectBorrow.next()) {

                Database.edit("INSERT INTO out_tbl VALUES(NULL, " + copyid + ", NOW(), NULL, '" + username + "');");

            } else {
                JOptionPane.showMessageDialog(null, "You have outstanding fines. Please visit a librarian");
            }
        } catch (Exception e) {
        }
        return false;

    }

    //Checks to see if a resource is available, otherwise returns false
    public boolean resourceAvailable(int copyid) {
        try {
            ResultSet checkAvailable = Database.query("SELECT copyid FROM copy_tbl AS temp WHERE resourceid = " + copyid + " + AND " +
                    "active = TRUE AND NOT EXISTS (SELECT * FROM out_tbl WHERE out_tbl.copyid = temp.copyid) AND " +
                    "NOT EXISTS (SELECT * FROM reservation_tbl WHERE reservation_tbl.copyid = temp.copyid);");

            return checkAvailable.next();
        } catch (Exception e) {
        }
        return false;
    }

    //Allows returning of a resource if the return date does not exceed the due date. Otherwise it puts the user into the
    public boolean returnCopy(String username, int copyid) {
        try {
            ResultSet returnResource = Database.query("SELECT * FROM out_tbl WHERE duedate < NOW() AND  +" +
                    "username = " + username + " AND copyid = " + copyid + " ;");

            Database.edit("INSERT INTO historic_tbl VALUES (NULL, " + copyid + ", datefrom, NOW(), " + username + ");");

            if (returnResource.next()) {
                try {
                    ResultSet overdueResource = Database.query("DELETE FROM out_tbl WHERE username = " + username + " AND copyid = " + copyid + " " +
                            "SELECT * FROM request_tbl, copy_tbl WHERE copy_tbl.resourceid = request_tbl.resourceid ORDER BY request_tbl.date " +
                            "INSERT INTO reservation_tbl VALUES (NULL, " + copyid + ", NOW(), " + username + ");");

                    JOptionPane.showMessageDialog(null, "Resource is overdue, please go to a librarian");
                } catch (Exception e) {
                }
                return false;

            } else {
                return true;
            }
        } catch (Exception e) {
        }
        return true;
    }

    //Allows requesting of a resource if a resource is not available
    public boolean requestCopy(String username, int copyid) {
        try {
            ResultSet requestResource = Database.query("INSERT INTO request_tbl VALUES (NULL, resourceid, NOW(), username)" +
                    "SELECT * FROM out_tbl, copy_tbl WHERE out_tbl.copyid = copy_tbl.copyid AND " +
                    "copy_tbl.resourceid = ENTER_HERE AND duedate = NULL ORDER BY date;");

            return requestResource.next();
        } catch (Exception e) {
        }
        return false;
    }


    //The user will be able to pick up his/her reserved copy of a resource once the requested resource has been returned
    public boolean pickupCopy(int copyid) {
        try {
            ResultSet pickupResource = Database.query("SELECT * FROM request_tbl, copy_tbl WHERE " +
                    "request_tbl.copyid = copy_tbl.requestid AND resource copyid = " + copyid + ";");

            return pickupResource.next();
        } catch (Exception e) {
        }
        return false;
    }


}

//USE USERNAME AND COPYID


//ResultSet checkExistingFines = Database.query("SELECT FROM fine_tb1 WHERE username = '"+ userID + "' AND amount = '"+ fineToPay + "' ';" );

