package connections;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	 private static final String URL = "jdbc:mysql://localhost:3306/bankerDB"; // Replace with your DB URL
	    private static final String USER = "root"; // Replace with your DB username
	    private static final String PASSWORD = "67896789Bhargav@"; // Replace with your DB password

	    public static Connection getConnection() throws SQLException, ClassNotFoundException {
	        Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure you have the correct MySQL driver
	        return DriverManager.getConnection(URL, USER, PASSWORD);
	    }
}