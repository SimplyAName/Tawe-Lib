package main;

import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.Database;
import main.FineSystem;

public class FineManagement extends FineSystem {

	@FXML
	private TextField username;
	private TextField fineToPay;

	public void payAmount() {
		try {
			Database.edit("UPDATE user_tb1 SET balance = balance - " + Integer.parseInt(fineToPay.getText())
					+ " WHERE username =  " + username.getText() + ";");

			String alteredFine = fineToPay.getText() + "was payed off sucessfully";
			username.clear();
			fineToPay.clear();

		} catch

		(Exception e) {
			String failedFine = "An error occured";
		}
	}

	public boolean getAccountBalance(String username, int fineBalance) {
		try {
			ResultSet balance = Database.query(
					"SELECT FROM user_tb1 WHERE username = '" + username + "' AND amount = '" + fineBalance + "' ';");
			return balance.next();
		} catch 
		(Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
