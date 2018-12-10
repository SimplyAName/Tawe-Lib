package main;

import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.Database;
import main.FineSystem;

public class FineManagement extends FineSystem {
	private final int BOOK_FINE_PER_DAY = 200;
	private final int DVD_FINE_PER_DAY = 200;
	private final int LAPTOP_FINE_PER_DAY = 1000;
	
	private final int BOOK_FINE_MAX = 2500;
	private final int DVD_FINE_MAX = 2500;
	private final int LAPTOP_FINE_MAX = 10000;
	
	
	public void payAmount(User username, int amount) {
		
		try {
			Database.edit("UPDATE user_tb1 SET balance = balance - " + amount
					+ " WHERE username =  '" + username+ "';");			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean generateFine(String username, int copyID, int daysOverdue){
		//refering to histid in the database
		int histid = 0;
		int fineAmount = 0;
		try{
			ResultSet set = Database.query("SELECT type FROM resource_tbl, copy_tbl WHERE resource_tbl.resourceid = copy_tbl.resourceid AND copy_tbl.copyid = "
		+copyID +";");
			ResultSet set2 = Database.query("SELECT * FROM historic_tbl, copy_tbl WHERE historic_tbl.copyid = copy_tbl.copyid AND username = '"
		+username +"' AND copy_tbl.copyid = "+copyID+" ORDER BY datetil ASC");
						
			if(set.next()){
				if(set2.next()){
					histid = set2.getInt("histid");
					switch(set.getString("type")){
					case "book":
						fineAmount += (BOOK_FINE_PER_DAY * daysOverdue);
						if(fineAmount > BOOK_FINE_MAX){
							fineAmount = BOOK_FINE_MAX;
						}
						break;
					
					case "dvd":
						fineAmount += (DVD_FINE_PER_DAY * daysOverdue);
						if(fineAmount > DVD_FINE_MAX){
						fineAmount = DVD_FINE_MAX;
						}	
						break;
					
					case "laptop":
						fineAmount += (LAPTOP_FINE_PER_DAY * daysOverdue);
						if(fineAmount > LAPTOP_FINE_MAX){
							fineAmount = LAPTOP_FINE_MAX;
						}
						break;
					}
				}
			}
			
			System.out.println(fineAmount);
			try{
				Database.edit("INSERT INTO fine_tbl VALUES ("+histid+", "+fineAmount+", NOW(), "+daysOverdue+", '"+username+"');");
			}catch(Exception e){
				throw new Exception("Could not complete");
			}
			try{
				Database.edit("UPDATE user_tbl SET balance = balance + "+fineAmount+" WHERE username ='"+username+"';");
			}catch(Exception e){
				throw new Exception("Could not complete update");
			}
				 
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	
	/*
	@FXML
	private TextField username;
	private TextField fineToPay;
	
	public void payAmount() {
		try {
			Database.edit("UPDATE user_tb1 SET balance = balance - " + Integer.parseInt(fineToPay.getText())
					+ " WHERE username =  '" + username.getText() + "';");

			String alteredFine = fineToPay.getText() + "was payed off sucessfully";
			username.clear();
			fineToPay.clear();

		} catch(Exception e) {
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
	*/
}
