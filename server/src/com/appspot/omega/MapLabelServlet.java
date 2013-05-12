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

public class MapLabelServlet extends HttpServlet {
	private static final long serialVersionUID = -8777648466822874611L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		
		SQLConnection conn = new SQLConnection();
		ResultSet rs = conn.execute("select count(*) as arv, " +
				"erakond.id, " +
				"erakond.nimi, " +
				"piirkond.* " +
				"from piirkond, isik, erakond, kandidaat " +
				"where piirkond.id = kandidaat.piirkond and " +
				"erakond.id = kandidaat.erakond and " +
				"kandidaat.id = isik.kandidaat " +
				"group by piirkond.id, erakond.id");
		
		Gson gs = new Gson();
		ArrayList<MapLabel> pk = new ArrayList<MapLabel>();
		try {
			int arv;
			int piirkond_id;
			int[] count = new int[16];
			while (rs.next()) {
				arv = rs.getInt("arv");
				piirkond_id = rs.getInt(4);
				count[piirkond_id] += arv;
				pk.add(new MapLabel(arv, rs.getInt(2), rs.getString(3), piirkond_id, rs.getString(5), rs.getString("loc")));
			}
			for (MapLabel i:pk){
				i.protsent = String.valueOf(Math.round((((double) i.arv)/count[i.piirkond_id])*100)) + "%";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = gs.toJson(pk);
		
		conn.close();
		response.setDateHeader("Expires", System.currentTimeMillis() + 24*60*60*1000);
		response.addDateHeader("Last-Modified", System.currentTimeMillis());
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
	    out.print(result);
	}
}
