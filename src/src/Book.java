
public class Book extends Resource {

	private String author,publisher,genre,ISBN,language;
	
	public Book(int rID,String title, int year, String rImg, String author, String publisher, String genre, String ISBN,String language){
		super(rID,title,year,rImg);
		this.author = author;
		this.genre = genre;
		this.ISBN = ISBN;
		this.language = language;
		this.publisher = publisher;
	}

	private String getAuthor(){
		return author;
	}
	
	private String getPublisher(){
		return publisher;
	}

	private String getGenre(){
		return genre;
	}
	
	private String getISBN(){
		return ISBN;
	}
	
	private String getLanguage(){
		return language;
	}

}
