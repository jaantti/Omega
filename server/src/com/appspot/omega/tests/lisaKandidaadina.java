package com.appspot.omega.tests;

import com.thoughtworks.selenium.*;

import org.testng.annotations.*;

import static org.testng.Assert.*;
import java.util.regex.Pattern;

public class lisaKandidaadina extends SeleneseTestCase {
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
	
	@Test public void testLisaKandidaadina() throws Exception {
		selenium.open("/web/index.html");
		selenium.click("id=logi_sisse");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Kandideerijale");
		selenium.type("id=kandidaat_eesnimi", "Mati");
		selenium.type("id=kandidaat_perenimi", "Kuusk");
		selenium.type("id=kandidaat_id", "12345678901");
		selenium.select("id=kandidaat_erakond", "label=Rohelised");
		selenium.select("id=kandidaat_piirkond", "label=Tartumaa");
		selenium.type("id=kandidaat_aadress", "Kuuse1");
		selenium.type("id=kandidaat_telefon", "123456");
		selenium.type("id=kandidaat_epost", "mati@rohelised.ee");
		selenium.type("id=kandidaat_pilt", "-");
		selenium.click("id=submit_kandidaat");
		assertEquals(selenium.getAlert(), "x");
		assertEquals(selenium.getAlert(), "x");
		selenium.click("link=Tartumaa");
		verifyTrue(selenium.isTextPresent("Mati Kuusk"));
		//selenium.runScript("eemaldaKandidaat(\"Mati\", \"Kuusk\");");
		selenium.open("/EemaldaRegServlet?eesnimi=Mati&perenimi=Kuusk");
	}
}
