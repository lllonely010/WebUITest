package tests;

import org.openqa.selenium.WebDriver;
import helper.WebDriverFactory;
import pages.BasePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class BaseTest {
	
    public WebDriver driver;
    public BasePage basepage;
    
   
    @BeforeEach
    public void setup () {
    	
		driver = WebDriverFactory.createWebDriver();
		driver.manage().window().maximize();
		basepage = new BasePage(driver);
		basepage.loginToRG();
    }
 
    @AfterEach
    public void teardown () {
        driver.quit();
    }

}
