package tests;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import pages.RegisterPage;

import org.junit.jupiter.api.Test;;

@RunWith(JUnitPlatform.class)
public class RegisterTests extends BaseTest {	
		
    @Test
    public void test01 () {
    	RegisterPage registerPage = new RegisterPage(driver);
    	registerPage.navigateToRegisterPage();
    	
    	registerPage.verifyEmail("12");
    	
    }
	
}
