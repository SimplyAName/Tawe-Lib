package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DefaultWindowController implements Initializable {

    private User user;
    private Librarian librarian;

    @FXML
    private BorderPane mainPane;

    public DefaultWindowController(User user) {
        this.user = user;
    }

    public DefaultWindowController(Librarian librarian) {
        this.librarian = librarian;
    }

    @FXML
    private void homeButtonAction() {

        //When button is pressed the new fxml file is loaded and the center of the window is changed to it
        if (user == null) {
            loadLibrarianInterface();
        } else {
            loadUserInterface();
        }
    }

    @FXML
    private void editAccountAction() {

        User editUser;

        if (user == null) {
            editUser = librarian;
        } else {
            editUser = user;
        }

        try {
            Stage editAccountStage = new Stage();
            FXMLLoader editAccountLoader = new FXMLLoader(getClass().getResource("AccountEdit.fxml"));
            editAccountLoader.setController(new AccountEditController(editUser));
            Parent editAccountPane = editAccountLoader.load();
            editAccountStage.setScene(new Scene(editAccountPane));
            editAccountStage.setTitle("Edit Account");

            editAccountStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void logoutAction() {

        try {
            Stage loginStage = new Stage();
            Parent loginPane = FXMLLoader.load(getClass().getResource("Login.fxml"));
            loginStage.setScene(new Scene(loginPane));
            loginStage.setTitle("Tawe Lib Login");

            loginStage.show();

            Stage mainStage = (Stage) mainPane.getScene().getWindow();

            mainStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadUserInterface() {

        try {
            FXMLLoader userInterfaceLoader = new FXMLLoader(getClass().getResource("UserInterface.fxml"));
            userInterfaceLoader.setController(new UserInterfaceController(user));
            Parent userPane = userInterfaceLoader.load();
            mainPane.setCenter(userPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadLibrarianInterface() {

        try {
            FXMLLoader librarianInterfaceLoader = new FXMLLoader(getClass().getResource("LibrarianInterface.fxml"));
            librarianInterfaceLoader.setController(new LibrarianInterfaceController(librarian));
            Parent librarianPane = librarianInterfaceLoader.load();
            mainPane.setCenter(librarianPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (user == null) {
            loadLibrarianInterface();
        } else {
            loadUserInterface();
        }

    }
}
