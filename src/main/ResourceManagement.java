package main;

import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

import main.Database;

//import main.Database;

public class ResourceManagement {

    /**
     * .
     * @author James Kim
     * @author Karl Odukwe
     */


    /*
     * NOT COMPLETE YET
     */



    /**
     * Totals the fine of a book and removes it from the book table
     * @param id The ID of the user
     * @param bookFine The calculated fine for a book
     * @return return true with calculated fine, false otherwise
     */

    /**
     * Totals the fine of a book and removes it from the book table
     * @param username The ID of the user
     * @return return true with calculated fine, false otherwise
     */

    public boolean generateBookFine(String username) {
        try {
            int BOOK_FINE = 200;
            ResultSet createBookFine = Database.query("SELECT FROM out_tbl WHERE username = '" + username + "' ;");
            if (!createBookFine.next()) {
                Database.edit("INSERT INTO historic_tbl VALUES(amount, '" + BOOK_FINE  + "' + username, '" + username + "');");
            }

        } catch (Exception e) {

        }
        return false;
    }

    /**
     * Totals the fine of a laptop and removes it from the laptop table
     * @param username The ID of the user
     * @return return true with calculated fine, false otherwise
     */

    public boolean generateLaptopFine(String username) {
        try {
            int LAPTOP_FINE = 1000;
            ResultSet createLaptopFine = Database.query("SELECT FROM out_tbl WHERE username = '" + username + "' ;");
            if (!createLaptopFine.next()) {Database.edit("INSERT INTO historic_tbl VALUES(amount, '" + LAPTOP_FINE + "' + username, '"+ username +"');");

            }
        } catch (Exception e) {

        }
        return false;

    }

    // Generates the total fine for a overdue DVD


    /**
     *
     * @param username The ID of the useR
     * @return returns true with calculated fine, false otherwise
     */

    public boolean generateDVDFine(String username) {
        try {
            int DVD_FINE = 200;
            ResultSet createDVDFine = Database.query("");
            if(!createDVDFine.next()) {
                Database.edit("INSERT INTO historic_tbl VALUES(amount, '" + DVD_FINE+ "' + username, '" + username + "');");
            }
        } catch (Exception e) {

        }
        return false;

    }

    //Allows a librarian to loan out a resource. If there are any outstanding fines however, the system will disallow borrowing until
    // fine is 0 (no outstanding fines)
    public boolean lendCopy(String username, int copyid) {
        try {
            ResultSet rejectLending = Database.query("SELECT * FROM user_tbl WHERE username = '" + username + "' " +
                    "AND balance > 0;");
            if (!rejectLending.next()) {

                Database.edit("INSERT INTO out_tbl VALUES(NULL, " + copyid + ", NOW(), NULL, '" + username + "');");

                JOptionPane.showMessageDialog(null, "Lending successful");

            } else {
                JOptionPane.showMessageDialog(null, "Lending unsuccessful. " +
                        "User has outstanding fines.");
            }
        } catch (Exception e) {
        }
        return false;

    }

    //Checks to see if a resource is available or unavailable and gives a message
    public boolean resourceAvailable(int copyid) {
        try {
            ResultSet checkAvailable = Database.query("SELECT copyid FROM copy_tbl WHERE copyid = " + copyid + " " +
                    "AND active = TRUE AND NOT EXISTS (SELECT * FROM out_tbl WHERE  copyid = " + copyid + ") AND " +
                    "NOT EXISTS (SELECT * FROM reservation_tbl WHERE copyid = " + copyid + ");");

            if (checkAvailable.next()) {
                JOptionPane.showMessageDialog(null, "Resource is available");
            } else {
                JOptionPane.showMessageDialog(null, "Resource is unavailable");
            }

        } catch (Exception e) {
        }
        return false;
    }

