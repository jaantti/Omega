package com.appspot.omega.tests;

import com.thoughtworks.selenium.*;

import org.testng.annotations.*;

import static org.testng.Assert.*;
import java.util.regex.Pattern;

public class haaletamineHaaleEemaldamine extends SeleneseTestCase {
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
	
	@Test public void testHaaletamineHaaleEemaldamine() throws Exception {
		selenium.open("/web/index.html");
		selenium.click("id=logi_sisse");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Harjumaa");
		selenium.waitForPageToLoad("");
		selenium.click("css=a > p");
		selenium.click("id=vali_button");
		selenium.waitForPageToLoad("");
		selenium.click("link=Kasutajainfo");
		selenium.waitForPageToLoad("");
		verifyTrue(selenium.isTextPresent("Eduard Pärn"));
		selenium.click("link=Statistika");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("my_table")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyEquals(selenium.getTable("my_table.8.0"), "Eduard");
		verifyEquals(selenium.getTable("my_table.8.1"), "Pärn");
		selenium.click("link=Kasutajainfo");
		selenium.click("id=tuhista_haal");
		verifyTrue(selenium.isTextPresent("Teie hääl on veel andmata"));
	}
}
