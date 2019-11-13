package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import Helper.WebDriverFactory;
import pages.BasePage;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


public class BaseTest {
	
    public WebDriver driver;
    public WebDriverWait wait;
    public BasePage basepage;
    
    @BeforeEach
    public void setup () {
    	
		driver = WebDriverFactory.createWebDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
 
        //Instantiate the BasePage Class
		basepage = new BasePage(driver);
    }
 
    @AfterEach
    public void teardown () {
        //driver.quit();
    }

}
