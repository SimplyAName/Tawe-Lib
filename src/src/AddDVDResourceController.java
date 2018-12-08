package src;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

public class AddDVDResourceController extends AddResourceBase {

    @FXML
    private Stage addResourceStage;

    @FXML
    private TextField resourceTitle, resourceYear, dvdDirector, dvdRuntime, dvdLanguage, dvdSubLanguages;

    private BufferedImage uploadedImage;

    private boolean addSubLanguages(int dvdSubId, String subLanguageList) {

        String[] subLanguageArray = subLanguageList.split(",");

        try {
            for (String language : subLanguageArray) {
                Database.edit("INSERT INTO sublanguage_tbl VALUES (" + dvdSubId + ",'" + language + "');");
            }
            return true;
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return false;
    }

    /*
        This function is the action that add the book resource to the database.
        First it checks if it already exists in the database with the same name and resource type
        Next it add the resource information to the resource table assigning it an id and then to the book table with the same id linking them.
        If successful it clears the text boxes and shows a alert telling the user it was successful
     */
    @FXML
    private void addDvdAction() {

        Random randomId = new Random();

        int resourceId = randomId.nextInt(999999);

        int dvdSubId = randomId.nextInt(999999);

        String resourceType = "dvd";

        if (!checkResource(this.resourceTitle.getText(), resourceType)) {

            if (addSubLanguages(dvdSubId, dvdSubLanguages.getText())) {

                String imageLocation = "resourceImages/" + resourceTitle.getText() + "_" + resourceId + ".png";

                if (uploadImage(uploadedImage, imageLocation)) {

                    try {

                        Database.edit("INSERT INTO resource_tbl VALUES(" + resourceId + ",'" + resourceType + "','" + resourceTitle.getText() + "'," + Integer.parseInt(resourceYear.getText()) + ",'" + "testImageLocation" + "');");
                        Database.edit("INSERT INTO dvd_tbl VALUES (" + resourceId + ",'" + dvdDirector.getText() + "','" + Integer.parseInt(dvdRuntime.getText()) + "','" + dvdSubId + "','" + dvdLanguage.getText() + "');");

                        String alertTitle = resourceTitle.getText() + " added successfully!";
                        String alertHeader = resourceTitle.getText() + " was added successfully to the system and is now avalible to everyone.";
                        String alertMessage = resourceTitle.getText() + " is now available in the system. You can edit this DVD and add more copies by going back to home and clicking edit resource.";

                        resourceAddedSuccessfully(alertTitle, alertHeader, alertMessage);

                        resourceTitle.clear();
                        resourceYear.clear();
                        dvdDirector.clear();
                        dvdRuntime.clear();
                        dvdSubLanguages.clear();
                        dvdLanguage.clear();

                    } catch (Exception e1) {
                        //Error alert if it already exists.
                        String alertTitle = "Error adding resource";
                        String alertHeader = "Could not add resource to the system";
                        String alertMessage = "An error occurred adding the DVD to the database. Please try again. If this continues to occur please contact to creators of this software.";

                        resourceAddedError(alertTitle, alertHeader, alertMessage);

                        e1.printStackTrace();
                    }
                }
            }
        } else {
            //Error alert if it already exists.
            String alertTitle = "Error adding resource";
            String alertHeader = "Could not add resource to the system";
            String alertMessage = "A DVD with this name already exists. Please consider increasing the ammount of copies instead or is this is a newer edition of the same item add year or version of that item to the end of it's name.";

            resourceAddedError(alertTitle, alertHeader, alertMessage);
        }

    }

}
