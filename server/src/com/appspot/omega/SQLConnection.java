package com.appspot.omega;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.Gson;

public class SQLConnection {
	Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    static private String dbURL = "jdbc:mysql://instance42133.db.xeround.com:7371/appfog";
    static private String username = "appfog";
    static private String password = "562325";

    public SQLConnection() {
		super();
		create();
	}

    public String execute(String query){
    	String result = null;
    	try {
			if (stmt.execute("select * from piirkond order by nimi")) {
			    rs = stmt.getResultSet();
			} else {
			    System.err.println("select failed");
			}
			Gson gs = new Gson();
            Piirkond[] pk = new Piirkond[15];
            int i = 0;
            while (rs.next()) {
                pk[i] = new Piirkond(rs.getInt(1), rs.getString(2));
                i++;
            }
            result = gs.toJson(pk);
            
		} 
    	catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); 
            System.out.println("SQLState: " + ex.getSQLState()); 
            System.out.println("VendorError: " + ex.getErrorCode());
		}
    	return result;
    }
    
	public Connection create(){
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);
            stmt = conn.createStatement();
    	}
    	catch (ClassNotFoundException ex) {
            System.err.println("Failed to load mysql driver");
            System.err.println(ex);
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
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) { /* ignore */ }
            stmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) { /* ignore */ }
            conn = null;
        }
    }
}
