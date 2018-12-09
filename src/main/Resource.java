package main;
/**
 * This class is the superclass of the resources.
 * @author Jake Archer
 * @author Jakob Heeley
 * @version 1
 */
public class Resource {
	private String rID;
	private String year;
	private String title;
	private String rImg;
	
	/**
	 * Constructor for the class
	 * @param rID The unique ID for the resource
	 * @param title The title of the resource
	 * @param year The year of the resource
	 * @param rImg The image for the resource
	 */
	public Resource(String rID,String title, String year, String rImg){
		this.rID = rID;
		this.title = title;
		this.year = year;
		this.rImg = rImg;
	}
	
	/**
	 * returns the resource ID
	 * @return rID
	 */
	public String getID() {
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
	public String getYear() {
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
