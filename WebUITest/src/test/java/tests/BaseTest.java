package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import helper.WebDriverFactory;
import pages.BasePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


public class BaseTest {
	
    public WebDriver driver;
    public WebDriverWait wait;
    public BasePage basepage;
    
    @BeforeEach
    public void setup () {
    	
		driver = WebDriverFactory.createWebDriver();
		basepage = new BasePage(driver);
    }
 
    @AfterEach
    public void teardown () {
        //driver.quit();
    }

}
