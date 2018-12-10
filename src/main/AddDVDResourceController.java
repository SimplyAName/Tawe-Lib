package main;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Add's a DVD to the database.
 * @author Michael
 *
 */
public class AddDVDResourceController extends AddResourceBase {

    @FXML
    private Stage addResourceStage;
    @FXML
    private TextField resourceTitle;
    @FXML
    private TextField resourceYear;
    @FXML
    private TextField dvdDirector;
    @FXML
    private TextField dvdRuntime;
    @FXML
    private TextField dvdLanguage; 
    @FXML
    private TextField dvdSubLanguages;

    private BufferedImage uploadedImage;

    /**
     * Adds the subtitle languages to the sub language table.
     * @param dvdSubId the ID for the subtitles.
     * @param subLanguageList used for setting the array to a string split with commas.
     * @return
     */
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

    /**
    *    This function is the action that add the book resource to the database.
    *    First it checks if it already exists in the database with the same name and resource type.
    *    Next it adds the resource information to the resource table assigning it an id
    *    then to the book table with the same id linking them.
    *    If successful it clears the text boxes and shows a alert.
    */
    @FXML
    private void addDvdAction() {

        Random randomId = new Random();
        int resourceId = randomId.nextInt(999999);
        int dvdSubId = randomId.nextInt(999999);
        String resourceType = "dvd";

        if (!checkResource(this.resourceTitle.getText(), resourceType)) {

            if (addSubLanguages(dvdSubId, dvdSubLanguages.getText())) {

                String imageName = resourceTitle.getText() + "_" + resourceId + ".png";

                String imageLocation = "src/main/resourceImages/" + imageName;

                if (uploadImage(imageLocation)) {

                    imageLocation = "main/resourceImages/" + imageName;

                    try {

                        Database.edit("INSERT INTO resource_tbl VALUES(" + resourceId 
                        		+ ",'" + resourceType + "','" + resourceTitle.getText() 
                        		+ "'," + Integer.parseInt(resourceYear.getText()) 
                        		+ ",'" + imageLocation + "');");
                        Database.edit("INSERT INTO dvd_tbl VALUES (" + resourceId 
                        		+ ",'" + dvdDirector.getText() + "','" + 
                        		Integer.parseInt(dvdRuntime.getText()) + "','" 
                        		+ dvdSubId + "','" + dvdLanguage.getText() + "');");

                        String alertTitle = resourceTitle.getText() + " added successfully!";
                        String alertHeader = resourceTitle.getText() + " was added successfully"
                        		+ " to the system and is now avalible to everyone.";
                        String alertMessage = resourceTitle.getText() + " is now available in the system."
                        		+ " You can edit this DVD and add more copies "
                        		+ "by going back to home and clicking edit resource.";
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
                        String alertMessage = "An error occurred adding the DVD to the database."
                        		+ " Please try again. If this continues to occur please contact to"
                        		+ " creators of this software.";

                        resourceAddedError(alertTitle, alertHeader, alertMessage);
                        e1.printStackTrace();
                    }
                }
            }
        } else {
            //Error alert if it already exists.
            String alertTitle = "Error adding resource";
            String alertHeader = "Could not add resource to the system";
            String alertMessage = "A DVD with this name already exists. "
            		+ "Please consider increasing the ammount of copies "
            		+ "instead or is this is a"
            		+ " newer edition of the same item add year or version of "
            		+ "that item to the end of it's name.";

            resourceAddedError(alertTitle, alertHeader, alertMessage);
        }

    }

}

