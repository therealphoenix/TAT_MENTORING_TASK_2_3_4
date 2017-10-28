package com.klindziuk.flowershop.server.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddFlowerRequestTest extends BaseRequestTest {

    @Test(priority = 0)
    public void addFlowerSmokeTest() {
        String сommand = setRequest("add/addflower.xml");
        String response = requester.sendRequest(сommand);
        String expected = "Flower added successfully.";
        Assert.assertEquals(response, expected);
    }

}
