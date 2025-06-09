package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	
	
	private WebDriver driver;
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		
	}
	
	
	public WebElement  getElement(By locator) {
		return driver.findElement(locator);
		}
	
	public void sendKeysWithGetElement(By locator, String value) {
		driver.findElement(locator).sendKeys(value);;
		
	}
	
	public void  doClick(By locator) {
		 driver.findElement(locator).click();;
		}
	
	public String  getElementText(By locator) {
		 return driver.findElement(locator).getText();
		}
	
	public void doSendKeysWithWait(By locator, int timeOut, String value) {
		WebElement ele = waitForElementVisible(locator, timeOut);
		ele.clear();
		ele.sendKeys(value);
	}

	public void doClickWithWait(By locator, int timeOut) {
		waitForElementVisible(locator, timeOut).click();
	}
	
	public WebElement waitForElementPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	
	public static void shortWait() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void mediumWait() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void longWait() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getpageTitle() {
		return driver.getTitle();
	}
	

}
