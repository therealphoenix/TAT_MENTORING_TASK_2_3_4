package com.klindziuk.flowershop.server.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * GetAll, GetAllSubscriptions, GetAllUsers, GetBouquet tests
 */
public class GetAllRequestTest extends BaseRequestTest {
	
	// compare size of strings to check equals
	@Test(priority = 0)
	public void getAllFlowersSmokeTest() {
		String command = setRequest("get/getallflowers.xml");
		int actual = requester.sendRequest(command).length();
		int expected = 757;
		Assert.assertEquals(actual, expected);
	}

}
