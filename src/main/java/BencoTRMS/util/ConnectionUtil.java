package BencoTRMS.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private Connection con;
	
	private final String url = "";
	
	private final String username = "";
	
	private final String password = "";
	
	public Connection getConnection() throws SQLException {
		
		con = DriverManager.getConnection(url, username, password);
		
		return con;
	}
}
