package groupproject;

import java.sql.*;
import java.io.*;

public class SQLTester {
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

		// This is an amended version of Horstmann’s test program.
		//SimpleDataSource.init("database.properties");
		InputStream stream = SQLTester.class.getResourceAsStream("/database.properties");
		SimpleDataSource.init(stream);
		
		Connection conn = SimpleDataSource.getConnection();
		System.out.println("Connected!");
		
		Statement st = conn.createStatement();
		
		try {
			printTable("User", st);
		} finally {
			st.close();
			conn.close();
		}
		
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