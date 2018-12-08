package src;
import java.sql.*;
import java.sql.PreparedStatement;

public class LibrarySearch {
    public static void main(String[] args) throws Exception {
        ResultSet rs = Database.query("SELECT * FROM resource_tbl " + type "WHERE type="
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

    }



