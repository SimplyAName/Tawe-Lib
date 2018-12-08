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




    }



