package src;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddResourceController implements Initializable {

    @FXML
    private ChoiceBox<String> resourceSelection;

    @FXML
    private BorderPane resourceCreationMainPane;

    private int resourceId;
    private String resourceName;
    private String resourceType;
    private int resourceYear;
    private String resourceImageLoc;

    /*
     * Changes the resource type when the choice box is changed and sets the pane the the correct one that suits the resource selected.
     */

    protected void resourceSelected(){

        Parent newScene;

        resourceType = resourceSelection.getValue();

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

    /**
     * @param resourceName The name of the resource.
     * @return ture if a duplicate resource is found, false if not.
     */
    private boolean checkResource(String resourceName) {

        try {
            ResultSet checkedResource = Database.query("SELECT type, title FROM resource_tbl WHERE type = " + resourceType + " AND " + resourceName + ";");

            if (checkedResource.next()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return true;

    }

    /**
     *
     */
    protected void addResourceAction(){

        if (checkResource(resourceName)) {
            if (resourceType.equals("Book")) {
                addBook();
            } else if (resourceType.equals("Laptop")) {
                addLaptop();
            } else if (resourceType.equals("DVD")) {
                addDVD();
            } else {
                System.out.println("Error. Resource not recognised!");
            }
        }
    }

    private boolean addBook() {

        try{
            Database.edit("INSERT ;");

            return true;
        }catch(Exception e1){
            e1.printStackTrace();

            return false;
        }

    }

    private boolean addDVD() {

        try{
            Database.edit("INSERT ;");

            return true;
        }catch(Exception e1){
            e1.printStackTrace();

            return false;
        }

    }

    private boolean addLaptop() {

        try{
            Database.edit("INSERT ;");

            return true;
        }catch(Exception e1){
            e1.printStackTrace();

            return false;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceSelection.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resourceType = resourceSelection.getValue();
                resourceSelected();
            }
        });

        try {
            Parent newScene = FXMLLoader.load(getClass().getResource("AddBookResource.fxml"));

            resourceCreationMainPane.setCenter(newScene);
        }catch (Exception e2){
            e2.printStackTrace();
        }
    }
}
