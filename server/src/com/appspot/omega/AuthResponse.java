package com.appspot.omega;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class AuthResponse extends HttpServlet{
	private static final long serialVersionUID = -4547145543576335113L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		if (code != null) {
			
	        try {
	        	
	            URL url = new URL("https://accounts.google.com/o/oauth2/token");
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
	            connection.setDoOutput(true);
	            connection.setRequestMethod("POST");

	            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
	            
	            code = URLEncoder.encode(code, "UTF-8");
	            String client_id = URLEncoder.encode("215240851949-4767gcfso0aj09bpdto19bgk9ujg6p2s.apps.googleusercontent.com", "UTF-8");
	            String client_secret = URLEncoder.encode("pZMM5lk5D1KlrIy-gsndOds3", "UTF-8");
	            String redirect_uri = URLEncoder.encode("http://localhost:8888/AuthResponse", "UTF-8");
	            String grant_type = URLEncoder.encode("authorization_code", "UTF-8");
	            
	            writer.write("code=" + code +
	            		"&client_id=" + client_id +
	            		"&client_secret=" + client_secret +
	            		"&redirect_uri=" + redirect_uri +
	            		"&grant_type=" + grant_type
	            		);
	            writer.close();
	    
	            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
	                BufferedInputStream reader = new BufferedInputStream(connection.getInputStream());
	                String token_response = "";
	                int smth;
	                while ((smth = reader.read()) != -1){
	                	token_response += (char)smth;
	                }
	                Gson gs = new Gson();
	                LoginToken token = gs.fromJson(token_response, LoginToken.class);
	                connection.disconnect();
	                
	                url = new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + token.access_token);
	                BufferedReader reader2 = new BufferedReader(new InputStreamReader(url.openStream()));
	                String line;
	                String info = "";

	                while ((line = reader2.readLine()) != null) {
		                info += line;
	                }
	                Isik isik = gs.fromJson(info, Isik.class);
	                reader.close();
	                
	                SQLConnection conn = new SQLConnection();
	        		ResultSet rs = conn.execute("select * from isik where google_id = '"+ isik.id +"';");
	        		String check = null;
	        		try {
	        			while (rs.next()) {
	        				check = rs.getString("google_id");
	        			}
	        		} catch (SQLException e) {
	        			// TODO Auto-generated catch block
	        			e.printStackTrace();
	        		}
	        		if (check == null) {
	        			conn.execute("insert into isik (google_id, nimi, email, kandidaat) values " +
	        					"('" + isik.id + "', '" + isik.given_name + " " + isik.family_name + "'," +
	        							" '" + isik.email + "', " + "-1);");
	        		}
	        		conn.close();
	                response.sendRedirect("http://localhost:8888/LoginServlet?google_id=" + isik.id + "&login=yes");
	                
	            } else {
	            	System.out.println("Some Error ");
	                // Server returned HTTP error code.
	            }
	        } catch (MalformedURLException e) {
	        	e.printStackTrace();
	            // ...
	        } catch (IOException e) {
	        	e.printStackTrace();
	            // ...
	        }
		}
		else {
			response.sendRedirect("http://localhost:8888/LoginServlet?google_id=-1&login=yes");
		}
	}
}