


/* README
 * 
 * Use the code below to open this window from somewhere else:
 * 
			FXMLLoader fLoader = new FXMLLoader(getClass().getResource("LibrarianInterface.fxml"));
			LibrarianInterfaceController controller = new LibrarianInterfaceController(new Librarian(0, "al","Alex Rasa", "07714634634", "2 Green Road", null, 1.23, null, null)); 
			fLoader.setController(controller);
			VBox root = fLoader.load();
			
			Scene scene = new Scene(root,600,400);
			//presets the labels and object values on the scene
			controller.setup();
 * */


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * This is the 'Librarian Dashboard' of the system.
 * @author Alex Rasa
 * @version 1.1.2
 *
 */
public class LibrarianInterfaceController {
	
	@FXML ImageView imgProfPic;
	@FXML Label lblName;
	@FXML Label lblBalance;

	private Librarian librarian;
	
	public LibrarianInterfaceController(Librarian librarian) {
		this.librarian = librarian;
	}

	/**
	 * This method can be called to setup values on the window before it gets displayed.
	 * */
	public void setup() {
		lblName.setText(librarian.getName());
		lblBalance.setText(String.valueOf(librarian.getFineBalance()));
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
