package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This is the 'User Dashboard' of the system.
 * @author Alex Rasa
 * @version 1.1.2
 *
 */
public class UserInterfaceController implements Initializable {

	@FXML
	private ImageView profileImageView;

	@FXML
	private Label lblName;

	@FXML
	private Label lblBalance;

	@FXML
	private TableView<UserDashResource> borrowedTable;

	@FXML
	private TableView<UserDashResource> requestedTable;

	@FXML
	private TableView<UserDashResource> reservedTable;
	
	private User user;
	
	public UserInterfaceController(User user) {
		this.user = user;
	}

	@FXML
	private void showLibraryAction() {
		try {
			Stage libraryStage = new Stage();
			FXMLLoader libraryLoader = new FXMLLoader(getClass().getResource("Library.fxml"));
			LibraryController libraryController = new LibraryController();
			libraryLoader.setController(libraryController);
			Parent libraryPane = libraryLoader.load();
			libraryStage.setScene(new Scene(libraryPane));
			libraryStage.setTitle("Library");

			libraryController.setup(user);

			libraryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showBorrowedResources() {

		ArrayList<UserDashResource> borrowedResources = new ArrayList<>();

		try {
			ResultSet borrowedResourcesResult = Database.query("SELECT resource_tbl.resourceid, resource_tbl.title, resource_tbl.type, out_tbl.datefrom, out_tbl.duedate " +
					"FROM out_tbl, copy_tbl, resource_tbl WHERE out_tbl.copyid = copy_tbl.copyid AND " +
					"copy_tbl.resourceid = resource_tbl.resourceid AND out_tbl.username = '" + user.getUsername() + "';");

			while (borrowedResourcesResult.next()) {
				int resourceId = borrowedResourcesResult.getInt("resource_tbl.resourceid");
				String resourceTitle = borrowedResourcesResult.getString("resource_tbl.title");
				String resourceType = borrowedResourcesResult.getString("resource_tbl.type");
				String resourceDateFrom = borrowedResourcesResult.getString("out_tbl.datefrom");
				String resourceDueDate = borrowedResourcesResult.getString("out_tbl.duedate");

				borrowedResources.add(new UserDashResource(resourceId, resourceTitle, resourceType, resourceDateFrom, resourceDueDate));
			}

			borrowedTable.getItems().addAll(borrowedResources);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showRequestedResource() {

		ArrayList<UserDashResource> requestedResources = new ArrayList<>();

		try {
			ResultSet borrowedResourcesResult = Database.query("SELECT resource_tbl.resourceid, resource_tbl.title, resource_tbl.type, request_tbl.date " +
					"FROM request_tbl, resource_tbl " +
					"WHERE resource_tbl.resourceid = request_tbl.resourceid AND request_tbl.username = '" + user.getUsername() + "';");

			while (borrowedResourcesResult.next()) {
				int resourceId = borrowedResourcesResult.getInt("resource_tbl.resourceid");
				String resourceTitle = borrowedResourcesResult.getString("resource_tbl.title");
				String resourceType = borrowedResourcesResult.getString("resource_tbl.type");
				String resourceDateFrom = borrowedResourcesResult.getString("request_tbl.date");

				requestedResources.add(new UserDashResource(resourceId, resourceTitle, resourceType, resourceDateFrom));
			}

			requestedTable.getItems().addAll(requestedResources);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void showReservedResource() {

		ArrayList<UserDashResource> reservedResources = new ArrayList<>();

		try {
			ResultSet reservedResourcesResult = Database.query("SELECT resource_tbl.resourceid, resource_tbl.title, resource_tbl.type, reservation_tbl.date " +
					"FROM reservation_tbl, copy_tbl, resource_tbl " +
					"WHERE reservation_tbl.copyid = copy_tbl.copyid AND copy_tbl.resourceid = resource_tbl.resourceid AND reservation_tbl.username = '" + user.getUsername() + "';");

			while (reservedResourcesResult.next()) {
				int resourceId = reservedResourcesResult.getInt("resource_tbl.resourceid");
				String resourceTitle = reservedResourcesResult.getString("resource_tbl.title");
				String resourceType = reservedResourcesResult.getString("resource_tbl.type");
				String resourceDateFrom = reservedResourcesResult.getString("reservation_tbl.date");

				reservedResources.add(new UserDashResource(resourceId, resourceTitle, resourceType, resourceDateFrom));
			}

			reservedTable.getItems().addAll(reservedResources);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	private void showTransactionAction() {
		try {
			Stage transactionsStage = new Stage();
			FXMLLoader transactionsLoader = new FXMLLoader(getClass().getResource("TransactionHistory.fxml"));
			transactionsLoader.setController(new TransactionHistoryController(user));
			Parent transactionsPane = transactionsLoader.load();
			transactionsStage.setScene(new Scene(transactionsPane));
			transactionsStage.setTitle("Transaction History");

			transactionsStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblName.setText(user.getFirstName() + " " + user.getLastName());
		lblBalance.setText(String.valueOf(user.getFineBalance()));
		profileImageView.setImage(user.getProfileImage());

		showBorrowedResources();
		showRequestedResource();
		showReservedResource();
	}
}
