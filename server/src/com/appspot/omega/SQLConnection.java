package com.appspot.omega;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.google.appengine.api.rdbms.AppEngineDriver;

public class SQLConnection {
	Connection conn = null;
	ResultSet rs = null;

	static private String dbURL = "jdbc:google:rdbms://omega-vl-sql:omega-vl/appfog";
	static private String username = "appfog";
	static private String password = "562325";

	public SQLConnection() {
		super();
		create();
	}

	public ResultSet execute(String query){
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
		} 
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			System.out.println("SQLState: " + ex.getSQLState()); 
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return rs;
	}

	public Connection create(){
		try{
			//Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new AppEngineDriver());
			conn = DriverManager.getConnection(dbURL, username, password);
		}
		/*catch (ClassNotFoundException ex) {
            System.err.println("Failed to load mysql driver");
            System.err.println(ex);
    	}*/
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
			System.out.println("SQLState: " + ex.getSQLState()); 
			System.out.println("VendorError: " + ex.getErrorCode()); 
		}
		return conn;
	}
	public void close(){
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) { /* ignore */ }
			rs = null;
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) { /* ignore */ }
			conn = null;
		}
	}
}
