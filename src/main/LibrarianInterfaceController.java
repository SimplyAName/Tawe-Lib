package main;

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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is the 'Librarian Dashboard' of the system.
 * @author Alex Rasa
 * @version 1.1.2
 *
 */
public class LibrarianInterfaceController implements Initializable {

	@FXML
	private ImageView profileImageView;
	@FXML
	private Label lblName;
	@FXML
	private Label lblBalance;

    private BorderPane mainPane;

	private Librarian librarian;

    public LibrarianInterfaceController(Librarian librarian, BorderPane mainPane) {
		this.librarian = librarian;
        this.mainPane = mainPane;
	}

    @FXML
    private void overdueCopiesAction() {
        try {
            Stage addResourceStage = new Stage();
            addResourceStage.setTitle("Overdue copies");
            Parent overdueCopiesScene = FXMLLoader.load(getClass().getResource("OverdueCopies.fxml"));
            addResourceStage.setScene(new Scene(overdueCopiesScene));
            addResourceStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@FXML
	private void addResourceAction() {
		try {
			Stage addResourceStage = new Stage();
			addResourceStage.setTitle("Add resource");
			Parent addResourceScene = FXMLLoader.load(getClass().getResource("AddResource.fxml"));
			addResourceStage.setScene(new Scene(addResourceScene));
			addResourceStage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @FXML
    private void addUserAction() {
        try {
            Stage addResourceStage = new Stage();
            addResourceStage.setTitle("Create New User");
            Parent addResourceScene = FXMLLoader.load(getClass().getResource("CreateUser.fxml"));
            addResourceStage.setScene(new Scene(addResourceScene));
            addResourceStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showLibraryAction() {
        try {
            FXMLLoader libraryLoader = new FXMLLoader(getClass().getResource("Library.fxml"));
            libraryLoader.setController(new LibraryController());
            Parent libraryPane = libraryLoader.load();
            mainPane.setCenter(libraryPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblName.setText(librarian.getFirstName() + " " + librarian.getLastName());
		lblBalance.setText(String.valueOf(librarian.getFineBalance()));
		profileImageView.setImage(librarian.getProfileImage());
	}
}
