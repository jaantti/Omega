package com.appspot.omega;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class CandidateServlet extends HttpServlet{
	private static final long serialVersionUID = -2033546828432009469L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String query;
		if (id == null) {
			query = "call kandidaat_by_id("+ 12 +")";
		}
		else{query = "call kandidaat_by_id("+ id +")";}
		
		Gson gs = new Gson();
		SQLConnection conn = new SQLConnection();
		ArrayList<Kandidaat> pk = new ArrayList<Kandidaat>();
		try {
			ResultSet rs = conn.execute(query);
			while (rs.next()) {
				pk.add(new Kandidaat(rs.getInt(1), rs.getString(2) + " " + rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = gs.toJson(pk);
		conn.close();
		
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
