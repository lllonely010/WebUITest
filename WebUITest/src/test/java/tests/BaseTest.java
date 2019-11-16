package tests;

import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.qameta.allure.Step;
import helper.WebDriverFactory;
import pages.BasePage;

@RunWith(JUnitPlatform.class)
public class BaseTest {

	public static WebDriver driver = WebDriverFactory.createWebDriver();
	public static BasePage basepage = new BasePage(driver);;
	public static Logger LOGGER = LogManager.getLogger(BaseTest.class);

	@BeforeAll
	@Step("Log in to the web site")
	public static void setupAll() {
		LOGGER.info("Start to set up for all tests");
		driver.manage().window().maximize();
		basepage.loginToRG();
	}

	@AfterAll
	@Step("Log off and cose the broswer")
	public static void teardownAll() {
		LOGGER.info("close the browser");
		driver.quit();
		LOGGER.info("All tests finished");
	}

}
