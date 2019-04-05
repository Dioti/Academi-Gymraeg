import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager
{
	/**
	 * Constructor for DatabaseManager class
	 * Initialises data source using database.properties file
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public DatabaseManager() throws ClassNotFoundException, IOException {
		InputStream stream = DatabaseManager.class.getResourceAsStream("/database.properties");
		SimpleDataSource.init(stream);
	}
	
	
	
	/**
	 * Add an entry into a given table
	 * @param table
	 * @param id
	 */
	public void addEntry(String table, String[] entry) {
		try {
			Connection conn = SimpleDataSource.getConnection();
			String query = "";
			PreparedStatement pst;
			try {
				// create query
				query = "INSERT INTO " + table + " VALUES (";
				String values = "";
				for(int i = 0; i < entry.length; i++) { // create values string
					values = values + "?";
					if(i < entry.length - 1) {
						values = values + ", ";
					}
				}
				query = query + values + ")";
				System.out.println(query);
				
				// prepare statement
				pst = conn.prepareStatement(query);
				for(int i = 1; i < entry.length + 1; i++) {
					pst.setString(i, entry[i-1]);
				}
				
				// execute query
				pst.executeUpdate();
				
				// print details about entry that's been added
				System.out.print("\nAdded entry[");
				for(int i = 0; i < entry.length; i++) {
					System.out.print(entry[i]);
					if(i < entry.length - 1) {
						System.out.print(", ");
					}
				}
				System.out.println("]\n");
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("\nError adding entry.\n");
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Removes an entry from the database
	 * @param table
	 * @param id
	 */
	public void removeEntry(String table, String id) {
		if(!id.equals("")) {
			try {
				Connection conn = SimpleDataSource.getConnection();
				String query = "";
				PreparedStatement pst;
				ResultSet rs;
				try {
					// get details about the entry being removed
					String[] cols = getColumns(table);
					query = "SELECT * FROM " + table + " WHERE " + cols[0] + " = ?";
					pst = conn.prepareStatement(query);
					pst.setString(1, id);
					rs = pst.executeQuery();
					String[] entry = new String[cols.length];
					while(rs.next()) {
						for(int i = 1; i < cols.length + 1; i++) {
							entry[i-1] = rs.getString(i);
						}
					}
					
					// remove entry
					query = "DELETE FROM " + table + " WHERE " + cols[0] + " = ?";
					pst = conn.prepareStatement(query);
					pst.setString(1, id);
					pst.executeUpdate();
					
					// print details about entry that's been removed
					System.out.print("\nRemoved entry[");
					for(int i = 0; i < entry.length; i++) {
						System.out.print(entry[i]);
						if(i < entry.length - 1) {
							System.out.print(", ");
						}
					}
					System.out.println("]\n");
				} finally {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("\nError deleting entry.\n");
			}
		}
	}
	
	
	
	/**
	 * Updates an entry given the table and the entry id
	 * @param table
	 * @param id
	 * @param update
	 */
	public void updateEntry(String table, String id, String[] update) {
		try {
			Connection conn = SimpleDataSource.getConnection();
			String query = "";
			PreparedStatement pst;
			ResultSet rs;
			try {
				// get details about the entry being updated
				String[] cols = getColumns(table);
				query = "SELECT * FROM " + table + " WHERE " + cols[0] + " = ?";
				pst = conn.prepareStatement(query);
				pst.setString(1, id);
				rs = pst.executeQuery();
				String[] old = new String[cols.length];
				while(rs.next()) {
					for(int i = 1; i < cols.length + 1; i++) {
						old[i-1] = rs.getString(i);
					}
				}
				
				// update entry and print changed entry
				System.out.println("\nUpdated " + cols[0] + " " + id + "\n------------------");
				for(int i = 0; i < update.length; i++) {
					if(!update[i].equals("")) {
						query = "UPDATE " + table + " SET " + cols[i+1] + " = ? WHERE " + cols[0] + " = ?";
						pst = conn.prepareStatement(query);
						pst.setString(1, update[i]);
						pst.setString(2, id);
						pst.executeUpdate();
						System.out.println(cols[i+1] + ": " + old[i+1] + " -> " + update[i]);
					}
				}
				System.out.println();
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("\nError updating entry.\n");
			e.getStackTrace();
		}
	}
	
	
	
	/**
	 * Gets the column names of a given table
	 * Returns column names inside String array
	 * @param table
	 * @return
	 */
	public String[] getColumns(String table) {
		String[] columns = null;
		try {
			Connection conn = SimpleDataSource.getConnection();
			String query = "";
			PreparedStatement pst;
			ResultSet rs;
			try {
				query = "SELECT * FROM " + table;
				pst = conn.prepareStatement(query);
				rs = pst.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				columns = new String[rsmd.getColumnCount()];
				for(int i = 1; i < rsmd.getColumnCount() + 1; i++) {
					columns[i-1] = rsmd.getColumnName(i);
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("\nError getting columns.\n");
		}
		return columns;
	}
	
	
	
	/**
	 * Prints a specified table
	 * @param rs
	 * @throws SQLException
	 */
	public void printTable(String table) {
		try {
			Connection conn = SimpleDataSource.getConnection();
			Statement st = conn.createStatement();
			try {
				String query = "SELECT * FROM " + table;
				System.out.println(table + "\n=============");
				
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData rsmd = rs.getMetaData();
				for(int i = 1; i < rsmd.getColumnCount() + 1; i++) {
					System.out.printf("%-35s", rsmd.getColumnName(i));
				}
				System.out.println();
				for(int i = 1; i < rsmd.getColumnCount() + 1; i++) {
					System.out.printf("%-35s", "--------");
				}
				while(rs.next()) {
					System.out.println();
					for(int i = 1; i < rsmd.getColumnCount() + 1; i++) {
						System.out.printf("%-35s", rs.getString(i));
					}
				}
				System.out.println("\n");
			} finally {
				st.close();
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Error printing table.\n");
		}
	}
	
	
	
	/**
	 * Carries out a report
	 * @param reportNo
	 * @param id
	 */
	public void doReport(int reportNo, String id) {
		switch(reportNo) {
			case 1: // modules taught by [staff_id]
				try {
					Connection conn = SimpleDataSource.getConnection();
					String query = "";
					PreparedStatement pst;
					ResultSet rs;
					try {
						// execute query
						query = "SELECT Module.module_id, Module.module_name FROM Module INNER JOIN Teaches ON Teaches.module_id=Module.module_id WHERE Teaches.staff_id = ?";
						pst = conn.prepareStatement(query);
						pst.setString(1, id);
						rs = pst.executeQuery();
						
						// print results
						System.out.println("\nModules taught by staff_id " + id + "\n=============");
						
						ResultSetMetaData rsmd = rs.getMetaData();
						for(int i = 1; i < rsmd.getColumnCount() + 1; i++) {
							System.out.printf("%-35s", rsmd.getColumnName(i));
						}
						System.out.println();
						for(int i = 1; i < rsmd.getColumnCount() + 1; i++) {
							System.out.printf("%-35s", "--------");
						}
						while(rs.next()) {
							System.out.println();
							for(int i = 1; i < rsmd.getColumnCount() + 1; i++) {
								System.out.printf("%-35s", rs.getString(i));
							}
						}
						System.out.println("\n");
					} finally {
						conn.close();
					}
				} catch (SQLException e) {
					System.out.println("\nError carrying out report.\n");
				}
				break;
			case 2: // students registered on [module_id]
				try {
					Connection conn = SimpleDataSource.getConnection();
					String query = "";
					PreparedStatement pst;
					ResultSet rs;
					try {
						// execute query
						query = "SELECT Student.student_id, Student.student_name FROM Student INNER JOIN Registered ON Registered.student_id=Student.student_id WHERE Registered.module_id = ?";
						pst = conn.prepareStatement(query);
						pst.setString(1, id);
						rs = pst.executeQuery();
						
						// print results
						System.out.println("\nStudents registered on module_id " + id + "\n=============");
						
						ResultSetMetaData rsmd = rs.getMetaData();
						for(int i = 1; i < rsmd.getColumnCount() + 1; i++) {
							System.out.printf("%-35s", rsmd.getColumnName(i));
						}
						System.out.println();
						for(int i = 1; i < rsmd.getColumnCount() + 1; i++) {
							System.out.printf("%-35s", "--------");
						}
						while(rs.next()) {
							System.out.println();
							for(int i = 1; i < rsmd.getColumnCount() + 1; i++) {
								System.out.printf("%-35s", rs.getString(i));
							}
						}
						System.out.println("\n");
					} finally {
						conn.close();
					}
				} catch (SQLException e) {
					System.out.println("\nError carrying out report.\n");
				}
				break;
			case 3: // staff who teach [student_id]
				try {
					Connection conn = SimpleDataSource.getConnection();
					String query = "";
					PreparedStatement pst;
					ResultSet rs;
					try {
						// execute query
						query = "SELECT Teaches.staff_id, Staff.staff_name, Teaches.module_id FROM Teaches INNER JOIN Staff ON Staff.staff_id=Teaches.staff_id INNER JOIN Registered ON Registered.module_id=Teaches.module_id WHERE Registered.student_id = ?";
						pst = conn.prepareStatement(query);
						pst.setString(1, id);
						rs = pst.executeQuery();
						
						// print results
						System.out.println("\nStaff who teach student_id " + id + "\n=============");
						
						ResultSetMetaData rsmd = rs.getMetaData();
						for(int i = 1; i < rsmd.getColumnCount() + 1; i++) {
							System.out.printf("%-35s", rsmd.getColumnName(i));
						}
						System.out.println();
						for(int i = 1; i < rsmd.getColumnCount() + 1; i++) {
							System.out.printf("%-35s", "--------");
						}
						while(rs.next()) {
							System.out.println();
							for(int i = 1; i < rsmd.getColumnCount() + 1; i++) {
								System.out.printf("%-35s", rs.getString(i));
							}
						}
						System.out.println("\n");
					} finally {
						conn.close();
					}
				} catch (SQLException e) {
					System.out.println("\nError carrying out report.\n");
				}
				break;
			case 4: // staff who teach more than one module
				try {
					Connection conn = SimpleDataSource.getConnection();
					String query = "";
					PreparedStatement pst;
					ResultSet rs;
					try {
						// execute query
						query = "SELECT DISTINCT Teaches.staff_id, Staff.staff_name FROM Teaches INNER JOIN Staff ON Staff.staff_id=Teaches.staff_id GROUP BY Teaches.staff_id HAVING COUNT(*) > 1";
						pst = conn.prepareStatement(query);
						rs = pst.executeQuery();
						
						// print results
						System.out.println("\nStaff who teach more than one module\n=============");
						
						ResultSetMetaData rsmd = rs.getMetaData();
						for(int i = 1; i < rsmd.getColumnCount() + 1; i++) {
							System.out.printf("%-35s", rsmd.getColumnName(i));
						}
						System.out.println();
						for(int i = 1; i < rsmd.getColumnCount() + 1; i++) {
							System.out.printf("%-35s", "--------");
						}
						while(rs.next()) {
							System.out.println();
							for(int i = 1; i < rsmd.getColumnCount() + 1; i++) {
								System.out.printf("%-35s", rs.getString(i));
							}
						}
						System.out.println("\n");
					} finally {
						conn.close();
					}
				} catch (SQLException e) {
					System.out.println("\nError carrying out report.\n");
				}
				break;
			default:
				System.out.println("\nUnknown report\n");
				break;
		}
	}
}
