/**
 * DVD class, subclass of Resource, creates DVD's
 *@author Jake Archer
 *@author Jakob Heeley
 */
public class DVD extends Resource {

	private String director,language;
	private double runtime;
	private String[] subLanguage;
	
	/**
	 * 
	 * @param rID The unique ID for the resource
	 * @param title The title of the resource
	 * @param year The year of the resource
	 * @param rImg The image for the resource
	 * @param director The director of the 
	 * @param runtime
	 * @param language
	 * @param subLanguage
	 */
	public DVD (int rID,String title, int year, String rImg, String director, double runtime, String language, String[] subLanguage){
		super(rID,title,year,rImg);
		this.director = director;
		this.language = language;
		this.runtime = runtime;
		this.subLanguage = subLanguage;
	}
	
	/**
	 * get the director of the DVD
	 * @return
	 */
	public String getDirector(){
		return director;
	}
	
	/**
	 * get the runtime of the DVD
	 * @return runtime
	 */
	public double getRuntime(){
		return runtime;
	}
	
	/**
	 * get the language of the DVD
	 * @return
	 */
	public String getLanguage(){
		return language;
	}
	
	/**
	 * get the languages of the subtitles
	 * @return subLanguage
	 */
	public String[] getSubLanguages(){
		return subLanguage;
	}
}
