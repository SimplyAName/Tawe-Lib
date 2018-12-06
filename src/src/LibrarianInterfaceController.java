package src;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LibrarianInterfaceController {

    @FXML
    private BorderPane mainPane;

    @FXML
    protected void addResourceAction(){

        try {
            Stage addResourceStage = new Stage();

            BorderPane addResourceDefaultWindow = FXMLLoader.load(getClass().getResource("AddResource.fxml"));

            addResourceStage.setScene(new Scene(addResourceDefaultWindow));
            addResourceStage.setTitle("Add new resource");
            addResourceStage.show();

        }catch(Exception e1){
            e1.printStackTrace();
        }
    }
}