    //Allows returning of a resource. If the resource is overdue it will notify the librarian that a fine will be issued
    public boolean returnCopy(String username, int copyid) {
        try {
            ResultSet identicalResource = Database.query("SELECT * FROM out_tbl WHERE copyid = " + copyid + " ;");
            if (identicalResource.next()) {

                ResultSet overdueType = Database.query("SELECT resource_tbl.resourceid, resource_tbl.type FROM copy_tbl, resource_tbl " +
                        "WHERE copy_tbl.resourceid = resource_tbl.resourceid AND copy_tbl.copyid = " + copyid + " ;");

                if(identicalResource.getString("duedate") != null){


                    LocalDate now = LocalDate.now();
                    LocalDate localDate = LocalDate.parse(identicalResource.getString("duedate"));


                    overdueType.next();

                    Period period = Period.between(now, localDate);
                    if (period.getDays() > 0) {

                        JOptionPane.showMessageDialog(null, "Resource is overdue, fine will be issued");

                        if(overdueType.getString("type").equals("book")) {
                            generateBookFine(username);
                        } else if(overdueType.getString("type").equals("DVD")) {
                            generateDVDFine(username);
                        } else {
                            generateLaptopFine(username);
                        }

                    }
                }

                ResultSet checkReserved = Database.query("SELECT * FROM request_tbl, copy_tbl WHERE " +
                        "copy_tbl.resourceid = request_tbl.resourceid AND copy_tbl.copyid = " + copyid + " ORDER BY date;");

                if (checkReserved.next()) {
                    requestCopy(username, overdueType.getInt("resource_tbl.resourceid"));

                    Database.edit("INSERT INTO reservation_tbl VALUES (NULL, " + copyid + ", NOW(), '" + username + "');");

                }

                ResultSet returnResource = Database.query("SELECT * FROM out_tbl WHERE username = '" + username  + "' " +
                        "AND copyid = " + copyid + " ;");

                Database.edit("DELETE FROM out_tbl WHERE username = '" + username + "' AND copyid = " + copyid + " ;");

                Database.edit("INSERT INTO historic_tbl VALUES (NULL, " + copyid + ", datefrom, NOW(), '" + username + "');");

                JOptionPane.showMessageDialog(null, "Resource successfully returned");

            } else {
                JOptionPane.showMessageDialog(null, "Copy ID of resource does not match the returning resource");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    //Allows requesting of a copy of a resource if a copy is not available
    public boolean requestCopy(String username, int resourceid) {
        try {
            Database.edit("INSERT INTO request_tbl VALUES (reqid, " + resourceid + ", NOW(),'" + username + "');");

            ResultSet requestResource = Database.query("SELECT * FROM out_tbl, copy_tbl WHERE " +
                    "out_tbl.copyid = copy_tbl.copyid AND " +
                    "copy_tbl.resourceid = " + resourceid + " AND ISNULL(duedate) ORDER BY datefrom;");

            if (requestResource.next()) {
                int copyid = requestResource.getInt("copyid");
                Date datefrom = requestResource.getDate("datefrom");
                int minloanperiod = requestResource.getInt("minloanperiod");
                Date duedate;


                Calendar cal = Calendar.getInstance();
                cal.setTime(datefrom);
                cal.add(Calendar.DAY_OF_MONTH, minloanperiod);

                if ((new Date()).before(cal.getTime())) {
                    Database.edit("UPDATE out_tbl SET duedate = DATE_ADD('"+datefrom+"', INTERVAL " + (minloanperiod + 1) +" DAY) " +
                            "WHERE copyid = " + copyid + ";");

                } else {

                    Database.edit("UPDATE out_tbl SET duedate = DATE_ADD(DATE(NOW()),INTERVAL 1 DAY) " +
                            "WHERE copyid = " + copyid + ";");

                    JOptionPane.showMessageDialog(null, "Request successful");
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        } return false;
    }


    //The user will be able to pick up his/her reserved copy of a resource once the requested resource has been returned
    public boolean pickupCopy (String username, int copyid) {
        try {
            ResultSet pickupResource = Database.query("SELECT * FROM reservation_tbl, copy_tbl WHERE " +
                    "reservation_tbl.copyid = copy_tbl.copyid AND reservation_tbl.copyid = " + copyid + ";");

            Database.edit("DELETE FROM reservation_tbl WHERE username = '" + username + "' AND copyid = " + copyid + " ;");

            Database.edit("INSERT INTO out_tbl VALUES(outid, " + copyid + ", NOW(), NULL, '" + username + "');");


            if (pickupResource.next()) {
                JOptionPane.showMessageDialog(null,"Resource has been borrowed successfully");
            } else {
                JOptionPane.showMessageDialog(null,"This resource has either not been requested or is currently unavailable");

            }
        } catch (Exception e) {
        } return false;
    }



}

