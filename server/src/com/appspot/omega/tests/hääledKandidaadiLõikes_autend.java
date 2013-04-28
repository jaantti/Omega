package com.appspot.omega.tests;

import com.thoughtworks.selenium.*;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import java.util.regex.Pattern;

public class hääledKandidaadiLõikes_autend extends SeleneseTestNgHelper {
	@Test public void testHÃ¤Ã¤ledKandidaadiLÃµikes_autend() throws Exception {
		selenium.open("web/index.html");
		selenium.click("id=logi_sisse");
		selenium.waitForPageToLoad("30000");
		selenium.click("statistika");
		selenium.waitForPageToLoad("");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("my_table")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.type("id=stat_kandidaat", "Maali Sirel");
		selenium.click("name=stat_otsi");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("my_table")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyEquals(selenium.getTable("my_table.1.0"), "Maali");
		assertEquals(selenium.getTable("my_table.1.0"), "Maali");
		assertEquals(selenium.getTable("my_table.1.1"), "Sirel");
	}
}
