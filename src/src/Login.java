package src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Login extends Application {
	private Stage stage;
	private static User user;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	this.stage = primaryStage;
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            loader.setController(new LoginController());
            Parent root = loader.load();
            
            primaryStage.setTitle("Tawe Lib");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
            
        }catch (Exception e1){

            e1.printStackTrace();

        }
    }
    public static User getUser(){
    	return LoginController.getUser();
    }
    
    
    
}
