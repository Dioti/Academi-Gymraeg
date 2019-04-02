package groupproject;

import java.sql.*;

public class PopulateDB
{
	public static void main(String[] args) throws SQLException {
		// connect to the database
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "password123"); // TODO alternative connection
		Statement stmt = conn.createStatement();
		//ResultSet rs = null;
		
		// execute statements
		stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS javatech");
		System.out.println("Database created!\n-------------------");
		stmt.execute("USE javatech");
	
		dropTables(stmt);
		createTables(stmt);
		populateTables(stmt);
		
		// print User table
		//rs = stmt.executeQuery("SELECT * FROM User");
		printTable("User", stmt);
		printTable("English", stmt);
		printTable("Welsh", stmt);
		printTable("Dictionary", stmt);
		
		//conn.close();
	}
	
	
	
	
	public static void dropTables(Statement stmt) throws SQLException {
		String query;
		System.out.println("Dropping existing tables...");
		
		query = "DROP TABLE IF EXISTS Dictionary";
		stmt.executeUpdate(query);
		query = "DROP TABLE IF EXISTS Welsh";
		stmt.executeUpdate(query);
		query = "DROP TABLE IF EXISTS English";
		stmt.executeUpdate(query);
		query = "DROP TABLE IF EXISTS User";
		stmt.executeUpdate(query);
		
		System.out.println("Dropped tables!\n-------------------");
	}
	
	
	
	/**
	 * Create tables
	 * @param stmt
	 * @throws SQLException
	 */
	public static void createTables(Statement stmt) throws SQLException {
		String query;
		System.out.println("Creating tables...\n-------------------");
		
		// create User table
		query = "CREATE TABLE User " +
					"(id INTEGER AUTO_INCREMENT, " + 
					"username VARCHAR(20) NOT NULL, " +
					"password VARCHAR(30) NOT NULL, " + // TODO implement passsword hashing
					"email VARCHAR(35) NOT NULL, " +
					"forename VARCHAR(25) NOT NULL, " +
					"permissions VARCHAR(15) NOT NULL," +
					"PRIMARY KEY(id))" +
					"ENGINE = INNODB";
		stmt.executeUpdate(query);
		System.out.println("Created User table");
		
		// create English words table
		query = "CREATE TABLE English " +
					"(id INTEGER AUTO_INCREMENT, " +
					"word VARCHAR(25) UNIQUE, " +
					"PRIMARY KEY(id))" +
					"ENGINE = INNODB";
		stmt.executeUpdate(query);
		System.out.println("Created English table");
		
		// create Welsh words table
		query = "CREATE TABLE Welsh " +
					"(id INTEGER AUTO_INCREMENT, " +
					"word VARCHAR(25) UNIQUE, " +
					"gender CHAR(1) NOT NULL, " +
					"PRIMARY KEY(id))" +
					"ENGINE = INNODB";
		stmt.executeUpdate(query);
		System.out.println("Created Welsh table");
		
		// create Dictionary table
		query = "CREATE TABLE Dictionary" +
					"(id INTEGER AUTO_INCREMENT, " +
					"english VARCHAR(25) NOT NULL, " +
					"welsh VARCHAR(25) NOT NULL, " +
					"type VARCHAR(10) NOT NULL," +
					"PRIMARY KEY(id), " + 
					"FOREIGN KEY(english) REFERENCES English(word) ON DELETE CASCADE ON UPDATE CASCADE, " +
					"FOREIGN KEY(welsh) REFERENCES Welsh(word) ON DELETE CASCADE ON UPDATE CASCADE)" +
					"ENGINE = INNODB";
		stmt.executeUpdate(query);
		System.out.println("Created Dictionary table");
		
		System.out.println("-------------------\nCreated tables!\n-------------------");
	}
	
	
	
	public static void populateTables(Statement stmt) throws SQLException {
		String query;
		System.out.println("Populating tables...\n-------------------");
		
		// populate User table
		query = "INSERT INTO User (username, password, email, forename, permissions)" +
				"VALUES ('eeu900', 'admin_password', 'eeu900@bangor.ac.uk', 'Natasha', 'admin'), " +
				"('johnsmith', 'password', 'john.smith@gmail.com', 'John', 'user'), " +
				"('janesmith', 'password', 'jane.smith@gmail.com', 'Jane', 'user')";
		stmt.executeUpdate(query);
		System.out.println("Populated User table");
		
		// populate English table
		query = "INSERT INTO English (word)" +
				"VALUES (''), " +
				"(''), " +
				"(''), " +
				"(''), " +
				"(''), " +
				"(''), " +
				"(''), " +
				"(''), " +
				"(''), " +
				"(''), " +
				"('')";
		stmt.executeUpdate(query);
		System.out.println("Populated English table");
		
		// populate Welsh table
		query = "INSERT INTO Welsh (word, gender)" +
				"VALUES ('', ''), " +
				"('', ''), " +
				"('', ''), " +
				"('', ''), " +
				"('', ''), " +
				"('', ''), " +
				"('', ''), " +
				"('', ''), " +
				"('', ''), " +
				"('', ''), " +
				"('', '')";
		stmt.executeUpdate(query);
		System.out.println("Populated Welsh table");
		
		// populate Dictionary table
		
		
		System.out.println("-------------------\nPopulated tables!\n-------------------");
	}
	
	
	
	/**
	 * Print a single table
	 * rs = executeQuery("SELECT * FROM Table");
	 * @param rs
	 * @throws SQLException
	 */
	public static void printTable(String table, Statement stmt) throws SQLException {
		String query = "SELECT * FROM " + table;
		System.out.println("Printing " + table.toUpperCase() + " table...\n-------------------");
		
		ResultSet rs = stmt.executeQuery(query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (rs.next()) {
		    for(int i = 1; i < columnsNumber; i++) {
		        System.out.print(rs.getString(i) + " | ");
		    }
		    System.out.println();
		}
		
		System.out.println("-------------------\nPrinted " + table + " table!\n-------------------");
	}
	
	
	
	
}
