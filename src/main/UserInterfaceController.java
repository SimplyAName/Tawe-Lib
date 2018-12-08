package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is the 'User Dashboard' of the system.
 * @author Alex Rasa
 * @version 1.1.2
 *
 */
public class UserInterfaceController implements Initializable {

	@FXML
	private ImageView profileImageView;

	@FXML
	private Label lblName;

	@FXML
	private Label lblBalance;
	
	private User user;
	
	public UserInterfaceController(User user) {
		this.user = user;
	}


	public void showRequestedResource(int resourceID, String resourceTitle) {
		
	}
	
	public void showReservedResource(int resourceID, String resourceTitle) {
		
	}
	
	public void showUnavailableResource(int resourceID, String resourceTitle) {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblName.setText(user.getFirstName() + " " + user.getLastName());
		lblBalance.setText(String.valueOf(user.getFineBalance()));
		profileImageView.setImage(user.getProfileImage());
	}
}
