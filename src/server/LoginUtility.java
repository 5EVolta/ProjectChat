package server;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class LoginUtility {
	
	private String dbServer = "jdbc:mysql://localhost/users_database"; //jdbc:mysql identifies the type of database
	private String dbUsername = "root";                                //users_database is the table we refer to
	private String dbPassword = "";
	
	public boolean login(String username, String password) throws SQLException, NoSuchAlgorithmException{
		
		boolean isRegistered = false;
		String hashedPassword = Hasher.hash(password);
		
		Connection con = DriverManager.getConnection(dbServer, dbUsername, dbPassword);
		PreparedStatement query = con.prepareStatement("SELECT * FROM users WHERE username = '"  + username + "' AND password = '" + hashedPassword + "'");
		ResultSet result = query.executeQuery();
		
		// Control the result table to see if the query was successful
		if(result.next())
			isRegistered = true;		
		else
			isRegistered = false;
		
		
		// I close all the connections or otherwise someone could get angry
		result.close();
		query.close();
		con.close();
		
		return isRegistered;
	}

}
