package main;

import javafx.fxml.FXML;
import java.sql.ResultSet;
import javafx.scene.control.TextField;

public class FineSystem {
	
	public int getAccountBalance(String username){
		try {
			ResultSet set = Database.query("SELECT balance FROM user_tb1 WHERE username = '" + username + "';");
			if(set.next()){
				return set.getInt("balance");
			}
			return 0;
		} catch 
		(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	
	/*
    @FXML
    private TextField username;
    private TextField fineToPay;

    protected boolean checkExistingFines(String userID, double fineToPay) {
        try {
            ResultSet checkExistingFines = Database.query("SELECT FROM fine_tb1 WHERE username = '" + userID + "' AND amount = '" + fineToPay + "' ';");
            return true;
        } catch
        (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean getTransactionHistory(String userID) {

        try {
            ResultSet checkTransactionHistory = Database.query("SELECT amount,date FROM transaction_tbl WHERE username = '" + userID + "';");
            return true;
        } catch
        (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    */
}
