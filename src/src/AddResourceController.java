package src;

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
                //This pane is incase of an error so it will just show an empty pane.
                newScene = new Pane();
            }

            resourceCreationMainPane.setCenter(newScene);
        }catch (Exception e1){
            e1.printStackTrace();
        }
    }

    protected void addResourceAction(){

        if(resourceType.equals("Book")){
            addBook();
        }else if(resourceType.equals("Laptop")){
            addLaptop();
        }else if(resourceType.equals("DVD")){
            addDVD();
        }else{
            System.out.println("Error. Resource not recognised!");
        }
    }

    private void addBook(){

        try{
            Database.edit("INSERT ;");
        }catch(Exception e1){
            e1.printStackTrace();
        }

    }

    private void addDVD(){

        try{
            Database.edit("INSERT ;");
        }catch(Exception e1){
            e1.printStackTrace();
        }

    }

    private void addLaptop(){

        try{
            Database.edit("INSERT ;");
        }catch(Exception e1){
            e1.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Parent newScene = FXMLLoader.load(getClass().getResource("AddBookResource.fxml"));

            resourceCreationMainPane.setCenter(newScene);
        }catch (Exception e2){
            e2.printStackTrace();
        }
    }
}
