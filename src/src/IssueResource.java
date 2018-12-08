package src;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;


public class IssueResource {

    public static void main(String[] args) {

    }
    public static issueResource(String username, int copyid) {
        ResultSet rs = Database.query("SELECT * FROM resource_tbl WHERE resourceid = " + resourceid);
        PreparedStatement ps = null;



        while(rs.next()) {
            if(.equals("Yes")) {
                ResultSet rs1 = Database.query("INSERT INTO out_tbl VALUES(" +
                        "outid," +
                        "copyid," +
                        "datefrom," +
                        "duedate," +
                        "username");

                JOptionPane.showMessageDialog(null, "Resource " + title + "has been issued");
            } if(TITLE.equals("No")) {
            JOptionPane.showMessageDialog(null, "Resource is currently not available");
        }
        }
    }
}
