package org.testautomation.framework.tests;

import org.testautomation.framework.base.BaseTest;
import org.testautomation.framework.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/*
Created by Jyoti
06/12/2022
 */
public class HomePageTest extends BaseTest {

    @Test(priority = 0)
    public void homePageUrlTest() {
        String actualUrl = homePage.getHomePageUrl();
        Assert.assertEquals(actualUrl, prop.getProperty("url"));
    }

    @Test(priority = 1)
    public void homePageTitleTest() {
        String actualTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
    }

    @DataProvider
    public Object[][] getItemData() {
        return new Object[][]{
                {"Laptop"},
                {"SmartPhone"},
                {"Desktop"}
        };
    }

/*    @Test(priority = 2,dataProvider = "getItemData")
    public void searchTest(String searchKey) throws InterruptedException {
        String actualSearchHeader = homePage.doSearch(searchKey);
        Assert.assertEquals(actualSearchHeader,"Search results");
    }*/

}
