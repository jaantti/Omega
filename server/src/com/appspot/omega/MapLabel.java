package com.appspot.omega;

public class MapLabel {
	String piirkond;
	int piirkond_id;
	double[] loc;
	String erakond;
	int erakond_id;
	String protsent;
	int arv;
	public MapLabel(int arv, int erakond_id, String erakond, int piirkond_id, String piirkond, String loc) {
		super();
		this.arv = arv;
		this.piirkond = piirkond;
		this.piirkond_id = piirkond_id;
		this.erakond_id = erakond_id;
		this.erakond = erakond;
		this.protsent = "0%";
		this.loc = new double[2];
		String[] loc_split = loc.split(",");
		this.loc[0] = Double.parseDouble(loc_split[0]);
		this.loc[1] = Double.parseDouble(loc_split[1]);
	}
}
