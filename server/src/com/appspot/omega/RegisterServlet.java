package com.appspot.omega;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet{

	private static final long serialVersionUID = -1695014573868136801L;

	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		
		String firstName = request.getParameter("kandidaat_eesnimi").trim();
		String lastName = request.getParameter("kandidaat_perenimi");
		String candidateId = request.getParameter("kandidaat_id");
		String party = request.getParameter("kandidaat_erakond");
		String area = request.getParameter("kandidaat_piirkond");
		String address = request.getParameter("kandidaat_aadress");
		String phone = request.getParameter("kandidaat_telefon");
		String email = request.getParameter("kandidaat_epost");

		boolean proceed = false;
		SQLConnection conn = new SQLConnection();
		PreparedStatement ps = null;
		Statement stmt = null;

		if(firstName != null && lastName != null && candidateId != null && party != null && area != null && address != null && phone != null && email != null){
			String[] name = firstName.split("=");
			firstName = name[1];
			String[] name2 = lastName.split("=");
			lastName = name2[1];		
			String[] ID = candidateId.split("=");
			candidateId = ID[1];
			String[] Partys = party.split("=");
			party = Partys[1];
			
			String[] Areas = area.split("=");
			area = Areas[1];
			String[] Aadres = address.split("=");
			address = Aadres[1];
			String[] Phones = phone.split("=");
			phone = Phones[1];
			String [] Epost = email.split("=");
			email = Epost[1];
			System.out.println(firstName + lastName + candidateId + party + area + address + phone + email);
			if(firstName.length() > 0 && lastName.length() > 0 && candidateId.length() > 0 && party.length() > 0 && area.length() > 0 && address.length() > 0 && phone.length() > 0 && email.length() > 0){
				proceed = true;
			}
		}
		try {
			String sql = "INSERT INTO kandidaat(isikukood, eesnimi, perenimi, erakond, piirkond, aadress, telefon, epost, pilt) VALUES (?,?,?,?,?,?,?,?,?)";
			ps = conn.conn.prepareStatement(sql);
			stmt = conn.conn.createStatement();
			if (proceed){
				ps.setString(1, candidateId);
				ps.setString(2, firstName);
				ps.setString(3, lastName);
				ps.setString(4, party);
				ps.setString(5, area);
				ps.setString(6, address);
				ps.setString(7, phone);
				ps.setString(8, email);
				ps.setString(9, "-");
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}

	}
}

