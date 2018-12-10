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
	private TextField LaptopID, LaptopTitle, LaptopYear, LaptopManufacturer, LaptopModel, LaptopOS;
    @FXML
    private TextField dvdID, dvdTitle, dvdYear, dvdDirector, dvdRuntime, dvdLanguage, dvdSubtitles;
	
	private String imageLocation = "";

    @FXML
    private void editBookAction() throws IllegalArgumentException, SQLException {
    	
    		String imageLocation = "resourceImages/" + bookTitle.getText() + "_" + bookID.getText() + ".png";
    		ResourceEdit.editBook(Integer.parseInt(bookID.getText()), bookTitle.getText(), Integer.parseInt(bookYear.getText()),
    				imageLocation, bookAuthor.getText(), bookPublisher.getText(), bookGenre.getText(),
    				bookISBN.getText(), bookLanguage.getText());
    			
    		}
    
    @FXML
    private void editDVDAction() throws IllegalArgumentException, SQLException {
    	String imageLocation = "resourceImages/" + dvdTitle.getText() + "_" + dvdID.getText() + ".png";  	
    	ResourceEdit.editDVD(Integer.parseInt(dvdID.getText()), dvdTitle.getText(), Integer.parseInt(dvdYear.getText()), imageLocation, dvdDirector.getText(),
    			Integer.parseInt(dvdRuntime.getText()), dvdLanguage.getText(), dvdSubtitles.getText() );
    }
    
    @FXML
    private void editLaptopAction() throws IllegalArgumentException, SQLException {

    	String imageLocation = "resourceImages/" + LaptopTitle.getText() + "_" + LaptopID.getText() + ".png";
    	ResourceEdit.editLaptop(Integer.parseInt(LaptopID.getText()), LaptopTitle.getText(), Integer.parseInt(LaptopYear.getText())
    			, imageLocation, LaptopManufacturer.getText(), LaptopModel.getText(), LaptopOS.getText());
    	
    }
    
    private void addSubtitle() {
    	
    }
    
 }
    
    
