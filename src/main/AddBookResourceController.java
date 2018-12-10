package main;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.Random;
/**
 * Adds a book to the database
 * @author Michael
 *
 */
public class AddBookResourceController extends AddResourceBase {

    @FXML
    private TextField resourceTitle, resourceYear, bookAuthor, bookPublisher, bookGenre, bookISBN, bookLanguage;

    @FXML
    private ImageView bookImageView;

    /**
    *    This function is the action that add the book resource to the database.
    *    First it checks if it already exists in the database with the same name and resource type
    *    Next it adds the resource information to the resource table assigning it an id
    *    then to the book table with the same id linking them.
    *    If successful it clears the text boxes and shows a alert.
    */
    @FXML
    private void addBookAction() {

        String resourceType = "book";

        Random randomId = new Random();

        int resourceId = randomId.nextInt(999999);

        if (!checkResource(this.resourceTitle.getText(), resourceType)) {

            String imageName = resourceTitle.getText() + "_" + resourceId + ".png";

            String imageLocation = "src/main/resourceImages/" + imageName;

            if (uploadImage(imageLocation)) {

                imageLocation = "main/resourceImages/" + imageName;

                try {

                    Database.edit("INSERT INTO resource_tbl VALUES" +
                            "(" + resourceId + ",'" + resourceType + "','" + resourceTitle.getText() + "'," + Integer.parseInt(resourceYear.getText()) + ",'" + imageLocation + "');");
                    Database.edit("INSERT INTO book_tbl VALUES" +
                            "(" + resourceId + ",'" + bookAuthor.getText() + "','" + bookPublisher.getText() + "','" + bookGenre.getText() + "'," + Integer.parseInt(bookISBN.getText()) + ",'" + bookLanguage.getText() + "');");

                    String alertTitle = resourceTitle.getText() + " added successfully!";
                    String alertHeader = resourceTitle.getText() + " was added successfully"
                    		+ " to the system and is now available to everyone.";
                    String alertMessage = resourceTitle.getText() + " is now available in the system. "
                    		+ "You can edit this book and add more copies by going back to home and clicking edit resource.";
                    resourceAddedSuccessfully(alertTitle, alertHeader, alertMessage);

                    resourceTitle.clear();
                    resourceYear.clear();
                    bookAuthor.clear();
                    bookPublisher.clear();
                    bookGenre.clear();
                    bookISBN.clear();
                    bookLanguage.clear();

                } catch (Exception e1) {
                    //Error alert if it already exists.

                    String alertTitle = "Error adding resource";
                    String alertHeader = "Could not add resource to the system";
                    String alertMessage = "An error occurred adding this book to the database."
                    		+ " Please try again. If this continues to occur please contact to creators of this software.";
                    resourceAddedError(alertTitle, alertHeader, alertMessage);

                    e1.printStackTrace();
                }

            }
        } else {
            //Error alert if it already exists.
            String alertTitle = "Error adding resource";
            String alertHeader = "Could not add resource to the system";
            String alertMessage = "A book with this name already exists. Please consider increasing the ammount of copies"
            		+ " instead or is this is a newer edition of the same item add year or version of that item to"
            		+ " the end of it's name.";
            resourceAddedError(alertTitle, alertHeader, alertMessage);
        }

    }

}
