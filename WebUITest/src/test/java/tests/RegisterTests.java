package tests;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pages.RegisterPage;

@Epic("Register Tests Epic")
@Feature("Page elements validation Tests")
@RunWith(JUnitPlatform.class)
public class RegisterTests extends BaseTest {
	public RegisterPage registerPage = new RegisterPage(driver);

	@BeforeEach
	@Step("Go to Register Page")
	public void start() {
		LOGGER.info("Start to run test cases for Register Page");
		registerPage.navigateToRegisterPage();
		registerPage.waitPageLoad();
	}

	@Story("Test Mobile number using the used input")
	@Description("Using the used input mobile number, feild check failed and error message after click next button")
	@DisplayName("Test Mobile number using the used input")
	@Test
	public void testMobileWithInusedNumber() {
		registerPage.inputMobile("12345678");
		registerPage.verifyMobileMsbox(
				"Number is already in use. If this is your number, please login, retrieve your PIN or reset your password .");
		registerPage.clickNext();
		registerPage.verifyMobileError("Telephone number must be longer than 9 characters");
	}
	
	@Story("Test Mobile number with 2 characters")
	@Description("Using 2 characters mobile number, feild check failed and error message after click next button")
	@DisplayName("Test Mobile number with 2 characters")
	@Test
	public void testMobileWithInvalidNumber() {
		registerPage.inputMobile("12");
		registerPage.verifyMobileMsbox("Phone number is invalid");
		registerPage.clickNext();
		registerPage.verifyMobileError("Telephone number must be longer than 9 characters");
	}
	
	@Story("Test Mobile number 8 characters")
	@Description("Using 8 characters mobile number, feild check passed and error message displayed after click next button")
	@DisplayName("Test Mobile number 8 characters")
	@Test
	public void testMobileWithInvalidNextNumber() {
		registerPage.inputMobile("69356898");
		registerPage.verifyMobileMsbox("Number available to register");
		registerPage.clickNext();
		registerPage.verifyMobileError("Telephone number must be longer than 9 characters");
	}
	
	@Story("Test Mobile number with more than 13 characters")
	@Description("Using more than 13 characters mobile number, feild check passed and error message displayed after click next button")
	@DisplayName("Test Mobile number with more than 13 characters")
	@Test
	public void testMobileWithInvalidMore13Number() {	
		registerPage.inputMobile("12345678910112");
		registerPage.verifyMobileMsbox("Number available to register");
		registerPage.clickNext();
		registerPage.verifyMobileError("Telephone number must be 13 or less characters");
	}
	
	
	@Story("Test Mobile number with valid input")
	@Description("Using valid mobile number, feild check passed and no error after click next button")
	@DisplayName("Test Mobile number with valid input")
	@Test
	public void testMobileWithValidNumber() {
		registerPage.inputMobile("6935689812");
		registerPage.verifyMobileMsbox("Number available to register");
		registerPage.clickNext();
		registerPage.verifyNoMobileError();
	}	
	
	@Story("Test Mail Address field with all numbers address")
	@Description("Using all numbers as email address input, feild check failed and error message dispayed after click next button")
	@DisplayName("Test Mail Address field with all numbers address")
	@Test
	public void testInvalidEmail() {
		registerPage.inputEmail("123456");
		registerPage.verifyMailMsbox("Email is invalid");
		registerPage.clickNext();
		registerPage.verifyMailError("Email address is invalid");
	}
	
	@Story("Test Mail Address field with the used address")
	@Description("Using the used address email address input, feild check failed and error message displayed after click next button")
	@DisplayName("Test Mail Address field with the used address")
	@Test
	public void testInUsedEmail() {
		registerPage.inputEmail("123456@gmail.com");
		registerPage.verifyMailMsbox("Email is already in use");
		registerPage.clickNext();
		registerPage.verifyMailError("Email address already in use. Forgotten your Pin?");
	}
	
	@Story("Test Mail Address field in valid address")
	@Description("Using valid email address input, feild check passed and no error message")
	@DisplayName("Test Mail Address field in valid address")
	@Test
	public void testValidEmail() {
		registerPage.inputEmail("123456456@gmail.com");
		registerPage.verifyMailMsbox("Email available to register");
		registerPage.verifyNoEmailError();

	}

	@Story("Test Password field with invalid input")
	@Description("Using invalid input to test validation and error message")
	@DisplayName("Test Password field with invalid input")
	@Test
	public void testInvalidPassword() {
		registerPage.inputPassword("1");
		registerPage.verifyPassowrdError("The password does not meet the correct format.");
		registerPage.inputPassword("1234567890123");
		registerPage.verifyPassowrdError("The password does not meet the correct format.");
		registerPage.inputPassword("wertgfdertyuj");
		registerPage.verifyPassowrdError("The password does not meet the correct format.");
	}
	
	@Story("Test Password field with valid input")
	@Description("Using valid password input, validation passed and no error message")
	@DisplayName("Test Password field with valid input")
	@Test
	public void testValidPassword() {
		registerPage.inputPassword("Qw11234668");
		registerPage.verifyNoPassowrdError();
		registerPage.clickNext();
		registerPage.verifyNoPassowrdError();
	}

	@Story("Test all requied err messages without any input")
	@Description("Without any inputs, click next button to test all requied error messages in Register Page")
	@DisplayName("Test all requied err messages without any input")
	@Test
	public void testRequiredErrorMessage() {
		registerPage.clickNext();
		registerPage.verifyMobileError("Mobile Number is required");
		registerPage.verifyMailError("Email address is required");
		registerPage.verifyPassowrdError("Password is required");
		registerPage.verifySpanError("Please complete the Recaptcha.");
		registerPage.verifySpanError("You must agree to the Terms & Conditions");
		registerPage.verifyWarningNotification("There is an error in the form. Please check for details below.");
	}

	@Story("User tried to register with all valid input")
	@Description("Using valid mobile number, mail password and check all required checkbox")
	@DisplayName("Verify happy flow in Register Page")
	@Test
	public void testFlow() {
		registerPage.inputMobile("69356898");
		registerPage.verifyMobileMsbox("Number available to register");
		registerPage.inputEmail("123456890@gmail.com");
		registerPage.verifyMailMsbox("Email available to register");
		registerPage.inputPassword("Qw11234668");
		registerPage.clickRecaptchaanchor(); // stopped because of picture
		// registerPage.clickTerms();
		// registerPage.clickNext();
	}

	@AfterEach
	@Step("Finish test case")
	public void end() {
		LOGGER.info("Finish tests cases on Register Page");
	}
}
