/**
 * This class is the superclass of the resources.
 * @author 908169
 * @version 1
 */
public class Resource {
	private int rID,year;
	private String title,rImg;
	
	/**
	 * Constructor for the class
	 * @param rID
	 * @param title
	 * @param year
	 * @param rImg
	 */
	public Resource(int rID,String title, int year, String rImg){
		this.rID = rID;
		this.title = title;
		this.year = year;
		this.rImg = rImg;
	}
	
	/**
	 * returns the resource ID
	 * @return rID
	 */
	public int getID() {
		return rID;
	}
	
	/**
	 * returns the title
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * returns the year
	 * @return year
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * gets the image
	 * @return rImg
	 */
	public String getImage() {
		return rImg;
	}
}
