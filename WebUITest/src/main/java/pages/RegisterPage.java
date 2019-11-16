package pages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

import io.qameta.allure.Step;

public class RegisterPage extends BasePage {

	public RegisterPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
	}

	By pagetitle = By.cssSelector("h1:nth-child(3)");
	By mobilenumber = By.id("field-Member_CLI");
	By climsgbox = By.id("climsgbox");
	By mobileerror = By.cssSelector(".div-Member_CLI .error");
	By mailaddress = By.id("field-Member_Email");
	By emailmsgbox = By.id("emailmsgbox");
	By emailerror = By.cssSelector(".div-Member_Email .error");
	By password = By.id("field-MemberPassword");
	By passworderror = By.cssSelector(".div-MemberPassword .error");
	By warningnotification = By.cssSelector(".warning-notification");
	By marketing = By.name("Marketing");
	By datasharing = By.className("DataSharing");
	By iframe = By.xpath("//iframe");
	By recaptchaanchor = By.id("recaptcha-anchor");
	By recaptchaerror = By.cssSelector(".error:nth-child(1)");
	By terms = By.name("terms");
	By termserror = By.xpath("//*[@id=\"header3\"]/ol/li/div/div/span");
	By next = By.id("labyrinth_UserDetails_next");
	By cancel = By.id("labyrinth_cancel");

	@Step("Navigate To Register Page")
	public void navigateToRegisterPage() {
		LOGGER.info("Navigate To Register Page");
		driver.navigate().to(BasePage.URL + "/register");
	}

	@Step("Type {input} for mobile number.")
	public void inputMobile(String input) {
		LOGGER.info("Type [{}] for Mobile number", input);
		waitVisibility(mobilenumber);
		clearText(mobilenumber);
		inputText(mobilenumber, input);
		driver.findElement(mobilenumber).sendKeys(Keys.TAB);
	}

	@Step("Type {input} for email address.")
	public void inputEmail(String input) {
		LOGGER.info("Type [{}] for Email Address", input);
		waitVisibility(mailaddress);
		clearText(mailaddress);
		inputText(mailaddress, input);
		driver.findElement(mailaddress).sendKeys(Keys.TAB);
	}

	@Step("Type {input} for password.")
	public void inputPassword(String input) {
		LOGGER.info("Type [{}] for password", input);
		waitVisibility(password);
		clearText(password);
		inputText(password, input);
		driver.findElement(password).sendKeys(Keys.TAB);
	}

	@Step("Verify {expectedText} for mobile message box.")
	public void verifyMobileMsbox(String expectedText) {
		LOGGER.info("Verify [{}] for Mobile message box", expectedText);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		waitLoadInnerText(climsgbox, expectedText);
		assertEquals(expectedText, readText(climsgbox));
	}

	@Step("Verify {expectedText} for mail message box.")
	public void verifyMailMsbox(String expectedText) {
		LOGGER.info("Verify [{}] for Mail message box", expectedText);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		waitLoadInnerText(emailmsgbox, expectedText);
		assertEquals(expectedText, readText(emailmsgbox));
	}

	@Step("Verify {expectedText} for warning notification.")
	public void verifyWarningNotification(String expectedText) {
		LOGGER.info("Verify [{}] for warning notification", expectedText);
		waitLoadInnerText(warningnotification, expectedText);
		assertEquals(expectedText, readText(warningnotification));
	}

	@Step("Verify {expectedText} for span message.")
	public void verifySpanError(String expectedText) {
		switch (expectedText) {

		case "Please complete the Recaptcha.":
			LOGGER.info("Verify [{}] for recaptcha error", expectedText);
			waitLoadInnerText(recaptchaerror, expectedText);
			assertEquals(expectedText, readText(recaptchaerror));
			break;
		case "You must agree to the Terms & Conditions":
			LOGGER.info("Verify [{}] for Terms & Conditions", expectedText);
			waitLoadInnerText(termserror, expectedText);
			assertEquals(expectedText, readText(termserror));
			break;
		default:
			break;
		}
	}

	@Step("Verify {expectedText} for mobile error.")
	public void verifyMobileError(String expectedText) {
		LOGGER.info("Verify [{}] for Mobile Error", expectedText);
		waitLoadInnerText(mobileerror, expectedText);
		assertEquals(expectedText, readText(mobileerror));
	}

	@Step("Verify {expectedText} for mail error.")
	public void verifyMailError(String expectedText) {
		LOGGER.info("Verify [{}] for Mail Error", expectedText);
		waitLoadInnerText(emailerror, expectedText);
		assertEquals(expectedText, readText(emailerror));
	}

	@Step("Verify {expectedText} for password error.")
	public void verifyPassowrdError(String expectedText) {
		LOGGER.info("Verify [{}] for password error", expectedText);
		waitLoadInnerText(passworderror, expectedText);
		assertEquals(expectedText, readText(passworderror));
	}

	@Step("Verify no mail error.")
	public void verifyNoEmailError() {
		LOGGER.info("Verify no Email Error");
		assertThrows(NoSuchElementException.class, () -> driver.findElement(emailerror));
	}

	@Step("Verify no mobile error.")
	public void verifyNoMobileError() {
		LOGGER.info("Verify no Mobile Error");
		assertThrows(NoSuchElementException.class, () -> driver.findElement(mobileerror));
	}

	@Step("Verify no password error.")
	public void verifyNoPassowrdError() {
		LOGGER.info("Verify no password Error");
		assertThrows(NoSuchElementException.class, () -> driver.findElement(passworderror));
	}

	@Step("Check recaptcha anchor.")
	public void clickRecaptchaanchor() {
		LOGGER.info("Check recaptcha anchor");
		driver.switchTo().frame(driver.findElement(iframe));
		waitVisibility(recaptchaanchor);
		driver.findElement(recaptchaanchor).click();
	}

	@Step("Click checkbox Terms & Conditions.")
	public void clickTerms() {
		LOGGER.info("Click checkbox Terms & Conditions");
		waitVisibility(terms);
		driver.findElement(terms).click();
	}

	@Step("Click next button")
	public void clickNext() {
		LOGGER.info("Click next button");
		waitVisibility(next);
		scrollToView(next);
		driver.findElement(next).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	@Step("Click cancel.")
	public void clickCancel() {
		LOGGER.info("Click cancel button");
		waitVisibility(cancel);
		driver.findElement(cancel).click();
	}
}
