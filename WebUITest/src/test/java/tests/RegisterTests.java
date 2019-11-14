package tests;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegisterPage;

@Epic("Register Page Tests Epic")
@Feature("Input Test")
@RunWith(JUnitPlatform.class)
public class RegisterTests extends BaseTest {	
	public RegisterPage registerPage;
	
    @BeforeEach
    public void start () {
    	registerPage = new RegisterPage(driver);
    	registerPage.navigateToRegisterPage();
    	registerPage.waitPageLoad();
    }
	
    @Story("Test Mobile Number field in Register Page")
    @Description("Using invalid and valid mobile number input to test validation and error message")
	@DisplayName("Verify Mobile Number field in Register Page")
    @Test
    public void testMobile () {
    	//verify invalid mobile number for fronted and backEnd
    	registerPage.inputMobile("12345678");
    	registerPage.verifyMobileMsbox("Number is already in use. If this is your number, please login, retrieve your PIN or reset your password .");    	
    	registerPage.clickNext();
    	registerPage.verifyMobileError("Telephone number must be longer than 9 characters");  	    	
    	registerPage.inputMobile("12");
    	registerPage.verifyMobileMsbox("Phone number is invalid");
    	registerPage.clickNext();
    	registerPage.verifyMobileError("Telephone number must be longer than 9 characters");
    	//verify mobile number valid for fronted and invalid for backEnd
    	registerPage.inputMobile("69356898");
    	registerPage.verifyMobileMsbox("Number available to register");   
    	registerPage.clickNext();
    	registerPage.verifyMobileError("Telephone number must be longer than 9 characters");    	
    	registerPage.inputMobile("12345678910112");
    	registerPage.verifyMobileMsbox("Number available to register"); 
    	registerPage.clickNext();
    	registerPage.verifyMobileError("Telephone number must be 13 or less characters");    
    	//verify valid for fronted and backEnd
      	registerPage.inputMobile("6935689812");
    	registerPage.verifyMobileMsbox("Number available to register");  
    	registerPage.clickNext();
    	registerPage.verifyNoMobileError();

    }
    
    @Story("Test Mail Address field in Register Page")
    @Description("Using invalid and valid email address input to test validation and error message")
	@DisplayName("Verify Email Address in Register Page")
    @Test
    public void testEmail () {
    	//verify invalid mail address for fronted and backend
    	registerPage.inputEmail("123456");
    	registerPage.verifyMailMsbox("Email is invalid");
    	registerPage.clickNext();
    	registerPage.verifyMailError("Email address is invalid");
    	registerPage.inputEmail("123456@gmail.com");
    	registerPage.verifyMailMsbox("Email is already in use"); 
    	registerPage.clickNext();
    	registerPage.verifyMailError("Email address already in use. Forgotten your Pin?");
    	//verify valid mail address for fronted and backend
    	registerPage.navigateToRegisterPage();
    	registerPage.inputEmail("123456456@gmail.com");
    	registerPage.verifyMailMsbox("Email available to register"); 
    	registerPage.verifyNoEmailError();
    	
    	
    }
    
    @Story("Test Password field in Register Page")
    @Description("Using invalid and valid password input to test validation and error message")
	@DisplayName("Verify Password Number feild in Register Page")
    @Test
    public void testPassword () {
    	//verify invalid password 
    	registerPage.inputPassword("1");
    	registerPage.verifyPassowrdError("The password does not meet the correct format.");
    	registerPage.inputPassword("1234567890123");
    	registerPage.verifyPassowrdError("The password does not meet the correct format.");
    	registerPage.inputPassword("wertgfdertyuj");
    	registerPage.verifyPassowrdError("The password does not meet the correct format.");
    	//verify valid password
    	registerPage.navigateToRegisterPage();
    	registerPage.inputPassword("Qw11234668");
    	registerPage.verifyNoPassowrdError();
    	registerPage.clickNext();
    	registerPage.verifyNoPassowrdError();
    }
    
    @Story("Test all error messages in Register Page")
    @Description("No input to test all error messages in Register Page")
	@DisplayName("Verify Error message in Register Page")
    @Test
    public void testErrorMessage () {
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
    public void testFlow () {
    	registerPage.inputMobile("69356898");
    	registerPage.verifyMobileMsbox("Number available to register");
    	registerPage.inputEmail("123456890@gmail.com");
    	registerPage.verifyMailMsbox("Email available to register");
    	registerPage.inputPassword("Qw11234668");
    	registerPage.clickRecaptchaanchor(); //stopped because of picture
    	//registerPage.clickTerms();
    	//registerPage.clickNext();
    }
	
	 
    @AfterEach
    public void end () {

    }
}
