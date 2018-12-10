package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AddResourceBase implements Initializable {

    @FXML
    protected ImageView uploadImageView;

    protected BufferedImage uploadedImage;

    /**
     * @param resourceName The name of the resource to be checked
     * @param resourceType The type of resource to be checked
     * @return Returns true if a duplicate resource is found, false if not.
     */
    protected boolean checkResource(String resourceName, String resourceType) {

        try {
            ResultSet checkedResource = Database.query("SELECT type, title FROM resource_tbl WHERE type = '" + resourceType + "' AND title = '" + resourceName + "';");
            return checkedResource.next();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return true;
    }

    protected boolean uploadImage(String imageLocation) {

        try {
            ImageIO.write(uploadedImage, "png", new File(imageLocation));
            return true;
        } catch (IOException e) {
            System.out.println("Write error for uploading image: " + e.getMessage());
            return false;
        }

    }

    protected void resourceAddedSuccessfully(String alertTitle, String alertHeader, String alertMessage) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(alertTitle);
        alert.setHeaderText(alertHeader);
        alert.setContentText(alertMessage);

        alert.showAndWait();

    }

    protected void resourceAddedError(String alertTitle, String alertHeader, String alertMessage) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(alertTitle);
        alert.setHeaderText(alertHeader);
        alert.setContentText(alertMessage);

        alert.showAndWait();

    }

    @FXML
    private void uploadImageAction() {

        Stage addResourceStage = new Stage();

        try {
            FileChooser uploadResourceImage = new FileChooser();
            uploadResourceImage.setTitle("Upload image");
            uploadResourceImage.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpeg", "*.jpg"));
            File selectedFile = uploadResourceImage.showOpenDialog(addResourceStage);
            if (selectedFile != null) {
                uploadedImage = ImageIO.read(selectedFile);
                uploadImageView.setImage(new Image(selectedFile.toURI().toString()));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            File defaultImage = new File("src/main/resourceImages/defaultImage.png");
            uploadedImage = ImageIO.read(defaultImage);
            uploadImageView.setImage(new Image(defaultImage.toURI().toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
