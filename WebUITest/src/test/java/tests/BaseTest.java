package tests;

import org.openqa.selenium.WebDriver;

import helper.PropertyManager;
import helper.WebDriverFactory;
import pages.BasePage;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RunWith(JUnitPlatform.class)
public class BaseTest {
	
    static {
		System.setProperty("log4j.configurationFile",PropertyManager.logFilePath);
    }
    public static WebDriver driver;
    public static BasePage basepage;    	
    public static Logger LOGGER = LogManager.getLogger(BaseTest.class);
   
    @BeforeAll
    public static void setup()
    {
    	
        LOGGER.info("Start to set up for all tests");
		driver = WebDriverFactory.createWebDriver();
		basepage = new BasePage(driver);
		basepage.loginToRG();    	
    }
    
    @AfterAll
    public static void teardown () {
        driver.quit();
        LOGGER.info("All tests finished");
    }

}
