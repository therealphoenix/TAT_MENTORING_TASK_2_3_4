package com.klindziuk.flowershop.server.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetFlowerRequestTest extends BaseRequestTest{
		
	@Test(priority = 0)
	public void getFlowerSmokeTest() {
		String command = setRequest("get/getflower.xml");
		int actual = requester.sendRequest(command).length();
		int expected = 78;
		Assert.assertEquals(actual, expected);
	}
}
