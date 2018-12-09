package main;

/**
 * Book class, subclass of resource, Creates a book
 * @author Jake Archer
 * @author Jakob Heeley
 * @version 1
 */
public class Book extends Resource {

	private String author,publisher,genre,ISBN,language;

	/**
	 *
	 * @param rID The unique ID for the resource
	 * @param title The title of the resource
	 * @param year The year of the resource
	 * @param rImg The image for the resource
	 * @param author The author of the book
	 * @param publisher The publisher of the book
	 * @param genre The genre of the book
	 * @param ISBN The unique ISBN of the book
	 * @param language The language of the nook
	 */
	public Book(String rID,String title, String year, String rImg, String author, String publisher, String genre, String ISBN,String language){
		super(rID,title,year,rImg);
		this.author = author;
		this.genre = genre;
		this.ISBN = ISBN;
		this.language = language;
		this.publisher = publisher;
	}

	/**
	 * get the author
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * get the publisher
	 * @return publihser
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * get the genre
	 * @return genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * get the ISBN
	 * @return ISBN
	 */
	public String getISBN() {
		return ISBN;
	}

	/**
	 * get the language
	 * @return language
	 */
	public String getLanguage() {
		return language;
	}

}
