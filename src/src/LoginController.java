package src;

import java.io.File;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoginController {
    //private static User user = null;
    @FXML
    private TextField usernameField;
    @FXML
    private Button loginButton;

    @FXML
    private Label infoLabel;
    
    @FXML
    private ImageView backgroundImage;

    @FXML
    private void handleLoginButtonAction(ActionEvent a){
    	
    	
        User tmpuser = null;
        String tmpUsername = "ohno";
        try{
            tmpUsername = usernameField.getText();
        }catch(Exception e){
            System.out.println("\nisthis\n");
        }
        try{
        	System.out.println("1");
            ResultSet set = Database.query("SELECT * FROM user_tbl WHERE username = '"+ tmpUsername +"';");
            if(set.next()){
            	System.out.println("2");
                ResultSet set2 = Database.query("SELECT * FROM librarian_tbl WHERE username = '"+ tmpUsername +"';");
                if (set2.next()){
                	System.out.println("3");
                    tmpuser = new Librarian(set.getString("username"), set.getString("firstnames"), set.getString("lastname"),
                            set.getString("addrline1"),set.getString("postcode"), set.getString("phone"), new Image("#test.png"),
                            set.getInt("balance"), set2.getInt("staffno"), set2.getDate("empdate"));
                    System.out.println("4");
                }else{
                	System.out.println("5");
                	try{
                		
                		tmpuser = new User(set.getString("username"), set.getString("firstnames"), set.getString("lastname"),
                				set.getString("addrline1"),set.getString("postcode"), set.getString("phone"), new Image("#test.png"),
                				set.getInt("balance"));
                		
                	
                	}catch(Exception e){
                		System.out.println("5.5");
                	}
                }
                infoLabel.setTextFill(Color.BLACK);
                infoLabel.setText("Welcome "+ tmpuser.getFirstName()+" "+ tmpuser.getLastName());
                System.out.println("6");
                
                //FOR TESTING ONLY PLS REMOVE
                
                Stage newStage = new Stage();
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("newAccountEdit.fxml"));
                
                loader.setController(new NewAccountEditController(tmpuser));//TODO: This needs a variable that exists when passing user, this could be a global or just passing the object
                
                Pane root = loader.load();
                
                newStage.setTitle("Tawe Lib");
                newStage.setScene(new Scene(root, 600, 400));
                newStage.show();
     			
                
                
                ///

            }else{
                infoLabel.setTextFill(Color.RED);
                infoLabel.setText("Please enter a correct username");
            }
        }catch (Exception e){
            e.printStackTrace();
            infoLabel.setTextFill(Color.RED);
            infoLabel.setText("Failed to Login in. Please try again");
        }
        
    }
}
