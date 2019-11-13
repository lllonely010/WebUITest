package pages;

import org.openqa.selenium.WebDriver;

public class BasePage extends PageGenerator{
	

	public static final String REGISTER_LINK = "http://myrgo-preprod.ctt.co.uk/register";
	public WebDriver driver;
	
	public BasePage(WebDriver driver) {
		super(driver);
	}

	public void navigateTo_Page(String url) {
		driver.get(url);
	}
	
	public void navigateTo_RegisterPage() {
		navigateTo_Page(REGISTER_LINK);
	}
}
