package main;

/**
 * Laptop class, subclass to Resource, used to create a Laptop
 * @author Jake Archer
 * @author Jakob Heeley
 * @version 1
 */
public class Laptop extends Resource {

	private String manufacturer,model,OS;

	/**
	 *
	 * @param rID The unique ID for the resource
	 * @param title The title of the resource
	 * @param year The year of the resource
	 * @param rImg The image for the resource
	 * @param manufacturer The manufacturer of the Laptop
	 * @param model The model of the Laptop
	 * @param OS What Operating System the Laptop uses
	 */
	public Laptop(int rID,String title, int year, String rImg, String manufacturer, String model, String OS) {
		super(rID,title,year,rImg);
		this.manufacturer = manufacturer;
		this.model = model;
		this.OS = OS;
	}

	/**
	 * Gets the manufacturer
	 * @return manufacturer
	 */
	public String getManufacturer(){
		return manufacturer;
	}

	/**
	 * get the model
	 * @return model
	 */
	public String getModel(){
		return model;
	}

	/**
	 * get the Operating System
	 * @return OS
	 */
	public String getOS(){
		return OS;
	}
}
