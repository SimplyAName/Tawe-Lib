package main;

import java.io.WriteAbortedException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

/**
 * Controller for the account edit window.
 * @author Chris
 *
 */
public class AccountEditController {
    private String selectedPicLocation;
    private String customImage1Location;
    private String customImage2Location;

    //This is a list of the default images avalible to the user
    private String default1Location = "main/users/default1.png";
    private String default2Location = "main/users/default2.png";
    private String default3Location = "main/users/default3.png";
    private String default4Location = "main/users/default4.png";
    private String default5Location = "main/users/default5.png";
    private String default6Location = "main/users/default6.png";
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
    private Button custom1Button;
    @FXML
    private Button custom2Button;
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
     * Takes a users profile to edit
     * @param userToEdit This users account that will be edited
     */
    public AccountEditController(User userToEdit) {
        this.user = userToEdit;
    }

    /**
     * Saves the changes to the database and then updates the user profile.
     */
    @FXML
    private void handlesavechangesButtonAction() {
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
            e.printStackTrace();
        } catch (Exception e) {

            infoLabel.setText("ERROR - Could not connect to database");
            infoLabel.setTextFill(Color.BLACK);
        }
    }

    /**
     * Updates the selected image.
     * @param a
     */
    @FXML
    private void updateSelectedImage(ActionEvent a) {

        for (int i = 0; i < defaultImages.length; i++) {
            if (a.getSource().equals((Object) defaultImages[i])) {
                selectedPicLocation = defaultImageLocations[i];
                updateImageBorders(defaultImages[i]);
            }
        }
        if (a.getSource().equals(custom1Button)) {
            selectedPicLocation = customImage1Location;
            updateImageBorders(custom1Button);
        }
        if (a.getSource().equals(custom2Button)) {
            selectedPicLocation = customImage2Location;
            updateImageBorders(custom2Button);
        }
        formatEditButton();
    }

    /**
     * updates the selected Button(images) border
     * @param selectedButton the button that has been selected
     */
    private void updateImageBorders(Button selectedButton) {
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

    }

    /**
     * Initializes the window.
     */
    /*@FXML
    private void initialize() {
    }*/

    /**
     * Allows a user to edit the image, so long as it is not a preset image.
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
                new CustomDrawing(selectedPicLocation, selectedPicLocation);


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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        selectedPicLocation = default1Location;
        customImage1Location = "main/users/" + this.user.getUsername() + "-1.png";
        customImage2Location = "main/users/" + this.user.getUsername() + "-2.png";

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
        custom1Button.setTextFill(Color.rgb(0, 0, 0, 0));
        custom1Button.setPrefSize(75, 75);
        custom2Button.setTextFill(Color.rgb(0, 0, 0, 0));
        custom2Button.setPrefSize(75, 75);

        defaultImages[0] = default1Button;
        defaultImages[1] = default2Button;
        defaultImages[2] = default3Button;
        defaultImages[3] = default4Button;
        defaultImages[4] = default5Button;
        defaultImages[5] = default6Button;

        try {

            Image testImage = new Image(customImage1Location);

            custom1Button.setBackground(new Background(new BackgroundImage(new Image(customImage1Location), null, null, null, new BackgroundSize(75, 75, false, false, false, false))));
        } catch (Exception e) {
            custom1Button.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        }

        try {
            custom2Button.setBackground(new Background(new BackgroundImage(new Image(customImage2Location), null, null, null, new BackgroundSize(75, 75, false, false, false, false))));
        } catch (Exception e) {
            custom2Button.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        }

        for (int i = 0; i < defaultImageLocations.length; i++) {
            if (defaultImageLocations[i].equals(selectedPicLocation)) {
                updateImageBorders(defaultImages[i]);
            }
        }
        formatEditButton();

                CustomDrawing customDrawing = new CustomDrawing();

                customDrawing.start(customDrawingStage);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Formats the edit Button.
     */
    private void formatEditButton() {
        if ((!selectedPicLocation.equals(customImage1Location)) && (!selectedPicLocation.equals(customImage2Location))) {
            editButton.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        } else {
            editButton.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
        }
    }
}