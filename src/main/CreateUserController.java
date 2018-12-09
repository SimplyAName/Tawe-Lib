package main;

import java.io.WriteAbortedException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.corba.se.spi.orbutil.fsm.Action;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class CreateUserController {
	//used for the default images
	private static String default1Location = "users/default1.png";
    private static String default2Location = "users/default2.png";
    private static String default3Location = "users/default3.png";
    private static String default4Location = "users/default4.png";
    private static String default5Location = "users/default5.png";
    private static String default6Location = "users/default6.png";
    private String[] defaultImageLocations = {default1Location, default2Location, default3Location, default4Location, default5Location, default6Location};
    private static String selectedPicLocation;
    private static String customImage1Location;
    private static String customImage2Location;
	
	@FXML 
	private TextField firstName;
	@FXML 
	private TextField lastName;
	@FXML 
	private TextField eAddress;
	@FXML 
	private TextField enterUsername;
	@FXML 
	private TextField ePostCode;
	@FXML 
	private TextField phoneNumber;
	@FXML 
	private AnchorPane CreateUserPane;
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
    private Button custom1Button;
    //@FXML
    //private Button custom2Button;
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
	private String fName;
	private String lName;
	private String address;
	private String username;
	private String postCode;
	private String phone;
	private int balance = 0;
	
	//private String nameQuery = "select username from user_tbl where username = " + enterUsername.getText() + ";";
	private String addUserQuery;
	private String imagelocation;
	
	
	@FXML 
	private void addUser(ActionEvent a) throws IllegalArgumentException, SQLException{
		fName = firstName.getText();
		lName = lastName.getText();
		address = eAddress.getText();
		postCode = ePostCode.getText();
		phone = phoneNumber.getText();
		username = enterUsername.getText();
		imagelocation = "";
		
		//checks if the username is already taken
		//if (Database.query(nameQuery) == null) {
		//	username = enterUsername.getText();
		//} else {
		//	;
		//}
		

		try{
		addUserQuery = (" insert into user_tbl (username, firstnames, lastname, addrline1, postcode, phone, imagelocation, balance)"
				+ " values (" + fName + " ," + lName + " ," + address + " ," + postCode + " ," + imagelocation + " ," + balance + " );" );
		}
		
		catch (Exception e1) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.showAndWait();
			
		}
		
	}
	
	
	
	
	//@FXML private void createAnImage() {
		
	//	new CustomDrawing().launchInNewWindow(selectedPicLocation, selectedPicLocation);
		
	//}
	
	@FXML
    private void updateSelectedImage(ActionEvent a){

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
        //if (a.getSource().equals(custom2Button)) {
        //    selectedPicLocation = customImage2Location;
        //    updateImageBorders(custom2Button);
        //}
        formatEditButton();
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
        if (!custom1Button.equals(selectedButton)) {
            custom1Button.setBorder(null);
        }
        //if (!custom2Button.equals(selectedButton)) {
        //    custom2Button.setBorder(null);
        //}
        selectedButton.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderStroke.MEDIUM)));

    }
    

    
    @FXML
    private void initialize() {

        selectedPicLocation = default1Location;
        customImage1Location = "users/" + enterUsername.getText() + "-1.png";
        customImage2Location = "users/" + enterUsername.getText() + "-2.png";



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
        //custom2Button.setTextFill(Color.rgb(0, 0, 0, 0));
        //custom2Button.setPrefSize(75, 75);

        defaultImages[0] = default1Button;
        defaultImages[1] = default2Button;
        defaultImages[2] = default3Button;
        defaultImages[3] = default4Button;
        defaultImages[4] = default5Button;
        defaultImages[5] = default6Button;
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
