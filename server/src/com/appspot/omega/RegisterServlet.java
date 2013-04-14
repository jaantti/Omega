package com.appspot.omega;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet{

	private static final long serialVersionUID = -1695014573868136801L;

	public void doPost(HttpServletRequest request,
            HttpServletResponse response) {
			String firstName = request.getParameter("kandidaat_nimi");
			String candidateId = request.getParameter("kandidaat_id");
			String party = request.getParameter("kandidaat_erakond");
			String area = request.getParameter("kandidaat_piirkond");
			String address = request.getParameter("kandidaat_aadress");
			String phone = request.getParameter("kandidaat_telefon");
			String email = request.getParameter("kandidaat_epost");
			System.out.println(firstName + candidateId + party + area + address + phone + email);
			boolean proceed = false;

			if(firstName != null && candidateId != null && party != null && area != null && address != null && phone != null && email != null){
				if(firstName.length() > 0 && candidateId.length() > 0 && party.length() > 0 && area.length() > 0 && address.length() > 0 && phone.length() > 0 && email.length() > 0){
					proceed = true;
				}
			}
	}
}

