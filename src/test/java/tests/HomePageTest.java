package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import constants.AppConstants;

public class HomePageTest extends BaseTest{
	
	@Test
	public void verifyLogoAvailabilityTest() {
		
		Assert.assertEquals(hp.isValidAideLogoAvailable(), true);
		
		
	}
	
	
	@Test
	public void verifyLoginButtonAvailabilityTest() {
		
		Assert.assertEquals(hp.isLoginButtonAvailable(), true);
		
	}
	
	@Test
	public void verifyOverlayAvailabilityTest() {
		
		Assert.assertEquals(hp.isLayeredComponentDisplayed(), true);
		
	}
	
	@Test
	public void verifyHomePageTitle() {
		
		Assert.assertEquals(hp.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE);
	}

}
