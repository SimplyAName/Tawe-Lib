package src;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;


public class DefaultWindowController {

    @FXML
    private BorderPane mainPane;

    @FXML
    protected void homeButtonAction(){

        //When button is pressed the new fxml file is loaded and the center of the window is changed to it
        try {
            Parent newScene = FXMLLoader.load(getClass().getResource("LibrarianInterface.fxml"));
            mainPane.setCenter(newScene);
        }catch (Exception e2){
            e2.printStackTrace();
        }
    }

}
