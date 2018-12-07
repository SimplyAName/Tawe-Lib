package src;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.util.Random;

public class AddLaptopResourceController {


    @FXML
    private TextField resourceTitle, resourceYear, laptopManufacturer, laptopModal, laptopOS;

    private String resourceType = "laptop";
    private String resourceImageLoc;

    /**
     * @param resourceName The name of the resource to be checked
     * @param resourceType The type of resource to be checked
     * @return Returns true if a duplicate resource is found, false if not.
     */
    private boolean checkResource(String resourceName, String resourceType) {

        try {
            ResultSet checkedResource = Database.query("SELECT type, title FROM resource_tbl WHERE type = '" + resourceType + "' AND title = '" + resourceName + "';");
            return checkedResource.next();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return true;
    }

    /*
        This function is the action that add the book resource to the database.
        First it checks if it already exists in the database with the same name and resource type
        Next it add the resource information to the resource table assigning it an id and then to the book table with the same id linking them.
        If successful it clears the text boxes and shows a alert telling the user it was successful
     */
    @FXML
    private void addLaptopAction() {

        Random randomId = new Random();

        int resourceId = randomId.nextInt(999999);

        if (!checkResource(this.resourceTitle.getText(), this.resourceType)) {
            try {

                Database.edit("INSERT INTO resource_tbl VALUES(" + resourceId + ",'" + resourceType + "','" + resourceTitle.getText() + "'," + Integer.parseInt(resourceYear.getText()) + ",'" + "testImageLocation" + "');");
                Database.edit("INSERT INTO laptop_tbl VALUES (" + resourceId + ",'" + laptopManufacturer.getText() + "','" + laptopModal.getText() + "','" + laptopOS.getText() + "');");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(resourceTitle.getText() + " added successfully!");
                alert.setHeaderText(resourceTitle.getText() + " was added successfully to the system and is now avalible to everyone.");
                alert.setContentText(resourceTitle.getText() + " is now available in the system. If there are any error please edit it or delete it using the respective methods.");

                alert.showAndWait();

                resourceTitle.clear();
                resourceYear.clear();
                laptopManufacturer.clear();
                laptopModal.clear();
                laptopOS.clear();

            } catch (Exception e1) {
                //Error alert if it already exists.
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error adding resource");
                alert.setHeaderText("Could not add resource to the system");
                alert.setContentText("An error occurred adding the data to the database. Please try again. If this continues to occur please contact to creators of this software.");

                alert.showAndWait();

                e1.printStackTrace();
            }
        } else {
            //Error alert if it already exists.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error adding resource");
            alert.setHeaderText("Could not add resource to the system");
            alert.setContentText("A resource a this type already exsists with this name. Please consider increasing the ammount of copies instead or is this is a newer edition of the same item add year or version of that item to the end of it's name.");

            alert.showAndWait();
        }

    }

}
