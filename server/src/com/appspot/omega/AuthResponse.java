package com.appspot.omega;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthResponse extends HttpServlet{
	private static final long serialVersionUID = -4547145543576335113L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code =  request.getParameter("code");
		String access_token = request.getParameter("access_token");
		if (code != null) {
			System.out.println(code);
			
			/*response.sendRedirect("https://accounts.google.com/o/oauth2/token?" +
					"code=" + code +
            		"&client_id=215240851949-4767gcfso0aj09bpdto19bgk9ujg6p2s.apps.googleusercontent.com" +
            		"&client_secret=pZMM5lk5D1KlrIy-gsndOds3" +
            		"&redirect_uri=http://localhost:8888/AuthResponse" +
            		"&grant_type=authorization_code");*/
			
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
	                System.out.println("POST success");
	                BufferedInputStream reader = new BufferedInputStream(connection.getInputStream());
	                String response2 = "";
	                int smth;
	                while ((smth = reader.read()) != -1){
	                	response2 += (char)smth;
	                }
	                System.out.println(response2);
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
	}
}