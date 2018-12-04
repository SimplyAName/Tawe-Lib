
public class DVD extends Resource {

	private String director,language;
	private double runtime;
	private String[] subLanguage;

	public DVD (int rID,String title, int year, String rImg, String director, double runtime, String language, String[] subLanguage){
		super(rID,title,year,rImg);
		this.director = director;
		this.language = language;
		this.runtime = runtime;
		this.subLanguage = subLanguage;
	}
	
	public String getDirector(){
		return director;
	}
	
	public double getRuntime(){
		return runtime;
	}
	
	public String getLanguage(){
		return language;
	}
	
	public String[] getSubLanguages(){
		return subLanguage;
	}
}
