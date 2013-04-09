package com.appspot.omega;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.appidentity.AppIdentityService;
import com.google.appengine.api.appidentity.AppIdentityServiceFactory;

public class AuthServlet extends HttpServlet{
	private static final long serialVersionUID = -9059699120089647216L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> scopes = new ArrayList<String>();
		scopes.add("https://www.googleapis.com/auth/urlshortener");
		AppIdentityService appIdentity = AppIdentityServiceFactory.getAppIdentityService();
		AppIdentityService.GetAccessTokenResult accessToken = appIdentity.getAccessToken(scopes);
	}
}