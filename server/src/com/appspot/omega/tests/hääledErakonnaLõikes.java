package com.appspot.omega.tests;

import com.thoughtworks.selenium.*;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import java.util.regex.Pattern;

public class hääledErakonnaLõikes extends SeleneseTestNgHelper {
	@Test public void testHÃ¤Ã¤ledErakonnaLÃµikes() throws Exception {
		selenium.open("web/index.html");
		selenium.click("statistika");
		selenium.waitForPageToLoad("");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("my_table")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.select("id=stat_partei", "label=Punased");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("my_table")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyEquals(selenium.getTable("my_table.0.2"), "Piirkond");
		assertEquals(selenium.getTable("my_table.0.2"), "Piirkond");
	}
}
