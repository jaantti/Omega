package com.appspot.omega;

public class Isik {
	String id;
	String given_name;
	String family_name;
	String email;
	int kandidaat;
	String k_eesnimi;
	String k_perenimi;
	public Isik(){
		super();
		this.kandidaat = -1;
		this.k_eesnimi = null;
		this.k_perenimi = null;
	}
	public Isik(String id, String given_name, String family_name,
			String email) {
		super();
		this.id = id;
		this.given_name = given_name;
		this.family_name = family_name;
		this.email = email;
		this.kandidaat = -1;
		this.k_eesnimi = null;
		this.k_perenimi = null;
	}
	public Isik(String id, String given_name, String family_name,
			String email, int kandidaat, String k_eesnimi, String k_perenimi) {
		super();
		this.id = id;
		this.given_name = given_name;
		this.family_name = family_name;
		this.email = email;
		this.kandidaat = kandidaat;
		this.k_eesnimi = k_eesnimi;
		this.k_perenimi = k_perenimi;
	}
	@Override
	public String toString() {
		return "Isik [id=" + id + ", given_name=" + given_name
				+ ", family_name=" + family_name + ", email=" + email
				+ ", kandidaat=" + kandidaat + ", k_eesnimi=" + k_eesnimi
				+ ", k_perenimi=" + k_perenimi + "]";
	}
	
}
