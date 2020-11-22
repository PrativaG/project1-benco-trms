package Benco.TRMS.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private Connection con;
	
	private final String url = "jdbc:postgresql://localhost:5432/postgres?";
	
	private final String username = "postgres";
	
	private final String password = "Panday90";
	
	public Connection getConnection() throws SQLException {
		
		con = DriverManager.getConnection(url, username, password);
		
		return con;
	}
}
