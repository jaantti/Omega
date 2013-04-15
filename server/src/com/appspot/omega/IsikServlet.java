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

public class IsikServlet extends HttpServlet{
	private static final long serialVersionUID = -2747497862462389331L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String google_id = request.getParameter("google_id");
		String query;
		query = "select isik.nimi, isik.google_id, isik.email, isik.kandidaat, kandidaat.eesnimi, kandidaat.perenimi " +
				"from appfog.isik, appfog.kandidaat " +
				"where isik.google_id = '"+ google_id +"' and kandidaat.id = isik.kandidaat;";

		Gson gs = new Gson();
		SQLConnection conn = new SQLConnection();
		ArrayList<Isik> pk = new ArrayList<Isik>();
		try {
			ResultSet rs = conn.execute(query);
			while (rs.next()) {
				String[] isik_name = rs.getString("nimi").split("\\s+");
				pk.add(new Isik(rs.getString("google_id"), isik_name[0], isik_name[1], rs.getString("email"), rs.getInt("kandidaat"), rs.getString("eesnimi"), rs.getString("perenimi")));
			}
			if (pk.isEmpty()) {
				rs = conn.execute("select isik.nimi, isik.google_id, isik.email, isik.kandidaat " +
						"from appfog.isik " +
						"where isik.google_id = '"+ google_id +"';");
				while (rs.next()) {
					String[] isik_name = rs.getString("nimi").split("\\s+");
					pk.add(new Isik(rs.getString("google_id"), isik_name[0], isik_name[1], rs.getString("email")));
				}
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
