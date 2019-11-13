package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage{

	public RegisterPage(WebDriver driver) {
		super(driver);
	}

	@FindBy (id="field-Member_CLI")
	WebElement mobile_number;    
	
	@FindBy (id="climsgbox")
	WebElement climsgbox;   

	@FindBy (id="field-Member_Email")
	WebElement mail_address;  
	
	@FindBy (id="emailmsgbox")
	WebElement emailmsgbox; 
		
	@FindBy (id="field-MemberPassword")
	WebElement password;
	
	@FindBy (css=".error")
	WebElement error;
	
	@FindBy (css=".warning-notification")
	WebElement warningnotification;
	
	@FindBy (name="Marketing")
	WebElement marketing;
	
	@FindBy (name="DataSharing")
	WebElement datasharing;
	
	@FindBy (id="recaptcha-anchor")
	WebElement recaptchaanchor;
	
	@FindBy (name="terms")
	WebElement terms;
	
	@FindBy (id="labyrinth_UserDetails_next")
	WebElement next;
	
	@FindBy (id="labyrinth_cancel")
	WebElement cancel; 
	
}
