package main;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CreateUserController {
	@FXML private TextField firstName;
	@FXML private TextField lastName;
	@FXML private TextField eAddress;
	@FXML private TextField enterUsername;
	@FXML private TextField ePostCode;
	@FXML private TextField phoneNumber;
	@FXML private AnchorPane CreateUserPane;
	
	private String fName;
	private String lName;
	private String address;
	private String username;
	private String postCode;
	private String phone;
	private int balance;
	
	private String nameQuery = "select username from user_tbl where username = " + enterUsername.getText() + ";";
	private String addUserQuery;
	private String imagelocation;
	
	
	@FXML private void addUser() throws IllegalArgumentException, SQLException{
		fName = firstName.getText();
		lName = lastName.getText();
		address = eAddress.getText();
		postCode = ePostCode.getText();
		phone = phoneNumber.getText();
		balance = 0;
		imagelocation = "";
		
		//checks if the username is already taken
		if (Database.query(nameQuery) == null) {
			username = enterUsername.getText();
		} else {
			;
		}
		

		try{
		addUserQuery = (" insert into user_tbl (username, firstnames, lastname, addrline1, postcode, phone, imagelocation, balance)"
				+ " values (" + fName + " ," + lName + " ," + address + " ," + postCode + " ," + imagelocation + " ," + balance + " );" );
		}
		
		catch (Exception e1) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.showAndWait();
			
		}
		
	}



}
