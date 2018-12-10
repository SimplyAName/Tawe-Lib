package main;

import javafx.fxml.FXML;
import java.sql.ResultSet;
import javafx.scene.control.TextField;


/**
 * This class represents a fine system, where you can check existing fines
 * and view transaction history
 * @author Karl Odukwe
 *
 */
public class FineSystem {

    @FXML
    private TextField username;
    private TextField fineToPay;

    
    /**
     * Checks a user's account to see if they have any existing fines
     * @param userID The ID of the user
     * @param fineToPay
     * @return Returns true with existing fines, false if no fines exist
     */
    protected boolean checkExistingFines(String userID, double fineToPay) {
        try {
            ResultSet checkExistingFines = Database.query("SELECT FROM fine_tb1 "
            		+ "WHERE username = '" + userID + "' AND amount = '" + fineToPay + "' ';");
            return true;
        } catch
        (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Gets the user's transaction history, if they have any
     * @param userID ID of the user
     * @return Returns true with user transaction history, false if non exists
     */
    private boolean getTransactionHistory(String userID) {

        try {
            ResultSet checkTransactionHistory = Database.query("SELECT amount,date"
            		+ " FROM transaction_tbl WHERE username = '" + userID + "';");
            return true;
        } catch
        (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
