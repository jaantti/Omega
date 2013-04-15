package com.appspot.omega;

import java.io.IOException;
import java.io.PrintWriter;
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
			response.sendRedirect("/web/index.html#loginredirect%%"+google_id);
		}
		else {
			response.sendRedirect("https://accounts.google.com/o/oauth2/auth?" +
					"scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile&" +
					"response_type=code&" +
					"state=TESTING&" +
					//"redirect_uri=http://www.omega-vl.appspot.com/AuthResponse&" +
					"redirect_uri=http://localhost:8888/AuthResponse&" +
					//"client_id=215240851949.apps.googleusercontent.com&" +
					"client_id=215240851949-4767gcfso0aj09bpdto19bgk9ujg6p2s.apps.googleusercontent.com&" 
					//+"approval_prompt=force"
					);
		}
	}
}
