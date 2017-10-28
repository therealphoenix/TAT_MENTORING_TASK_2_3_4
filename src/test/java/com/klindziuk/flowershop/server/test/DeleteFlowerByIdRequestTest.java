package com.klindziuk.flowershop.server.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DeleteFlowerByIdRequestTest extends BaseRequestTest {
	
	@Test(priority = 0)
	public void deleteFlowerByIdSmokeTest() {
		String command = setRequest("delete/deleteflower.xml");
		String response = requester.sendRequest(command);
		String expected = "Flower deleted successfully.";
		Assert.assertEquals(response, expected);
	}
}
