package src;

import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

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
        System.out.println("YAY");
        User tmpuser;
        String tmpUsername = "ohno";
        try{
            tmpUsername = usernameField.getText();
        }catch(Exception e){
            System.out.println("\nisthis\n");
        }
        try{
            ResultSet set = Database.query("SELECT * FROM user_tbl WHERE username = '"
                    + tmpUsername +"';");
            if(set.next()){
                ResultSet set2 = Database.query("SELECT * FROM librarian_tbl WHERE username = '"+ tmpUsername +"';");
                if (set2.next()){
                    tmpuser = new Librarian(set.getString("username"), set.getString("firstnames"), set.getString("lastname"),
                            set.getString("addrline1"),set.getString("postcode"), set.getString("phone"), (new Image("test.png")),
                            set.getInt("balance"), set2.getInt("staffno"), set2.getDate("empdate"));
                }
                tmpuser = new User(set.getString("username"), set.getString("firstnames"), set.getString("lastname"),
                        set.getString("addrline1"),set.getString("postcode"), set.getString("phone"), (new Image("test.png")),
                        set.getInt("balance"));
                infoLabel.setTextFill(Color.BLACK);
                infoLabel.setText("Welcome "+ user.getFirstName()+" "+ user.getLastName());

                user = tmpuser;


            }else{
                infoLabel.setTextFill(Color.RED);
                infoLabel.setText("Please enter a correct username");
            }
        }catch (Exception e){
            System.out.println(e);
            infoLabel.setTextFill(Color.RED);
            infoLabel.setText("Failed to Login in. Please try again");
        }
    }

    public static User getUser(){
        return user;
    }
}
