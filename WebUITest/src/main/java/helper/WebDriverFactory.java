package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.security.Security;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
	public static final String WEBDRIVER = PropertyManager.getInstance().getBroswer();
	
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
            options.addArguments("--enable-automation");
            return new ChromeDriver(options);
       	}
		return null;
    }
    
    public static void ignoreSecureOnChrome(WebDriver driver)
    {
    	DevTools devtool = ((ChromeDriver)driver).getDevTools();
    	devtool.createSession();
    	devtool.send(Security.enable());
    	devtool.send(Security.setIgnoreCertificateErrors(true));
    }
}
