package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import helper.PropertyManager;

public class BasePage{
	

	public WebDriver driver;
    public static String URL;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
    public void waitVisibility(By elementBy) {
    	new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(elementBy));
    }
    
    public void waitLoadInnerText(By elementBy, String expectedText) {
    	new WebDriverWait(driver, 10).until(ExpectedConditions.attributeContains(elementBy, "innerText", expectedText));
    }
    
    public void waitPageLoad() {
    	driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    }
    
    public void waitScriptDone() {
    	driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
    }
	
    public void inputText (By elementBy, String text) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).sendKeys(text);
    }
    
    public void clearText (By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).clear();
    }
    
    public void click (By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).click();
    }
 
    public String readInnerText (By elementBy) {
        waitVisibility(elementBy);
        return driver.findElement(elementBy).getAttribute("innerText");
    }
    
    public void scrollToView(By elementBy) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement Element = driver.findElement(elementBy);
        js.executeScript("arguments[0].scrollIntoView();", Element);
    	
    }
    
	public void loginToRG() {
		String username = PropertyManager.getInstance().getUsername();
		String password = PropertyManager.getInstance().getPassword();
		String server = PropertyManager.getInstance().getServer();
		URL = "http://"+username+":"+password+"@"+server;	
		driver.navigate().to(URL);
	}
}
