package com.appspot.omega;

public class Kandidaat {
	int id;
	String nimi;
	Erakond erakond;
	Piirkond piirkond;
	public Kandidaat(int id, String nimi, int idErakond, String nimiErakond, int idPiirkond, String nimiPiirkond) {
		super();
		this.id = id;
		this.nimi = nimi;
		this.erakond = new Erakond(idErakond, nimiErakond);
		this.piirkond = new Piirkond(idPiirkond, nimiPiirkond);
	}
}
