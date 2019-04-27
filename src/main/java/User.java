import java.io.Serializable;

public class User implements Serializable
{
	// variable declaration
	private String username;
	private String email;
	private String forename;
	private String permissions;
	
	public User() {
		// empty constructor
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setForename(String forename) {
		this.forename = forename;
	}
	
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
	public void setNewPassword() {
		// generate new password
		String newPass = "new_password";
		// update database
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getForename() {
		return forename;
	}
	
	public String getPermissions() {
		return permissions;
	}
	
	public void resetUser() {
		username = null;
		email = null;
		forename = null;
		permissions = null;
	}
	
	@Override
	public String toString() {
		String cName = this.getClass().getSimpleName();
		String attr = "username=" + username + ", email=" + email +
				", forename="  + forename + ", permissions=" + permissions;
		return cName + "[" + attr + "]";
	}
}
