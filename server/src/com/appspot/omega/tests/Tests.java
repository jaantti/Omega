package com.appspot.omega.tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Tests extends TestSuite{

	public static Test suite() {
		Tests suite = new Tests();
		suite.addTestSuite(nimejargiOtsing.class);
		suite.addTestSuite(nimejargiOtsing_autend.class);
		suite.addTestSuite(lisaKandidaadina.class);
		suite.addTestSuite(haaletamineHaaleEemaldamine.class);
		return suite;
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}
