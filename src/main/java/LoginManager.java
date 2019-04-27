import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import database.SimpleDataSource;

public class LoginManager
{
	public static boolean validate(String user, String pass) {
		boolean valid = false;
		
		InputStream stream = LoginManager.class.getResourceAsStream("/database.properties");
		try {
			SimpleDataSource.init(stream);
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Error connecting to database.");
			//e.printStackTrace();
		}
		
		try {
			Connection conn = SimpleDataSource.getConnection();
			try {
				String query = "SELECT password FROM User WHERE username = ?";
				PreparedStatement pst = conn.prepareStatement(query);
				pst.setString(1, user);
				ResultSet rs = pst.executeQuery();
				while(rs.next()) {
					if(pass.equals(rs.getString(1))) {
						valid = true;
					}
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Error validating login details.");
			//e.printStackTrace();
		}
		return valid;
	}
}
