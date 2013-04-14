package com.appspot.omega;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthServlet extends HttpServlet{
	private static final long serialVersionUID = -9059699120089647216L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String google_id = request.getParameter("google_id");
		
		SQLConnection conn = new SQLConnection();
		ResultSet rs = conn.execute("select * from isik where google_id = '"+ google_id +"';");
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
			System.out.println("log in newfag");
		}
		else {
			System.out.println("vana kala");
		}
	}
}