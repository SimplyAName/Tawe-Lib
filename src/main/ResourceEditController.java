package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

public class ResourceEditController {

    @FXML
    private Stage EditResourceStage;

	@FXML
    private TextField bookID, bookTitle, bookYear, bookAuthor, bookPublisher, bookGenre, bookISBN, bookLanguage;

    @FXML
    private void editBookAction() throws IllegalArgumentException, SQLException {
    		String imageLocation = "resourceImages/" + bookTitle.getText() + "_" + bookID.getText() + ".png";
    		ResourceEdit.editBook(bookID.getText(), bookTitle.getText(), bookYear.getText(), imageLocation, bookAuthor.getText(), bookPublisher.getText(), bookGenre.getText(), bookISBN.getText(), bookLanguage.getText());
    			
    		}
    
    @FXML
    private TextField dvdID, dvdTitle, dvdYear, dvdDirector, dvdRuntime, dvdLanguage, dvdSubtitles;
    
    @FXML
    private void editDVDAction() throws IllegalArgumentException, SQLException {
    	String imageLocation = "resourceImages/" + dvdTitle.getText() + "_" + dvdID.getText() + ".png";
    	ResourceEdit.editDVD(dvdID.getText(), dvdTitle.getText(), dvdYear.getText(), imageLocation, dvdDirector.getText(), dvdRuntime.getText(), dvdLanguage.getText(), dvdSubtitles.getText() );
    }
    
    @FXML
    private TextField laptopID, laptopTitle, laptopYear, laptopManufacturer, laptopModel, laptopOS;
    
    @FXML
    private void editLaptopAction() throws IllegalArgumentException, SQLException {
    	String imageLocation = "resourceImages/" + laptopTitle.getText() + "_" + laptopID.getText() + ".png";
    	ResourceEdit.editLaptop(laptopID.getText(), laptopTitle.getText(), laptopYear.getText(), imageLocation, laptopManufacturer.getText(), laptopModel.getText(), laptopOS.getText());
    }
    
 }
    
    
