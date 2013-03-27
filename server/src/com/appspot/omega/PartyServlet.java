package com.appspot.omega;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PartyServlet extends HttpServlet {
	private static final long serialVersionUID = -6353165191160800417L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String piirkond_id = request.getParameter("piirkond_id");
		
		SQLConnection conn = new SQLConnection();
		String result = conn.execute("call kandidaadid_piirkonnas("+ piirkond_id +")");
		conn.close();
		
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
