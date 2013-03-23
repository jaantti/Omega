package com.appspot.omega;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloWorld extends HttpServlet {
 	private static final long serialVersionUID = 2047315717134249734L;

 	public void doGet(HttpServletRequest request,
 			HttpServletResponse response)
 			throws ServletException, IOException {
 			response.setContentType("text/html");
 			PrintWriter out = response.getWriter();
 			out.println
 			(
 			"<!DOCTYPE html>\n" +
 			"<html>\n" +
 			"<head><title>A Test Servlet</title></head>\n" +
 			"<body bgcolor=\"#fdf5e6\">\n" +
 			"<h1>Test</h1>\n" +
 			"<p>Simple servlet for testing.</p>\n" +
 			"</body></html>"
 			);
 			}
}