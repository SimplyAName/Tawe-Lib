package src;
import java.sql.*;
/**
 * Contains static methods to interact with the database
 * @author Chris Humble - 960823
 *
 */
public class Database {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DATABASE = "jdbc:mysql://localhost:3306/tawe-lib";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "";
	/**
	 * Connect to database
	 * @return the connection
	 * @throws SQLException if a connection cannot be established
	 */
	private static Connection connect() throws SQLException{
		Connection connect;
		try{
			Class.forName(DRIVER).newInstance();
			connect = DriverManager.getConnection(DATABASE, DB_USERNAME, DB_PASSWORD);
			
		}catch (Exception e){
			throw new SQLException("Cannot connect to database");	
		}
		return connect;
	}
	/**
	 * Queries the database, returning the results as a java.sql.ResultSet
	 * @param myQuery your query as a string
	 * @return the result
	 * @throws IllegalArgumentException if your query is incorrect
	 * @throws SQLException if the connection could not be established
	 */
	public static ResultSet query(String myQuery) throws IllegalArgumentException, SQLException{
		ResultSet set = null;
		Connection connect = connect();
		try{
			Statement statement = connect.createStatement();
			set = statement.executeQuery(myQuery);
		}catch (Exception e){
			throw new IllegalArgumentException("Cannot query database. Please check your query");
		}
		return set;	
	}
	/**
	 * Edits the database if you supply a correct sql statement.
	 * @param myQuery the statement
	 * @throws IllegalArgumentException if the statement fails 
	 * @throws SQLException if the connection could not be established
	 */
	public static void edit(String myQuery) throws IllegalArgumentException, SQLException{
		Connection connect = connect();
		if(myQuery.startsWith("DROP") || myQuery.startsWith("ALTER")){
			throw new IllegalArgumentException("Cannot DROP or ALTER");
		}
		try{
			Statement statement = connect.createStatement();
			statement.execute(myQuery);
		}catch (Exception e){
			throw new IllegalArgumentException("Cannot edit database. Please check your query");
		}
	}
	
}
