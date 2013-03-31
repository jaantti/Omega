package com.appspot.omega;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class PartyServlet extends HttpServlet {
	private static final long serialVersionUID = -6353165191160800417L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String piirkond_id = request.getParameter("piirkond_id");
		
		Gson gs = new Gson();
		SQLConnection conn = new SQLConnection();
		ArrayList<KandidaatByRegion> pk = new ArrayList<KandidaatByRegion>();
		try {
			ResultSet rs = conn.execute("call kandidaadid_piirkonnas("+ piirkond_id +")");
			while (rs.next()) {
				pk.add(new KandidaatByRegion(rs.getInt(1), rs.getString(2) + " " + rs.getString(3), rs.getInt(4), rs.getString(5)));
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
