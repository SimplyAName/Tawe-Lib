package src;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddResourceController {

    @FXML
    private ChoiceBox<String> resourceSelection;

    @FXML
    private BorderPane resourceCreationMainPane;

    /*
     * Changes the resource type when the choice box is changed and sets the pane the the correct one that suits the resource selected.
     */

    @FXML
    protected void resourceSelectedAction() {

        Parent newScene;

        String resourceType = resourceSelection.getValue();

        try{
            if(resourceType.equals("Book")){
                newScene = FXMLLoader.load(getClass().getResource("AddBookResource.fxml"));
            }else if(resourceType.equals("Laptop")){
                newScene = FXMLLoader.load(getClass().getResource("AddLaptopResource.fxml"));
            }else if(resourceType.equals("DVD")){
                newScene = FXMLLoader.load(getClass().getResource("AddDVDResource.fxml"));
            }else{
                System.out.println("Error. Resource not recognised!");
                //This pane is in case of an error so it will just show an empty pane.
                newScene = new Pane();
            }

            resourceCreationMainPane.setCenter(newScene);
        }catch (Exception e1){
            e1.printStackTrace();
        }
    }
}
