package org.testautomation.framework.tests;

import com.microsoft.playwright.Page;
import org.testautomation.framework.base.BaseTest;
import org.testautomation.framework.constants.AppConstants;
import org.testautomation.framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/*
Created by Jyoti
06/01/2023
 */
public class LoginPageTest extends BaseTest {
    @Test(priority = 0)
    public void navigateToLoginPageTest() {
        loginPage = homePage.navigateToLoginPage();
        String actualTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
    }

    @DataProvider
    public Object[][] getItemData() {
        return new Object[][]{
                {"Laptop"},
                {"SmartPhone"},
                {"Desktop"}
        };
    }

}
