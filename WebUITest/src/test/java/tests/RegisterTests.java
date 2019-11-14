package tests;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import pages.RegisterPage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;;

@RunWith(JUnitPlatform.class)
public class RegisterTests extends BaseTest {	
	
	@DisplayName("Verify Mobile Number feild in Register Page")
    @Test
    public void testMobile () {
    	RegisterPage registerPage = new RegisterPage(driver);
    	registerPage.navigateToRegisterPage();
    	//verify invalid mobile number for frontEnd and backEnd
    	registerPage.inputMobile("12345678");
    	registerPage.verifyMobileMsbox("Number is already in use. If this is your number, please login, retrieve your PIN or reset your password .");    	
    	registerPage.clickNext();
    	registerPage.verifyMobileError("Telephone number must be longer than 9 characters");  	    	
    	registerPage.inputMobile("12");
    	registerPage.verifyMobileMsbox("Phone number is invalid");
    	registerPage.clickNext();
    	registerPage.verifyMobileError("Telephone number must be longer than 9 characters");
    	//verify mobile number valid for frontEnd and invalid for backEnd
    	registerPage.inputMobile("69356898");
    	registerPage.verifyMobileMsbox("Number available to register");   
    	registerPage.clickNext();
    	registerPage.verifyMobileError("Telephone number must be longer than 9 characters");    	
    	registerPage.inputMobile("12345678910112");
    	registerPage.verifyMobileMsbox("Number available to register"); 
    	registerPage.clickNext();
    	registerPage.verifyMobileError("Telephone number must be 13 or less characters");    
    	//verify valid for frontEnd and backEnd
      	registerPage.inputMobile("6935689812");
    	registerPage.verifyMobileMsbox("Number available to register");  
    	registerPage.clickNext();
    	registerPage.verifyNoMobileError();

    }
    
	@DisplayName("Verify Email Address in Register Page")
    @Test
    public void testEmail () {
    	RegisterPage registerPage = new RegisterPage(driver);
    	registerPage.navigateToRegisterPage();
    	//verify invalid mail address for frontend and backend
    	registerPage.inputEmail("123456");
    	registerPage.verifyMailMsbox("Email is invalid");
    	registerPage.clickNext();
    	registerPage.verifyMailError("Email address is invalid");
    	registerPage.inputEmail("123456@gmail.com");
    	registerPage.verifyMailMsbox("Email is already in use"); 
    	registerPage.clickNext();
    	registerPage.verifyMailError("Email address already in use. Forgotten your Pin?");
    	//verify valid mail address for frontend and backend
    	registerPage.navigateToRegisterPage();
    	registerPage.inputEmail("123456456@gmail.com");
    	registerPage.verifyMailMsbox("Email available to register"); 
    	registerPage.verifyNoEmailError();
    	
    	
    }
    
	@DisplayName("Verify Password Number feild in Register Page")
    @Test
    public void testPassword () {
    	RegisterPage registerPage = new RegisterPage(driver);
    	registerPage.navigateToRegisterPage();
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
    
	@DisplayName("Verify Error message in Register Page")
    @Test
    public void testErrorMessage () {
    	RegisterPage registerPage = new RegisterPage(driver);
    	registerPage.navigateToRegisterPage();
    	registerPage.clickNext();
    	registerPage.verifyMobileError("Mobile Number is required");
    	registerPage.verifyMailError("Email address is required");
    	registerPage.verifyPassowrdError("Password is required");
    	registerPage.verifySpanError("Please complete the Recaptcha.");
    	registerPage.verifySpanError("You must agree to the Terms & Conditions");
    	registerPage.verifyWarningNotification("There is an error in the form. Please check for details below.");
    }
	
	@DisplayName("Verify happy flow in Register Page")
    @Test
    public void testFlow () {
    	RegisterPage registerPage = new RegisterPage(driver);
    	registerPage.navigateToRegisterPage();
    	registerPage.inputMobile("69356898");
    	registerPage.verifyMobileMsbox("Number available to register");
    	registerPage.inputEmail("123456890@gmail.com");
    	registerPage.verifyMailMsbox("Email available to register");
    	registerPage.inputPassword("Qw11234668");
    	registerPage.clickRecaptchaanchor(); //stopped because of picture
    	//registerPage.clickTerms();
    	//registerPage.clickNext();
    }
}
