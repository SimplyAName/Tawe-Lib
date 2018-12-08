package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

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

        AnchorPane newScene;

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
                newScene = new AnchorPane();
            }

            resourceCreationMainPane.setCenter(newScene);
        }catch (Exception e1){
            e1.printStackTrace();
        }
    }
}
