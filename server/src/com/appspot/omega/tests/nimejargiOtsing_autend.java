package com.appspot.omega.tests;

import com.thoughtworks.selenium.*;

import org.testng.annotations.*;

import static org.testng.Assert.*;
import java.util.regex.Pattern;

public class nimejargiOtsing_autend extends SeleneseTestCase {
	@BeforeSuite(alwaysRun = true)
    public void setUp() {
        echo("in setup.");
        selenium = new DefaultSelenium("localhost", 
                4444, "*mock", "http://omega-vl.appspot.com/web/index.html");
        echo("selenium instance created:"+selenium.getClass());
        selenium.start();
        echo("selenium instance started. Opening website...");
        selenium.open("http://omega-vl.appspot.com/web/index.html");
    }
	private void echo(String msg){
        if(new Boolean(System.getProperties().getProperty("DEBUG")))
            System.out.println(msg);
    }
	
	@Test public void testNimejargiOtsing_autend() throws Exception {
		selenium.open("web/index.html");
		selenium.click("id=logi_sisse");
		selenium.waitForPageToLoad("30000");
		selenium.type("css=input.search", "sirel");
		//selenium.typeKeys("css=input.search", "sirel");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Sirel, Maali")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.mouseOver("//html/body/div/div/strong[.=\"Sirel\"]");
		selenium.click("//html/body/div/div/strong[.=\"Sirel\"]");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Maali Sirel")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("Maali Sirel"));
		assertTrue(selenium.isTextPresent("Maali Sirel"));
	}
}
