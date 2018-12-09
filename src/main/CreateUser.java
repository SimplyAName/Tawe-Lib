package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateUser extends Application {
	private Stage stage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	this.stage = primaryStage;
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateUser.fxml"));
            loader.setController(new CreateUserController());
            Parent root = loader.load();
            
            primaryStage.setTitle("Tawe Lib");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
            
        }catch (Exception e1){

            e1.printStackTrace();

        }
    }
    
}