package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ElementUtil;

public class LoginPage {

	// url : https://app.validaide.com/login

	private WebDriver driver;
	private ElementUtil ele;
	private By email = By.id("1-email");
	private By password = By.id("1-password");
	private By loginBTN= By.xpath("//button[@id='1-submit']");
	// private By invalidLoginMessage = By.cssSelector("span[class='animated fadeInUp'] span");
	private By invalidLoginMessage= By.xpath("//span[contains(text(),'Wrong email or password.')]");
	

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtil(driver);

	}
	
	
	public String checkInvalidLoginMessage() {
		
		// ele.getElement(email).sendKeys("test123@ymail.com");
		// ele.getElement(password).sendKeys("Test@12345");
		ElementUtil.mediumWait();
		ele.sendKeysWithGetElement(email, "test123@ymail.com");
		ele.sendKeysWithGetElement(password, "Test123@gmail.com");
		ele.doClick(loginBTN);
		ElementUtil.shortWait();
		return ele.getElementText(invalidLoginMessage);
		
	
		
//		driver.findElement(email).sendKeys("testuser123@gmail.com");
//		driver.findElement(password).sendKeys("test1234@12");
//		driver.findElement(loginBTN).click();
//		return driver.findElement(invalidLoginMessage).getText();
		
	
	
	
	}

}


//  id attribute is not available for this element
// name attribute is not available for this element

// span[class='animated fadeInUp'] span
