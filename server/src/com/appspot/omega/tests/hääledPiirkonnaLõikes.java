package com.appspot.omega.tests;

import com.thoughtworks.selenium.*;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import java.util.regex.Pattern;

public class hääledPiirkonnaLõikes extends SeleneseTestNgHelper {
	@Test public void testHÃ¤Ã¤ledPiirkonnaLÃµikes() throws Exception {
		selenium.open("web/index.html");
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
		assertEquals(selenium.getTable("my_table.0.2"), "Erakond");
	}
}
