package src;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.*;



/*
 * 
 * 	VERY INCOMPLETE 
 */
public class ResourceManagement {

	private int fineCost;

	public boolean generateBookFine(int daysOverdue, String userID) {
		ResultSet checkedResource = Database.query("SELECT from out_tb1, WHERE username = '" + userID + "' AND dueDate = '" + daysOverdue + "' + "';");
				return  checkedResource.next();
		}
	

	public boolean generateLaptopFine() {

	}

	public boolean generateDVDFine() {

	}

	public boolean isResourceRequested() {

	}

	public boolean borrowReservedResource() {


	}

	public boolean resourceAvailable(int id) {
		try {
			ResultSet checkAvailable = Database.query("SELECT copyid FROM copy_tbl AS temp WHERE resourceid = " + id + " + AND " +
					"active = TRUE AND NOT EXISTS (SELECT * FROM out_tbl WHERE out_tbl.copyid = temp.copyid) AND " +
					"NOT EXISTS (SELECT * FROM reservation_tbl WHERE reservation_tbl.copyid = temp.copyid);");

			return checkAvailable.next();
		} catch(Exception e) {
		} return false;
	}

	public returnCopy(String username, int id){
		ResultSet returnResource = Database.query("SELECT * FROM out_tbl WHERE username = "+ username + " AND copyid = "+ id +";\n" +
				"INSERT INTO historic_tbl VALUES (NULL, copy_id, date from, NOW(), username);\n");

		if(returnResource.next()) {
			if()
		}
	}

	public requestCopy(String username, int copyid) {
		ResultSet requestResource = Database.query("INSERT INTO request_tbl VALUES (NULL, resourceid, NOW(), username);" +
				"SELECT * FROM out_tbl, copy_tbl WHERE out_tbl.copyid = copy_tbl.copyid AND " +
				"copy_tbl.resourceid = ENTER_HERE AND duedate = NULL ORDER BY date");
	}





}

//USE USERNAME AND COPYID


//ResultSet checkExistingFines = Database.query("SELECT FROM fine_tb1 WHERE username = '"+ userID + "' AND amount = '"+ fineToPay + "' ';" );

