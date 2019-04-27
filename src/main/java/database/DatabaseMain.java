package database;


import java.sql.*;
import java.io.*;

public class DatabaseMain
{
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

		DatabaseManager dm = new DatabaseManager();
		DatabaseTextInterface dti = new DatabaseTextInterface(dm);
		dti.start();
		
	}
}