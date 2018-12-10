package main;


import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Edits tuples in the database.
 * @author Jake Archer
 * @author Jakob Heeley
 */
public class ResourceEdit {

	private static String query;
	private static String subquery;
	private static ResultSet sID; 
	
	public ResourceEdit() {
		
	}

	/**
	 * edits tuples in DVD
	 * @param rID Resource ID
	 * @param title Resource title
	 * @param year Resource year 
	 * @param rImg Resource Image
	 * @param director DVD Director
	 * @param runtime DVD runtime
	 * @param language DVD language
	 * @param dVDSubtitles array of DVD subtitle languages
	 * @throws IllegalArgumentException
	 * @throws SQLException if a connect cannot be established
	 */
	public static void editDVD(int rID, String title, int year, String rImg,
			String director, int runtime, String language, String dVDSubtitles)
					throws IllegalArgumentException, SQLException {
		
		Database.edit("UPDATE resource_tbl SET title = '" + title + "', year ="
		+ year + ", imagelocation = '" + rImg + "' WHERE resourceid = " + rID + ";");
		Database.edit("UPDATE dvd_tbl SET director = '" + director + "',"
				+ " runtime = " + runtime + ", language = '"+ language + "' "
						+ "WHERE resourceid = " + rID + ";");		
		
		sID = Database.query("select subid from dvd_tbl where resourceID"
				+ " = " + rID + "; ");
		sID.next();
		int subid = sID.getInt("subid");
		Database.edit("UPDATE sublanguage_tbl SET sublanguage = '" + dVDSubtitles
				+ "' WHERE subid = " + subid + ";");

		resourceEditedSuccessfully(title);

	}
	
	/**
	 * Edits the subtitle languages for the DVD
	 * @param dvdSubId
	 * @param subLanguageList
	 * @return Returns true with subtitles languages added to database
	 * False otherwise
	 */
	public static boolean editSubLanguages(int dvdSubId, String subLanguageList) {

		String[] subLanguageArray = subLanguageList.split(",");

		try {
			Database.edit("DELETE FROM sublanguage_tbl WHERE subid = " + dvdSubId +";");
			for (String language : subLanguageArray) {
				Database.edit("INSERT INTO sublanguage_tbl VALUES (" + dvdSubId 
						+ ",'" + language + "');");
			}
			return true;
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return false;
	}
	
	/**
	 * edits tuples in book
	 * @param rID Resource ID
	 * @param title Resource title
	 * @param year Resource year 
	 * @param rImg Resource Image
	 * @param author The book author
	 * @param publisher The book publisher
	 * @param genre The book genre
	 * @param ISBN The book ISBN
	 * @param language The books language
	 * @throws IllegalArgumentException
	 * @throws SQLException If there is no connection
	 */
	public static void editBook(int rID, String title, int year, String rImg, String author, String publisher, String genre, String ISBN, String language) throws IllegalArgumentException, SQLException{
		System.out.println("test2");
		Database.edit("UPDATE resource_tbl SET title = '" + title + "', year =" + year + ", imagelocation = '" + rImg + "' WHERE resourceid = " + rID + ";");
		Database.edit("UPDATE book_tbl SET author = '" + author + "', publisher = '" + publisher + "', genre = '" + genre +
				"', isbn =" + ISBN + ", language = '" + language + "' WHERE resourceid = " + rID + ";");

		resourceEditedSuccessfully(title);

	}
	
	/**
	 * edits the tuples in Laptop.
	 * @param rID Resource ID
	 * @param title Resource title
	 * @param year Resource year 
	 * @param rImg Resource Image
	 * @param manufacturer Who created the Laptop
	 * @param model Laptop model
	 * @param OS Operating System of Laptop
	 * @throws IllegalArgumentException
	 * @throws SQLException If there is no connection
	 */
	public static void editLaptop(int rID, String title, int year, String rImg, String manufacturer, String model, String OS) throws IllegalArgumentException, SQLException{
		
		Database.edit("UPDATE resource_tbl SET title = '" + title + "', year =" + year + ", imagelocation = '" + rImg + "' WHERE resourceid = " + rID + ";");
		
		Database.edit("UPDATE laptop_tbl SET manufacturer = '" + manufacturer + "', model = '" + model + "', opsystem = '" + OS + "' WHERE resourceid = " + rID + ";");

		resourceEditedSuccessfully(title);
	}
	
	/**
	 * delete a resource from the table.
	 * @param rID
	 * @throws IllegalArgumentException
	 * @throws SQLException If there is no connection
	 */
	public void deleteResource(int rID) throws IllegalArgumentException, SQLException{
		
		query = ("delete from resource_tbl where resourceID = " + rID + ";" );
		Database.edit(query);
		
	}

	/**
	 *  Edits the title of the resource
	 * @param title The resource title being edited
	 */
	private static void resourceEditedSuccessfully(String title) {

		String alertTitle = "Resource successfully edit!";
		String alertHeader = title + " has been update";
		String alertMessage = title + "has been update successfully and now is now using the updated information";

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(alertTitle);
		alert.setHeaderText(alertHeader);
		alert.setContentText(alertMessage);

		alert.showAndWait();

	}

	/**
	 * Returns an error alert message if anything goes wrong with editing
	 * @param alertTitle The title for the error alert message
	 * @param alertHeader The header for the error alert message
	 * @param alertMessage The error alert message
	 */
	private static void resourceEditedError(String alertTitle, String alertHeader, String alertMessage) {

		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(alertTitle);
		alert.setHeaderText(alertHeader);
		alert.setContentText(alertMessage);

		alert.showAndWait();

	}
}

