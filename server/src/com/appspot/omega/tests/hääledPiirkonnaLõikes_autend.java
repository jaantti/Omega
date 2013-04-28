package com.appspot.omega.tests;

import com.thoughtworks.selenium.*;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import java.util.regex.Pattern;

public class hääledPiirkonnaLõikes_autend extends SeleneseTestNgHelper {
	@Test public void testHÃ¤Ã¤ledPiirkonnaLÃµikes_autend() throws Exception {
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

		selenium.select("id=stat_piirkond", "label=Tartumaa");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("my_table")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyEquals(selenium.getTable("my_table.0.2"), "Erakond");
		verifyFalse(selenium.isElementPresent("my_table.0.2"));
		assertEquals(selenium.getTable("my_table.0.2"), "Erakond");
		assertFalse(selenium.isElementPresent("my_table.0.2"));
	}
}
