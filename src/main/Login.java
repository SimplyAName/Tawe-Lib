package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Chris
 * Sets the stage of the login.
 * Beginning of the system.
 */
public class Login extends Application {

    /**
     * Lanches the login stage.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

            primaryStage.setTitle("Tawe Lib Login");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
