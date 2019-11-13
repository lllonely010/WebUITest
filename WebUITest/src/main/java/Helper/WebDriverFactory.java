package Helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    public static WebDriver createWebDriver() {
       	String webdriver = PropertyManager.getInstance().browser;
       	       	
        switch(webdriver) {
            case "firefox":
            	System.setProperty("webdriver.gecko.driver", "lib\\geckodriver.exe");
                return new FirefoxDriver();
            case "chrome":
            	System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
            	return new ChromeDriver();
            default:
                throw new RuntimeException("Unsupported webdriver: " + webdriver);
        }
    }
}
