package com.appspot.omega.tests;

import com.thoughtworks.selenium.*;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import java.util.regex.Pattern;

public class hääledErakonnaLõikes_autend extends SeleneseTestNgHelper {
	@Test public void testHÃ¤Ã¤ledErakonnaLÃµikes_autend() throws Exception {
		selenium.open("web/index.html");
		selenium.click("id=logi_sisse");
		selenium.waitForPageToLoad("30000");
		selenium.type("Passwd", "kartul123");
		selenium.type("id=Email", "omega.veebirakendus@gmail.com");
		selenium.click("id=signIn");
		selenium.waitForPageToLoad("30000");
		selenium.waitForPageToLoad("");
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
		verifyFalse(selenium.isElementPresent("my_table.0.2"));
		assertEquals(selenium.getTable("my_table.0.2"), "Piirkond");
		assertFalse(selenium.isElementPresent("my_table.0.2"));
	}
}
