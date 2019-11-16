package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import pages.RegisterPage;

@Feature("Functional testing on Register Page")
@RunWith(JUnitPlatform.class)
public class RegisterTests extends BaseTest {
	public RegisterPage registerPage = new RegisterPage(driver, wait);

	@BeforeEach
	@Step("Go to Register Page")
	public void start() {
		LOGGER.info("Start to run test cases for Register Page");
		registerPage.navigateToRegisterPage();
		registerPage.waitPageLoad();
	}

	@Nested
	class testMobileFeild {

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

		@Story("Test Mobile number with {invalidmobile} characters")
		@Description("Using 2 characters mobile number, feild check failed and error message after click next button")
		@DisplayName("Test Mobile number with invalid characters")
		@ParameterizedTest
		@ValueSource(strings = {"1234567", "1"})
		public void testMobileWith2Number(String invalidmobile) {
			registerPage.inputMobile(invalidmobile);
			registerPage.verifyMobileMsbox("Phone number is invalid");
			registerPage.clickNext();
			registerPage.verifyMobileError("Telephone number must be longer than 9 characters");
		}

		@Story("Test Mobile number 8 characters")
		@Description("Using 8 characters mobile number, feild check passed and error message displayed after click next button")
		@DisplayName("Test Mobile number 8 characters")
		@Test
		public void testMobileWith8Number() {
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

	}

	@Nested
	class testEmailFeild {
		@Story("Test Mail Address field with {invalidmail} address")
		@Description("Using all numbers as email address input, feild check failed and error message dispayed after click next button")
		@DisplayName("Test Mail Address field with invalid address")
		@ParameterizedTest
		@ValueSource(strings = {"123456", "$%#&^%%$$", "uewtwergfrg@"})
		public void testInvalidEmailAddress(String invalidmail) {
			registerPage.inputEmail(invalidmail);
			registerPage.verifyMailMsbox("Email is invalid");
			registerPage.clickNext();
			registerPage.verifyMailError("Email address is invalid");
		}

		@Story("Test Mail Address field with the used address")
		@Description("Using the used address email address input, feild check failed and error message displayed after click next button")
		@DisplayName("Test Mail Address field with the used address")
		@Test
		public void testInUsedEmailAddress() {
			registerPage.inputEmail("123456@gmail.com");
			registerPage.verifyMailMsbox("Email is already in use");
			registerPage.clickNext();
			registerPage.verifyMailError("Email address already in use. Forgotten your Pin?");
		}

		@Story("Test Mail Address field in valid address")
		@Description("Using valid email address input, feild check passed and no error message")
		@DisplayName("Test Mail Address field in valid address")
		@Test
		public void testValidEmailAddress() {
			registerPage.inputEmail("123456456@gmail.com");
			registerPage.verifyMailMsbox("Email available to register");
			registerPage.verifyNoEmailError();

		}

	}

	@Nested
	class testPasswordFeild {
		@Story("Test Password field with invalid input")
		@Description("Using invalid input to test validation and error message")
		@DisplayName("Test Password field with invalid input")
		@ParameterizedTest
		@ValueSource(strings = {"1", "1234567890123", "$%#", "u", "wertgfdertyuj"})
		public void testInvalidPassword(String invalidpassword) {
			registerPage.inputPassword(invalidpassword);
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

	}

	@Nested
	class testPageAction {
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

	}

	@Nested
	class testFLow {
		@Story("User tried to register with all valid input")
		@Description("Using valid mobile number, mail password and check all required checkbox")
		@DisplayName("Verify happy flow in Register Page")
		@Test
		public void testHappyFlow() {
			registerPage.inputMobile("69356898");
			registerPage.verifyMobileMsbox("Number available to register");
			registerPage.inputEmail("123456890@gmail.com");
			registerPage.verifyMailMsbox("Email available to register");
			registerPage.inputPassword("Qw11234668");
			registerPage.clickRecaptchaanchor(); // stopped because of picture
			// registerPage.clickTerms();
			// registerPage.clickNext();
		}

	}

	@AfterEach
	@Step("Finish test case")
	public void end() {
		LOGGER.info("Finish tests cases on Register Page");
	}
}
