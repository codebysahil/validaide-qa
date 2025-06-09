package base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import factory.DriverFactory;
import pages.HomePage;
import pages.LoginPage;

public class BaseTest {
	
	
	public WebDriver driver;
	public DriverFactory df;
	public HomePage hp;
	public LoginPage lp;
	public Properties prop;
	
	@Parameters("urlKey") 
	@BeforeClass
	public void setup(String urlKey) {
		
		df = new DriverFactory();
		prop=df.initProp();
		driver =df.launchBrowser(prop,urlKey);
		hp = new HomePage(driver);
		lp= new LoginPage(driver);
		
	}
	
	
	@AfterClass
	public void teardown() {
		
		driver.quit();
	}
	

}
