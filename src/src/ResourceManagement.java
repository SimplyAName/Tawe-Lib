import java.sql.ResultSet;


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
	

}

//USE USERNAME AND COPYID


//ResultSet checkExistingFines = Database.query("SELECT FROM fine_tb1 WHERE username = '"+ userID + "' AND amount = '"+ fineToPay + "' ';" );

