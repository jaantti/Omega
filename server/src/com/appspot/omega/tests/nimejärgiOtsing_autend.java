package com.appspot.omega.tests;

import com.thoughtworks.selenium.*;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import java.util.regex.Pattern;

public class nimejärgiOtsing_autend extends SeleneseTestNgHelper {
	@Test public void testNimejÃ¤rgiOtsing_autend() throws Exception {
		selenium.open("web/index.html");
		selenium.click("id=logi_sisse");
		selenium.waitForPageToLoad("30000");
		selenium.type("css=input.search", "sirel");
		selenium.typeKeys("css=input.search", "sirel");
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
