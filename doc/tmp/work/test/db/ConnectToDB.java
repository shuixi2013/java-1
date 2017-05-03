package work.test.db;

import java.sql.*;

public class ConnectToDB {
	private String bruker;
	private String passord;
	private Connection con;
	
	public ConnectToDB(String bruker, String passord) {
		this.bruker = bruker;
		this.passord = passord;
	}

	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException{
		con = DriverManager.getConnection(
				"jdbc:mysql://localhost/",
				bruker,
				passord);	
		
		System.out.println("This works!");
		return con;		
	}
}
