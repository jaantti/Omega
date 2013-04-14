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

public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = -6353165191160800417L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		
		String google_id = request.getParameter("google_id");
		String login = request.getParameter("login");
		
		if (login != null && login.equals("yes")) {
			Gson gs = new Gson();
			GoogleId info = new GoogleId(google_id);
			String result = gs.toJson(info);
			
			response.setContentType("text/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(result);
		}
		else {
			response.sendRedirect("https://accounts.google.com/o/oauth2/auth?" +
					"scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile&" +
					"response_type=code&" +
					"state=TESTING&" +
					//"redirect_uri=http://www.omega-vl.appspot.com/AuthResponse&" +
					"redirect_uri=http://localhost:8888/AuthResponse&" +
					//"client_id=215240851949.apps.googleusercontent.com&" +
					"client_id=215240851949-4767gcfso0aj09bpdto19bgk9ujg6p2s.apps.googleusercontent.com&" +
					"approval_prompt=force"
					);
		}
		
		/*
		SQLConnection conn = new SQLConnection();

		String jsonOut;
		ResultSet rs = conn.execute("call isik_login('" + request.getParameter("nimi") + "')");
		Gson gs = new Gson();
		ArrayList<Isik> haaled = new ArrayList<Isik>();
		try {
			while (rs.next()) {
				haaled.add(new Isik(rs.getLong(1), rs.getString(2),
						rs.getInt(3), rs.getString(4), rs.getString(5)));
			}				
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		jsonOut = gs.toJson(haaled);
		out.print(jsonOut);
		conn.close();*/
	}
	

}
