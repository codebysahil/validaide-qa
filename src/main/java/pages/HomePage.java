package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import constants.AppConstants;
import utils.ElementUtil;

public class HomePage {

	// url : https://www.validaide.com/

	private WebDriver driver;
	private ElementUtil ele;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtil(driver);

	}

	private By loginButton = By.linkText("Login");
	private By validaideLogo = By.cssSelector("img[alt='Validaide logo']");
	private By layeredComponent = By.cssSelector("div[id='bgLayers_comp-m8d2k7ui'] div[class='tcElKx i1tH8h']");

	public boolean isLoginButtonAvailable() {
		// return driver.findElement(loginButton).isDisplayed();
		// WebElement loginEle = ele.getElement(loginButton);
		return ele.getElement(loginButton).isDisplayed();
		// return loginEle.isDisplayed();

	}

	public boolean isValidAideLogoAvailable() {
		// return driver.findElement(validaideLogo).isDisplayed();
		return ele.getElement(validaideLogo).isDisplayed();
	}

	public void clickLogin() {

		driver.findElement(loginButton).click();

	}

	public boolean isLayeredComponentDisplayed() {

		// return driver.findElement(layeredComponent).isDisplayed();
		return ele.waitForElementVisible(layeredComponent, AppConstants.LARGE_DEFAULT_TIME_OUT).isDisplayed();

	}

	public String getHomePageTitle() {
		return ele.getpageTitle();
	}

}
