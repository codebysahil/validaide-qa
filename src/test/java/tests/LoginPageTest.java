package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class LoginPageTest extends BaseTest {
	
	@Test
	public void verifyInvalidLoginTextTest() {
		Assert.assertEquals(lp.checkInvalidLoginMessage(), "WRONG EMAIL OR PASSWORD.");
	}

}
