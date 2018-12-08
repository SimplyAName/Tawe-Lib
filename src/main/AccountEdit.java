package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccountEdit extends Application {
	private Stage stage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	this.stage = primaryStage;
        try {
        	//new AccountEditController(Login.getUser());
            //Parent root = FXMLLoader.load(getClass().getResource("AccountEdit.fxml"));
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountEdit.fxml"));
            loader.setController(new AccountEditController(new User()));//TODO: This needs a variable that exists when passing user, this could be a global or just passing the object
            Parent root = loader.load();
            
            primaryStage.setTitle("Tawe Lib");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
            
        }catch (Exception e1){

            e1.printStackTrace();

        }
    }
    
}
