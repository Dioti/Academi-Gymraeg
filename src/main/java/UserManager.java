import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import database.SimpleDataSource;

// creates an instance of the user class based on current username
public class UserManager
{
	
	User u;
	
	public UserManager(String username) {	
		InputStream stream = UserManager.class.getResourceAsStream("/database.properties");
		try {
			SimpleDataSource.init(stream);
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Error connecting to database.");
			//e.printStackTrace();
		}
		
		u = new User();
		u.setUsername(username);
		updateBean();
	}
	
	private void updateBean() {
		try {
			Connection conn = SimpleDataSource.getConnection();
			try {
				String query = "SELECT email, forename, permissions FROM User WHERE username = ?";
				PreparedStatement pst = conn.prepareStatement(query);
				pst.setString(1, u.getUsername());
				ResultSet rs = pst.executeQuery();
				while(rs.next()) {
					u.setEmail(rs.getString(1));
					u.setForename(rs.getString(2));
					u.setPermissions(rs.getString(3));
					System.out.println(u.toString());
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Error updating user bean.");
			//e.printStackTrace();
		}
	}
	
	public User getUser() {
		return u;
	}
}
