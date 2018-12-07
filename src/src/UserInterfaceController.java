


/* README
 * 
 * Use the code below to open this window from somewhere else:
 * 
  			FXMLLoader fLoader = new FXMLLoader(getClass().getResource("UserInterface.fxml"));
			UserInterfaceController controller = new UserInterfaceController(new User(0, "al","Alex Rasa", "07714634634", "2 Green Road", null, 1.23)); 
			fLoader.setController(controller);
			VBox root = fLoader.load();
			
			Scene scene = new Scene(root,600,400);
			//presets the labels and object values on the scene
			controller.setup();
 * */


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * This is the 'User Dashboard' of the system.
 * @author Alex Rasa
 * @version 1.1.2
 *
 */
public class UserInterfaceController {

	@FXML ImageView imgProfPic;
	@FXML Label lblName;
	@FXML Label lblBalance;
	
	private User user;
	
	public UserInterfaceController(User user) {
		this.user = user;	//####????
	}

	/**
	 * This method can be called to setup values on the window before it gets displayed.
	 * */
	public void setup() {
		lblName.setText(user.getName());
		lblBalance.setText(String.valueOf(user.getFineBalance()));
	}
	
	/**
	 * This will create an User and add it to the database.###???
	 */
	public void createNewUser(String username, String name, String phoneNum, String address, String profileImg) {
		
	}

	public void showRequestedResource(int resourceID, String resourceTitle) {
		
	}
	
	public void showReservedResource(int resourceID, String resourceTitle) {
		
	}
	
	public void showUnavailableResource(int resourceID, String resourceTitle) {
		
	}

}
