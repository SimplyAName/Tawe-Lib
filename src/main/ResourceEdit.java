package main;


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
	 * @param subLanguages array of DVD subtitle languages
	 * @param query The query that will edit the tuples
	 * @param subquery The query used for the sub languages
	 * @param sID Used for the subquery
	 * @throws IllegalArgumentException
	 * @throws SQLException if a connect cannot be established
	 */
	public static void editDVD(String rID, String title, String year, String rImg, String director, String runtime, String language, String subLanguages) throws IllegalArgumentException, SQLException {
		
		subquery = ("select subid from dvd_tbl where resourceID = " + rID);
		sID = Database.query(subquery);

		query =  "UPDATE resource_tbl SET title = '" + title +       "' WHERE resourceID = " + rID + "; " +
				 "UPDATE resource_tbl SET year = " + year +       " WHERE resourceID = " + rID + "; " +
				 "UPDATE resource_tbl SET imagelocation = '" + rImg +       "' WHERE resourceID = " + rID + "; " +
				 "UPDATE dvd_tbl SET director = '" + director +       "' WHERE resourceID = " + rID + "; " +
				 "UPDATE dvd_tbl SET runtime = " + runtime +       " WHERE resourceID = " + rID + "; " +
				 "UPDATE dvd_tbl SET language = '" + language +       "' where resourceID = " + rID + "; " +
				 "UPDATE sublanguage_tbl SET subid = '" + subLanguages +       "' WHERE subid = " + sID + "; ";

		Database.edit(query);
		
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
	public static void editBook(String rID, String title, String year, String rImg, String author, String publisher, String genre, String ISBN, String language) throws IllegalArgumentException, SQLException{
		
		query = ("update resource_tbl set title = " + title +       " where resourceID = " + rID + "; " +
				 "update resource_tbl set year = " + year +       " where resourceID = " + rID + "; " +
				 "update resource_tbl set imagelocation = " + rImg +       " where resourceID = " + rID + "; " +
				 "update book_tbl set author = " + author +       " where resourceID = " + rID + "; " +
				 "update book_tbl set publisher = " + publisher +       " where resourceID = " + rID + "; " +
				 "update book_tbl set genre = " + genre +       " where resourceID = " + rID + "; " +
				 "update book_tbl set isbn = " + ISBN +       " where resourceID = " + rID + "; " +
				 "update book_tbl set language = " + language +       " where resourceID = " + rID + "; " 
				 );
		Database.edit(query);
		
	}
	
	/**
	 * edits the tuples in Laptop.
	 * @param rID Resource ID
	 * @param title Resource title
	 * @param year Resource year 
	 * @param rImg Resource Image
	 * @param manufacturer Who createde the Laptop
	 * @param model Laptop model
	 * @param OS Operating System of Laptop
	 * @throws IllegalArgumentException
	 * @throws SQLException If there is no connection
	 */
	public static void editLaptop(String rID, String title, String year, String rImg, String manufacturer, String model, String OS) throws IllegalArgumentException, SQLException{
		
		query = ("update resource_tbl set title = " + title +       " where resourceID = " + rID + "; " +
				 "update resource_tbl set year = " + year +       " where resourceID = " + rID + "; " +
				 "update resource_tbl set imagelocation = " + rImg +       " where resourceID = " + rID + "; " +
				 "update laptop_tbl set manufacturer = " + manufacturer +       " where resourceID = " + rID + "; " +
				 "update laptop_tbl set model = " + model +       " where resourceID = " + rID + "; " +
				 "update laptop_tbl set opsystem = " + OS +       " where resourceID = " + rID + "; "
				 );
		Database.edit(query);
		
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
}