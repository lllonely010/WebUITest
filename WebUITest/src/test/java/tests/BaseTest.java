package tests;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import helper.WebDriverFactory;
import pages.BasePage;

@Epic("WebUI Funaltional Tests Epic")
@RunWith(JUnitPlatform.class)
public class BaseTest {

	public static WebDriver driver = WebDriverFactory.createWebDriver();
	public static Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		       .withTimeout(Duration.ofSeconds(10))
		       .pollingEvery(Duration.ofSeconds(1))
		       .ignoring(NoSuchElementException.class);
	public static BasePage basepage = new BasePage(driver, wait);;
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
