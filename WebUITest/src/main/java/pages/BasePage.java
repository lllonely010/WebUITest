package pages;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import helper.PropertyManager;

public class BasePage {

	protected WebDriver driver;
	protected Wait<WebDriver> wait;
	protected static final String URL = PropertyManager.getInstance().getURL();
	protected final Logger LOGGER = LogManager.getLogger(BasePage.class);

	public BasePage(WebDriver driver, Wait<WebDriver> wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}

	public void waitVisibility(By elementBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
	}

	public void waitLoadInnerText(By elementBy, String expectedText) {
		wait.until(ExpectedConditions.attributeContains(elementBy, "innerText", expectedText));
	}

	public void waitPageLoad() {
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
	}

	public void waitScriptDone() {
		driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
	}

	public void inputText(By elementBy, String text) {
		waitVisibility(elementBy);
		driver.findElement(elementBy).sendKeys(text);
	}

	public void clearText(By elementBy) {
		waitVisibility(elementBy);
		driver.findElement(elementBy).clear();
	}

	public void click(By elementBy) {
		waitVisibility(elementBy);
		driver.findElement(elementBy).click();
	}

	public String readText(By elementBy) {
		waitVisibility(elementBy);
		return driver.findElement(elementBy).getText();
	}

	public void scrollToView(By elementBy) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(elementBy);
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public void loginToRG() {
		LOGGER.info("Start to login in [{}]", URL);
		driver.navigate().to(URL);
	}
}
