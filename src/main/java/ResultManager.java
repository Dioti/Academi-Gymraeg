import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;

public class ResultManager {
	
	
	
	/**
	 * Constructor for ResultManager
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ResultManager() throws ClassNotFoundException, IOException {
		InputStream stream = DatabaseManager.class.getResourceAsStream("/database.properties");
		SimpleDataSource.init(stream);
	}
	
	
	/**
	 * Adds a user's result into the Results table
	 * @param user_id
	 * @param test_id
	 * @param date
	 * @param score
	 */
	public void addResult(int user_id, String test_id, Date date, int score) {
		try {
			Connection conn = SimpleDataSource.getConnection();
			String query = "";
			PreparedStatement pst;
			try {
				// create query
				query = "INSERT INTO Result VALUES (?,?,?,?)";
				pst = conn.prepareStatement(query);
				pst.setInt(1, user_id);
				pst.setString(2, test_id);
				pst.setDate(3, new java.sql.Date(date.getDate()));
				pst.setInt(4, score);
				pst.executeUpdate();
				System.out.println("Success!");
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Gets all test results of specified student
	 * Returns results as an arraylist
	 * @param student_id
	 * @return
	 */
	public LinkedList<String[]> getResults(int user_id) {
		LinkedList<String[]> results = new LinkedList();
		String[] row = new String[3];
		try {
			Connection conn = SimpleDataSource.getConnection();
			String query = "";
			PreparedStatement pst;
			try {
				query = "SELECT test_id, date, score FROM Results WHERE user_id = ?";
				pst = conn.prepareStatement(query);
				pst.setInt(1, user_id);
				ResultSet rs = pst.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				while(rs.next()) {
					System.out.println();
					for(int i = 1; i < rsmd.getColumnCount() + 1; i++) {
						row[i-1] = rs.getString(i);
					}
					results.add(row);
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}

}

