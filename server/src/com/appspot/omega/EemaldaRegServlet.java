package com.appspot.omega;

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

public class EemaldaRegServlet extends HttpServlet{
	private static final long serialVersionUID = -6353166191160800417L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Enumeration<String> result = request.getParameterNames();
		ArrayList<String> list= Collections.list(result);
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		SQLConnection conn = new SQLConnection();

		String eesn = request.getParameter("eesnimi");
		String peren = request.getParameter("perenimi");
		String command = "delete from kandidaat where eesnimi='" + eesn + "' and " +
				"perenimi='" + peren + "'";
		conn.execute(command);
		Gson json = new Gson();
		
		String output = json.toJson("success");
		out.print(output);


		conn.close();
	}


}

