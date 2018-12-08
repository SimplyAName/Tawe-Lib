package main;

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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController {

    private static User user = null;

    @FXML
    private TextField usernameField;

    @FXML
    private Button loginButton;

    @FXML
    private Label infoLabel;

    @FXML
    private void handleLoginButtonAction(ActionEvent a){

        String tmpUsername = "ohno";

        try{
            tmpUsername = usernameField.getText();
        }catch(Exception e){
            System.out.println("\nisthis\n");
        }
        try{
            ResultSet set = Database.query("SELECT * FROM user_tbl WHERE username = '" + tmpUsername + "';");

            if(set.next()){
                ResultSet set2 = Database.query("SELECT * FROM librarian_tbl WHERE username = '"+ tmpUsername +"';");

                Stage loginStage = (Stage) loginButton.getScene().getWindow();

                FXMLLoader userInterfaceLoader = new FXMLLoader(getClass().getResource("DefaultWindow.fxml"));

                if (set2.next()){
                    Librarian user = new Librarian(set.getString("username"), set.getString("firstnames"), set.getString("lastname"), set.getString("addrline1"), set.getString("postcode"), set.getString("phone"), new Image(set.getString("imagelocation")), set.getInt("balance"), set2.getInt("staffno"), set2.getDate("empdate"));
                    userInterfaceLoader.setController(new DefaultWindowController(user));
                } else {
                    User user = new User(set.getString("username"), set.getString("firstnames"), set.getString("lastname"), set.getString("addrline1"), set.getString("postcode"), set.getString("phone"), new Image(set.getString("imagelocation")), set.getInt("balance"));
                    userInterfaceLoader.setController(new DefaultWindowController(user));
                }

                Stage userStage = new Stage();


                Parent userInterface = userInterfaceLoader.load();
                userStage.setScene(new Scene(userInterface));
                userStage.setTitle("Tawe-Lib");

                userStage.show();

                loginStage.close();

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

    public static User getUser(){
        return user;
    }
}
