package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import helper.PropertyManager;

public class RegisterPage extends BasePage{

	public RegisterPage(WebDriver driver) {
		super(driver);
	}

	By mobilenumber = By.id("field-Member_CLI");
	By climsgbox = By.id("climsgbox");
	By mailaddress = By.id("field-Member_Email");
	By emailmsgbox = By.id("emailmsgbox");
	By password = By.id("field-MemberPassword");
	By error = By.cssSelector(".error");
	By warningnotification = By.cssSelector(".warning-notification");
	By marketing = By.name("Marketing");
	By datasharing = By.className("DataSharing");
	By recaptchaanchor = By.id("recaptcha-anchor");
	By terms = By.name("terms");
	By next = By.id("labyrinth_UserDetails_next");
	By cancel = By.id("labyrinth_cancel");
	
	
	public void navigateToRegisterPage() {
		String username = PropertyManager.getInstance().getUsername();
		String password = PropertyManager.getInstance().getPassword();
		String url = PropertyManager.getInstance().getURL();
		driver.navigate().to("http://"+username+":"+password+"@"+url+"/register");
	}
	
	public void verifyEmail(String num) {
		inputText(mobilenumber, num);
        driver.findElement(mobilenumber).sendKeys(Keys.ENTER);				
	}
	
	
}
