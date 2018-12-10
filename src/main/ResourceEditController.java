package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

public class ResourceEditController implements Initializable{

    @FXML
    private Stage EditResourceStage;

    @FXML
    protected ImageView uploadImageView;

	@FXML
    private TextField bookID, bookTitle, bookYear, bookAuthor, bookPublisher, bookGenre, bookISBN, bookLanguage;
	@FXML
	private TextField LaptopID, LaptopTitle, LaptopYear, LaptopManufacturer, LaptopModel, LaptopOS;
    @FXML
    private TextField dvdID, dvdTitle, dvdYear, dvdDirector, dvdRuntime, dvdLanguage, dvdSubtitles;

    private BufferedImage uploadedImage;

    private int resourceId;

    public ResourceEditController(int resourceId){
        this.resourceId = resourceId;
    }

    @FXML
    private void editBookAction() throws IllegalArgumentException, SQLException {
    	
    		String imageLocation = "src/main/resourceImages/" + bookTitle.getText() + "_" + resourceId + ".png";
    		ResourceEdit.editBook(resourceId, bookTitle.getText(), Integer.parseInt(bookYear.getText()),
    				imageLocation, bookAuthor.getText(), bookPublisher.getText(), bookGenre.getText(),
    				bookISBN.getText(), bookLanguage.getText());
    			
    		}
    
    @FXML
    private void editDVDAction() throws IllegalArgumentException, SQLException {
    	String imageLocation = "src/main/resourceImages/" + dvdTitle.getText() + "_" + resourceId + ".png";
    	ResourceEdit.editDVD(resourceId, dvdTitle.getText(), Integer.parseInt(dvdYear.getText()), imageLocation, dvdDirector.getText(),
    			Integer.parseInt(dvdRuntime.getText()), dvdLanguage.getText(), dvdSubtitles.getText() );
    }
    
    @FXML
    private void editLaptopAction() throws IllegalArgumentException, SQLException {

    	String imageLocation = "src/main/resourceImages/" + LaptopTitle.getText() + "_" + resourceId + ".png";
    	ResourceEdit.editLaptop(resourceId, LaptopTitle.getText(), Integer.parseInt(LaptopYear.getText())
    			, imageLocation, LaptopManufacturer.getText(), LaptopModel.getText(), LaptopOS.getText());
    	
    }
    
    private void addSubtitle() {
    	
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

        fillData();
    }

    private void fillData(){

        String resourceType = "";

        try {
            ResultSet resouceTypeResults = Database.query("SELECT type FROM resource_tbl WHERE resourceId = " + resourceId + ";");

            if(resouceTypeResults.next()){
                resourceType = resouceTypeResults.getString("type");
            }else{
                System.out.println("Error find resource");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        if(resourceType.equals("book")){
            try {
                ResultSet bookInfoResult = Database.query("SELECT resource_tbl.title, resource_tbl.year, resource_tbl.imagelocation, " +
                        "book_tbl.author, book_tbl.publisher, book_tbl.genre, book_tbl.isbn, book_tbl.language " +
                        "FROM resource_tbl, book_tbl WHERE resource_tbl.resourceid = book_tbl.resourceid AND resource_tbl.resourceid = " + resourceId + ";");

                if (bookInfoResult.next()){
                    bookTitle.setText(bookInfoResult.getString("resource_tbl.title"));
                    bookYear.setText(bookInfoResult.getString("resource_tbl.year"));
                    bookAuthor.setText(bookInfoResult.getString("book_tbl.author"));
                    bookPublisher.setText(bookInfoResult.getString("book_tbl.publisher"));
                    bookGenre.setText(bookInfoResult.getString("book_tbl.genre"));
                    bookISBN.setText(bookInfoResult.getString("book_tbl.isbn"));
                    bookLanguage.setText(bookInfoResult.getString("book_tbl.language"));

                    uploadImageView.setImage(new Image(bookInfoResult.getString("resource_tbl.imagelocation")));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }else if (resourceType.equals("dvd")){
            try {
                ResultSet dvdInfoResult = Database.query("SELECT resource_tbl.title, resource_tbl.year, resource_tbl.imagelocation, " +
                        "dvd_tbl.director, dvd_tbl.runtime, dvd_tbl.language, dvd_tbl.subid FROM resource_tbl, dvd_tbl " +
                        "WHERE resource_tbl.resourceid = dvd_tbl.resourceid AND resource_tbl.resourceid = " + resourceId + ";");

                if (dvdInfoResult.next()){
                    dvdTitle.setText(dvdInfoResult.getString("resource_tbl.title"));
                    dvdYear.setText(dvdInfoResult.getString("resource_tbl.year"));
                    dvdDirector.setText(dvdInfoResult.getString("dvd_tbl.director"));
                    dvdRuntime.setText(dvdInfoResult.getString("dvd_tbl.runtime"));
                    dvdLanguage.setText(dvdInfoResult.getString("dvd_tbl.language"));

                    int dvdSubtitlesId = dvdInfoResult.getInt("dvd_tbl.subid");

                    ResourceEdit.editSubLanguages(dvdSubtitlesId, dvdSubtitles.getText());

                    uploadImageView.setImage(new Image(dvdInfoResult.getString("resource_tbl.imagelocation")));
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }else if (resourceType.equals("laptop")){
            try {
                ResultSet bookInfoResult = Database.query("SELECT resource_tbl.title, resource_tbl.year, resource_tbl.imagelocation, " +
                        "laptop_tbl.manufacturer, laptop_tbl.model, laptop_tbl.opsystem FROM resource_tbl, laptop_tbl " +
                        "WHERE resource_tbl.resourceid = laptop_tbl.resourceid AND resource_tbl.resourceid = " + resourceId + ";");

                if (bookInfoResult.next()){
                    LaptopTitle.setText(bookInfoResult.getString("resource_tbl.title"));
                    LaptopYear.setText(bookInfoResult.getString("resource_tbl.year"));
                    LaptopManufacturer.setText(bookInfoResult.getString("laptop_tbl.manufacturer"));
                    LaptopModel.setText(bookInfoResult.getString("laptop_tbl.model"));
                    LaptopOS.setText(bookInfoResult.getString("laptop_tbl.opsystem"));

                    uploadImageView.setImage(new Image(bookInfoResult.getString("resource_tbl.imagelocation")));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }else{
            System.out.println("Resource type could not be found !");
        }
    }
    
 }
    
    
