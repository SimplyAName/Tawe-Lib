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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.sun.xml.internal.fastinfoset.util.PrefixArray;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;	//for list item selection action
import javafx.collections.FXCollections;	//unused####
import javafx.collections.ObservableList;	//unused

import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;		//for listview mouse event
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;				//alerts for popup errors
import javafx.scene.control.Alert.AlertType;	//alerts for popup errors
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;		//for popup window ####
import javafx.stage.Stage;			//for popup window ####


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
	@FXML Button btnRequestCopy;
	@FXML Button btnReturnResource;
	@FXML Button btnLendResource;
	@FXML Button btnEditResource;
	@FXML Button btnDeleteResource;
	@FXML Button btnSearch;
	
	@FXML TextField txtSearch;
	//@FXML ListView<String> lstResources;				// not needed anymore ####?
	@FXML ListView<String> listSubLanguages;
	@FXML TableView<List<String>> tblResources;			//previously Resource type###
	@FXML TableView<List<String>> tblCopies;
	
	@FXML CheckBox checkLaptop;
	@FXML CheckBox checkDVD;
	@FXML CheckBox checkBook;
	
	/**
	 * The choices allowed for the amount of days a (minimum) loan of a copy is allowed.
	 */

	private final Integer[] LOAN_DURATIONS = {1, 7, 14, 28};
	
	public LibraryController() {
		//displayData(null);		//#####
	}


	/**
	 * This method is called when the window is first displayed to setup the window.#### can repleace the method name with 'load' so it's automatically called.
	 */
	public void setup(User user) {
		//Initialisation			//### should make this method into initialise
		setupTables();
		//Hides Controlls only Librarians have access to from users and resize the window.
		if ((user instanceof Librarian) == false) {
			btnLendResource.setVisible(false);
			btnReturnResource.setVisible(false);
			btnEditResource.setVisible(false);
			btnDeleteResource.setVisible(false);
			btnTransactionHistory.setVisible(false);
			//listCopies.setVisible(false);
			//btnRequestCopy.relocate(btnLendResource.getLayoutX(), btnLendResource.getLayoutY());
			//txtSearch.getScene().getWindow().setWidth(690);		//690 #####
			//txtSearch.getScene().getWindow().setHeight(425);		//425		######
			//stage.setWidth(700);
			//stage.setHeight(440);
		} else {
            
		}
		
		fillResourceList("", null, null);
		fillCopiesList("0");
		//This sets the event handler for selecting the list.
		//When an item in the list is selected the detailed information panel updates with the data of the selected resource.
		tblResources.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        public void handle(MouseEvent event) {
	    		//System.out.println("Works:	" + tblResources.getSelectionModel().getSelectedIndex());		//####testing
	            //System.out.println("clicked on " + lstResources.getSelectionModel().getSelectedItem());		//####
	    		//displayData(getResourceByID("1", "Book"));				//#####testing
	        	List<String> selected = tblResources.getSelectionModel().getSelectedItem();
	        	displayData(getResourceByID(selected.get(0), selected.get(1)));
	        	fillCopiesList(selected.get(0));		//### filling copies list
	        }
	    });
		
		btnLendResource.setOnAction(this::handleLendResourceAction);
		btnReturnResource.setOnAction(this::handleReturnResourceAction);
		btnEditResource.setOnAction(this::handleEditResourceAction);
		btnDeleteResource.setOnAction(this::handleDeleteResourceAction);
		btnRequestCopy.setOnAction(this::handleRequestCopyAction);
		btnSearch.setOnAction(this::handleSearchAction);
		
	}
	
	private void setupTables() {
        tblCopies.setEditable(false);
        //Setting up colums for the copy table.
        TableColumn<List<String>, String> copyColumn0 = new TableColumn<>("ID");
        copyColumn0.setMinWidth(5);
        copyColumn0.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().get(0)));
	    
	    TableColumn<List<String>, String> copyColumn1 = new TableColumn<>("Availability");
	    copyColumn1.setMinWidth(25);
	    copyColumn1.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().get(1)));

	    tblCopies.getColumns().add(copyColumn0);
	    tblCopies.getColumns().add(copyColumn1);
	    
	    
        tblResources.setEditable(false);
	    //Setting up the columns for the resources table.			
	    TableColumn<List<String>, String> resourceColumn0 = new TableColumn<>("ID");
	    resourceColumn0.setMinWidth(5);
	    resourceColumn0.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().get(0)));
	    //column.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().get));			///delete####
	    //tblResources.getColumns(). //######
	    
	    TableColumn<List<String>, String> resourceColumn1 = new TableColumn<>("Type");
	    resourceColumn1.setMinWidth(25);
	    resourceColumn1.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().get(1)));
	    
	    TableColumn<List<String>, String> resourceColumn2 = new TableColumn<>("Title");
	    resourceColumn2.setMinWidth(60);
	    resourceColumn2.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().get(2)));
	    
	    TableColumn<List<String>, String> resourceColumn3 = new TableColumn<>("Year");
	    resourceColumn3.setMinWidth(20);
	    resourceColumn3.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().get(3)));

	    tblResources.getColumns().add(resourceColumn0);
	    tblResources.getColumns().add(resourceColumn1);
	    tblResources.getColumns().add(resourceColumn2);
	    tblResources.getColumns().add(resourceColumn3);
        
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
		
		//This displays extra information based on the type of the resource.
		if (obj instanceof Laptop) {
			Laptop laptop = (Laptop)obj;
			lblType.setText("Laptop");
			
			lblBoldData1.setText("Manufacturer:");
			lblBoldData2.setText("Model:");
			lblBoldData3.setText("OS:");
			
			lblData1.setText(laptop.getManufacturer());
			lblData2.setText(laptop.getModel());
			lblData3.setText(laptop.getOS());
			
			listSubLanguages.setVisible(false);
			lblBoldData4.setVisible(false);
			lblBoldData5.setVisible(false);
			lblData4.setVisible(false);
			lblData5.setVisible(false);
		} else if (obj instanceof DVD) {
			DVD dvd = (DVD)obj;
			lblType.setText("DVD");
			
			lblBoldData1.setText("Director:");
			lblBoldData2.setText("Runtime:");
			lblBoldData3.setText("Language:");
			lblBoldData4.setText("Sub-languages:");			
			
			lblData1.setText(dvd.getDirector());
			lblData2.setText(String.valueOf((int)dvd.getRuntime()) + " minutes");
			lblData3.setText(dvd.getLanguage());

			
			String[] lst = dvd.getSubLanguages();
			//ObservableList<String> list = FXCollections.observableArrayList(Arrays.asList(lst));	//#####
			listSubLanguages.getItems().clear();
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
			lblType.setText("Book");
			
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

		
	/**
	 * This method returns the resource object with the provided 'resourceid'.
	 */
	private Resource getResourceByID(String resourceid, String type) {
		 try {
			 /*
			 	String tempTable = null;
			 	switch (type) {
			 	case "Laptop":
			 		tempTable = "laptop_tbl";
			 		break;
			 	case "DVD":
			 		tempTable = "dvd_tbl";
			 		break;
			 	case "Book":
			 		tempTable = "";
			 		break;
			 	default:
			 		tempTable = null;
			 		break;
			 	}
	            ResultSet set = Database.query("SELECT * FROM resource_tbl, '" + tempTable  + "' WHERE resource_tbl.resourceid = '" + resourceid + "' and '" + tempTable + "'.resourceid = '" + resourceid + "' ;");

	            LibrarySearch libSearch = new LibrarySearch();
	            ArrayList<Resource> resources = libSearch.search(null, null);
	            if (set.next()) {
	            	
	            	if (type.equals("Laptop")) {
	            		Laptop laptop = new Laptop();
	            	}
	            	
	            	return null;
	            } else {
	                Alert alrt = new Alert(AlertType.INFORMATION);
	                alrt.setTitle("ERROR");
	                alrt.setHeaderText(null);
	                alrt.setContentText("ERROR - Could not find the resource");

	                alrt.showAndWait();
	            }*/
			 	LibrarySearch libSearch = new LibrarySearch();
	            if (type.equals("Laptop")) {
	            	Laptop laptop = libSearch.createLaptop(Integer.parseInt(resourceid));
	            	return laptop;
	            } else if (type.equals("DVD")) {
	            	DVD dvd = libSearch.createDVD(Integer.parseInt(resourceid));
	            	return dvd;
	            } else if (type.equals("Book")) {
	            	Book book = libSearch.createBook(Integer.parseInt(resourceid));
	            	return book;
	            }
	            Alert alrt = new Alert(AlertType.INFORMATION);
	            alrt.setTitle("ERROR");
	            alrt.setHeaderText(null);
	            alrt.setContentText("ERROR - Could not read the resource type");

	            alrt.showAndWait();
	            return null;

	        } catch (Exception e) {
			 //alertDatabaseException();
	            e.printStackTrace();
	            return null;
	        }
	}
	
	/**
	 * This method will fill the listView on the left with all resources in the database.
	 */
	private void fillResourceList(String searchString, String resourceType, String orderBy) {
        try {
            LibrarySearch libSearch = new LibrarySearch();
            ArrayList<Resource> resources = libSearch.search(searchString, resourceType, orderBy);
            
		
            //The code below is automatically adding collumns from the array below.	//###
            //String[] colNames = {"ID","Type","Title","Year"};
            //The code bellow adds the columns above to the 'tblResources' table.
		    /*
            for (int i = 0; i < colNames.length; i++) {
		
		        TableColumn<List<String>, String> column = new TableColumn<>(colNames[i]);
		
		        
		        final int index = i ;				
		        //This sets the input method by which the cells get populated.
		        column.setCellValueFactory(cellData -> 
		            new SimpleStringProperty(cellData.getValue().get(index)));
		
		        tblResources.getColumns().add(column);
		
		    }*/
		    
		    
		    List<List<String>> resList = new ArrayList<List<String>>();
            
            if (!resources.isEmpty()) {
            	this.displayData(resources.get(0));
	            for (Resource res : resources) {
	            	String type = null;
	            	if (res instanceof Laptop) {
	            		type = "Laptop";
	            	} else if (res instanceof DVD) {
	            		type = "DVD";
	            	} else if (res instanceof Book) {
	            		type = "Book";
	            	}
	            	List<String> tempList = new ArrayList<String>();
	            	tempList.add(String.valueOf(res.getID()));
	            	tempList.add(type);
	            	tempList.add(res.getTitle());
	            	tempList.add(String.valueOf(res.getYear()));
	            	resList.add(tempList);
	            }
	            
            } else {
            	//if there are no resource in the database redirect to add resource? ########?? extra feature?? broken*** needs work
            	// **#** finish functionality
            	/* If there are no resources in the database there is the choice between 
            	redirecting to create resource window OR going back to the menu*/
            	Stage popup = new Stage();
            	popup.initModality(Modality.APPLICATION_MODAL);
            	//popup.initOwner(this);	//####
                VBox popupVbox = new VBox(20);
                popupVbox.getChildren().add(new Label("There are no resources in this database, do you wish to create one?"));
                Scene popupScene = new Scene(popupVbox, 300, 200);
                popup.setScene(popupScene);
                popup.show();
            }
            
            tblResources.getItems().clear();
		    ObservableList<List<String>> tableData = FXCollections.observableArrayList(resList);
		    tblResources.setItems(tableData); 
			
		    tblResources.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);		//############ move to initialise

        } catch (Exception e) {
			//alertDatabaseException();
	        e.printStackTrace();
        }
    }
	
	private void fillCopiesList(String resourceID) {
		try {
		//String[] columns = {"", "Availability"};			//###### delete
		ArrayList<List<String>> copies = getCopies(resourceID);		//get lblID text as parameter		#####!!!!!!
	    
	    List<List<String>> copyList = new ArrayList<List<String>>();
        
	    tblCopies.getItems().clear();
        if (!copies.isEmpty()) {
            for (List<String> copy : copies) {
            	List<String> tempList = new ArrayList<String>();
            	tempList.add(String.valueOf(copy.get(0)));
            	tempList.add(copy.get(1));
            	copyList.add(tempList);
            } 
        } 
	    ObservableList<List<String>> tableData = FXCollections.observableArrayList(copyList);
	    tblCopies.setItems(tableData); 
		
	    tblCopies.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);		//############ move to initialise
		} catch (Exception e) {
			//alertDatabaseException();
	        e.printStackTrace();
        }
	}
	
	/**
	 * This method gets all copies from the database for the given resource id.
	 * @param resourceID The ID of the resource in the database.
	 * @return An list of rows containing copy id and availability status.
	 */
	private ArrayList<List<String>> getCopies(String resourceID) {
		System.out.println(resourceID);		//####test
		ArrayList<List<String>> copyList = new ArrayList<List<String>>();
		try{
			ResultSet result = Database.query("SELECT * FROM copy_tbl WHERE resourceid = " + resourceID + " AND active = 1;");
			System.out.println("SELECT * FROM copy_tbl WHERE resourceid = " + resourceID + " AND active = 1;");		//###test
			while(result.next()){
				List<String> copyLine = new  ArrayList<String>();
				copyLine.add(result.getString("copyid"));
				//ResultSet result2 = Database.query("SELECT * FROM out_tbl WHERE copyid = " + result.getString("copyid") + ";");	//####
				ResultSet result2 = Database.query("SELECT * FROM copy_tbl AS temp WHERE copyid = " + result.getString("copyid") + " AND NOT EXISTS (SELECT * FROM out_tbl WHERE out_tbl.copyid = temp.copyid) AND NOT EXISTS (SELECT * FROM reservation_tbl WHERE reservation_tbl.copyid = temp.copyid);");
				if (result2.next()) {
					copyLine.add("Available");		
				} else {
					copyLine.add("Not Available");		
				}
				copyList.add(copyLine);
			}
			System.out.println("Success!!!");		//test #####
			return copyList;
			
		}catch(Exception e){
			System.out.println("Get copies broke!!!");				//#######test delete
			//alertDatabaseException();
			e.printStackTrace();
			//return null;
		}
		return null;
	}
	
	/**
	 * Creates and alert to inform there is a problem with the database connection/query.
	 */
	/*private void alertDatabaseException() {
		alertDatabaseException();
	}*/
	/* ==== FORM ACTIONS ==== */
	
	/**
	 * This action handler lends the selected resource to the given user.
	 * @param e
	 */
	private void handleLendResourceAction(ActionEvent e) {
		//Book book = new Book(0, "Of Mice and Men", 1889, "defaultImage.png", "Some Author again", "some publisher", "Genre", "12-123-5-314-23-523-", "English");	//##tests, delete
		//User user = new User(0, "john", "john doe", "0128712894", "some address", "asda", 0);		//##### tests, delete!
		//borrowCopy(, "1");		//#######
		//*#*********finish functionality.
		
		
		ResultSet availableCopies;
		try {
			availableCopies = Database.query("SELECT copyid FROM copy_tbl AS temp WHERE resourceid = " + lblID.getText() + " AND active = TRUE AND NOT EXISTS (SELECT * FROM out_tbl WHERE out_tbl.copyid = temp.copyid) AND NOT EXISTS (SELECT * FROM reservation_tbl WHERE reservation_tbl.copyid = temp.copyid);");

		//Checking if there is any available copy of the selected resource.
		if (availableCopies.next()) {
			//add feture **#**
		
			//Creating a popup window that will ask the librarian to input an username and the loan duration in order to loan a resource.
			 final Stage popup = new Stage();
			 popup.initModality(Modality.APPLICATION_MODAL);
			 popup.initOwner(btnLendResource.getScene().getWindow());
			 popup.setResizable(false);
	         
	         VBox popupVBox = new VBox(20);
	         popupVBox.setPadding(new Insets(20, 20, 20, 20));
	         popupVBox.getChildren().add(new Label("Lending a copy of: \"" + lblTitle.getText() + "\""));
	         popupVBox.getChildren().add(new Label("Select user and lending duration in order to proceed:"));
	         
	         HBox userHBox = new HBox(5);
	         userHBox.getChildren().add(new Label("Username:"));
	         TextField userField = new TextField();
	         userHBox.getChildren().add(userField);
	         popupVBox.getChildren().add(userHBox);
	
	         HBox durationHBox = new HBox(5);
	         durationHBox.getChildren().add(new Label("Loan Duration:"));
	         ComboBox comboDuration = new ComboBox<String>();
	         //List<Integer> comboItems = new ArrayList<>();
	         //comboItems.addAll(LEND_DURATIONS);
	         comboDuration.getItems().addAll(Arrays.asList(LOAN_DURATIONS));
	         durationHBox.getChildren().add(comboDuration);
	         durationHBox.getChildren().add(new Label("(in days)"));
	         popupVBox.getChildren().add(durationHBox);
	         
	         HBox buttonsHBox = new HBox();
	         
	         Button btnLend = new Button("Lend");
	         Button btnReturn = new Button("Return");
	         buttonsHBox.getChildren().add(btnLend);
	         buttonsHBox.getChildren().add(btnReturn);
	         
	         popupVBox.getChildren().add(buttonsHBox);
	         
	         Scene popupScene = new Scene(popupVBox, 400, 250);
	         popup.setScene(popupScene);
	         popup.show();
	         
	    
	         /*	pressing on the button will change the user of the copy in the database 
	      	to the one specified. */
	         //btnLend.setOnAction(this::handleLendAction);			//### delete
	         //btnReturn.setOnAction(this::handleReturnAction);		//####
	         btnLend.setOnAction(new EventHandler<ActionEvent>() {
	             @Override
	             public void handle(ActionEvent event) {
	                 //**#** Call function to loan resource.
	            	 ResourceManagement resManage = new ResourceManagement();
	            	 try {
						resManage.lendCopy(userField.getText(),Integer.parseInt(availableCopies.getString("copyid")), String.valueOf(comboDuration.getValue()));
						fillCopiesList(lblID.getText());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	 //System.out.println(userField.getText() + "  " + comboDuration.getValue());
	             }
	         });
	         
	         /* Pressing 'Return' will close the window*/
	         btnReturn.setOnAction(new EventHandler<ActionEvent>() {
	             @Override
	             public void handle(ActionEvent event) {
	                 popup.close();
	             }
	         });
		}
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
         
	}
	
	/**
	 * This action handler returns the selected resource from an user.
	 * @param e
	 */
	private void handleReturnResourceAction(ActionEvent e) {
		//*#*********finish functionality.
		
		final Stage popup = new Stage();
		 popup.initModality(Modality.APPLICATION_MODAL);
		 popup.initOwner(btnLendResource.getScene().getWindow());
		 popup.setResizable(false);
        
        VBox popupVBox = new VBox(20);
        popupVBox.setPadding(new Insets(20, 20, 20, 20));
        popupVBox.getChildren().add(new Label("Returning a copy of: \"" + lblTitle.getText() + "\""));
        popupVBox.getChildren().add(new Label("Select user to proceed"));
        
        HBox userHBox = new HBox(5);
        userHBox.getChildren().add(new Label("Username:"));
        TextField userField = new TextField();
        userHBox.getChildren().add(userField);
        popupVBox.getChildren().add(userHBox);
        
        HBox idHBox = new HBox(5);
        idHBox.getChildren().add(new Label("copy id:"));
        TextField idField = new TextField();
        idHBox.getChildren().add(idField);
        popupVBox.getChildren().add(idHBox);
        
        HBox buttonsHBox = new HBox();
        
        Button btnReturn = new Button("Return");
        Button btnCancel = new Button("Cancel");
        buttonsHBox.getChildren().add(btnReturn);
        buttonsHBox.getChildren().add(btnCancel);
        
        popupVBox.getChildren().add(buttonsHBox);
        
        Scene popupScene = new Scene(popupVBox, 400, 250);
        popup.setScene(popupScene);
        popup.show();
        
        btnReturn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //**#** Call function to loan resource.
           	 ResourceManagement resManage = new ResourceManagement();
           	 try {
					resManage.returnCopy(userField.getText(), Integer.parseInt(idField.getText()));
					fillCopiesList(lblID.getText());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        /* Pressing 'Return' will close the window*/
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popup.close();
            }
        });
	}
	
	
	/**
	 * This action handler opens the edit resource window with the selected item.
	 * @param e
	 */
	private void handleEditResourceAction(ActionEvent e) {
		//*#*********finish functionality.
		
	}
	
	/**
	 * This action handler removes the selected resource??? **#** remove action?
	 * @param e
	 */
	private void handleDeleteResourceAction(ActionEvent e) {
		//*##*********finish functionality.
	}
	
	/**
	 * This action handler requests an copy of the selected resource for the User logged on.
	 * @param e
	 */
	private void handleRequestCopyAction(ActionEvent e) {
		//*#*********finish functionality.
		
	}
	
	/**
	 * This action handler brings up the transaction history of the selected copy.
	 */
	@FXML
	private void showCopyHistoryAction() {
		try {
			Stage copyHistoryStage = new Stage();

			List<String> selected = tblCopies.getSelectionModel().getSelectedItem();

			FXMLLoader copyHistoryLoader = new FXMLLoader(getClass().getResource("CopyHistory.fxml"));
			copyHistoryLoader.setController(new CopyHistoryController(Integer.parseInt(selected.get(0))));
			Parent copyHistoryPane = copyHistoryLoader.load();

			copyHistoryStage.setScene(new Scene(copyHistoryPane));
			copyHistoryStage.setTitle("Copy History");

			copyHistoryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This action handler populates the resources list with the selected filters and search query.
	 * @param e
	 */
	private void handleSearchAction(ActionEvent e) {
		//*#*********finish functionality.
	}
	
	/*			//############ DELETE unused
	//* POPUP WINDOWS 
	
	private void handleLendAction(ActionEvent e) {
		//*#*********finish functionality.
		
	}
	
	private void handleReturnAction(ActionEvent e) {
		//*#*********finish functionality.
	}
	
	*/
}



