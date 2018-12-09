package main;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import main.DVD;
import main.Database;
import main.Laptop;
import main.Resource;

public class LibrarySearch {
	
	public LibrarySearch(){
		
	}
	
	public ArrayList<Resource> search(String type, String orderBy){
		
		ArrayList<Resource> list = new ArrayList<Resource>();
		ResultSet rs;
		String tmpType = "WHERE type = '";
		String tmpOrder = "ORDER BY ";
		if (type != null){
			tmpType += type += "' ";
		}else{
			tmpType = "";
		}
		if(orderBy != null){
			tmpOrder += orderBy;
		}else{
			tmpOrder = "";
		}
		
		try{
			rs = Database.query("SELECT * FROM resource_tbl "+ tmpType +tmpOrder+";");
			System.out.println("SELECT * FROM resource_tbl "+ tmpType +tmpOrder+";");
			while(rs.next()){
				int id = rs.getInt("resourceid");
				String specificType = rs.getString("type");		
				if(specificType.equals("book")){
					Book tmpbook = createBook(id);
					list.add(tmpbook);
				}else if(specificType.equals("dvd")){					
					DVD tmpdvd = createDVD(id);
					list.add(tmpdvd);
				}else if(specificType.equals("laptop")){					
					Laptop tmplaptop = createLaptop(id);
					list.add(tmplaptop);
				}				
			}
		}catch(Exception e){
			return null;
		}
		return list;
	}
			
	private Book createBook(int id){		
		ResultSet set;
		try{
			set = Database.query("SELECT * FROM book_tbl, resource_tbl WHERE book_tbl.resourceid = resource_tbl.resourceid AND book_tbl.resourceid = "+ id +";");
			if(set.next()){
				Book book = new Book(id, set.getString("title"), set.getInt("year"), set.getString("imageLocation"), set.getString("author"), set.getString("publisher"), set.getString("Genre"), set.getString("isbn"), set.getString("language"));
				return book;				
			}
		}catch(Exception e){
			return null;
		}
		return null;
	}
	private DVD createDVD(int id){	
		ResultSet set2 = null;
		try{
			set2 = Database.query("SELECT * FROM dvd_tbl, resource_tbl WHERE dvd_tbl.resourceid = resource_tbl.resourceid AND dvd_tbl.resourceid = "+ id +";");
			if(set2.next()){
				ResultSet set4;
				int arraySize = 0;
			
				set4 = Database.query("Select Count(dvd_tbl.subid) FROM sublanguage_tbl, dvd_tbl WHERE sublanguage_tbl.subid = dvd_tbl.subid AND resourceid = "+id +";");
				set4.next();
				arraySize = set4.getInt(1);
				
				String[] subtitles = new String[arraySize];
				ResultSet set3 = null;

				set3 = Database.query("SELECT * FROM sublanguage_tbl, dvd_tbl WHERE sublanguage_tbl.subid = dvd_tbl.subid AND dvd_tbl.resourceid = "+ id +";");
				set3.next();
				for(int i = 0; i< subtitles.length; i++){
					subtitles[i] = set3.getString("sublanguage");	
					set3.next();
				}		
				DVD dvd = new DVD(id, set2.getString("title"), set2.getInt("year"), set2.getString("imageLocation"), set2.getString("director"), set2.getInt("runtime"), set2.getString("language"), subtitles);
				return dvd;
			}
		}catch(Exception e){
            e.printStackTrace();
		}
		return null;
	}
	private Laptop createLaptop(int id){		
		ResultSet set;
		try{
			set = Database.query("SELECT * FROM laptop_tbl, resource_tbl WHERE laptop_tbl.resourceid = resource_tbl.resourceid AND laptop_tbl.resourceid = "+ id +";");		
			if(set.next()){				
				Laptop laptop = new Laptop(id, set.getString("title"), set.getInt("year"), set.getString("imageLocation"), set.getString("manufacturer"), set.getString("model"), set.getString("opsystem"));				
				return laptop;
			}
		}catch(Exception e){
			return null;
		}
		return null;
	}
	
	
	/*
	
    public static void main(String[] args) throws Exception {
    	
        ResultSet rs = Database.query("SELECT * FROM resource_tbl " + 
                "ORDER BY type, title;");
        PreparedStatement ps = null;
        Exception error = new Exception("Resource not found");

        while(rs.next()){
            System.out.println(rs.getString("type"));
            System.out.println(rs.getString("title"));
            System.out.println(rs.getString("year"));
        }

            }

            public int borrowResource(Book, DVD, Laptop Resource;) throws Exception {
                ResultSet rs = Database.query("SELECT * user_tbl WHERE username =  " +
                        "" +
                        "duedate");
                PreparedStatement ps = null;

            }

            public int returnResource(Book, DVD, Laptop Resource;) throws Exception {
                ResultSet rs = Database.query("SELECT * FROM out_tbl WHERE username = ‘' AND copyid = '' "+
                        "INSERT INTO historic_tbl VALUES (NULL, copy_id, date from, NOW(), username");
                PreparedStatement ps = null;

            }

            public int reservedCopy(Book, DVD, Laptop Resource;) throws Exception {
                ResultSet rs = Database.query("SELECT * FROM request_tbl, copy_tbl WHERE request_tbl.copyid = copy_tbl.requestid " +
                        "AND resource copyid = ENTER_HERE;\n");
                PreparedStatement ps = null;
            }
	*/
}



