package main;

import java.io.WriteAbortedException;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Controller for the account edit window
 * @author Jake 
 * @author Chris
 * @author Michael
 *
 */
public class CreateUserController {
    private static String selectedPicLocation;
    private static String customImage1Location;
    private static String customImage2Location;

    private static String default1Location = "users/default1.png";
    private static String default2Location = "users/default2.png";
    private static String default3Location = "users/default3.png";
    private static String default4Location = "users/default4.png";
    private static String default5Location = "users/default5.png";
    private static String default6Location = "users/default6.png";
    private String[] defaultImageLocations = {default1Location, default2Location, default3Location, default4Location, default5Location, default6Location};


    @FXML
    private TextField usernameField;
    @FXML
    private TextField firstnamesField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField postcodeField;
    @FXML
    private TextField phoneField;
    @FXML
    private Button default1Button;
    @FXML
    private Button default2Button;
    @FXML
    private Button default3Button;
    @FXML
    private Button default4Button;
    @FXML
    private Button default5Button;
    @FXML
    private Button default6Button;
    @FXML
    private Button editButton;
    @FXML
    private Button savechangesButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label infoLabel;

    private Button[] defaultImages = new Button[6];
    private User user;

    

    /**
     * Saves the changes to the database and then updates the user profile
     * @param a
     */
    @FXML
    private void createUserAction(){
        String newUsername = usernameField.getText();
        try {
            ResultSet set = Database.query("SELECT username FROM user_tbl WHERE username = '" + usernameField.getText() + "';");
            if (set.next()) {
               
                    System.out.println("Username already exists!!!");
                
            } else{
                //if the username has not been taken
                try {
                	
                    Database.edit("INSERT INTO user_tbl (username, firstnames, lastname, addrline1, postcode, phone, imagelocation, balance) VALUES ('" + usernameField.getText() + "', '" +
                    firstnamesField.getText() + "', '" + lastnameField.getText() + "', '" + addressField.getText() + "', '" + postcodeField.getText() + "', '" + phoneField.getText() + "', '" + 
                    selectedPicLocation + "', " + 0 + " );");
                    
                    System.out.println("User added!");

                } catch (Exception e) {
                	e.printStackTrace();
                }
            }
        
        } catch (IllegalArgumentException e) {
            infoLabel.setText("ERROR - Please Check your changes are in the correct format");
            infoLabel.setTextFill(Color.RED);
        } catch (Exception e) {
            infoLabel.setText("ERROR - Could not connect to database");
            infoLabel.setTextFill(Color.BLACK);
        }
    }

    /**
     * Updates the selected image
     * @param a
     */
    @FXML
    private void updateSelectedImage(ActionEvent a){

        for (int i = 0; i < defaultImages.length; i++) {
            if (a.getSource().equals((Object) defaultImages[i])) {
                selectedPicLocation = defaultImageLocations[i];
                updateImageBorders(defaultImages[i]);
            }
        }


    }

    /**
     * updates the selected Button(images) border
     * @param selectedButton the button that has been selected
     */
    private void updateImageBorders(Button selectedButton){
        for (Button elem : defaultImages) {
            if (!elem.equals(selectedButton)) {
                elem.setBorder(null);
            }
        }

        selectedButton.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderStroke.MEDIUM)));

    }

    /**
     * Initializes the window
     */
    @FXML
    private void initialize() {

        selectedPicLocation = default1Location;

        try {
            usernameField.setText(this.user.getUsername());
            firstnamesField.setText(this.user.getFirstName());
            lastnameField.setText(this.user.getLastName());
            addressField.setText(this.user.getAddressLine());
            postcodeField.setText(this.user.getPostcode());
            phoneField.setText(this.user.getPhoneNumber());
            //selectedPicLocation = this.user.getProfileImage().impl_getUrl();

        } catch (Exception e) {
            usernameField.setText("No information Avaliable");
        }


        default1Button.setPrefSize(75, 75);
        default1Button.setTextFill(Color.rgb(0, 0, 0, 0));
        default1Button.setBackground(new Background(new BackgroundImage(new Image("main/users/default1.png"), null, null, null, new BackgroundSize(75, 75, false, false, false, false))));
        ///////////////////////////////////////////////////
        default2Button.setTextFill(Color.rgb(0, 0, 0, 0));
        default2Button.setPrefSize(75, 75);
        default2Button.setBackground(new Background(new BackgroundImage(new Image("main/users/default2.png"), null, null, null, new BackgroundSize(75, 75, false, false, false, false))));
        ////////////////////////////////////////////////////
        default3Button.setTextFill(Color.rgb(0, 0, 0, 0));
        default3Button.setPrefSize(75, 75);
        default3Button.setBackground(new Background(new BackgroundImage(new Image("main/users/default3.png"), null, null, null, new BackgroundSize(75, 75, false, false, false, false))));
        ////////////////////////////////////////////////////
        default4Button.setTextFill(Color.rgb(0, 0, 0, 0));
        default4Button.setPrefSize(75, 75);
        default4Button.setBackground(new Background(new BackgroundImage(new Image("main/users/default4.png"), null, null, null, new BackgroundSize(75, 75, false, false, false, false))));
        ////////////////////////////////////////////////////
        default5Button.setTextFill(Color.rgb(0, 0, 0, 0));
        default5Button.setPrefSize(75, 75);
        default5Button.setBackground(new Background(new BackgroundImage(new Image("main/users/default5.png"), null, null, null, new BackgroundSize(75, 75, false, false, false, false))));
        ////////////////////////////////////////////////////
        default6Button.setTextFill(Color.rgb(0, 0, 0, 0));
        default6Button.setPrefSize(75, 75);
        default6Button.setBackground(new Background(new BackgroundImage(new Image("main/users/default6.png"), null, null, null, new BackgroundSize(75, 75, false, false, false, false))));
        ////////////////////////////////////////////////////


        defaultImages[0] = default1Button;
        defaultImages[1] = default2Button;
        defaultImages[2] = default3Button;
        defaultImages[3] = default4Button;
        defaultImages[4] = default5Button;
        defaultImages[5] = default6Button;


        for (int i = 0; i < defaultImageLocations.length; i++) {
            if (defaultImageLocations[i].equals(selectedPicLocation)) {
                updateImageBorders(defaultImages[i]);
            }
        }
       
    }

    /**
     * allows a user to edit the image, so long as it is not a preset image
     * @param a
     */
    @FXML
    private void handleeditButtonAction(ActionEvent a){
        boolean isDefault = false;
        try {
            for (String elem : defaultImageLocations) {
                if (elem.equals(selectedPicLocation)) {
                    isDefault = true;
                }
            }
            if (isDefault != true) {
                new CustomDrawing().launchInNewWindow(selectedPicLocation, selectedPicLocation);
            }

        } catch (Exception e) {

        }
    }

    
    @FXML
    private void cancelAction() {
    	Stage createWindowUser = (Stage) cancelButton.getScene().getWindow();
    	createWindowUser.close();
    }
}
