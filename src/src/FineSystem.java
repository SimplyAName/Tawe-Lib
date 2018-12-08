import javafx.fxml.FXML;
import java.sql.ResultSet;
import javafx.scene.control.TextField;
import java.sql.ResultSet;

public class FineSystem {
	
	@FXML
	private TextField username;
	private TextField fineToPay;

	protected boolean checkExistingFines(String userID, double fineToPay) {
		try {
			ResultSet checkExistingFines = Database.query("SELECT FROM fine_tb1 WHERE username = '"+ userID + "' AND amount = '"+ fineToPay + "' ';" );
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
}
