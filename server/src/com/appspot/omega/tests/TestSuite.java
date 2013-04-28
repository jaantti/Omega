package com.appspot.omega.tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;


public class TestSuite extends TestCase{

	public static Test suite() {
		TestSuite suite = new TestSuite();
		
		
		suite.addTestSuite(h‰‰ledErakonnaLıikes.class);
		suite.addTestSuite(h‰‰ledPiirkonnaLıikes.class);
		suite.addTestSuite(nimej‰rgiOtsing.class);
		suite.addTestSuite(h‰‰ledKandidaadiLıikes.class);
		suite.addTestSuite(h‰‰ledRiigiLıikes.class);
		suite.addTestSuite(h‰‰ledErakonnaLıikes.class);
		suite.addTestSuite(h‰‰ledPiirkonnaLıikes_autend.class);
		suite.addTestSuite(h‰‰ledKandidaadiLıikes_autend.class);
		suite.addTestSuite(h‰‰ledRiigiLıikes_autend.class);
		suite.addTestSuite(nimej‰rgiOtsing_autend.class);
		suite.addTestSuite(lisaKandidaadina.class);
		suite.addTestSuite(haaletamineHaaleEemaldamine.class);
		return suite;
	}

	public static void main(String[] args) {
		TestRunner.run(lisaKandidaadina.class);
	}
}
