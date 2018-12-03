package src;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DefaultWindowController {

    @FXML
    private BorderPane mainPane;

    @FXML
    protected void homeButtonAction(){

        try {
            Parent newScene = FXMLLoader.load(getClass().getResource("LibrarianInterface.fxml"));
            mainPane.setCenter(newScene);
        }catch (Exception e2){
            e2.printStackTrace();
        }
    }

}
