package src;

import java.io.File;
import java.io.WriteAbortedException;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Window;
/**
 * Controller for the account edit window
 * @author Chris
 *
 */
public class NewAccountEditController {
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
    private ImageView default1Image;
    @FXML
    private ImageView default2Image;
    @FXML
    private ImageView default3Image;
    @FXML
    private ImageView default4Image;
    @FXML
    private ImageView default5Image;
    @FXML
    private ImageView default6Image;
    @FXML
    private ImageView custom1Button;
    @FXML
    private ImageView custom2Button;
    @FXML
    private Button editButton;
    @FXML
    private Button savechangesButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label infoLabel;

    //private Images[] defaultImages = new Button[6];
    private ImageView[] defaultImages = new ImageView[6];
    private User user;

    /**
     * Takes a users profile to edit
     * @param userToEdit
     */
    public NewAccountEditController(User userToEdit){
        this.user = userToEdit;
    }

    /**
     * Saves the changes to the database and then updates the user profile
     * @param a
     */
    @FXML
    private void handlesavechangesButtonAction(ActionEvent a){
        String newUsername = usernameField.getText();
        try {
            ResultSet set = Database.query("SELECT username FROM user_tbl WHERE username = '" + newUsername + "';");
            if (set.next()) {
                if (this.user.getUsername().equals(newUsername)) {
                    //if the username exists in the database and is this user
                    Database.edit("UPDATE user_tbl SET firstnames = '" + firstnamesField.getText() + "', lastname ='" + lastnameField.getText()
                            + "', addrline1 = '" + addressField.getText() + "', postcode = '" + postcodeField.getText() + "', phone = '" + phoneField.getText()
                            + "', imagelocation = '" + selectedPicLocation + "' WHERE username = '" + newUsername + "';");
                } else {
                    throw new WriteAbortedException("Can't use that username", null);

                }
            } else {
                //if the username has not been taken
                try {
                    Database.edit("UPDATE user_tbl SET username = '" + newUsername + "' WHERE username = '" + this.user.getUsername() + "';");
                    Database.edit("UPDATE user_tbl SET firstnames = '" + firstnamesField.getText() + "', lastname ='" + lastnameField.getText()
                            + "', addrline1 = '" + addressField.getText() + "', postcode = '" + postcodeField.getText() + "', phone = '" + phoneField.getText()
                            + "', imagelocation = '" + selectedPicLocation + "' WHERE username = '" + newUsername + "';");

                } catch (Exception e) {
                	e.printStackTrace();
                }
            }
            this.user.setUsername(newUsername);
            this.user.setFirstName(firstnamesField.getText());
            this.user.setLastName(lastnameField.getText());
            this.user.setAddressLine(addressField.getText());
            this.user.setPostcode(postcodeField.getText());
            this.user.setPhoneNumber(phoneField.getText());
            this.user.setProfileImage(new Image(selectedPicLocation));


            infoLabel.setText("Saved");
            infoLabel.setTextFill(Color.GREEN);

        } catch (WriteAbortedException e) {
            infoLabel.setText("That username is already taken");
            infoLabel.setTextFill(Color.RED);
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
                defaultImages[i].setImage(new Image("users/default1.png"));
            }
        }
        /*
        if (a.getSource().equals(custom1Button)) {
            selectedPicLocation = customImage1Location;
            updateImageBorders(custom1Button);
        }
        if (a.getSource().equals(custom2Button)) {
            selectedPicLocation = customImage2Location;
            updateImageBorders(custom2Button);
        }
        formatEditButton();
        */
    }

    /**
     * updates the selected Button(images) border
     * @param selectedButton the button that has been selected
     */
    private void updateImageBorders(Button selectedButton){
        /*
    	for (Button elem : defaultImages) {
            if (!elem.equals(selectedButton)) {
                elem.setBorder(null);
            }
        }
        if (!custom1Button.equals(selectedButton)) {
            custom1Button.setBorder(null);
        }
        if (!custom2Button.equals(selectedButton)) {
            custom2Button.setBorder(null);
        }
        selectedButton.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderStroke.MEDIUM)));
		*/
    }

    /**
     * Initializes the window
     */
    @FXML
    private void initialize() {

        selectedPicLocation = default1Location;
        customImage1Location = "users/" + this.user.getUsername() + "-1.png";
        customImage2Location = "users/" + this.user.getUsername() + "-2.png";
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
        default1Image.setImage(new Image("users/default1.png"));
        default2Image.setImage(new Image("users/default2.png"));
        default3Image.setImage(new Image("users/default3.png"));
        default4Image.setImage(new Image("users/default4.png"));
        default5Image.setImage(new Image("users/default5.png"));
        default6Image.setImage(new Image("users/default6.png"));
        /*
        default1Image.setPrefSize(75, 75);
        default1Image.setTextFill(Color.rgb(0, 0, 0, 0));
        default1Image.setBackground(new Background(new BackgroundImage(new Image("users/default1.png"), null, null, null, new BackgroundSize(75, 75, false, false, false, false))));
        ///////////////////////////////////////////////////
        default2Image.setTextFill(Color.rgb(0, 0, 0, 0));
        default2Image.setPrefSize(75, 75);
        default2Image.setBackground(new Background(new BackgroundImage(new Image("users/default2.png"), null, null, null, new BackgroundSize(75, 75, false, false, false, false))));
        ////////////////////////////////////////////////////
        default3Image.setTextFill(Color.rgb(0, 0, 0, 0));
        default3Image.setPrefSize(75, 75);
        default3Image.setBackground(new Background(new BackgroundImage(new Image("users/default3.png"), null, null, null, new BackgroundSize(75, 75, false, false, false, false))));
        ////////////////////////////////////////////////////
        default4Image.setTextFill(Color.rgb(0, 0, 0, 0));
        default4Image.setPrefSize(75, 75);
        default4Image.setBackground(new Background(new BackgroundImage(new Image("users/default4.png"), null, null, null, new BackgroundSize(75, 75, false, false, false, false))));
        ////////////////////////////////////////////////////
        default5Image.setTextFill(Color.rgb(0, 0, 0, 0));
        default5Image.setPrefSize(75, 75);
        default5Image.setBackground(new Background(new BackgroundImage(new Image("users/default5.png"), null, null, null, new BackgroundSize(75, 75, false, false, false, false))));
        ////////////////////////////////////////////////////
        default6Image.setTextFill(Color.rgb(0, 0, 0, 0));
        default6Image.setPrefSize(75, 75);
        default6Image.setBackground(new Background(new BackgroundImage(new Image("users/default6.png"), null, null, null, new BackgroundSize(75, 75, false, false, false, false))));
        ////////////////////////////////////////////////////
        custom1Button.setTextFill(Color.rgb(0, 0, 0, 0));
        custom1Button.setPrefSize(75, 75);
        custom2Button.setTextFill(Color.rgb(0, 0, 0, 0));
        custom2Button.setPrefSize(75, 75);
		*/
        defaultImages[0] = default1Image;
        defaultImages[1] = default2Image;
        defaultImages[2] = default3Image;
        defaultImages[3] = default4Image;
        defaultImages[4] = default5Image;
        defaultImages[5] = default6Image;
		
        /*
        try {
            custom1Button.setBackground(new Background(new BackgroundImage(new Image(customImage1Location), null, null, null, new BackgroundSize(75, 75, false, false, false, false))));


        } catch (Exception e) {
            custom1Button.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        }
        try {
            custom2Button.setBackground(new Background(new BackgroundImage(new Image(customImage1Location), null, null, null, null)));
        } catch (Exception e) {
            custom2Button.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        }
        for (int i = 0; i < defaultImageLocations.length; i++) {
            if (defaultImageLocations[i].equals(selectedPicLocation)) {
                updateImageBorders(defaultImages[i]);
            }
        }
        formatEditButton();
        */
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
        	e.printStackTrace();
        }
    }

    /**
     * Formats the edit Button
     */
    private void formatEditButton(){
        if ((!selectedPicLocation.equals(customImage1Location)) && (!selectedPicLocation.equals(customImage2Location))) {
            editButton.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        } else {
            editButton.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
        }
    }
}

