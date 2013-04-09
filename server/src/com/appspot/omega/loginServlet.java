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
		PrintWriter out = response.getWriter();
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
		conn.close();
	}
	

}
