package main;

/* README
 * 
 * Use the code below to open this window from somewhere else:
 * 
  			FXMLLoader fLoader = new FXMLLoader(getClass().getResource("UserInterface.fxml"));
			UserInterfaceController controller = new UserInterfaceController(new User(0, "al","Alex Rasa", "07714634634", "2 Green Road", null, 1.23)); 
			fLoader.setController(controller);
			VBox root = fLoader.load();
			
			Scene scene = new Scene(root,600,400);
			//presets the labels and object values on the scene
			controller.setup();
 * */


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;	//unused####
import javafx.collections.ObservableList;	//unused
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import main.DVD;
import main.Laptop;

/**
 * This is the Library where resources can be searched and displayed.
 * @author Alex Rasa
 * @version 1.1.2
 *
 */
public class LibraryController {

	@FXML ImageView imgResPic;
	@FXML Label lblTitle;
	@FXML Label lblID;
	@FXML Label lblType;
	@FXML Label lblYear;
	
	//The text of the labels below will change depending on the type of resource selected.
	@FXML Label lblBoldData1;
	@FXML Label lblBoldData2;
	@FXML Label lblBoldData3;
	@FXML Label lblBoldData4;
	@FXML Label lblBoldData5;
	@FXML Label lblData1;
	@FXML Label lblData2;
	@FXML Label lblData3;
	@FXML Label lblData4;
	@FXML Label lblData5;
	
	@FXML Button btnTransactionHistory;
	@FXML Button btnReturnResource;
	@FXML Button btnLendResource;
	@FXML Button btnEditResource;
	@FXML Button btnSearch;
	
	@FXML TextField txtSearch;
	@FXML ListView<String> lstResources;
	@FXML ListView<String> listSubLanguages;
	
	public LibraryController() {
		displayData(null);
	}


	/**
	 * This method is called when the window is first displayed to setup the window.#### can repleace the method name with 'load' so it's automatically called.
	 */
	public void setup() {
		fillList();
		Laptop laptop = new Laptop(0, "Surface Pro", 1976, "imgDir", "Microsoft", "some model", "Windows 10");
		String[] arrayLang = {"French", "English"};
		DVD dvd = new DVD(0, "some dvd", 1999, "img", "James Cameron", 23, "English", arrayLang);
		Book book = new Book(0, "Of Mice and Men", 1889, "defaultImage.png", "Some Author again", "some publisher", "Genre", "12-123-5-314-23-523-", "English");
		displayData(book);
	}
	/**
	 * This method can be called to change object values on the panel to the right.
	 */
	private void displayData(Object obj) {
		
		//Display common resource data, if object is not an resource print to console.
		if (obj instanceof Resource) {
			Resource res = (Resource)obj;
			lblTitle.setText(res.getTitle());
			lblID.setText(String.valueOf(res.getID()));
			lblYear.setText(String.valueOf(res.getYear()));
			//File imgFile = new File(res.getImage());			//######
			Image img = new Image(res.getImage());
			imgResPic.setImage(img);
		} else {
			System.out.println("The object you are trying to display is not a 'Resource'!!!!");
			return;
		}
		
		//This displays extra information based onm the type of the resource.
		if (obj instanceof Laptop) {
			Laptop laptop = (Laptop)obj;
			lblType.setText("Laptop");
			
			lblBoldData1.setText("Manufacturer:");
			lblBoldData2.setText("Model:");
			lblBoldData3.setText("OS:");
			
			lblData1.setText(laptop.getManufacturer());
			lblData2.setText(laptop.getModel());
			lblData3.setText(laptop.getOS());
			
			listSubLanguages.setVisible(true);
			lblBoldData4.setVisible(false);
			lblBoldData5.setVisible(false);
			lblData4.setVisible(false);
			lblData5.setVisible(false);
		} else if (obj instanceof DVD) {
			DVD dvd = (DVD)obj;
			lblType.setText("Laptop");
			
			lblBoldData1.setText("Director:");
			lblBoldData2.setText("Runtime:");
			lblBoldData3.setText("Language:");
			lblBoldData4.setText("Sub-languages:");			
			
			lblData1.setText(dvd.getDirector());
			lblData2.setText(String.valueOf((int)dvd.getRuntime()) + " minutes");
			lblData3.setText(dvd.getLanguage());

			
			String[] lst = dvd.getSubLanguages();
			//ObservableList<String> list = FXCollections.observableArrayList(Arrays.asList(lst));	//#####
			listSubLanguages.getItems().addAll(lst);
			//listSubLanguages.setValue(dvd.getSubLanguages()[0]);	//####
			//choiceSubLanguages.setItems(list);		//####
			listSubLanguages.setVisible(true);
			lblBoldData4.setVisible(true);
			lblBoldData5.setVisible(false);
			lblData4.setVisible(false);
			lblData5.setVisible(false);
		} else if (obj instanceof Book) {
			Book book = (Book)obj;
			lblType.setText("book");
			
			lblBoldData1.setText("ISBN:");
			lblBoldData2.setText("Genre:");
			lblBoldData3.setText("Author:");
			lblBoldData4.setText("Publisher:");
			lblBoldData5.setText("Language:");
			lblData1.setText(book.getISBN());
			lblData2.setText(book.getGenre());
			lblData3.setText(book.getAuthor());
			lblData4.setText(book.getPublisher());
			lblData5.setText(book.getLanguage());
			
			listSubLanguages.setVisible(false);
			lblBoldData4.setVisible(true);
			lblBoldData5.setVisible(true);
			lblData4.setVisible(true);
			lblData5.setVisible(true);
		}
	}

		
	
	
	private void fillList() {
		
	}


}
