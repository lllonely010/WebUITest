package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.security.Security;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {

	public static final String WEBDRIVER = PropertyManager.getInstance().getBroswer();
	private static final Logger log = LogManager.getLogger(WebDriverFactory.class);

	public static WebDriver createWebDriver() {

		if (WEBDRIVER.equals("firefox")) {
			log.info("Setting up a firefox webdriver");
			WebDriverManager.firefoxdriver().setup();
			return new FirefoxDriver();
		} else if (WEBDRIVER.equals("chrome")) {
			log.info("Setting up a chrome webdriver");
			WebDriverManager.chromedriver().setup();
			return new ChromeDriver();

		} else if (WEBDRIVER.equals("edge")) {
			log.info("Setting up a edge webdriver");
			WebDriverManager.edgedriver().setup();
			return new EdgeDriver();
		} else {
			log.info("No setting for webdriver or driver not supported");
			return null;
		}
	}

	public static void ignoreSecureOnChrome(WebDriver driver) {
		DevTools devtool = ((ChromeDriver) driver).getDevTools();
		devtool.createSession();
		devtool.send(Security.enable());
		devtool.send(Security.setIgnoreCertificateErrors(true));
	}
}
