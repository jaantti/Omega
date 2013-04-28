package com.appspot.omega.tests;

import com.thoughtworks.selenium.*;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import java.util.regex.Pattern;

public class h��ledRiigiL�ikes extends SeleneseTestNgHelper {
	@Test public void testHääledRiigiLõikes() throws Exception {
		selenium.open("web/index.html");
		selenium.click("statistika");
		selenium.waitForPageToLoad("");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("my_table")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyEquals(selenium.getTable("my_table.0.0"), "Nimi");
		verifyEquals(selenium.getTable("my_table.0.1"), "Nimi");
		verifyEquals(selenium.getTable("my_table.0.2"), "Erakond");
		verifyEquals(selenium.getTable("my_table.0.3"), "Piirkond");
		verifyEquals(selenium.getTable("my_table.0.4"), "Hääli");
		assertEquals(selenium.getTable("my_table.0.0"), "Nimi");
		assertEquals(selenium.getTable("my_table.0.1"), "Nimi");
		assertEquals(selenium.getTable("my_table.0.2"), "Erakond");
		assertEquals(selenium.getTable("my_table.0.3"), "Piirkond");
		assertEquals(selenium.getTable("my_table.0.4"), "Hääli");
	}
}
