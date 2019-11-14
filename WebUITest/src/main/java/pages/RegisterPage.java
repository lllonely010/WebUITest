package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import io.qameta.allure.Step;


public class RegisterPage extends BasePage{

	public RegisterPage(WebDriver driver) {
		super(driver);
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
		driver.navigate().to(BasePage.url+"/register");
	}
	
	@Step("Type {input} for mobile number.")
	public void inputMobile(String input) {
		waitVisibility(mobilenumber);
		clearText(mobilenumber);
		inputText(mobilenumber, input);
        driver.findElement(mobilenumber).sendKeys(Keys.TAB);
	}
	
	@Step("Type {input} for email address.")
	public void inputEmail(String input) {
		waitVisibility(mailaddress);
		clearText(mailaddress);
		inputText(mailaddress, input);
        driver.findElement(mailaddress).sendKeys(Keys.TAB);
	}	
	
	@Step("Type {input} for password.")
	public void inputPassword(String input) {
		waitVisibility(password);
		clearText(password);
		inputText(password, input);
        driver.findElement(password).sendKeys(Keys.TAB);
	}
	
	@Step("Verify {expectedText} for mobile message box.")
	public void verifyMobileMsbox(String expectedText) {
		waitScriptDone();
		waitLoadInnerText(climsgbox,expectedText);
		assertEquals(expectedText, readInnerText(climsgbox));		
	}
	
	@Step("Verify {expectedText} for mail message box.")
	public void verifyMailMsbox(String expectedText) {
		waitScriptDone();
		waitLoadInnerText(emailmsgbox,expectedText);
		assertEquals(expectedText, readInnerText(emailmsgbox));		
	}
	
	@Step("Verify {expectedText} for warning notification.")
	public void verifyWarningNotification(String expectedText) {
		waitLoadInnerText(warningnotification,expectedText);
		assertEquals(expectedText, readInnerText(warningnotification));			
	}
	
	@Step("Verify {expectedText} for span message.")
	public void verifySpanError(String expectedText) {		
		switch(expectedText) {
		
		case "Please complete the Recaptcha.":
			waitLoadInnerText(recaptchaerror,expectedText);
			assertEquals(expectedText, readInnerText(recaptchaerror));	
			break;
		case "You must agree to the Terms & Conditions":
			waitLoadInnerText(termserror,expectedText);
			assertEquals(expectedText, readInnerText(termserror));	
			break;	
		default :
			break;
		}	
	}
	
	@Step("Verify {expectedText} for mobile error.")
	public void verifyMobileError(String expectedText) {
		waitLoadInnerText(mobileerror,expectedText);
		assertEquals(expectedText, readInnerText(mobileerror));			
	}
	
	@Step("Verify {expectedText} for mail error.")
	public void verifyMailError(String expectedText) {
		waitLoadInnerText(emailerror,expectedText);
		assertEquals(expectedText, readInnerText(emailerror));			
	}
	
	@Step("Verify {expectedText} for password error.")
	public void verifyPassowrdError(String expectedText) {
		waitLoadInnerText(passworderror,expectedText);
		assertEquals(expectedText, readInnerText(passworderror));			
	}
	
	@Step("Verify no mail error.")
	public void verifyNoEmailError() {
		
		assertThrows(NoSuchElementException.class,()->  driver.findElement(emailerror));			
	}
	
	@Step("Verify no mobile error.")
	public void verifyNoMobileError() {
		
		assertThrows(NoSuchElementException.class,()->  driver.findElement(mobileerror));			
	}
	
	@Step("Verify no password error.")
	public void verifyNoPassowrdError() {
		
		assertThrows(NoSuchElementException.class,()->  driver.findElement(passworderror));			
	}

	@Step("Check recaptcha anchor.")
	public void clickRecaptchaanchor() {		
		driver.switchTo().frame(driver.findElement(iframe));
		waitVisibility(recaptchaanchor);
		driver.findElement(recaptchaanchor).click(); 		
	}
	
	@Step("Check terms.")
	public void clickTerms() {
		waitVisibility(terms);
		driver.findElement(terms).click();
	}
	
	@Step("Click next.")
	public void clickNext() {
		waitVisibility(next);
		driver.findElement(next).click();
	}
	
	@Step("Click cancel.")
	public void clickCancel() {
		waitVisibility(cancel);
		driver.findElement(cancel).click();
	}
}
