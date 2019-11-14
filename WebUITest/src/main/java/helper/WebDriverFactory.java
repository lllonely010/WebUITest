package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
	public static final String WEBDRIVER = PropertyManager.getInstance().getBroswer();
	
	private WebDriverFactory() {
		throw new RuntimeException("Unsupported webdriver");
	}

    public static WebDriver createWebDriver() {
    	
       	if(WEBDRIVER.equals("firefox")) 
       	{
       		System.setProperty("webdriver.gecko.driver", "lib\\geckodriver.exe");
            return new FirefoxDriver();
       	}else if(WEBDRIVER.equals("chrome")) 
       	{
       		System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("disable-infobars");
            options.addArguments("--disable-extensions");
            return new ChromeDriver(options);
       	}
		return null;
    }
}
