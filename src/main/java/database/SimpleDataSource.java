package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
   A simple data source for getting database connections.
*/
public class SimpleDataSource
{
   /**
      Initializes the data source.
      @param fileName the name of the property file that
      contains the database driver, url, username and password
    */
   public static void init(InputStream stream)
      throws IOException, ClassNotFoundException
   {
      Properties props = new Properties();
      props.load(stream);
      
      /*String driver = props.getProperty("jdbc.driver");
      url = props.getProperty("jdbc.url");
      username = props.getProperty("jdbc.user");
      password = props.getProperty("jdbc.pass");*/
      
      String driver = "com.mysql.cj.jdbc.Driver";
      url = "jdbc:mysql://localhost:3306/academi-cymraeg?serverTimezone=UTC";
      username = "root";
      password = "password123";
      
      /*System.out.println("driver: " + driver);
      System.out.println("url: " + url);
      System.out.println("username: " + username);
      System.out.print("password: " + password.charAt(0));
      for(int i = 0; i < password.length() - 2; i++) {
    	  System.out.print("*");
      }
      System.out.println(password.charAt(password.length() - 1) + "\n--------------");*/

      Class.forName(driver);
   }

   /**
      Gets a connection to the database.
      @return the database connection
   */
   public static Connection getConnection() throws SQLException
   {
      return DriverManager.getConnection(url, 
         username, password);
   }

   private static String url;
   private static String username;
   private static String password;
}