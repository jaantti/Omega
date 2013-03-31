package com.appspot.omega;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HaaledServlet extends HttpServlet {
	private static final long serialVersionUID = -6353165191160800417L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Enumeration<String> result = request.getParameterNames();
		ArrayList<String> list= Collections.list(result);
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		SQLConnection conn = new SQLConnection();
		
		System.out.println(list);
		if (list.contains("koik")){
			out.print(conn.executeHaaled("koik"));
		}
		
		if (list.contains("nimi")){
			if (list.contains("erakond")){
				
			}
			if (list.contains("piirkond")){
				
			}
			if (list.contains("piirkond") && list.contains("erakond")){
				
			}
		}
		System.out.println(list);
		System.out.println(list.contains("asd"));
		
		conn.close();
	}
}
