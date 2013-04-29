package com.appspot.omega;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.google.appengine.api.rdbms.AppEngineDriver;

public class SQLConnection {
	Connection conn = null;
	Statement stmt = null;
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
			Statement stmt = conn.createStatement();
			stmt.execute(query);
			rs = stmt.getResultSet();
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
			DriverManager.registerDriver(new AppEngineDriver());
			conn = DriverManager.getConnection(dbURL, username, password);
		}
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
