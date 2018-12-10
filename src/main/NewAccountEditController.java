package src;

import java.io.File;
import java.io.FileInputStream;

import javafx.scene.input.MouseEvent; 


import java.io.WriteAbortedException;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
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
    private String selectedPicLocation;
    private String customImage1Location;
    private String customImage2Location;

    private final String emptyCustomImageLocation = "users/EmptyCustomImage.png";
    private final String default1Location = "users/default1.png";
    private final String default2Location = "users/default2.png";
    private final String default3Location = "users/default3.png";
    private final String default4Location = "users/default4.png";
    private final String default5Location = "users/default5.png";
    private final String default6Location = "users/default6.png";
    
    private final String[] allImageLocations = {default1Location, default2Location, default3Location, default4Location, default5Location, default6Location, customImage1Location, customImage1Location};


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
    private ImageView custom1Image;
    @FXML
    private ImageView custom2Image;
    @FXML
    private Button editButton;
    @FXML
    private Button savechangesButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label infoLabel;

    private ImageView[] defaultImages = new ImageView[6];
    private ImageView[] allImages = new ImageView[8];
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
    private void handleSaveChangesButtonAction(ActionEvent a){
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
            try{
            	this.user.setProfileImage(new Image(selectedPicLocation));
            }catch(Exception e){
            	
            }

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
    
    private void updateSelectedImage(ImageView selectedImage){    	
        for (int i = 0; i < allImages.length; i++) {
            if (selectedImage.equals(allImages[i])) {
            	selectedPicLocation = allImageLocations[i];            		
                try{
                	selectedImage.setImage(new Image(allImageLocations[i]));                	
                }catch(Exception e){
                	
                }                
                allImages[i].setEffect(new DropShadow(BlurType.ONE_PASS_BOX, Color.BLACK, 10, 10, 0, 0));
                allImages[i].toFront();                
            }else{        
            	allImages[i].setEffect(null);
            	allImages[i].toBack();
            }
            formatEditButton();
        }
    }
    
    /**
     * Initializes the window
     */
    @FXML
    private void initialize() {

        selectedPicLocation = default1Location;
        customImage1Location = "users/" + this.user.getUsername() + "-1.png";
        allImageLocations[6] = customImage1Location;
        customImage2Location = "users/" + this.user.getUsername() + "-2.png";
        allImageLocations[7] = customImage2Location;
        
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
        default1Image.setImage(new Image(default1Location));
        default2Image.setImage(new Image(default2Location));
        default3Image.setImage(new Image(default3Location));
        default4Image.setImage(new Image(default4Location));
        default5Image.setImage(new Image(default5Location));
        default6Image.setImage(new Image(default6Location));
        try{ 
        	custom1Image.setImage(new Image(customImage1Location));
        }catch(Exception e){
        	custom1Image.setImage(new Image(emptyCustomImageLocation));
        }
        try{
        	custom2Image.setImage(new Image(customImage2Location));
        }catch(Exception e){
        	custom2Image.setImage(new Image(emptyCustomImageLocation));
        }
        
        defaultImages[0] = default1Image;
        defaultImages[1] = default2Image;
        defaultImages[2] = default3Image;
        defaultImages[3] = default4Image;
        defaultImages[4] = default5Image;
        defaultImages[5] = default6Image;
        
        allImages[0] = default1Image;
        allImages[1] = default2Image;
        allImages[2] = default3Image;
        allImages[3] = default4Image;
        allImages[4] = default5Image;
        allImages[5] = default6Image;
        allImages[6] = custom1Image;
        allImages[7] = custom2Image;
        
        for (ImageView elem : allImages){
        	elem.setOnMouseClicked(e ->{
        		updateSelectedImage(elem);
        	});
        	
        	
        }
        formatEditButton();
        
    }
    
    /**
     * allows a user to edit the image, so long as it is not a preset image
     * @param a
     */
    
    @FXML
    private void handleEditButtonAction(ActionEvent a){
    	
        boolean isDefault = false;
        try {
            if (!selectedPicLocation.equals(customImage1Location) && !selectedPicLocation.equals(customImage2Location)){
            	isDefault = true;
            }
            if (isDefault != true) {
                new CustomDrawing().launchInNewWindow(selectedPicLocation, selectedPicLocation);
            }

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
    }
    
    private void formatEditButton(){
    	
        if ((!selectedPicLocation.equals(customImage1Location)) && (!selectedPicLocation.equals(customImage2Location))) {
            editButton.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
            
        } else {
            editButton.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
            
        }
    }
    @FXML
    private void handleCancelChangesButtonAction(){
    	initialize();
    }
    
}

