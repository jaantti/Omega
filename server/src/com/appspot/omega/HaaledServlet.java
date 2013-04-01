package com.appspot.omega;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class HaaledServlet extends HttpServlet {
	private static final long serialVersionUID = -6353165191160800417L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Enumeration<String> result = request.getParameterNames();
		ArrayList<String> list= Collections.list(result);
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		SQLConnection conn = new SQLConnection();

		if (list.contains("koik")){
			String jsonOut;
			ResultSet rs = conn.execute("call koik_haaled()");
			Gson gs = new Gson();
			ArrayList<HaaledKandidaat> haaled = new ArrayList<HaaledKandidaat>();
			try {
				while (rs.next()) {
					haaled.add(new HaaledKandidaat(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
				}				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			jsonOut = gs.toJson(haaled);
			out.print(jsonOut);
		}

		if (list.contains("erakond") && !list.contains("piirkond")){
			String jsonOut;
			ResultSet rs = conn.execute("call appfog.kandidaadid_erakonnas_haaled('"+ request.getParameter("erakond")+"')");
			
			Gson gs = new Gson();
			ArrayList<HaaledErakond> haaled = new ArrayList<HaaledErakond>();
			try {
				while (rs.next()) {
					haaled.add(new HaaledErakond(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
				}				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			jsonOut = gs.toJson(haaled);
			out.print(jsonOut);
		}
		if (list.contains("piirkond") && !list.contains("erakond")){
			String jsonOut;
			ResultSet rs = conn.execute("call appfog.kandidaadid_piirkonnas_haaled('"+ request.getParameter("piirkond")+"')");
			Gson gs = new Gson();
			ArrayList<HaaledPiirkond> haaled = new ArrayList<HaaledPiirkond>();
			try {
				while (rs.next()) {
					haaled.add(new HaaledPiirkond(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
				}				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			jsonOut = gs.toJson(haaled);
			out.print(jsonOut);

		}
		if (list.contains("piirkond") && list.contains("erakond")){
			String jsonOut;
			ResultSet rs = conn.execute(haaled_erakond_piirkond(request.getParameter("erakond"), request.getParameter("piirkond")));
			Gson gs = new Gson();
			ArrayList<HaaledErakondPiirkond> haaled = new ArrayList<HaaledErakondPiirkond>();
			try {
				while (rs.next()) {
					haaled.add(new HaaledErakondPiirkond(rs.getString(1), rs.getString(2), rs.getInt(3)));
				}				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			jsonOut = gs.toJson(haaled);
			out.print(jsonOut);
		}
		if (list.contains("eesnimi")){
			String jsonOut;
			ResultSet rs = conn.execute("call kandidaadi_haaled('"+ 
			request.getParameter("eesnimi") +"','" +request.getParameter("perenimi") +"')");
			Gson gs = new Gson();
			ArrayList<HaaledKandidaat> haaled = new ArrayList<HaaledKandidaat>();
			try {
				while (rs.next()) {
					haaled.add(new HaaledKandidaat(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
				}				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			jsonOut = gs.toJson(haaled);
			out.print(jsonOut);
		}

		conn.close();
	}

	String haaled_erakond_piirkond(String erakond, String piirkond){
		String s = "select eesnimi, perenimi, count(kandidaat.id) as haaled " +
				"from appfog.kandidaat, appfog.isik, appfog.erakond, appfog.piirkond " +
				"where kandidaat.erakond = erakond.id and erakond.nimi = '" + erakond + "' " +
				"and isik.kandidaat = kandidaat.id and piirkond.id = kandidaat.piirkond " +
				"and piirkond.nimi = '"+ piirkond + "' " + "group by kandidaat.id order by haaled";
		
		return s;
	}
}
