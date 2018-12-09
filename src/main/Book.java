package main;

/**
 * Book class, subclass of resource, Creates a book.
 * @author Jake Archer
 * @author Jakob Heeley
 * @version 1
 */
public class Book extends Resource {

	private String author;
	private String publisher;
	private String genre;
	private String ISBN;
	private String language;

	/**
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
	public Book(int rID,String title, int year, String rImg, String author, String publisher, String genre, String ISBN,String language){
		super(rID,title,year,rImg);
		this.author = author;
		this.genre = genre;
		this.ISBN = ISBN;
		this.language = language;
		this.publisher = publisher;
	}

	/**
     * Gets the author of the book.
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}

	/**
     * Gets the publisher of the book.
	 * @return publihser
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
     * Gets the genre of the book.
	 * @return genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
     * Gets the ISBN number of the book.
	 * @return ISBN
	 */
	public String getISBN() {
		return ISBN;
	}

	/**
     * Gets the language of the book.
	 * @return language
	 */
	public String getLanguage() {
		return language;
	}

}
