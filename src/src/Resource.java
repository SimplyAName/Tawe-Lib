
public class Resource {
	private int rID,year;
	private String title,rImg;
	
	public Resource(int rID,String title, int year, String rImg){
		this.rID = rID;
		this.title = title;
		this.year = year;
		this.rImg = rImg;
	}
	
	public int getID() {
		return rID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getImage() {
		return rImg;
	}
}
