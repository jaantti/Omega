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

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = -3982250249783853347L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("query");
		
		Gson gs = new Gson();
		SQLConnection conn = new SQLConnection();
		ArrayList<String> suggestions = new ArrayList<String>();
		ArrayList<String> data = new ArrayList<String>();
		try {
			ResultSet rs = conn.execute("select id, eesnimi, perenimi from kandidaat where perenimi like '"+query+"%'");
			while (rs.next()) {
				suggestions.add(rs.getString(3)+", "+rs.getString(2));
				data.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SearchResult pk = new SearchResult(query, suggestions, data);
		String result = gs.toJson(pk);
		conn.close();
		
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
