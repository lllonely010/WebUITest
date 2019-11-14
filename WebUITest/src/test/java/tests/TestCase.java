package tests;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import helper.PropertyManager;
import helper.WebDriverFactory;
import pages.RegisterPage;

@RunWith(JUnitPlatform.class)
public class TestCase {
	public WebDriver driver;
	
	String username = PropertyManager.getInstance().getUsername();
	String password = PropertyManager.getInstance().getPassword();
	
	@Test
	public void Test01() throws InterruptedException
    {
		driver = WebDriverFactory.createWebDriver();
		driver.navigate().to("http://"+username+":"+password+"@myrgo-preprod.ctt.co.uk/register");
		RegisterPage registerPage = new RegisterPage(driver);
    	registerPage.inputMobile("12");
    	registerPage.verifyMobileMsbox("Phone number is invalid");
		
	}

}
