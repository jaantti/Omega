package com.appspot.omega;


import java.io.*;
import java.sql.*;
import java.util.*;

import com.google.gson.Gson;

public class JavaMySQL {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String dbURL = "jdbc:mysql://instance42133.db.xeround.com:7371/appfog";
            String username = "appfog";
            String password = "562325";

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(dbURL, username, password);
            
            stmt = conn.createStatement();

            if (stmt.execute("select * from piirkond order by id")) {
                rs = stmt.getResultSet();
            } else {
                System.err.println("select failed");
            }
            
            /*stmt = conn.createStatement();
            
            if (stmt.execute("insert into piirkond values (default, 'Haapsalu')")){
            	
            }
            else{
            	System.err.println("insert failed");
            }*/
            Gson gs = new Gson();
            Piirkond[] pk = new Piirkond[15];
            int i = 0;
            while (rs.next()) {
                pk[i] = new Piirkond(rs.getInt(1), rs.getString(2));
                //System.out.print(rs.getString(1) + " ");
                //System.out.println(rs.getString(2));
                //System.out.println(rs);
                i++;
            }
            String test = gs.toJson(pk);
            System.out.println(test);

        } catch (ClassNotFoundException ex) {
            System.err.println("Failed to load mysql driver");
            System.err.println(ex);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage()); 
            System.out.println("SQLState: " + ex.getSQLState()); 
            System.out.println("VendorError: " + ex.getErrorCode()); 
        } finally {
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
}