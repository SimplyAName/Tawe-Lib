
public class Laptop extends Resource {

	private String manufacturer,model,OS;
	
	public Laptop(int rID,String title, int year, String rImg, String manufacturer, String model, String OS) {
		super(rID,title,year,rImg);
		this.manufacturer = manufacturer;
		this.model = model;
		this.OS = OS;
	}
	
	public String getManufacturer(){
		return manufacturer;
	}
	
	public String getModel(){
		return model;
	}
	
	public String getOS(){
		return OS;
	}
}
