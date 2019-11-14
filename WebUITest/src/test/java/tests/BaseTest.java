package tests;

import org.openqa.selenium.WebDriver;
import helper.WebDriverFactory;
import pages.BasePage;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class BaseTest {
	
    public static WebDriver driver;
    public static BasePage basepage;    
   
    @BeforeAll
    public static void setup()
    {
		driver = WebDriverFactory.createWebDriver();
		basepage = new BasePage(driver);
		basepage.loginToRG();    	
    }
    
    @AfterAll
    public static void teardown () {
        driver.quit();
    }

}
