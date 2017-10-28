package com.klindziuk.flowershop.server.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FindByRequestTest extends BaseRequestTest {

    // compare size of strings to check equals
    @Test(priority = 0)
    public void FindByNameSmokeTest() {
        String command = setRequest("findby/findbyname.xml");
        int actual = requester.sendRequest(command).length();
        int expected = 33;
        Assert.assertEquals(actual, expected);
    }

    // compare size of strings to check equals
    @Test(priority = 1)
    public void FindByCountrySmokeTest() {
        String command = setRequest("findby/findbycountry.xml");
        int actual = requester.sendRequest(command).length();
        int expected = 56;
        Assert.assertEquals(actual, expected);
    }

}
